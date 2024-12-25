package com.example.basicapp1.entity;

import lombok.Data;
import java.util.List;

@Data
public class HistoryEntity {
    private int id;
    private int customerId;
    private int itemId;
    private String purchaseDate;
    private int quantity;

    private CustomerEntity customer; // CustomerEntityとの関係は1対1の関係なので、CustomerEntity型のフィールドを追加
    private List<ItemEntity> items;  // 複数のItemEntityとの関係は1対多の関係なので、List<ItemEntity>型のフィールドを追加
}
