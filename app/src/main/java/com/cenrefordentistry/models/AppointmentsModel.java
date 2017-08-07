package com.cenrefordentistry.models;

/**
 * Created by Ramu on 06-08-2017.
 */

public class AppointmentsModel {


       private int appointment_id;
       private int appointment_person_id;
       private String appointment_datetime;
       private int appointment_site_id;
       private int  appointment_provider_id;
       private  String appointment_provider_text;
       private int  appointment_type_id;
       private int  appointment_status_id;
       private float  appointment_booking_price;
       private int  appointment_remote_id;
       private String portal_system_message;


    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getAppointment_person_id() {
        return appointment_person_id;
    }

    public void setAppointment_person_id(int appointment_person_id) {
        this.appointment_person_id = appointment_person_id;
    }

    public String getAppointment_datetime() {
        return appointment_datetime;
    }

    public void setAppointment_datetime(String appointment_datetime) {
        this.appointment_datetime = appointment_datetime;
    }

    public int getAppointment_site_id() {
        return appointment_site_id;
    }

    public void setAppointment_site_id(int appointment_site_id) {
        this.appointment_site_id = appointment_site_id;
    }

    public int getAppointment_provider_id() {
        return appointment_provider_id;
    }

    public void setAppointment_provider_id(int appointment_provider_id) {
        this.appointment_provider_id = appointment_provider_id;
    }

    public String getAppointment_provider_text() {
        return appointment_provider_text;
    }

    public void setAppointment_provider_text(String appointment_provider_text) {
        this.appointment_provider_text = appointment_provider_text;
    }

    public int getAppointment_type_id() {
        return appointment_type_id;
    }

    public void setAppointment_type_id(int appointment_type_id) {
        this.appointment_type_id = appointment_type_id;
    }

    public int getAppointment_status_id() {
        return appointment_status_id;
    }

    public void setAppointment_status_id(int appointment_status_id) {
        this.appointment_status_id = appointment_status_id;
    }

    public float getAppointment_booking_price() {
        return appointment_booking_price;
    }

    public void setAppointment_booking_price(float appointment_booking_price) {
        this.appointment_booking_price = appointment_booking_price;
    }

    public int getAppointment_remote_id() {
        return appointment_remote_id;
    }

    public void setAppointment_remote_id(int appointment_remote_id) {
        this.appointment_remote_id = appointment_remote_id;
    }

    public String getPortal_system_message() {
        return portal_system_message;
    }

    public void setPortal_system_message(String portal_system_message) {
        this.portal_system_message = portal_system_message;
    }
}
