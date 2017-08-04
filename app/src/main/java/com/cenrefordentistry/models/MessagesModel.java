package com.cenrefordentistry.models;

/**
 * Created by Ramu on 29-07-2017.
 */

public class MessagesModel {

             private int        message_id;
             private int        message_type_id;
             private String     message_text;
             private String     message_valid_from_date;
             private String     message_valid_until_date;
             private boolean        message_is_proximity;
             private String     message_subject;
             private int        message_is_active;
             private int        message_is_read;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getMessage_type_id() {
        return message_type_id;
    }

    public void setMessage_type_id(int message_type_id) {
        this.message_type_id = message_type_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public String getMessage_valid_from_date() {
        return message_valid_from_date;
    }

    public void setMessage_valid_from_date(String message_valid_from_date) {
        this.message_valid_from_date = message_valid_from_date;
    }

    public String getMessage_valid_until_date() {
        return message_valid_until_date;
    }

    public void setMessage_valid_until_date(String message_valid_until_date) {
        this.message_valid_until_date = message_valid_until_date;
    }

    public boolean getMessage_is_proximity() {
        return message_is_proximity;
    }

    public void setMessage_is_proximity(boolean message_is_proximity) {
        this.message_is_proximity = message_is_proximity;
    }

    public String getMessage_subject() {
        return message_subject;
    }

    public void setMessage_subject(String message_subject) {
        this.message_subject = message_subject;
    }

    public int getMessage_is_active() {
        return message_is_active;
    }

    public void setMessage_is_active(int message_is_active) {
        this.message_is_active = message_is_active;
    }

    public int getMessage_is_read() {
        return message_is_read;
    }

    public void setMessage_is_read(int message_is_read) {
        this.message_is_read = message_is_read;
    }
}
