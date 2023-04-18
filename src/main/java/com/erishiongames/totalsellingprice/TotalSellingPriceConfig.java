package com.erishiongames.totalsellingprice;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup(TotalSellingPrice.CONFIG_GROUP)
public interface TotalSellingPriceConfig extends Config
{
	@ConfigSection
	(
		name = "Sell amounts",
		description = "Choose what amounts you wish to see when calculating gold earned per world",
		position = 0
	)
		String sellAmounts = "Sell Amounts";

	@ConfigItem
	(
		keyName = "sell10",
		name = "Sell 10 per world",
		description = "",
		position = 0,
		section = sellAmounts
	)
		default boolean sell10()
	{
		return true;
	}

	@ConfigItem
		(
			keyName = "sell20",
			name = "Sell 20 per world",
			description = "",
			position = 1,
			section = sellAmounts
		)
	default boolean sell20()
	{
		return true;
	}

	@ConfigItem
		(
			keyName = "sell30",
			name = "Sell 30 per world",
			description = "",
			position = 2,
			section = sellAmounts
		)
	default boolean sell30()
	{
		return true;
	}

	@ConfigItem
		(
			keyName = "sell40",
			name = "Sell 40 per world",
			description = "",
			position = 3,
			section = sellAmounts
		)
	default boolean sell40()
	{
		return true;
	}

	@ConfigItem
		(
			keyName = "sell50",
			name = "Sell 50 per world",
			description = "",
			position = 4,
			section = sellAmounts
		)
	default boolean sell50()
	{
		return true;
	}

	@ConfigItem
		(
			keyName = "sellAll",
			name = "Sell all per world",
			description = "",
			position = 5,
			section = sellAmounts
		)
	default boolean sellAll()
	{
		return true;
	}

	@ConfigItem
		(
			keyName = "sellCustom",
			name = "Sell custom amount per world",
			description = "",
			position = 6,
			section = sellAmounts
		)
	default boolean sellCustom()
	{
		return true;
	}

	@ConfigItem
		(
			keyName = "amountPerWorldToSell",
			name = "Amount to sell per world",
			description = "",
			position = 7,
			section = sellAmounts
		)
	default int amountPerWorldToSell()
	{
		return 1;
	}

	@ConfigItem
		(
			keyName = "calculateAmountOfWorldHopsNeeded",
			name = "Calculate amount of world hops to sell stack?",
			description = "",
			position = 1
		)
	default boolean calculateAmountOfWorldHopsNeeded()
	{
		return true;
	}

	@ConfigItem
	(
		keyName = "highlightColor",
		name = "Highlight Color",
		description = "The desired color for messages",
		position = 2
	)
	default Color highlightColor()
	{
		return Color.BLUE;
	}
}
