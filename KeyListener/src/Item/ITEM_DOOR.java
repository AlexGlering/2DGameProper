package Item;

import Entity.Entity;
import com.example.GamePanel;

public class ITEM_DOOR extends Entity {

    public ITEM_DOOR(GamePanel gamePanel){
        super(gamePanel);
        name = "Door";
        down1 = setup("/Object/door");
        collision = true;

        collisionArea.x = 0;
        collisionArea.y = 16;
        collisionArea.width = 48;
        collisionArea.height = 32;
        collisionAreaDefaultX = collisionArea.x;
        collisionAreaDefaultY = collisionArea.y;
    }
}
