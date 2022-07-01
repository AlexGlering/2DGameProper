package Entity;

import com.example.GamePanel;
import java.util.Random;

public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gamePanel){
        super(gamePanel);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0] = "Hello, lad.";
        dialogues[1] = "So, you've come to this island to find the treasure?";
        dialogues[2] = "I too used to scour these lands for that sweet loot,/n" +
                "but now I'm too old and frail for such journeys.";
        dialogues[3] = "Anyhow, may your travels be fruitful/n" +
                "and full of bountiful booty,";
    }

    @Override
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 3;
        }
        super.speak();
    }

    public void getImage(){
        up1 = setup("/NPC/oldman_up_1");
        up2 = setup("/NPC/oldman_up_2");
        down1 = setup("/NPC/oldman_down_1");
        down2 = setup("/NPC/oldman_down_2");
        left1 = setup("/NPC/oldman_left_1");
        left2 = setup("/NPC/oldman_left_2");
        right1 = setup("/NPC/oldman_right_1");
        right2 = setup("/NPC/oldman_right_2");
    }

    //setting npc behavior
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
