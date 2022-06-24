package com.example;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //Create JFrame containing gamePanel
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Blue Boy Adventures");

        //Setting up gamePanel and gameSetup, starting gameThread and displaying it all to the screen
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setVisible(true);
        window.pack();
        gamePanel.gameSetup();
        gamePanel.startGameThread();
    }
}
