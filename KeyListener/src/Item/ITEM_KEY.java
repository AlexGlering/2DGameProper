package Item;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ITEM_KEY extends ParentItem{

    public ITEM_KEY(){
        name = "Key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Object/key.png")));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
