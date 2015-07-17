import groovy.json.*

class DiseaseElement {
  def disease, name, symptoms, symptomNames
}

class SymptomElement {
  String symptom, name, labs, labNames
}

class DistributionElement {
  String eref
  String gender
  String d0
  String d1
  String d2
  String d3
  String d4
  String d5
  String d6
  String d7
  String d8
  String d9
  String d10
  String d11
  String allages
}

class Element {
    String term
    String name
    String URL
}
File file = new File("disease-sign-symptoms.txt")
Boolean header = true
if (!file.exists()) {
     println "File does not exist"

} else {
    List headers = new ArrayList<>()

  file.eachLine { line ->
      List<String> columns = line.split("\t")
      if(header){
          headers.addAll(columns)
          header = false;
      } else {
          def element = new DiseaseElement()
          for (int i =0 ; i<columns.size();++i){
              if(i==2){
                  element[headers.get(i)]=Arrays.asList(columns.get(i).replaceAll("[^A-Za-z0-9_ ]","").split(" "))
              } else {
                  element[headers.get(i)]=Arrays.asList(columns.get(i))
              }
          }
          List<String> symptomNames = new ArrayList<>()
          element.symptoms.each { symptom ->
              symptomNames.add(symptom.toLowerCase().replaceAll("_"," "))
          }
          element.symptomNames=symptomNames
          println new JsonBuilder(element).toPrettyString()
      }
  }
  //println headers

}
