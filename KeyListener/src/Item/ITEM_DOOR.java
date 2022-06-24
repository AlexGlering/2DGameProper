package Item;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_DOOR extends ParentItem{

    public ITEM_DOOR(){
        name = "Door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/door.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
