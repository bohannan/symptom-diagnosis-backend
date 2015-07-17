package com.transcendinsights.hack.model;

import com.mongodb.DBObject;

import java.util.List;

/**
 * Created by ljb9764 on 7/16/15.
 */
public class Disease {
    public String disease, name;
    List<String> symptoms, symptomNames;

    public Disease(DBObject object) {
        this.disease = (String) object.get("disease");
        this.name = (String) object.get("name");
        this.symptoms = (List<String>) object.get("symptoms");
        this.symptomNames = (List<String>) object.get("symptomNames");
    }
}
