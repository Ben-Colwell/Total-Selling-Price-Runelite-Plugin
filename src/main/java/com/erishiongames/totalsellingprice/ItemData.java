package com.erishiongames.totalsellingprice;


public class ItemData {
    private int id;
    private int value;
    private String name;
    private double lowAlchValue;
	private double highAlchValue;

    public ItemData(int id, int value, String name)
	{
        this.id = id;
        this.value = value;
        this.name = name;
        lowAlchValue = value * 0.4;
		highAlchValue = value * 0.6;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
		lowAlchValue = value * 0.4;
		highAlchValue = value * 0.6;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLowAlchValue() {
        return lowAlchValue;
    }

    public void setLowAlchValue(int lowAlchValue) {
        this.lowAlchValue = lowAlchValue;
		value = (lowAlchValue * 5) / 2;
		highAlchValue = value * 0.6;
    }

	public double getHighAlchValue()
	{
		return highAlchValue;
	}

	public void setHighAlchValue(int highAlchValue)
	{
		this.highAlchValue = highAlchValue;
		value = (highAlchValue * 5) / 3;
		lowAlchValue = value * 0.4;
	}
}
