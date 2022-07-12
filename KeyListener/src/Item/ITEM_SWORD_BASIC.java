package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_SWORD_BASIC extends Entity {

    public ITEM_SWORD_BASIC(GamePanel gamePanel) {
        super(gamePanel);

        name = "Basic Sword";
        down1 = setup("/Object/sword_normal", gamePanel.getTileSize(), gamePanel.getTileSize());
        attackValue = 1;
        itemDescription = "[" + name + "]/nJust a basic blunt sword./nCan't really cut anything,/nbut can probably be used to /nclub something with.";
    }
}
