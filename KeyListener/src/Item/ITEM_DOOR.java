package Item;

import com.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_DOOR extends ParentItem{
    GamePanel gamePanel;

    public ITEM_DOOR(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/door.png")));
            utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
