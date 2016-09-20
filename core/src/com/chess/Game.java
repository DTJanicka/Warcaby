package com.chess;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author: DorotaJanicka
 * @since: 02.09.2016
 */
public class Game {

    Board board;
    private GameObject [][] tgo;
    private GameObject go;
    private int x,y;

    public Game(){
        board = new Board();
    }

    public void click(int x, int y){
        //bp.checkPosition(x,y);
        if(board.checkPosition(x,y) == null){System.out.println("Nie ma klikniętej planszy");}
    }


    public void draw(Batch batch){

        tgo = board.send();
        for(int i = 0; i <8; i++){
            x = 0;
            for (int j = 0; j <8; j++) {

                tgo[i][j].setPosition(x, y);
                tgo[i][j].setSize(100,100);
                tgo[i][j].draw(batch);
                go = board.getPawn(tgo[i][j]);

                if(go != null){
                    go.setPosition(x,y);
                    go.setSize(100,100);
                    go.draw(batch);
                }
                x = x + 100;
            }
            y = y + 100;
        }

    }

}
