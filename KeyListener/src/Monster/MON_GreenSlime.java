package Monster;

import Entity.Entity;
import com.example.GamePanel;

import java.util.Random;


public class MON_GreenSlime extends Entity {

    public MON_GreenSlime(GamePanel gamePanel) {
        super(gamePanel);

        name = "Green Slime";
        type = 2;
        speed = 1;
        maxLife = 5;
        life = maxLife;

        collisionArea.x = 3;
        collisionArea.y = 18;
        collisionArea.width = 42;
        collisionArea.height = 30;
        collisionAreaDefaultX = collisionArea.x;
        collisionAreaDefaultY = collisionArea.y;

        getImage();
    }

    public void getImage(){
        String monster1 = "/Monster/greenslime_down_1";
        String monster2 = "/Monster/greenslime_down_2";

        up1 = setup(monster1, gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup(monster2, gamePanel.getTileSize(), gamePanel.getTileSize());
        down1 = setup(monster1, gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup(monster2, gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup(monster1, gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup(monster2, gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup(monster1, gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup(monster2, gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    @Override
    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    @Override
    public void damageReaction(){

        actionLockCounter = 0;
        //when receiving damage, the slime moves away from player position
        direction = gamePanel.player.direction;
    }
}
