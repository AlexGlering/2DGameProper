package Item;

import com.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_KEY extends ParentItem{

    GamePanel gamePanel;

    public ITEM_KEY(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/key.png")));
            utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
