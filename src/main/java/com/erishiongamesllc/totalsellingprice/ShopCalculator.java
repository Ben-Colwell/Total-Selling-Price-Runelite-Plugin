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
package com.erishiongamesllc.totalsellingprice;

import javax.inject.Inject;

public class ShopCalculator
{
	@Inject
	private TotalSellingPriceConfig config;

	@Inject
	private WidgetHandler widgetHandler;

	public void calculateAllGoldSellingOptions()
	{
		if (config == null)
		{
			return;
		}
		if (config.sell10())
		{
			calculateGoldEarnedFromSelling(10);
		}
		if (config.sell20())
		{
			calculateGoldEarnedFromSelling(20);
		}
		if (config.sell30())
		{
			calculateGoldEarnedFromSelling(30);
		}
		if (config.sell40())
		{
			calculateGoldEarnedFromSelling(40);
		}
		if (config.sell50())
		{
			calculateGoldEarnedFromSelling(50);
		}
		if (config.sellAll())
		{
			calculateGoldEarnedFromSelling(widgetHandler.getAmountOfItemInPlayerInventory());
		}
		if (config.sellCustom() )
		{
			calculateGoldEarnedFromSelling(config.amountPerWorldToSell());
		}
	}

	private void calculateGoldEarnedFromSelling(int amountPerWorld)
	{
		int amountOfItemInShopInventory = widgetHandler.getAmountOfItemInShopInventory();
		int amountOfItemInPlayerInventory = widgetHandler.getAmountOfItemInPlayerInventory();

		int goldPerWorld = calculateGoldPerWorld(amountPerWorld, amountOfItemInShopInventory);

		int totalGold = (int) Math.floor(goldPerWorld * ((double) amountOfItemInPlayerInventory / amountPerWorld));
		int averageGold = (goldPerWorld / amountPerWorld);

		double percentOfMaxGold = calculateMaxGoldPercent(totalGold, amountOfItemInPlayerInventory);

		String colorAmountPerWorld = colorMessage(String.format("%,d", amountPerWorld));
		String colorGoldPerWorld = colorMessage(String.format("%,d", goldPerWorld));
		String colorAverageGold = colorMessage(String.format("%,d", averageGold));
		String colorTotalGold = colorMessage(String.format("%,d", totalGold));
		String colorPercent = colorMessage(String.format("%.2f", percentOfMaxGold));

		widgetHandler.createChatMessage("Sell " + colorAmountPerWorld + ": " + colorGoldPerWorld + " gold. Average: " + colorAverageGold + ". Total: " + colorTotalGold + ". Percent: " + colorPercent + ".");

		displayAmountOfWorldHopsNeeded(amountPerWorld);
	}

	private int calculateGoldPerWorld(int amountPerWorld, int amountInInventory)
	{
		int amountInShopInventory = amountInInventory;

		ShopInfo currentShop = widgetHandler.getCurrentShop();
		int currentGold = 0;
		float shopChange = currentShop.getChangePercent();

		for (int i = 0; i < amountPerWorld; i++)
		{
			float buyPercent = currentShop.getBuyPercent() - (shopChange * amountInShopInventory);

			//if try buy for less than 10%, buy for 10%
			if (buyPercent < .1f)
			{
				buyPercent = .1f;
			}

			int shopBuyPrice = (int) Math.floor(buyPercent * widgetHandler.getItemData().getValue());

			currentGold += shopBuyPrice;
			amountInShopInventory += 1;
		}
		return currentGold;
	}

	private float calculateMaxGoldPercent(int totalGold, int amountInPlayerInventory)
	{
		//calculate the max amount of gold possible
		int maxGoldPossible = calculateGoldPerWorld(1, 0) * amountInPlayerInventory;

		return (float) totalGold / maxGoldPossible;

	}

	private void displayAmountOfWorldHopsNeeded(int amountPerWorld)
	{
		if (!config.calculateAmountOfWorldHopsNeeded())
		{
			return;
		}

		int hopsNeeded = (widgetHandler.getAmountOfItemInPlayerInventory() / amountPerWorld);
		String colorHopsNeeded = colorMessage(String.format("%,d", hopsNeeded));
		widgetHandler.createChatMessage("World hops needed: " + colorHopsNeeded);
	}

	public String colorMessage(String message)
	{
		String color = Integer.toHexString(config.highlightColor().getRGB()).substring(2);
		return "<col=" + color + ">" + message + "</col>";
	}
}