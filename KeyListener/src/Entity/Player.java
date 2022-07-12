package Entity;

import Item.ITEM_SHIELD_WOOD;
import Item.ITEM_SWORD_BASIC;
import com.example.GamePanel;
import com.example.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity{
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        super(gamePanel);
        this.keyHandler = keyHandler;

        screenX = gamePanel.getScreenWidth()/2 - (gamePanel.getTileSize()/2);
        screenY = gamePanel.getScreenHeight()/2 - (gamePanel.getTileSize()/2);

        collisionArea = new Rectangle(8, 16, 32, 32);
        collisionAreaDefaultX = collisionArea.x;
        collisionAreaDefaultY = collisionArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues(){
        //setting player sprite default position
        worldX = gamePanel.getTileSize() * 23;
        worldY = gamePanel.getTileSize() * 21;
        speed = 4;
        direction = "down";

        //Player status
        level = 1;
        maxLife = 6;
        life = maxLife;
        strenght = 1; //more strength equals more attack power
        dexterity = 1; //more dex equals less received damage
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new ITEM_SWORD_BASIC(gamePanel);
        currentShield = new ITEM_SHIELD_WOOD(gamePanel);
        attack = calculateAttack(); //total attack value is decided by strength and weapon
        defence = calculateDefence(); //vice versa with dex and shield
    }

    public int calculateAttack(){
        return attack = strenght * currentWeapon.attackValue;
    }

    public int calculateDefence(){
        return defence = dexterity * currentShield.defenceValue;
    }

    public void getPlayerImage(){
        up1 = setup("/Player/Walking sprites/boy_up_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/Player/Walking sprites/boy_up_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup("/Player/Walking sprites/boy_down_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/Player/Walking sprites/boy_down_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/Player/Walking sprites/boy_left_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/Player/Walking sprites/boy_left_2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/Player/Walking sprites/boy_right_1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/Player/Walking sprites/boy_right_2", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void getPlayerAttackImage(){
        attackUp1 = setup("/Player/Attacking sprites/boy_attack_up_1", gamePanel.getTileSize(), gamePanel.getTileSize()*2);
        attackUp2 = setup("/Player/Attacking sprites/boy_attack_up_2", gamePanel.getTileSize(), gamePanel.getTileSize()*2);
        attackDown1 = setup("/Player/Attacking sprites/boy_attack_down_1", gamePanel.getTileSize(), gamePanel.getTileSize()*2);
        attackDown2 = setup("/Player/Attacking sprites/boy_attack_down_2", gamePanel.getTileSize(), gamePanel.getTileSize()*2);
        attackLeft1 = setup("/Player/Attacking sprites/boy_attack_left_1", gamePanel.getTileSize()*2, gamePanel.getTileSize());
        attackLeft2 = setup("/Player/Attacking sprites/boy_attack_left_2", gamePanel.getTileSize()*2, gamePanel.getTileSize());
        attackRight1 = setup("/Player/Attacking sprites/boy_attack_right_1", gamePanel.getTileSize()*2, gamePanel.getTileSize());
        attackRight2 = setup("/Player/Attacking sprites/boy_attack_right_2", gamePanel.getTileSize()*2, gamePanel.getTileSize());
    }

    @Override
    public void update(){

        if(attacking){
            attacking();
        }
        //only increase sprite counter and animate player if player is moving
        else if (keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.rightPressed || keyHandler.leftPressed || keyHandler.enterPressed){

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

            //check npc collision
            int npcIndex = gamePanel.checker.checkEntity(this, gamePanel.npcs);
            interactNPC(npcIndex);

            //check monster collision
            int monsterIndex = gamePanel.checker.checkEntity(this, gamePanel.monsters);
            contactMonster(monsterIndex);

            //Check event
            gamePanel.eventHandler.checkEvent();

            //If collision is false, player can't move
            if(!collisionOn && !keyHandler.enterPressed){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            if(keyHandler.enterPressed && !attackCanceled){
                gamePanel.playSFX(7);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gamePanel.keyHandler.enterPressed = false;

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

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter>30){
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    public void attacking(){
        spriteCounter++;

        //show attack sprite 1 for x amount of frames
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        //show attack sprite 2 for x amount of frames
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;


            //Save the current world(X and Y) collisionArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int collisionAreaWidth = collisionArea.width;
            int collisionAreaHeight = collisionArea.height;

            //Adjust player's world(X and Y) for the attackArea
            switch (direction) {
                case "up" -> worldY -= attackArea.height;
                case "down" -> worldY += attackArea.height;
                case "left" -> worldX -= attackArea.width;
                case "right" -> worldX += attackArea.width;
            }

            //attackArea becomes collisionArea
            collisionArea.width = attackArea.width;
            collisionArea.height = attackArea.height;

            //check monster collision with the updated world(X and Y) and collisionArea
            int monsterIndex = gamePanel.checker.checkEntity(this, gamePanel.monsters);
            damageMonster(monsterIndex);

            //After checking collision, restore original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            collisionArea.width = collisionAreaWidth;
            collisionArea.height = collisionAreaHeight;
        }
        //reset attack sprite
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpItem(int index){
        if(index != 999) {}
    }

    public void interactNPC(int index) {

        if (gamePanel.keyHandler.enterPressed) {
            if (index != 999) {
                attackCanceled = true;
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npcs[index].speak();
            }
        }
    }

    public void contactMonster(int index){
        if(index != 999 && !invincible) {
            gamePanel.playSFX(6);

            int damage = gamePanel.monsters[index].attack - defence;
            if(damage < 0){
                damage = 0;
            }

            life -= damage;
            gamePanel.ui.addMessage(damage + " HP damage received");

            invincible = true;
        }
    }

    public void damageMonster(int index){
        if(index != 999){
            if(!gamePanel.monsters[index].invincible){
                gamePanel.playSFX(5);

                int damage = attack - gamePanel.monsters[index].defence;
                if(damage < 0){
                    damage = 0;
                }

                gamePanel.monsters[index].life -= damage;
                gamePanel.monsters[index].invincible = true;
                gamePanel.monsters[index].damageReaction();

                if(gamePanel.monsters[index].life <= 0){
                  gamePanel.monsters[index].isDying = true;
                  gamePanel.ui.addMessage(gamePanel.monsters[index].name + " slain");
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        //drawing player sprite
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up" -> {
                if(!attacking){
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if(attacking){
                    tempScreenY = screenY - gamePanel.getTileSize();
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
            }
            case "down" -> {
                if(!attacking){
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if(attacking){
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }
            }
            case "left" -> {
                if(!attacking){
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - gamePanel.getTileSize();
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }
            }
            case "right" -> {
                if(!attacking){
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if(attacking){
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
            }
        }

        //player opacity is decreased slightly when invisible
        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
