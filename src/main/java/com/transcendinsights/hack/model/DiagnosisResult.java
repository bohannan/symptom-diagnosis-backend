package com.transcendinsights.hack.model;

import java.util.List;

/**
 * Created by ljb9764 on 7/16/15.
 */
public class DiagnosisResult implements Comparable<DiagnosisResult> {
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
    List<String> suggestedLabs;

    public DiagnosisResult(String diagName, String url, List<String> symptomsMatched, Double frequency,List<String> suggestedLabs) {
        this.diagName = diagName;
        this.url = url;
        this.symptomsMatched = symptomsMatched;
        this.frequency = frequency;
        this.suggestedLabs = suggestedLabs;
    }

    public String getDiagName() {
        return diagName;
    }

    public void setDiagName(String diagName) {
        this.diagName = diagName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getSymptomsMatched() {
        return symptomsMatched;
    }

    public void setSymptomsMatched(List<String> symptomsMatched) {
        this.symptomsMatched = symptomsMatched;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public List<String> getSuggestedLabs() {
        return suggestedLabs;
    }

    public void setSuggestedLabs(List<String> suggestedLabs) {
        this.suggestedLabs = suggestedLabs;
    }

    @Override
    public int compareTo(DiagnosisResult o) {
        if (this.getSymptomsMatched() != null && o.getSymptomsMatched()!=null){
            if(this.getSymptomsMatched().size() == o.getSymptomsMatched().size()){
                if (this.getFrequency() != null && o.getFrequency() != null) {
                    if (this.frequency.equals(o.frequency)) {
                        return 0;
                    }
                    return this.frequency < o.frequency ? 1 : -1;
                }
                //one is null the one with a frequency wins!
                return this.frequency ==null ? (o.frequency==null)? 0 : 1 : -1 ;
            }
            return this.symptomsMatched.size() < o.symptomsMatched.size() ? 1 : -1;
        }
        return this.symptomsMatched == null ? (o.symptomsMatched==null)? 0 : -1 : 1  ;
    }
}
