package com.erishiongames.totalsellingprice;

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
	private ShopInfo currentShop = null;
	private ItemComposition itemComposition = null;
	private ItemData itemdata = new ItemData();
	private Widget[] shopItems = null;
	private Widget[] playerItems = null;
	private float amountOfItemInShopInventory = 0;
	private float amountOfItemInPlayerInventory = 0;

	@Inject
    private Client client;

	@Inject
	private TotalSellingPrice totalSellingPrice;

    @Inject
    private ItemManager itemManager;

	@Inject
	private TotalSellingPriceConfig config;

	//fill out item data for the item being sold
	//check how many of the item is already in the shops inventory, and how many you have
	//create function to calculate the amount of gold earned per world


	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked clicked)
	{
		menuOptionClicked = clicked;
		if(!isShopOpen() || !isCheckingValue())
		{
			return;
		}
		currentShopName = getCurrentShopName();
		currentShop = getCurrentShop();
		if (currentShop == null)
		{
			return;
		}
		assignWidgetVariables();
		if (clickedInventory == inventoryType.NONE) return;
		if (clickedInventory == inventoryType.SHOP_INVENTORY)
		{
			totalSellingPrice.createChatMessage("Buying from shops is not supported right now");
			return;
		}
		assignItemVariables();

		amountOfItemInShopInventory = findAmountOfItemInInventory(shopItems);
		amountOfItemInPlayerInventory = findAmountOfItemInInventory(playerItems);

		if (config.calculateAmountOfWorldHopsNeeded())
		{
			float temp = amountOfItemInPlayerInventory / config.amountPerWorldToSell();

			totalSellingPrice.createChatMessage("World hops needed: " + temp);
		}



	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
//		if (currentShop !=null)
//		{
//			totalSellingPrice.createChatMessage(currentShop.toString());
//		}
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

		itemdata.setName(itemComposition.getName());
		itemdata.setId(itemComposition.getId());
		itemdata.setValue(itemComposition.getPrice());
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
		itemdata = new ItemData();
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

	private ShopInfo getCurrentShop()
	{
		for (ShopInfo shopInfo : ShopInfo.values())
		{
			if (shopInfo.getName().equals(currentShopName))
			{
				return shopInfo;
			}
		}
		totalSellingPrice.createChatMessage("this shop is not supported right now");
		return null;
	}

	private int findAmountOfItemInInventory(Widget[] inventory)
	{
		for (Widget item: inventory)
		{
			if (item.getName().contains(itemdata.getName()))
			{
				return item.getItemQuantity();
			}
		}
		return 0;
	}

	private void calculateGoldEarnedFromSelling(int amountPerWorld, float shopBuyPercent, float shopChangePercent)
	{
		double gold = 0;
		for (int i = 0; i < amountPerWorld; i++)
		{
			gold += calculateStorePurchasePrice(shopBuyPercent, shopChangePercent);
		}
	}

	private double calculateStorePurchasePrice(float shopBuyPercent, float shopChangePercent)
	{
		float shopBuy = shopBuyPercent * 100;
		float shopChange = shopChangePercent * 100;
		float priceRedux = shopBuy - (shopChange * amountOfItemInShopInventory);
		if (priceRedux < 10)
		{
			priceRedux = 10;
		}
		float sellPrice = (priceRedux / 100) * itemdata.getValue();

		return Math.floor(sellPrice);
	}



	//    public void temp()
//    {
//            storeCalculator.itemData.setId(menuOptionClicked.getItemId());
//
//            int clickedWidgetId = clickedWidget.getId();
//            int clickedWidgetParentId = clickedWidget.getParentId();
//            String itemName = Text.removeFormattingTags(menuOptionClicked.getMenuTarget());
//
//            switch (clickedWidgetId)
//            {
//                case WidgetHandler.SHOP_INVENTORY_WIDGET_ID:
//                    //display value of clicked item
//                    int baseValue = itemManager.getItemComposition(itemID).getPrice();
//
//
//                    System.out.println(menuOptionClicked.getWidget().getName());
//                    System.out.println(menuOptionClicked.getMenuTarget());
//                    System.out.println(itemName);
//                    break;
//
//                case WidgetHandler.SHOP_PLAYER_INVENTORY_WIDGET_ID:
//                    client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Selling is not setup yet!", null);
//                    break;
//            }
//    }
    }

