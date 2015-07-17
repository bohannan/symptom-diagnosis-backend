package com.transcendinsights.hack.model;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ljb9764 on 7/16/15.
 */
public class Symptom {
    public String symptom, name;
    public Object labs;
    public Object labNames;

    public Symptom(DBObject object) {
        this.symptom = (String) object.get("symptom");
        this.name = (String) object.get("name");
        this.labs = object.get("labs");
        this.labNames = object.get("labNames");
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getLabs() {
        return Arrays.asList(((BasicDBList) labs).toArray());
    }

    public void setLabs(Object labs) {
        this.labs = labs;
    }

    public List<Object> getLabNames() {
        return Arrays.asList(((BasicDBList)labNames).toArray());
    }

    public void setLabNames(Object labNames) {
        this.labNames = labNames;
    }
}
