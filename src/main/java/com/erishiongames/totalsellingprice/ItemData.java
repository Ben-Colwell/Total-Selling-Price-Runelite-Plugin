package com.erishiongames.totalsellingprice;

public class ItemData {
    private int id;
    private int quantity;
    private int value;
    private String name;


    public ItemData(int id, int quantity, int value, String name) {
        this.id = id;
        this.quantity = quantity;
        this.value = value;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
