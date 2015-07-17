package com.transcendinsights.hack.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * Created by ljb9764 on 7/16/15.
 */
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

    private String ageGroup;
    private String gender;
    public List<String> symptoms;


    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }
}
