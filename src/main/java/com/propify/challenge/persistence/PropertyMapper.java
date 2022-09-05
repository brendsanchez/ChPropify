package com.propify.challenge.persistence;

import com.propify.challenge.model.Property;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface PropertyMapper {

    void insert(Property property);

    Set<Property> search(@Param("minRentPrice") String minRentPrice, @Param("maxRentPrice") String maxRentPrice);

    Property findById(Integer id);

    void update(Property property);

    void delete(Integer id);
}
