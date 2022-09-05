package com.propify.challenge;

import com.propify.challenge.exception.NotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // API endpoints for CRUD operations on entities of type Property
    @GetMapping("/properties")
    public ResponseEntity<Collection<Property>> search(String minRentPrice, String maxRentPrice) {
        return ResponseEntity.ok().body(propertyService.search(minRentPrice, maxRentPrice));
    }

    @GetMapping("property/{id}")
    public ResponseEntity<Property> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(propertyService.findById(id));
    }

    @PostMapping("/property")
    public ResponseEntity<Void> insert(Property property) throws NotValidException {
        propertyService.insert(property);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/property")
    public ResponseEntity<Void> update(Property property) throws NotValidException {
        propertyService.update(property);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/property/{id}")
    public ResponseEntity<Void>  delete(@PathVariable("id") int id) {
        propertyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/properties/report")
    public ResponseEntity<PropertyReport> report() {
        return ResponseEntity.ok().body(propertyService.propertyReport());
    }
}
