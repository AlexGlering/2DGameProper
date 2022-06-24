package Item;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_BOOTS extends ParentItem{

    public ITEM_BOOTS(){
        name = "Boots";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/boots.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
