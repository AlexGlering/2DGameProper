package Item;

import com.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ParentItem {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.getTileSize()> gp.player.worldX - gp.player.screenX &&
                worldX - gp.getTileSize()< gp.player.worldX + gp.player.screenX &&
                worldY + gp.getTileSize()> gp.player.worldY - gp.player.screenY &&
                worldY - gp.getTileSize()< gp.player.worldY + gp.player.screenY){

            g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }
}
