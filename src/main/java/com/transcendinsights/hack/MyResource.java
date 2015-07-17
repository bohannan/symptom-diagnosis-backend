package com.transcendinsights.hack;

import com.google.gson.Gson;
import com.mongodb.*;
import com.transcendinsights.hack.model.DiagnosisResult;
import com.transcendinsights.hack.model.Disease;
import com.transcendinsights.hack.model.Symptom;
import com.transcendinsights.hack.model.SymptomRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
    @Produces(MediaType.TEXT_PLAIN)
    public String getDiagnosis(SymptomRequest symptomRequest) {
        List<DiagnosisResult> diags = new ArrayList<>();
        System.out.println("Hit post!!!!!");
        DBCollection collection = getCollectionFromDB("disease");
        if(collection!=null) {
            BasicDBObject inQuery = new BasicDBObject();
            inQuery.put("symptomNames", new BasicDBObject("$in", symptomRequest.symptoms));
            DBCursor cursor = collection.find(inQuery);
            while (cursor.hasNext()) {
                Disease disease = new Disease(cursor.next());

                diags.add(new DiagnosisResult(disease.name, null, null, null));
            }
        }

        return new Gson().toJson(diags);
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
