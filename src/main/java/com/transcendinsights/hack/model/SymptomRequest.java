package com.transcendinsights.hack.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * Created by ljb9764 on 7/16/15.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SymptomRequest {

   /*
   {
   "ageGroup":"d4",
   "gender":"F",
   "symptoms":[
        "rash",
        "fever",
        "abdominal pain",
        "headache","nausea"]}
    */

    public String ageGroup;
    public String gender;
    public List<String> symptoms;


}
