package com.chess;

import com.badlogic.gdx.graphics.g2d.Batch;

public class Game {

    private Board board;
    public CanMove canMove;
    public CanGo canGo;
    private Field [][] fields;
    private Field field;
    private Pawn pawn;
    private int xField, yField; /**wybierania pozycji na planszy*/
    private float xSquare, ySquare; /**określanie położenia planszy i pionów*/
    private boolean round; /** białe = true; czarne = false */
    private boolean firstclick; /**określam rodzaj kliknięcia 1=true, 2=false*/
    private int[] coordinates;
    private int[] index, index2;


    public Game(){
        board = new Board();
        canMove = new CanMove();
        canGo = new CanGo();
        fields = new Field[8][8];
        coordinates = new int[2];
        index = new int[]{0,0};
        index2 = new int[]{0,0};
        round = true;
        firstclick = true;
    }

    public void draw(Batch batch){

        fields = board.send();
        yField = 0;
        for(int i = 0; i <8; i++){
            xField = 0;

            for (int j = 0; j <8; j++) {

                fields[i][j].setPosition(xField, yField);
                fields[i][j].setSize(100,100);
                fields[i][j].draw(batch);
                pawn = board.getPawn(fields[i][j]);

                if(pawn != null){
                    pawn.setPosition(xField, yField);
                    pawn.setSize(100,100);
                    pawn.draw(batch);
                }
                xField = xField + 100;
            }
            yField = yField + 100;
        }
    }

    public Field checkPosition(int x, int y){ /**  sprawdzenie pozycji oraz przypisanie indeksów wybranej planszy to tablicy indeksów */
        fields = board.send();
        for (int i = 0; i<8; i++) {
            for (int j = 0; j <8; j++) {
                xSquare = fields[i][j].getX();
                ySquare = fields[i][j].getY();
                if (xSquare < x && x < (xSquare + 100) && ySquare < y && y < (ySquare + 100)) {
                    System.out.println("Została kliknięta plansza " + i + j);
                    index[0] = i;
                    index[1] = j;
                    System.out.println("index[0] " + index[0]);
                    System.out.println("index[1] " + index[1]);
                    return fields[i][j];
                }
            }
        }
        return null;
    }

    public void move(int x, int y){
        System.out.println("Firstclick: "+firstclick);
        field = checkPosition(x,y);  /** 2) sprawdzam co to za plansza */
        if (firstclick){ /** 1) dostaje wspórzędne 1kliknięcia */

            pawn = board.getPawn(field); /** 3) sprawdzam czy jest na niej pionek */
            System.out.println("Pawn: " + pawn);
            if (pawn != null || pawn.isWhite != round){
                System.out.println("iswhite: " + pawn.isWhite);
                System.out.println("round: " + round);
                if(pawn.isWhite == round){
                    if (round) {
                        if (canMove.white(index[0], index[1])) {
                            coordinates[0] = x;
                            coordinates[1] = y;
                            index2[0] = index[0]; /** przypisuje indeksy poprzedniego ruchu do niezależnej tablicy */
                            index2[1] = index[1];
                            firstclick = false;
                        }
                    }
                    else {
                        if (canMove.black(index[0],index[1])) {
                            coordinates[0] = x;
                            coordinates[1] = y;
                            index2[0] = index[0]; /** przypisuje indeksy poprzedniego ruchu do niezależnej tablicy */
                            index2[1] = index[1];
                            firstclick = false;
                        }
                    }
                }
            }
            firstclick = false;
        }
        else {  /** dostaje współrzędne 2 kliknięcia */
            System.out.println("drugie kliknięcie");
            System.out.println("index2[0]: "+ index2[0]+" index2[1]: "+ index2[1]);
            System.out.println("index[0]: "+index[0]+" index[1]: "+index[1]);
            if (round) {
                if (canGo.white(index2[0], index2[1], index[0], index[1], fields)) {
                    if (index[0]==7){
                        //board.setQueen(fields[index2[0]][index2[1]]);
                        board.moveFigure(fields[index2[0]][index2[1]], fields[index[0]][index[1]]);
                        firstclick = true;
                        round = false;
                    }
                    else {
                        board.moveFigure(fields[index2[0]][index2[1]], fields[index[0]][index[1]]);
                        firstclick = true;
                        round = false;
                    }

                }
            }
            else {
                if(canGo.black(index2[0], index2[1], index[0], index[1], fields)){
                    if (index[0]==0){

                        board.moveFigure(fields[index2[0]][index2[1]], fields[index[0]][index[1]]);
                        //board.setQueen(fields[index[0]][index[1]]);
                        firstclick = true;
                        round = true;
                    }
                    else {
                        board.moveFigure(fields[index2[0]][index2[1]], fields[index[0]][index[1]]);
                        firstclick = true;
                        round = true;
                    }
                }
            }
        }
    }
}
