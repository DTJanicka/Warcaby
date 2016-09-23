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
    private GameObject pawn;
    private int xp,yp; /**wybierania pozycji na planszy*/
    private float xd, yd; /**określanie położenia planszy i pionów*/
    private boolean round; /** białe = true; czarne = false */
    private boolean firstclick; /**określam rodzaj kliknięcia 1=true, 2=false*/
    private int[] coordinates;
    private int[] index;


    public Game(){
        board = new Board();
        tgo = new GameObject[8][8];
        coordinates = new int[2];
        index = new int[2];
        round = true;
        firstclick = true;
    }

    public void click(int x, int y){
        //bp.checkPosition(x,y);
        if(checkPosition(x,y) == null){System.out.println("Nie ma klikniętej planszy");}
    }


    public void draw(Batch batch){

        tgo = board.send();
        yp = 0;
        for(int i = 0; i <8; i++){
            xp = 0;

            for (int j = 0; j <8; j++) {

                tgo[i][j].setPosition(xp, yp);
                tgo[i][j].setSize(100,100);
                tgo[i][j].draw(batch);
                go = board.getPawn(tgo[i][j]);

                if(go != null){
                    go.setPosition(xp,yp);
                    go.setSize(100,100);
                    go.draw(batch);
                }
                xp = xp + 100;
            }
            yp = yp + 100;
        }
    }

    public GameObject checkPosition(int x, int y){
        tgo = board.send();
        for (int i = 0; i<8; i++) {
            for (int j = 0; j <8; j++) {
                xd = tgo[i][j].getX();
                yd = tgo[i][j].getY();
                System.out.println(" xp: " + xd + " yp: " +yd);

                if (xd < x && x < (xd + 100) && yd < y && y < (yd + 100)) {
                    System.out.println("Została kliknięta plansza " + i + j);
                    index[0] = i;
                    index[1] = j;
                    return tgo[i][j];
                }
            }
        }
        return null;
    }

    public void move(int x, int y){
        if (firstclick){ /** 1) dostaje wspórzędne 1kliknięcia */
            go = checkPosition(x,y); /** 2) sprawdzam co to za plansza */
            pawn = board.getPawn(go); /** 3) sprawdzam czy jest na niej pionek */
            if (pawn != null){
                if(pawn.iswhite == round){

                }
            }
        }

    }

    public boolean canmove(int x, int y){ /**dostaję plansze */

        return false;
    }

}
