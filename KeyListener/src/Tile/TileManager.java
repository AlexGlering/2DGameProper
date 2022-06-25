package Tile;

import com.example.GamePanel;
import com.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];

        getTileImage();
        loadMap("/Maps/world1");
    }

    public void getTileImage(){
            //loading in tileImage
            setup(0, "grass00", false);
            setup(1, "wall", true);
            setup(2, "water00", true);
            setup(3, "earth", false);
            setup(4, "tree", true);
            setup(5, "road00", false);
    }

    public void setup(int index, String imageName, boolean collision){
        UtilityTool utilityTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(
                    "/Tiles/New version/" + imageName + ".png")));
            tile[index].image = utilityTool.scaleImage(tile[index].image, gamePanel.getTileSize(), gamePanel.getTileSize());
            tile[index].collision = collision;

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(String filepath){
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()){
                String line = br.readLine();

                while(col < gamePanel.getMaxWorldCol()) {
                   String numbers[] = line.split(" ");
                   int num = Integer.parseInt(numbers[col]);

                   mapTileNum[col][row] = num;
                   col++;
                }
                if(col == gamePanel.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;


        while (worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()) {

            int tileNum = mapTileNum[worldCol][worldRow];

            //fitting tiles around player movement
            int worldX = worldCol * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            //procedurally generating tiles around player movement
            if(worldX + gamePanel.getTileSize()> gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.getTileSize()< gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.getTileSize()> gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.getTileSize()< gamePanel.player.worldY + gamePanel.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }
            worldCol++;

            if(worldCol == gamePanel.getMaxWorldCol()){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
