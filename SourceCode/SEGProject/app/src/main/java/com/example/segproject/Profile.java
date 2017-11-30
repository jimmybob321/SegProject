package com.example.segproject;

/**
 * Created by jamesmorrill on 2017-11-30.
 */

public class Profile {
    private String _id;
    private String _name;
    private String _password;
    private int _score;
    private String _type;
    public Profile(String id, String name, String password, int score, String type){
        _id = id;
        _name = name;
        _password = password;
        _score = score;
    }
    public String get_id(){
        return _id;
    }

    public int get_score() {
        return _score;
    }

    public String get_name() {
        return _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }
}
