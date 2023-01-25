package com.erishiongames.totalsellingprice;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.events.WidgetClosed;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.util.Text;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Objects;

public class WidgetHandler {
    public static final int SHOP_WIDGET_ID = 19660801;
    public static final int SHOP_WIDGET_CHILD_TEXT_FIELD = 1;

    public static final int SHOP_INVENTORY_WIDGET_ID = 19660816;
    public static final int SHOP_PLAYER_INVENTORY_WIDGET_ID = 19726336;

    
    @Inject
    private Client client;

    public String getCurrentShopName(){
        return Objects.requireNonNull(client.getWidget(SHOP_WIDGET_ID)).getChild(SHOP_WIDGET_CHILD_TEXT_FIELD).getText();
    }

    public String getClickedInventory(int parentID){
        switch(parentID){
            case SHOP_INVENTORY_WIDGET_ID: return "Shop Inventory";
            case SHOP_PLAYER_INVENTORY_WIDGET_ID: return "Player Inventory";
        }
        return "Invalid ID";
    }

    public boolean isShopOpen() {
        return client.getWidget(WidgetInfo.SHOP_INVENTORY_ITEMS_CONTAINER) != null;
    }

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded widgetLoaded){

    }

    @Subscribe
    public void onWidgetClosed(WidgetClosed widgetClosed){

    }

}
