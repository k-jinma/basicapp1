package com.example.basicapp1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM CUSTOMER WHERE id = #{id}")
    CustomerEntity getCustomerById(int id);
}
