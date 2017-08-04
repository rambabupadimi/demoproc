package com.cenrefordentistry.models;

/**
 * Created by Ramu on 23-07-2017.
 */

public class PersonalModel {

    public int person_id,referal_success_count;
    public int person_site_id,person_plan_status_id;
    public String person_created_date,general_welcome_message_1,general_welcome_message_2
            ,bank_sort_code,bank_account_number,account_holder_name,
            address_1,address_2,postcode,telephone_number,person_plan_renewal_date,
            person_last_seen_date,person_first_name;

    public String getPerson_first_name() {
        return person_first_name;
    }

    public void setPerson_first_name(String person_first_name) {
        this.person_first_name = person_first_name;
    }

    public String getPerson_last_seen_date() {
        return person_last_seen_date;
    }

    public void setPerson_last_seen_date(String person_last_seen_date) {
        this.person_last_seen_date = person_last_seen_date;
    }

    public String getPerson_plan_renewal_date() {
        return person_plan_renewal_date;
    }

    public void setPerson_plan_renewal_date(String person_plan_renewal_date) {
        this.person_plan_renewal_date = person_plan_renewal_date;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getPerson_site_id() {
        return person_site_id;
    }

    public void setPerson_site_id(int person_site_id) {
        this.person_site_id = person_site_id;
    }

    public int getPerson_plan_status_id() {
        return person_plan_status_id;
    }

    public void setPerson_plan_status_id(int person_plan_status_id) {
        this.person_plan_status_id = person_plan_status_id;
    }

    public String getPerson_created_date() {
        return person_created_date;
    }

    public void setPerson_created_date(String person_created_date) {
        this.person_created_date = person_created_date;
    }

    public String getGeneral_welcome_message_1() {
        return general_welcome_message_1;
    }

    public void setGeneral_welcome_message_1(String general_welcome_message_1) {
        this.general_welcome_message_1 = general_welcome_message_1;
    }

    public String getGeneral_welcome_message_2() {
        return general_welcome_message_2;
    }

    public void setGeneral_welcome_message_2(String general_welcome_message_2) {
        this.general_welcome_message_2 = general_welcome_message_2;
    }

    public int getReferal_success_count() {
        return referal_success_count;
    }

    public void setReferal_success_count(int referal_success_count) {
        this.referal_success_count = referal_success_count;
    }

    public String getBank_sort_code() {
        return bank_sort_code;
    }

    public void setBank_sort_code(String bank_sort_code) {
        this.bank_sort_code = bank_sort_code;
    }

    public String getBank_account_number() {
        return bank_account_number;
    }

    public void setBank_account_number(String bank_account_number) {
        this.bank_account_number = bank_account_number;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }
}
