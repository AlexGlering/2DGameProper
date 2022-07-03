package Entity;

import com.example.GamePanel;
import com.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {

    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle collisionArea = new Rectangle(0, 0, 48, 48);
    public int collisionAreaDefaultX;
    public int collisionAreaDefaultY;
    public boolean collisionOn = false;
    public GamePanel gamePanel;
    public int actionLockCounter = 0;
    public String[] dialogues = new String[20];
    public int dialogueIndex = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int type; //0 = player, 1 = npc, 2 = monster

    //Item
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;


    //Character status
    public int maxLife;
    public int life;

    public Entity (GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setAction(){}

    public void speak(){
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gamePanel.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }

    public void update(){
        setAction();

        collisionOn = false;
        gamePanel.checker.checkTile(this);
        gamePanel.checker.checkItem(this, false);
        boolean contactPlayer = gamePanel.checker.checkPlayer(this);
        gamePanel.checker.checkEntity(this, gamePanel.npcs);
        gamePanel.checker.checkEntity(this, gamePanel.monsters);

        if(this.type == 2 && contactPlayer && !gamePanel.player.invincible){
                gamePanel.player.life -= 1;
                gamePanel.player.invincible = true;
        }

        if(!collisionOn){
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
        int frameAdjust = 12;
        spriteCounter++;
        if(spriteCounter > frameAdjust){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if (spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(worldX + gamePanel.getTileSize()> gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.getTileSize()< gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.getTileSize()> gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.getTileSize()< gamePanel.player.worldY + gamePanel.player.screenY){

            BufferedImage image = null;
            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
            }

            g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }

    //loading and scaling player image
    public BufferedImage setup(String imagePath){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try{

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                    imagePath + ".png")));
            image = utilityTool.scaleImage(image, gamePanel.getTileSize(), gamePanel.getTileSize());

        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

}
