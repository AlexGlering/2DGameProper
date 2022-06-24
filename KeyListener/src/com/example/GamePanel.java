package com.example;

import Entity.Player;
import Item.ParentItem;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public int getTileSize() {
        return tileSize;
    }
    public int getMaxScreenCol() {return maxScreenCol;}
    public int getMaxScreenRow() {return maxScreenRow;}
    public int getScreenWidth() {return screenWidth;}
    public int getScreenHeight() {return screenHeight;}

    //World settings
    final int maxWorldCol = 50;
    final int maxWorldRow = 50;
    final int worldWidth = tileSize * maxWorldCol;
    final int worldHeight = tileSize * maxWorldRow;

    public int getMaxWorldCol() {return maxWorldCol;}
    public int getMaxWorldRow() {return maxWorldRow;}
    public int getWorldWidth() {return worldWidth;}
    public int getWorldHeight() {return worldHeight;}

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    public CollisionChecker checker = new CollisionChecker(this);
    //setting up amount of items able to be displayed to the screen at one time
    public ParentItem items[] = new ParentItem[10];
    public AssetSetter assetSetter = new AssetSetter(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void gameSetup(){
        assetSetter.setItem();
    }

    public void update(){
        player.update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Tiles
        tileManager.draw(g2);

        //Item
        for (ParentItem item : items) {
            if (item != null) {
                item.draw(g2, this);
            }
        }

        //Player
        player.draw(g2);

        g2.dispose();
    }

    @Override
    public void run() {
        int FPS = 60;
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        //setting up game loop
        while(gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
}
