package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_AXE extends Entity {

    public ITEM_AXE(GamePanel gamePanel) {
        super(gamePanel);
        name = "Woodcutter Axe";
        type = type_axe;
        down1 = setup("/Object/axe", gamePanel.getTileSize(), gamePanel.getTileSize());
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        itemDescription = "[" + name + "]/nSimple axe, found near an/nabandoned shack.";
    }
}
