package com.propify.challenge.model;

import com.propify.challenge.enums.PropertyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class PropertyReport {
    private Integer totalQuantity;

    private Map<PropertyType, Integer> quantityPerType;

    private double averageRentPrice;

    private Integer illinoisQuantity;

    public PropertyReport(Collection<Property> properties, String state) {
        this.totalQuantity = properties.size();
        this.quantityPerType = this.calculateQuantityPerType(properties);
        this.averageRentPrice = this.calculateAverangeRentPrice(properties);
        this.illinoisQuantity = this.quantifyBy(properties, state);
    }

    // Calculate the quantity of each type, 0 if there is no properties.
    private Map<PropertyType, Integer> calculateQuantityPerType(Collection<Property> properties) {
        Map<PropertyType, Integer> types = new EnumMap<>(PropertyType.class);

        for (PropertyType propertyType : PropertyType.values()) {
            types.put(propertyType, (int) properties.stream().filter(p -> propertyType.equals(p.getType())).count());
        }

        return types;
    }

    // Calculate the average rent price (exclude the properties without rent price or with rent price = 0)
    private double calculateAverangeRentPrice(Collection<Property> properties) {
        List<Property> propertiesWithoutPriceZero = properties.stream()
                .filter(p -> p.getRentPrice() > 0d)
                .collect(Collectors.toList());

        double sum = propertiesWithoutPriceZero.stream().mapToDouble(Property::getRentPrice).sum();

        BigDecimal sumDecimal = BigDecimal.valueOf(sum / propertiesWithoutPriceZero.size());

        sumDecimal = sumDecimal.setScale(2, RoundingMode.UP);

        return sumDecimal.doubleValue();
    }

    // Calculate the quantity of properties in the state of Illinois (IL)
    // to anything state
    private Integer quantifyBy(Collection<Property> properties, String state) {
        long value = properties.stream()
                .filter(p -> state.equalsIgnoreCase(p.getAddress().getState()))
                .count();

        return Math.toIntExact(value);
    }
}
