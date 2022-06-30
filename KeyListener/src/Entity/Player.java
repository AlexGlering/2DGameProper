package Entity;

import com.example.GamePanel;
import com.example.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity{
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        super(gamePanel);
        this.keyHandler = keyHandler;

        screenX = gamePanel.getScreenWidth()/2 - (gamePanel.getTileSize()/2);
        screenY = gamePanel.getScreenHeight()/2 - (gamePanel.getTileSize()/2);

        collisionArea = new Rectangle(
                8,
                16,
                32,
                32);
        collisionAreaDefaultX = collisionArea.x;
        collisionAreaDefaultY = collisionArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        //setting player sprite default position
        worldX = gamePanel.getTileSize() * 23;
        worldY = gamePanel.getTileSize() * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        up1 = setup("/Player/Walking sprites/boy_up_1");
        up2 = setup("/Player/Walking sprites/boy_up_2");
        down1 = setup("/Player/Walking sprites/boy_down_1");
        down2 = setup("/Player/Walking sprites/boy_down_2");
        left1 = setup("/Player/Walking sprites/boy_left_1");
        left2 = setup("/Player/Walking sprites/boy_left_2");
        right1 = setup("/Player/Walking sprites/boy_right_1");
        right2 = setup("/Player/Walking sprites/boy_right_2");
    }

    public void update(){
        //only increase sprite counter and animate player if player is moving
        if (keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.rightPressed || keyHandler.leftPressed){

            //handling key input to move player sprite
            if(keyHandler.upPressed){
                direction = "up";
            }
            else if(keyHandler.downPressed){
                direction = "down";
            }
            else if(keyHandler.leftPressed){
                direction = "left";
            }
            else if(keyHandler.rightPressed){
                direction = "right";
            }

            //checking for collision
            collisionOn = false;
            gamePanel.checker.checkTile(this);

            //check item collision
            int itemIndex = gamePanel.checker.checkItem(this, true);
            pickUpItem(itemIndex);

            //If collision is false, player can't move
            if(!collisionOn){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            //changing player image for every x number of frameAdjust
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

    }
    public void pickUpItem(int index){
        if(index != 999) {

        }
    }

    public void draw(Graphics2D g2){
        //drawing player sprite
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
        g2.drawImage(image, screenX, screenY, null);
    }
}
