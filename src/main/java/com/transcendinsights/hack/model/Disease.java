package com.transcendinsights.hack.model;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ljb9764 on 7/16/15.
 */
public class Disease {
    public String disease;
    public String name;
    public Object symptoms;
    public Object symptomNames;

    public Disease(DBObject object) {
        this.disease = (String) object.get("disease");
        this.name = (String) object.get("name");
        this.symptoms = object.get("symptoms");
        this.symptomNames =  object.get("symptomNames");
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getSymptoms() {
        return Arrays.asList(((BasicDBList) symptoms).toArray());
    }

    public List<Object>  getSymptomNames() {
        return Arrays.asList(((BasicDBList) symptomNames).toArray());
    }
}
