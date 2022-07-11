package Entity;

import com.example.GamePanel;
import com.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {

    public GamePanel gamePanel;
    public String[] dialogues = new String[20];
    public int worldX, worldY;
    public int speed;

    //Collision
    public Rectangle collisionArea = new Rectangle(0, 0, 48, 48);
    public int collisionAreaDefaultX;
    public int collisionAreaDefaultY;
    public Rectangle attackArea = new Rectangle(0, 0,0,0);

    //Images
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2,
            attackRight1, attackRight2;

    //Counter and indexes
    public int dialogueIndex = 0;
    public int invincibleCounter = 0;
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int dyingCounter = 0;

    //States
    public int spriteNum = 1;
    public String direction = "down";
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean collisionOn = false;
    public int type; //0 = player, 1 = npc, 2 = monster
    public boolean isAlive = true;
    public boolean isDying = false;

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
                gamePanel.playSFX(6);
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

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter>30){
                invincible = false;
                invincibleCounter = 0;
            }
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

            //Monster HP bar
            if(type == 2){

                double oneScale = (double)gamePanel.getTileSize()/maxLife;
                double hpBarValue = oneScale*life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX-1, screenY-4, gamePanel.getTileSize()+2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 3 , (int) hpBarValue, 10);
            }


            if(invincible){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }

            if(isDying){
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i = 5;

        if(dyingCounter <= i){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > i && dyingCounter <= i*2){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > i*2 && dyingCounter <= i*3){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > i*3 && dyingCounter <= i*4){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > i*4 && dyingCounter <= i*5){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > i*5 && dyingCounter <= i*6){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > i*6 && dyingCounter <= i*7){
            changeAlpha(g2, 0f);
        }
        if(dyingCounter > i*7 && dyingCounter <= i*8){
            changeAlpha(g2, 1f);
        }
        if(dyingCounter > i*8){
            isDying = false;
            isAlive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    //loading and scaling player image
    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try{

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                    imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);

        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

}
