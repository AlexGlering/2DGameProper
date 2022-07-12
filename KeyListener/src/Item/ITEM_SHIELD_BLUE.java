package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_SHIELD_BLUE extends Entity {

    public ITEM_SHIELD_BLUE(GamePanel gamePanel) {
        super(gamePanel);
        name = "Blue Iron Shield";
        type = type_shield;
        down1 = setup("/Object/shield_blue", gamePanel.getTileSize(), gamePanel.getTileSize());
        defenceValue = 2;
        itemDescription = "[" + name + "]/nBlue painted shield made of/ncorroded scrap metal./nSlighly better than nothing.";
    }
}
