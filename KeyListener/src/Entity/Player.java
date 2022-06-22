package Entity;

import com.example.GamePanel;
import com.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        //setting player sprite default position
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Walking sprites/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Walking sprites/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Walking sprites/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Walking sprites/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Walking sprites/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Walking sprites/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Walking sprites/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Player/Walking sprites/boy_right_2.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        //handling key input to move player sprite
        if(keyHandler.upPressed){
            direction = "up";
            y -= speed;
        }
        else if(keyHandler.downPressed){
            direction = "down";
            y += speed;
        }
        else if(keyHandler.leftPressed){
            direction = "left";
            x -= speed;
        }
        else if(keyHandler.rightPressed){
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
        //drawing player sprite
        BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };

        g2.drawImage(image, x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);


        //rectangle test
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
