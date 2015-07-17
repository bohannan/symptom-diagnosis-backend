package com.transcendinsights.hack.model;

import com.mongodb.DBObject;

/**
 * Created by ljb9764 on 7/16/15.
 */
public class Symptom {
    String symptom, name, labs, labNames;

    public Symptom(DBObject object) {
        this.symptom = (String) object.get("symptom");
        this.name = (String) object.get("name");
        this.labs = (String) object.get("labs");
        this.labNames = (String) object.get("labNames");
    }
}
