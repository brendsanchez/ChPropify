package com.propify.challenge.controller;

import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.service.PropertyService;
import com.propify.challenge.exception.NotValidException;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // API endpoints for CRUD operations on entities of type Property
    @GetMapping("/properties")
    public ResponseEntity<Collection<Property>> search(@Param("minRentPrice") String minRentPrice, @Param("maxRentPrice") String maxRentPrice) {
        return ResponseEntity.ok().body(propertyService.search(minRentPrice, maxRentPrice));
    }

    @GetMapping("property/{id}")
    public ResponseEntity<Property> findById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(propertyService.findById(id));
    }

    @PostMapping("/property")
    public ResponseEntity<Void> insert(@Valid @RequestBody Property property) throws NotValidException {
        propertyService.insert(property);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/property")
    public ResponseEntity<Void> update(@RequestBody Property property) throws NotValidException {
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
