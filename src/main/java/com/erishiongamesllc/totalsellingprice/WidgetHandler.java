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

import lombok.Getter;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.util.Text;
import javax.inject.Inject;
import java.util.Objects;

public class WidgetHandler {
    public static final int SHOP_WIDGET_ID = 19660801;
    public static final int SHOP_WIDGET_CHILD_TEXT_FIELD = 1;
    public static final int SHOP_INVENTORY_WIDGET_ID = 19660816;
    public static final int SHOP_PLAYER_INVENTORY_WIDGET_ID = 19726336;
    private Widget clickedWidget = null;
    private int clickedWidgetID = 0;
    private  MenuOptionClicked menuOptionClicked = null;
    private inventoryType clickedInventory = inventoryType.NONE;
    private String currentShopName = null;

	private ItemComposition itemComposition = null;

	private Widget[] shopItems = null;
	private Widget[] playerItems = null;


	@Getter
	private ItemData itemData = new ItemData();
	@Getter
	private ShopInfo currentShop = null;
	@Getter
	private int amountOfItemInShopInventory = 0;
	@Getter
	private int amountOfItemInPlayerInventory = 0;

	@Inject
    private Client client;

	@Inject
	private TotalSellingPricePlugin totalSellingPricePlugin;

    @Inject
    private ItemManager itemManager;

	@Inject
	private TotalSellingPriceConfig config;

	@Inject
	private ShopCalculator shopCalculator;

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked clicked)
	{
		menuOptionClicked = clicked;

		if(!isShopOpen() || !isCheckingValue())
		{
			return;
		}

		currentShopName = getCurrentShopName();
		currentShop = getCurrentShopValue();

		if (currentShop == null)
		{
			return;
		}

		assignWidgetVariables();

		if (clickedInventory == inventoryType.NONE)
		{
			return;
		}
		if (clickedInventory == inventoryType.SHOP_INVENTORY)
		{
			createChatMessage("Buying from shops is not supported right now");
			return;
		}

		assignItemVariables();

		amountOfItemInPlayerInventory = findAmountOfItemInInventory(playerItems);
		amountOfItemInShopInventory = findAmountOfItemInInventory(shopItems);

		shopCalculator.calculateAllGoldSellingOptions();
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if(currentShop != null && !isShopOpen())
		{
			resetVariables();
		}
	}

    private void assignWidgetVariables()
	{
        clickedWidget = menuOptionClicked.getWidget();
		assert clickedWidget != null;

		clickedWidgetID = clickedWidget.getId();
		clickedInventory = getClickedInventory(clickedWidgetID);
    }

	private void assignItemVariables()
	{
		itemComposition = itemManager.getItemComposition(menuOptionClicked.getItemId());

		itemData.setName(itemComposition.getName());
		itemData.setId(itemComposition.getId());
		itemData.setValue(itemComposition.getPrice());
		shopItems = Objects.requireNonNull(client.getWidget(SHOP_INVENTORY_WIDGET_ID)).getChildren();
		playerItems = Objects.requireNonNull(client.getWidget(SHOP_PLAYER_INVENTORY_WIDGET_ID)).getChildren();
	}

	private void resetVariables()
	{
		clickedWidget = null;
		clickedWidgetID = 0;
		menuOptionClicked = null;
		clickedInventory = inventoryType.NONE;
		currentShopName = null;
		currentShop = null;
		itemComposition = null;
		itemData = new ItemData();
		shopItems = null;
		playerItems = null;
		amountOfItemInShopInventory = 0;
		amountOfItemInPlayerInventory = 0;
	}

	private boolean isShopOpen()
	{
		return client.getWidget(WidgetInfo.SHOP_INVENTORY_ITEMS_CONTAINER) != null;
	}

	private boolean isCheckingValue()
	{
		return Text.removeTags(menuOptionClicked.getMenuOption()).equals("Value");
	}

    private String getCurrentShopName()
	{
        return Objects.requireNonNull(client.getWidget(SHOP_WIDGET_ID)).getChild(SHOP_WIDGET_CHILD_TEXT_FIELD).getText();
    }

    private inventoryType getClickedInventory(int widgetID)
	{
        switch(widgetID)
		{
            case SHOP_INVENTORY_WIDGET_ID: return inventoryType.SHOP_INVENTORY;
            case SHOP_PLAYER_INVENTORY_WIDGET_ID: return inventoryType.PLAYER_INVENTORY;
        }
        return inventoryType.NONE;
    }

	private ShopInfo getCurrentShopValue()
	{
		for (ShopInfo shopInfo : ShopInfo.values())
		{
			if (shopInfo.getName().equals(currentShopName))
			{
				return shopInfo;
			}
		}
		createChatMessage("this shop is not supported right now");
		return null;
	}

	private int findAmountOfItemInInventory(Widget[] inventory)
	{
		for (Widget item: inventory)
		{
			if (item.getName().contains(itemData.getName()))
			{
				return item.getItemQuantity();
			}
		}
		return 0;
	}








	public void createChatMessage(String message)
	{
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message, null);
	}
}