package com.erishiongames.totalsellingprice;


public class ItemData {
    private int id;
    private int quantity;
    private int value;
    private String name;
    private int lowAlchValue;
	private int highAlchValue;

    public ItemData(int id, int quantity, int value, String name, int lowAlchValue, int highAlchValue)
	{
        this.id = id;
        this.quantity = quantity;
        this.value = value;
        this.name = name;
        this.lowAlchValue = lowAlchValue;
		this.highAlchValue = highAlchValue;
    }

    public ItemData()
	{
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

    public int getLowAlchValue() {
        return lowAlchValue;
    }

    public void setLowAlchValue(int lowAlchValue) {
        this.lowAlchValue = lowAlchValue;
    }

	public int getHighAlchValue()
	{
		return highAlchValue;
	}

	public void setHighAlchValue(int highAlchValue)
	{
		this.highAlchValue = highAlchValue;
	}
}
