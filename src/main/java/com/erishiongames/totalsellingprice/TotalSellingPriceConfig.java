package com.erishiongames.totalsellingprice;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(TotalSellingPrice.CONFIG_GROUP)
public interface TotalSellingPriceConfig extends Config
{
	@ConfigItem
	(
		keyName = "amountPerWorldToSell",
		name = "Amount to sell each world",
		description = "How many items the user desires to sell per world"
	)
	default int amountPerWorldToSell()
	{
		return 1;
	}
	@ConfigItem
	(
		keyName = "calculateAmountOfWorldHopsNeeded",
		name = "Calculate amount of world hops to sell stack?",
		description = "Calculate amount of world hops to sell stack?"
	)
	default boolean calculateAmountOfWorldHopsNeeded()
	{
		return true;
	}
}
