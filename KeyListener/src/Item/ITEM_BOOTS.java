package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_BOOTS extends Entity {

    public ITEM_BOOTS(GamePanel gamePanel){
        super(gamePanel);
        name = "Boots";
        down1 = setup("/Object/boots", gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
