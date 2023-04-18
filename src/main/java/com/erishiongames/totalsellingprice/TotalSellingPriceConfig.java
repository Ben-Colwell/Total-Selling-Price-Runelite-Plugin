/* BSD 2-Clause License
 * Copyright (c) 2023, Erishion Games LLC <https://github.com/Erishion-Games-LLC>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
