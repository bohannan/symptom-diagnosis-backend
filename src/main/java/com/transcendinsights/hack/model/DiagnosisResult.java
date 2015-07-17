package com.transcendinsights.hack.model;

import java.util.List;

/**
 * Created by ljb9764 on 7/16/15.
 */
public class DiagnosisResult {
    /*
    {
  "diagName": "something",
  "url": "url",
  "symptomsMatched": [
    "abdominal pain",
    "headache",
    "nausea"
  ]
}
     */
    String diagName;
    String url;
    List<String> symptomsMatched;
    Double frequency;

    public DiagnosisResult(String diagName, String url, List<String> symptomsMatched, Double frequency) {
        this.diagName = diagName;
        this.url = url;
        this.symptomsMatched = symptomsMatched;
        this.frequency = frequency;
    }
}
