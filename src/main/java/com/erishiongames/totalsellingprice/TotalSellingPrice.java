package com.erishiongames.totalsellingprice;

import static com.erishiongames.totalsellingprice.TotalSellingPrice.PLUGIN_NAME;
import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = PLUGIN_NAME
)
public class TotalSellingPrice extends Plugin {
	public static final String PLUGIN_NAME = "Total Selling Price";
	public static final String CONFIG_GROUP = "totalsellingprice";

	@Inject
	private Client client;

	@Inject
	private WidgetHandler widgetHandler;

	@Inject
	private TotalSellingPriceConfig config;

	@Inject
	private EventBus eventBus;

	@Override
	protected void startUp() throws Exception
	{
		eventBus.register(widgetHandler);
	}

	@Override
	protected void shutDown() throws Exception
	{
		eventBus.unregister(widgetHandler);
	}


	public void createChatMessage(String message)
	{
		System.out.println(PLUGIN_NAME + ": " + message);
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", PLUGIN_NAME + ": " + message, null);
	}


	//93 is player inventory
	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged)
	{

	}

	@Provides
	TotalSellingPriceConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TotalSellingPriceConfig.class);
	}
}
