package com.propify.challenge.service;

import com.propify.challenge.exception.GeneralException;
import com.propify.challenge.exception.NotFoundException;
import com.propify.challenge.exception.NotValidException;
import com.propify.challenge.model.Property;
import com.propify.challenge.model.PropertyReport;
import com.propify.challenge.persistence.AddressMapper;
import com.propify.challenge.persistence.PropertyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@EnableAutoConfiguration
@Slf4j
public class PropertyService {
    private static final String STATE_IL = "IL";

    private final PropertyMapper propertyMapper;

    private final AddressMapper addressMapper;

    private final AlertService alertService;

    public PropertyService(PropertyMapper propertyMapper, AddressMapper addressMapper, AlertService alertService) {
        this.propertyMapper = propertyMapper;
        this.addressMapper = addressMapper;
        this.alertService = alertService;
    }

    public Collection<Property> search(String minRentPrice, String maxRentPrice) {
        return propertyMapper.search(minRentPrice, maxRentPrice);
    }

    public Property findById(int id) throws GeneralException {
        Property propertyFound = propertyMapper.findById(id);

        if (isNull(propertyFound)) {
            throw new NotFoundException("It was not found by id: " + id);
        }

        return propertyFound;
    }

    public void insert(Property property) throws GeneralException {
        int scale = BigDecimal.valueOf(property.getRentPrice()).scale();

        if (scale > 2) {
            throw new NotValidException("rentPrice max 2 decimal places");
        }

        propertyMapper.insert(property);
        log.info("CREATED: {}", property.getId());
    }

    public void update(Property property) throws GeneralException {
        if (isNull(property.getId())) {
            throw new NotValidException("id is required");
        }

        propertyMapper.update(property);
        log.info("UPDATED: {}", property.getId());
    }

    public void delete(int id) {
        propertyMapper.delete(id);
        log.info("DELETED: {}", id);

        alertService.sendPropertyDeletedAlert(id);
        // TODO: Sending the alert should be non-blocking (asynchronous)
        //  Extra points for only sending the alert when/if the transaction is committed
    }

    public PropertyReport propertyReport() {
        Set<Property> allProperties = propertyMapper.search(null, null);
        return new PropertyReport(allProperties, STATE_IL);
    }
}
