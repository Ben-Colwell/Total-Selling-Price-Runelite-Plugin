package com.erishiongames.totalsellingprice;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ItemContainer;
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



//[Item(id=995, quantity=16997435), Item(id=556, quantity=934), Item(id=554, quantity=7736), Item(id=557, quantity=994), Item(id=555, quantity=1000), Item(id=563, quantity=98)]
	//93 is player inventory
	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged itemContainerChanged){
		int containerId = itemContainerChanged.getContainerId();
		ItemContainer itemContainer= itemContainerChanged.getItemContainer();
		System.out.println("HI");
		System.out.println(Arrays.toString(itemContainer.getItems()));
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked menuOptionClicked){
		//Check if the player is in a shop interface and if they are checking the value of an item
		if(widgetHandler.isShopOpen() && Text.removeTags(menuOptionClicked.getMenuOption()).equals("Value")) {

			String currentShop = widgetHandler.getCurrentShopName();
			Widget currentWidget = menuOptionClicked.getWidget();
			int parentID = currentWidget.getParentId();
			System.out.println("HI FROM MENU");
		}
	}


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
