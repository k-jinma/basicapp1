package com.example.basicapp1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.basicapp1.entity.CustomerEntity;
import com.example.basicapp1.entity.HistoryEntity;
import com.example.basicapp1.entity.ItemEntity;

@Mapper
public interface CustomerMapper {

    CustomerEntity getCustomerById(int id);
    List<ItemEntity> getAllItems();
    HistoryEntity getHistoryById(int id);

}
