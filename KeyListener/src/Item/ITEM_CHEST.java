package Item;

import com.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_CHEST extends ParentItem{

    GamePanel gamePanel;

    public ITEM_CHEST(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/chest.png")));
            utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
