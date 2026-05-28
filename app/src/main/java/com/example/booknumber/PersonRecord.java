package com.example.booknumber;

public class PersonRecord {
    private int id_person;
    private String full_name;
    private String phone_num;
    private String origin;
    private String added_on;

    public PersonRecord() {}

    public PersonRecord(String full_name, String phone_num) {
        this.full_name = full_name;
        this.phone_num = phone_num;
    }

    public int getId_person() { return id_person; }
    public void setId_person(int id_person) { this.id_person = id_person; }

    public String getFull_name() { return full_name; }
    public void setFull_name(String full_name) { this.full_name = full_name; }

    public String getPhone_num() { return phone_num; }
    public void setPhone_num(String phone_num) { this.phone_num = phone_num; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getAdded_on() { return added_on; }
    public void setAdded_on(String added_on) { this.added_on = added_on; }
}
