package com.cenrefordentistry.models;

/**
 * Created by Ramu on 29-07-2017.
 */

public class VoucherModel {

        private int     voucher_local_id;
        private int     voucher_id;
        private int     voucher_type_id;
        private int     voucher_treatment_type_id;
        private float   voucher_fixed_amount;
        private float   voucher_percent_amount;
        private String  voucher_title;
        private String  voucher_text;
        private String  voucher_code;
        private String  voucher_product_code;
        private String  voucher_valid_from_date;
        private String  voucher_valid_until_date;
        private int     voucher_is_read;
        private int     voucher_is_active;
        private boolean     voucher_is_redeemed;

    public int getVoucher_local_id() {
        return voucher_local_id;
    }

    public void setVoucher_local_id(int voucher_local_id) {
        this.voucher_local_id = voucher_local_id;
    }

    public int getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(int voucher_id) {
        this.voucher_id = voucher_id;
    }

    public int getVoucher_type_id() {
        return voucher_type_id;
    }

    public void setVoucher_type_id(int voucher_type_id) {
        this.voucher_type_id = voucher_type_id;
    }

    public int getVoucher_treatment_type_id() {
        return voucher_treatment_type_id;
    }

    public void setVoucher_treatment_type_id(int voucher_treatment_type_id) {
        this.voucher_treatment_type_id = voucher_treatment_type_id;
    }

    public float getVoucher_fixed_amount() {
        return voucher_fixed_amount;
    }

    public void setVoucher_fixed_amount(float voucher_fixed_amount) {
        this.voucher_fixed_amount = voucher_fixed_amount;
    }

    public float getVoucher_percent_amount() {
        return voucher_percent_amount;
    }

    public void setVoucher_percent_amount(float voucher_percent_amount) {
        this.voucher_percent_amount = voucher_percent_amount;
    }

    public String getVoucher_title() {
        return voucher_title;
    }

    public void setVoucher_title(String voucher_title) {
        this.voucher_title = voucher_title;
    }

    public String getVoucher_text() {
        return voucher_text;
    }

    public void setVoucher_text(String voucher_text) {
        this.voucher_text = voucher_text;
    }

    public String getVoucher_code() {
        return voucher_code;
    }

    public void setVoucher_code(String voucher_code) {
        this.voucher_code = voucher_code;
    }

    public String getVoucher_product_code() {
        return voucher_product_code;
    }

    public void setVoucher_product_code(String voucher_product_code) {
        this.voucher_product_code = voucher_product_code;
    }

    public String getVoucher_valid_from_date() {
        return voucher_valid_from_date;
    }

    public void setVoucher_valid_from_date(String voucher_valid_from_date) {
        this.voucher_valid_from_date = voucher_valid_from_date;
    }

    public String getVoucher_valid_until_date() {
        return voucher_valid_until_date;
    }

    public void setVoucher_valid_until_date(String voucher_valid_until_date) {
        this.voucher_valid_until_date = voucher_valid_until_date;
    }

    public int getVoucher_is_read() {
        return voucher_is_read;
    }

    public void setVoucher_is_read(int voucher_is_read) {
        this.voucher_is_read = voucher_is_read;
    }

    public int getVoucher_is_active() {
        return voucher_is_active;
    }

    public void setVoucher_is_active(int voucher_is_active) {
        this.voucher_is_active = voucher_is_active;
    }

    public boolean getVoucher_is_redeemed() {
        return voucher_is_redeemed;
    }

    public void setVoucher_is_redeemed(boolean voucher_is_redeemed) {
        this.voucher_is_redeemed = voucher_is_redeemed;
    }
}
