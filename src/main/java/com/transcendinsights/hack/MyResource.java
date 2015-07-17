package com.transcendinsights.hack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.*;
import com.transcendinsights.hack.model.DiagnosisResult;
import com.transcendinsights.hack.model.Disease;
import com.transcendinsights.hack.model.Symptom;
import com.transcendinsights.hack.model.SymptomRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("symptoms")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        List<Symptom> symptoms = new ArrayList<>();

        DBCollection collection = getCollectionFromDB("symptoms");
        DBCursor cursor = collection.find();

        System.out.println("Hit get!!!!!");

        while( cursor.hasNext() ) {
            DBObject obj = cursor.next();
            symptoms.add(new Symptom(obj));
        }

        return new Gson().toJson(symptoms);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String getDiagnosis(String message) {
        List<DiagnosisResult> diags = new ArrayList<>();
        System.out.println("Hit post!!!!!"+message);
        Gson gson = new Gson();
        SymptomRequest symptomRequest = gson.fromJson(message, SymptomRequest.class);
        DBCollection collection = getCollectionFromDB("disease");
        if(collection!=null) {
            BasicDBObject inQuery = new BasicDBObject();
            inQuery.put("symptomNames", new BasicDBObject("$in", symptomRequest.getSymptoms()));
            DBCursor cursor = collection.find(inQuery);
            System.out.println("In Query!!!!!"+inQuery.toString());
            List<Disease> diagnosisList = new ArrayList<>();
            List<String> diseaseList = new ArrayList<>();
            Map<String, List<String>> matchingSymptomsMap = new HashMap<>();
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                Disease d = new Disease(object);
                diagnosisList.add(d);
                diseaseList.add(d.getDisease());
//                System.out.println(new Gson().toJson(disease.getSymptoms()));
                //match symptoms
                Set<String> symptomSet = new HashSet<>();
                symptomSet.addAll(symptomRequest.getSymptoms());
                List<String> matchingSmptomList = new ArrayList<>();
                for(Object o : d.getSymptomNames()){
                    String s = String.valueOf(o);
                    if(symptomSet.contains(s)){
                        matchingSmptomList.add(s);
                    }
                }
                matchingSymptomsMap.put(d.disease,matchingSmptomList);
            }
            System.out.println("Past first query");

            //now go get the url
            DBCollection coll2 = getCollectionFromDB("disease_url");
            BasicDBObject inQuery2 = new BasicDBObject();
            inQuery2.put("term", new BasicDBObject("$in", diseaseList));
            DBCursor urlCursor = coll2.find(inQuery2);
            Map<String,String> diseaseUrlMap = new HashMap<>();
            while (urlCursor.hasNext()) {
                DBObject object = urlCursor.next();
                diseaseUrlMap.put((String)object.get("term"),(String)object.get("URL"));
            }
            System.out.println("Past second query");


            //get frequency
            DBCollection coll3 = getCollectionFromDB("disease_frequency");
            BasicDBObject inQuery3 = new BasicDBObject();
            inQuery3.put("eref", new BasicDBObject("$in", diseaseList));
            inQuery3.put("gender",symptomRequest.getGender());
            DBCursor freqCursor = coll3.find(inQuery3);
            Map<String,Double> diseaseFreqMap = new HashMap<>();
            while (freqCursor.hasNext()) {
//                System.out.println("In loop");
                DBObject object = freqCursor.next();
                String freq = (String) object.get(symptomRequest.getAgeGroup());
                String disease = (String) object.get("eref");
                diseaseFreqMap.put(disease,Double.parseDouble(freq));
            }
            System.out.println("Past Last query");


            //collate all the data
            for(Disease d: diagnosisList){
                diags.add(new DiagnosisResult(d.getName(),
                        diseaseUrlMap.get(d.disease),
                        matchingSymptomsMap.get(d.disease),
                        diseaseFreqMap.get(d.disease)));
            }
        }
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(pretty.toJson(diags));

        Collections.sort(diags);

        return pretty.toJson(diags);
    }

    private DBCollection getCollectionFromDB(String collectionName){
        try {
            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            DB database = mongoClient.getDB("diag");
            return database.getCollection(collectionName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
