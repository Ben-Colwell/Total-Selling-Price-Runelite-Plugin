package com.erishiongamesllc.totalsellingprice;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TotalSellingPricePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TotalSellingPricePlugin.class);
		RuneLite.main(args);
	}
}