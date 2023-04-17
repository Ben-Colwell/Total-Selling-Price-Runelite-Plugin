package com.erishiongames.totalsellingprice;

public enum ShopInfo
{
	BANDIT_DUTY_FREE_GENERAL_STORE("Bandit Duty Free", 0.9f, 0.6f, 0.03f),
	MARTIN_THWAITS_LOST_AND_FOUND("Martin Thwait's Lost and Found.", 1.0f, 0.6f, 0.02f),
	WEST_ARDOUGNE_GENERAL_STORE("West Ardougne General Store", 1.2f, 0.55f, 0.02f),
	POLLNIVNEACH_GENERAL_STORE("Pollnivneach general store.", 1.0f, 0.55f, 0.01f),
	LEGENDS_GUILD_GENERAL_STORE("Legends Guild General Store.", 1.55f, 0.55f, 0.01f),
	;


	private final String name;
	private final float sellPercent;
	private final float buyPercent;
	private final float changePercent;

	ShopInfo(String name, float sellPercent, float buyPercent, float changePercent)
	{
		this.name = name;
		this.sellPercent = sellPercent;
		this.buyPercent = buyPercent;
		this.changePercent = changePercent;
	}

	public String getName()
	{
		return name;
	}

	public float getSellPercent()
	{
		return sellPercent;
	}

	public float getBuyPercent()
	{
		return buyPercent;
	}

	public float getChangePercent()
	{
		return changePercent;
	}
}
