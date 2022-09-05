package com.propify.challenge;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface PropertyMapper {

    void insert(Property property);

    Set<Property> search(String minRentPrice, String maxRentPrice);

    Property findById(Integer id);

    void update(Property property);

    void delete(Integer id);
}
