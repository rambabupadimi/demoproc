package com.cenrefordentistry.models;

/**
 * Created by Ramu on 01-08-2017.
 */


public class TreatmentQuestionsAndAnswers {
    private String TreatmentQuestion;
    private String TreatmentAnswer;

    public TreatmentQuestionsAndAnswers(String treatmentQuestion, String treatmentAnswer) {
        TreatmentQuestion = treatmentQuestion;
        TreatmentAnswer = treatmentAnswer;
    }

    public String getTreatmentQuestion() {
        return TreatmentQuestion;
    }

    public void setTreatmentQuestion(String treatmentQuestion) {
        TreatmentQuestion = treatmentQuestion;
    }

    public String getTreatmentAnswer() {
        return TreatmentAnswer;
    }

    public void setTreatmentAnswer(String treatmentAnswer) {
        TreatmentAnswer = treatmentAnswer;
    }
}
