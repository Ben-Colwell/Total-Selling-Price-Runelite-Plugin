package com.erishiongames.totalsellingprice;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import  net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.util.Text;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Total Selling Price"
)
public class TotalSellingPrice extends Plugin {

	private static final Pattern TAG_REGEXP = Pattern.compile("<[^>]*>");


	@Inject
	private Client client;

	@Inject
	private WidgetHandler widgetHandler;

	@Inject
	private TotalSellingPriceConfig config;

	@Inject
	private ItemManager itemManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}



	//93 is player inventory
	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged){
//		ItemContainer itemContainer= itemContainerChanged.getItemContainer();
//		System.out.println(Arrays.toString(itemContainer.getItems()));
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked menuOptionClicked){
		//Check if the player is in a shop interface and if they are checking the value of an item
		if(widgetHandler.isShopOpen() && Text.removeTags(menuOptionClicked.getMenuOption()).equals("Value")) {
			int itemID = menuOptionClicked.getItemId();
			String currentShopName = widgetHandler.getCurrentShopName();
			Widget clickedWidget = menuOptionClicked.getWidget();
			int clickedWidgetId = clickedWidget.getId();
			int clickedWidgetParentId = clickedWidget.getParentId();
			String itemName = removeTags(menuOptionClicked.getMenuTarget(), TAG_REGEXP);

			switch (clickedWidgetId) {
				case WidgetHandler.SHOP_INVENTORY_WIDGET_ID:
					//display value of clicked item
					int baseValue = itemManager.getItemComposition(itemID).getPrice();


					System.out.println(menuOptionClicked.getWidget().getName());
					System.out.println(menuOptionClicked.getMenuTarget());
					System.out.println(itemName);
					break;

				case WidgetHandler.SHOP_PLAYER_INVENTORY_WIDGET_ID:
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Selling is not setup yet!", null);
					break;
			}
		}
	}

	//This is not my code. Found in the Total Cost Plugin by Fiffers.
	private static String removeTags(String str, Pattern pattern)
	{
		StringBuffer stringBuffer = new StringBuffer();
		Matcher matcher = pattern.matcher(str);
		while (matcher.find())
		{
			matcher.appendReplacement(stringBuffer, "");
			String match = matcher.group(0);
			switch (match)
			{
				case "<lt>":
				case "<gt>":
					stringBuffer.append(match);
					break;
			}
		}
		matcher.appendTail(stringBuffer);
		return stringBuffer.toString();
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
