package Item;

import Entity.Entity;
import com.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_HEART extends Entity {

    public ITEM_HEART(GamePanel gamePanel) {
        super(gamePanel);
        name = "Heart";
        image = setup("/Object/heart_full", gamePanel.getTileSize(), gamePanel.getTileSize());
        image2 = setup("/Object/heart_half", gamePanel.getTileSize(), gamePanel.getTileSize());
        image3 = setup("/Object/heart_blank", gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
