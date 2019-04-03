package com.example.westagile;

public class StudentDetails {
    private int _id;
    private String _name;
    private String _gender;
    private String _email_id;
    private String _dob;
    private String _mobile_no;


    public StudentDetails() {
    }


    public StudentDetails( String _name, String _gender, String _email_id, String _dob, String _mobile_no) {
        this._name = _name;
        this._gender = _gender;
        this._email_id = _email_id;
        this._dob = _dob;
        this._mobile_no = _mobile_no;
    }

    public String get_dob() {
        return _dob;
    }

    public void set_dob(String _dob) {
        this._dob = _dob;
    }

    public String get_mobile_no() {
        return _mobile_no;
    }

    public void set_mobile_no(String _mobile_no) {
        this._mobile_no = _mobile_no;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public String get_email_id() {
        return _email_id;
    }

    public void set_email_id(String _email_id) {
        this._email_id = _email_id;
    }
}