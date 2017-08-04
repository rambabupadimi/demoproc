package com.cenrefordentistry.models;

/**
 * Created by Ramu on 28-07-2017.
 */

public class TreatmentInfoModel {

            private int  treatment_type_id;
            private String treatment_type_text;
            private String treatment_type_code;
            private String treatment_type_colour;

    public int getTreatment_type_id() {
        return treatment_type_id;
    }

    public void setTreatment_type_id(int treatment_type_id) {
        this.treatment_type_id = treatment_type_id;
    }

    public String getTreatment_type_text() {
        return treatment_type_text;
    }

    public void setTreatment_type_text(String treatment_type_text) {
        this.treatment_type_text = treatment_type_text;
    }

    public String getTreatment_type_code() {
        return treatment_type_code;
    }

    public void setTreatment_type_code(String treatment_type_code) {
        this.treatment_type_code = treatment_type_code;
    }

    public String getTreatment_type_colour() {
        return treatment_type_colour;
    }

    public void setTreatment_type_colour(String treatment_type_colour) {
        this.treatment_type_colour = treatment_type_colour;
    }
}
