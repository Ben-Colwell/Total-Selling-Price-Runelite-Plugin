package com.erishiongames.totalsellingprice;

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
	name = "Total Selling Price"
)
public class TotalSellingPrice extends Plugin {
	public final String pluginName = "Total Selling Price";


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
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", pluginName + ": " + message, null);
	}


	//93 is player inventory
	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged)
	{

	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	TotalSellingPriceConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TotalSellingPriceConfig.class);
	}
}
