package com.propify.challenge.persistence;

import com.propify.challenge.model.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface AddressMapper {

    void insert(Address address);

    Set<Address> search();

    Address findById(Integer id);

}
