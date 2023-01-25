package com.erishiongames;

import com.erishiongames.totalsellingprice.TotalSellingPrice;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TotalSellingPricePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TotalSellingPrice.class);
		RuneLite.main(args);
	}
}