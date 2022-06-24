package Item;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_CHEST extends ParentItem{

    public ITEM_CHEST(){
        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/chest.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
