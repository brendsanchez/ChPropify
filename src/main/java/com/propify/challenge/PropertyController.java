package com.propify.challenge;

import java.util.Collection;

public class PropertyController {

    PropertyService propertyService;

    // API endpoints for CRUD operations on entities of type Property

    public Collection<Property> search(String minRentPrice, String maxRentPrice) {
        return propertyService.search(minRentPrice, maxRentPrice);
    }

    public Property findById(int id) {
        return propertyService.findById(id);
    }

    public void insert(Property property) {
        // TODO: Property attributes must be validated
        propertyService.insert(property);
    }

    public void update(Property property) {
        // TODO: Property attributes must be validated
        propertyService.update(property);
    }

    public void delete(int id) {
        propertyService.delete(id);
    }

    public PropertyReport report() {
        return propertyService.propertyReport();
    }
}
