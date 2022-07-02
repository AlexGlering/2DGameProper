package Item;

import com.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_HEART extends ParentItem{

    GamePanel gamePanel;

    public ITEM_HEART(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Heart";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/heart_full.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/heart_half.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/heart_blank.png")));
            image = utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
            image2 = utilityTool.scaleImage(image2, gamePanel.getTileSize(), gamePanel.getTileSize());
            image3 = utilityTool.scaleImage(image3, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
