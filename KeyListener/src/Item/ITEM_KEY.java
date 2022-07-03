package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_KEY extends Entity {

    public ITEM_KEY(GamePanel gamePanel) {
        super(gamePanel);
        name = "Key";
        down1 = setup("/Object/key");
    }

}
