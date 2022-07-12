package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_AXE extends Entity {

    public ITEM_AXE(GamePanel gamePanel) {
        super(gamePanel);
        name = "Woodcutter Axe";
        down1 = setup("/Object/axe", gamePanel.getTileSize(), gamePanel.getTileSize());
        attackValue = (int)1.5;
        itemDescription = "[" + name + "]/nSimple axe, found near an/nabandoned shack.";
    }
}
