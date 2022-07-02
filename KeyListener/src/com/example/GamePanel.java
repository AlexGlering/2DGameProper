package com.example;

import Entity.Entity;
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

    public int getTileSize() {return tileSize;}
    public int getScreenWidth() {return screenWidth;}
    public int getScreenHeight() {return screenHeight;}

    //World settings
    final int maxWorldCol = 50;
    final int maxWorldRow = 50;

    public int getMaxWorldCol() {return maxWorldCol;}
    public int getMaxWorldRow() {return maxWorldRow;}


   //System settings
    TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound sfx = new Sound();
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //Player, NPC & Items
    public ParentItem[] items = new ParentItem[10];
    public Player player = new Player(this, keyHandler);
    public Entity[] npc = new Entity[10];

    //Game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public  final int dialogueState = 3;
    public final int titleState = 0;

    //Constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void gameSetup(){
        assetSetter.setItem();
        assetSetter.setNPC();
        //playMusic(0);
        gameState = titleState;
    }

    public void update(){
        if(gameState == playState){
            //player
            player.update();
            //npc
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    npc[i].update();
                }
            }
        }
        if(gameState == pauseState){
            //nothing
        }

    }

    //Drawing objects to the screen
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Debug
        long drawStart = 0;
        if(keyHandler.checkDrawTime){
            drawStart = System.nanoTime();
        }

        //Tile screen
        if(gameState == titleState){
        ui.draw(g2);
        }
        //others
        else{
            //Tiles
            tileManager.draw(g2);

            //Item
            for (ParentItem item : items) {
                if (item != null) {
                    item.draw(g2, this);
                }
            }

            //NPC
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){
                    npc[i].draw(g2);
                }
            }

            //Player
            player.draw(g2);

            //UI
            ui.draw(g2);
        }

        //Debug
        if(keyHandler.checkDrawTime){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.WHITE);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose();
    }

    //Sounds & Music
    public void playMusic(int index){
        music.setFile(index);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSFX(int index){
        sfx.setFile(index);
        sfx.play();
    }


    //Game loop
    @Override
    public void run() {
        int FPS = 60;
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

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

    //Staring game loop thread
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
}
