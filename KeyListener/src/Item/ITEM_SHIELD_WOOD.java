package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_SHIELD_WOOD extends Entity {

    public ITEM_SHIELD_WOOD(GamePanel gamePanel) {
        super(gamePanel);
        name = "Wood Shield";
        down1 = setup("/Object/shield_wood", gamePanel.getTileSize(), gamePanel.getTileSize());
        defenceValue = 1;
        itemDescription = "[" + name + "]/nOld dented shield. Might as /nwell've be made out of paper.";
    }
}
