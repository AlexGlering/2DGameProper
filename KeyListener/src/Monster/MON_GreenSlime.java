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
        maxLife = 4;
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

        up1 = setup(monster1);
        up2 = setup(monster2);
        down1 = setup(monster1);
        down2 = setup(monster2);
        left1 = setup(monster1);
        left2 = setup(monster2);
        right1 = setup(monster1);
        right2 = setup(monster2);
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
}
