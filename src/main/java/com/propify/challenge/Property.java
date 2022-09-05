package com.propify.challenge;

public class Property {

    public int id; // must be null for INSERT and not null for UPDATE

    public String createTime;

    public PropertyType type;

    public double rentPrice; // must be greater than 0, 2 decimal places

    public Address address; // must not be null

    public String emailAddress; // must be a valid email address

    public String code; // not null, only uppercase letters or numbers, 10 characters
}
