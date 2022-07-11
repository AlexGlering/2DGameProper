package com.example;

public class EventHandler {

    GamePanel gamePanel;
    EventRect[][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        //Setting small event trigger in the middle of tiles, Making it possible to trigger events where necessary.
        eventRect = new EventRect[gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];

        int col = 0;
        int row = 0;
        while(col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()){
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if(col == gamePanel.getMaxWorldCol()){
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent(){
        //Check if player is more than one tile away from last event.
        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gamePanel.getTileSize()){
            canTouchEvent = true;
        }

        if(canTouchEvent){
            if(hit(27, 16, "right")){squirrelAttack(27, 16, gamePanel.dialogueState);}
            if(hit(23, 12, "up")){healingPool(23, 12, gamePanel.dialogueState);}
        }
    }

    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;

        //getting player position
        gamePanel.player.collisionArea.x = gamePanel.player.worldX + gamePanel.player.collisionArea.x;
        gamePanel.player.collisionArea.y = gamePanel.player.worldY + gamePanel.player.collisionArea.y;

        //getting event trigger position
        eventRect[col][row].x = col* gamePanel.getTileSize() + eventRect[col][row].x;
        eventRect[col][row].y = row* gamePanel.getTileSize() + eventRect[col][row].y;

        //checking for collision, event only happens if eventDone returns false
        if(gamePanel.player.collisionArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone){
            if(gamePanel.player.direction.contentEquals(reqDirection) ||
            reqDirection.contentEquals("any")){
                hit = true;

                //record player position after hit
                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }

        //after checking for collision, reset x and y on eventRect and collisionArea
        gamePanel.player.collisionArea.x = gamePanel.player.collisionAreaDefaultX;
        gamePanel.player.collisionArea.y = gamePanel.player.collisionAreaDefaultY;
        eventRect[col][row].x =  eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y =  eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void squirrelAttack(int col, int row, int gameState){
        gamePanel.gameState = gameState;
        gamePanel.ui.currentDialogue = "You got bitten by an angry squirrel./nHe ran away before you could get your revenge.";
        gamePanel.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
        gamePanel.playSFX(5);
    }

    public void healingPool(int col, int row,int gameState){

        if(gamePanel.keyHandler.enterPressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialogue = "You feel refreshed. Life was restored.";
            gamePanel.player.life = gamePanel.player.maxLife;
            gamePanel.player.attackCanceled = true;
            gamePanel.playSFX(2);
        }
    }

    public void teleport(int col, int row,int gameState){
        gamePanel.gameState = gameState;
        gamePanel.player.worldX = gamePanel.getTileSize()*37; //set to specific tile
        gamePanel.player.worldY = gamePanel.getTileSize()*10; //set to specific tile
    }
}
