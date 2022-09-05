package com.propify.challenge;

public class PropertyType {

    public static final PropertyType SINGLE_FAMILY = new PropertyType("Single Family");
    public static final PropertyType MULTI_FAMILY = new PropertyType("Multi-family");
    public static final PropertyType CONDOMINIUM = new PropertyType("Condominium");
    public static final PropertyType TOWNHOUSE = new PropertyType("Townhouse");

    public String type;

    public PropertyType(String type) {
        this.type = type;
    }
}
