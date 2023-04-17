package com.erishiongames.totalsellingprice;

import net.runelite.api.Client;
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
    public Widget clickedWidget = null;
    public int clickedWidgetID = 0;
    public  MenuOptionClicked menuOptionClicked = null;
    public inventoryType clickedInventory = inventoryType.NONE;
    public String currentShopName = null;
	public ShopInfo currentShop = null;

	@Inject
    private Client client;

	@Inject
	private TotalSellingPrice totalSellingPrice;


    @Inject
    private ItemManager itemManager;




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
		createVariables();
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		if (currentShop !=null)
		{
			totalSellingPrice.createChatMessage(currentShop.toString());
		}
		if(currentShop != null && !isShopOpen())
		{
			resetVariables();
		}
	}

    private void createVariables()
	{
        clickedWidget = menuOptionClicked.getWidget();
		assert clickedWidget != null;

		clickedWidgetID = clickedWidget.getId();
		clickedInventory = getClickedInventory(clickedWidgetID);

		totalSellingPrice.createChatMessage("created variables");
    }

	private void resetVariables()
	{
		clickedWidget = null;
		clickedWidgetID = 0;
		menuOptionClicked = null;
		clickedInventory = inventoryType.NONE;
		currentShopName = null;
		currentShop = null;
		totalSellingPrice.createChatMessage("reset variables");
	}

	public boolean isShopOpen()
	{
		return client.getWidget(WidgetInfo.SHOP_INVENTORY_ITEMS_CONTAINER) != null;
	}

	private boolean isCheckingValue()
	{
		return Text.removeTags(menuOptionClicked.getMenuOption()).equals("Value");
	}

    public String getCurrentShopName()
	{
        return Objects.requireNonNull(client.getWidget(SHOP_WIDGET_ID)).getChild(SHOP_WIDGET_CHILD_TEXT_FIELD).getText();
    }

    public inventoryType getClickedInventory(int widgetID)
	{
        switch(widgetID)
		{
            case SHOP_INVENTORY_WIDGET_ID: return inventoryType.SHOP_INVENTORY;
            case SHOP_PLAYER_INVENTORY_WIDGET_ID: return inventoryType.PLAYER_INVENTORY;
        }
        return inventoryType.NONE;
    }

	public ShopInfo getCurrentShop()
	{
		for (ShopInfo shopInfo : ShopInfo.values())
		{
			if (shopInfo.getName().equals(currentShopName))
			{
				totalSellingPrice.createChatMessage("good shop");
				return shopInfo;
			}
		}
		totalSellingPrice.createChatMessage("this shop is not supported right now");
		return null;
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

