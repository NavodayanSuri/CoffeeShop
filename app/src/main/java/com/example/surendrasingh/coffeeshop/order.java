package com.example.surendrasingh.coffeeshop;

public class order {
    String name,phone,type,number_of_cups,table_no,temperature;

    public order(String name, String phone, String type, String number_of_cups, String table_no, String temperature) {
        this.name = name;
        this.phone = phone;
        this.type = type;
        this.number_of_cups = number_of_cups;
        this.table_no = table_no;
        this.temperature = temperature;
    }
}
