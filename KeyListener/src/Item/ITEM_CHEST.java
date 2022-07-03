package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_CHEST extends Entity {

    public ITEM_CHEST(GamePanel gamePanel){
        super(gamePanel);
        name = "Chest";
        down1 = setup("/Object/chest");
    }
}
