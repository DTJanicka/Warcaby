package com.chess;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author: DorotaJanicka
 * @since: 02.09.2016
 */
public class Game {

    private Board board;
    private GameObject [][] tgo;
    private GameObject go;
    private GameObject pawn;
    private int xp,yp; /**wybierania pozycji na planszy*/
    private float xd, yd; /**określanie położenia planszy i pionów*/
    private boolean round; /** białe = true; czarne = false */
    private boolean firstclick; /**określam rodzaj kliknięcia 1=true, 2=false*/
    private int[] coordinates;
    private int[] index, index2;


    public Game(){
        board = new Board();
        tgo = new GameObject[8][8];
        coordinates = new int[2];
        index = new int[]{0,0};
        index2 = new int[]{0,0};
        round = true;
        firstclick = true;
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

    private GameObject checkPosition(int x, int y){ /**  sprawdzenie pozycji oraz przypisanie indeksów wybranej planszy to tablicy indeksów */
        tgo = board.send();
        for (int i = 0; i<8; i++) {
            for (int j = 0; j <8; j++) {
                xd = tgo[i][j].getX();
                yd = tgo[i][j].getY();
                if (xd < x && x < (xd + 100) && yd < y && y < (yd + 100)) {
                    System.out.println("Została kliknięta plansza " + i + j);
                    index[0] = i;
                    index[1] = j;
                    System.out.println("index[0] " + index[0]);
                    System.out.println("index[1] " + index[1]);
                    return tgo[i][j];
                }
            }
        }
        return null;
    }

    public void move(int x, int y){
        System.out.println("Firstclick: "+firstclick);
        go = checkPosition(x,y);  /** 2) sprawdzam co to za plansza */
        if (firstclick){ /** 1) dostaje wspórzędne 1kliknięcia */

            pawn = board.getPawn(go); /** 3) sprawdzam czy jest na niej pionek */
            System.out.println("Pawn: " + pawn);
            if (pawn != null || pawn.iswhite != round){
                System.out.println("iswhite: " + pawn.iswhite);
                System.out.println("round: " + round);
                if(pawn.iswhite == round){
                    if (round) {
                        if (canmovewhite(x, y)) {
                            coordinates[0] = x;
                            coordinates[1] = y;
                            index2[0] = index[0]; /** przypisuje indeksy poprzedniego ruchu do niezależnej tablicy */
                            index2[1] = index[1];
                            firstclick = false;
                        }
                    }
                    else {
                        if (canmoveblack(x, y)) {
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
            if (round) {
                if (cangowhite()) {
                    board.move(tgo[index2[0]][index2[1]], tgo[index[0]][index[1]]);
                    firstclick = true;
                    round = false;

                }
            }
            else {
                if(cangoblack()){
                    board.move(tgo[index2[0]][index2[1]], tgo[index[0]][index[1]]);
                    firstclick = true;
                    round = true;
                }
            }
        }
    }

    private boolean canmovewhite(int x, int y){ /**dostaję plansze */
        checkPosition(x,y);
        System.out.println("Funkcja canmove");
        /** index[0] = i; index[1] = j */

        System.out.println("Plansza po tablicy indeksów: "+index[0]+index[1]);
        if (index[1]==0){
            System.out.println("Po 1 ifie");
            if (board.getPawn(tgo[(index[0]+1)][1])== null || board.getPawn(tgo[(index[0]+1)][1]).iswhite != round){
                System.out.println("Może się ruszyć");
                return true;
            }
        }
        else if (index[1] >0 && index[1] <7){
            System.out.println("Po 2 ifie");
            if (board.getPawn(tgo[(index[0]+1)][(index[1]-1)])== null || board.getPawn(tgo[(index[0]+1)][(index[1]+1)])== null || board.getPawn(tgo[(index[0]+1)][(index[1]-1)]).iswhite != round || board.getPawn(tgo[(index[0]+1)][(index[1]+1)]).iswhite != round){
                System.out.println("Może się ruszyć");
                return true;
            }
        }
        else if (index[1] == 7){
            System.out.println("Po 3 ifie");
            if (board.getPawn(tgo[(index[0]+1)][6])== null || board.getPawn(tgo[(index[0]+1)][6]).iswhite != round){
                System.out.println("Może się ruszyć");
                return true;
            }
        }
        System.out.println("Nie może się ruszyć");
        return false;
    }
    private boolean canmoveblack(int x, int y){ /**dostaję plansze */
        checkPosition(x,y);
        System.out.println("Funkcja canmove");
        /** index[0] = i; index[1] = j */

        System.out.println("Plansza po tablicy indeksów: "+index[0]+index[1]);
        if (index[1]==0){
            System.out.println("Po 1 ifie");
            if (board.getPawn(tgo[(index[0]-1)][1])== null || board.getPawn(tgo[(index[0]-1)][1]).iswhite != round){
                System.out.println("Może się ruszyć");
                return true;
            }
        }
        else if (index[1] >0 && index[1] <7){
            System.out.println("Po 2 ifie");
            if (board.getPawn(tgo[(index[0]-1)][(index[1]-1)])== null || board.getPawn(tgo[(index[0]-1)][(index[1]+1)])== null || board.getPawn(tgo[(index[0]-1)][(index[1]-1)]).iswhite != round || board.getPawn(tgo[(index[0]-1)][(index[1]+1)]).iswhite != round){
                System.out.println("Może się ruszyć");
                return true;
            }
        }
        else if (index[1] == 7){
            System.out.println("Po 3 ifie");
            if (board.getPawn(tgo[(index[0]-1)][6])== null || board.getPawn(tgo[(index[0]-1)][6]).iswhite != round){
                System.out.println("Może się ruszyć");
                return true;
            }
        }
        System.out.println("Nie może się ruszyć");
        return false;
    }

    private boolean cangowhite(){ /** sprawdzam czy jest to plansza na którą mogę się ruszyć */
        System.out.println("Funkcja cango");
        System.out.println("index2[0]: "+ index2[0]+" index2[1]: "+ index2[1]);
        System.out.println("index[0]: "+index[0]+" index[1]: "+index[1]);
        if ((index2[0]+1) == index[0]){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((index2[1]-1) == index[1]) {
                System.out.println("Po drugim ifie indeksowym (index2[0]-1) == index[0])");
                return true;
            }
            else if ((index2[1]+1) ==index[1]){
                System.out.println("Po drugim ifie indeksowym (index2[0]+1) ==index[0])");
                return true;
            }
        }
        System.out.println("Coś w ifach poszło nie tak");
        return false;
    }

    private boolean cangoblack(){ /** sprawdzam czy jest to plansza na którą mogę się ruszyć */
        System.out.println("Funkcja cango");
        System.out.println("index2[0]: "+ index2[0]+" index2[1]: "+ index2[1]);
        System.out.println("index[0]: "+index[0]+" index[1]: "+index[1]);
        if ((index2[0]-1) == index[0]){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((index2[1]+1) == index[1] || (index2[1]-1) ==index[1])
                System.out.println("Po drugim ifie indeksowym");
            return true;
        }
        System.out.println("Coś w ifach poszło nie tak");
        return false;
    }


}
