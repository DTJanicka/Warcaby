package com.chess;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author: DorotaJanicka
 */
public class Game {

    private Board board;
    public CanMove canMove;
    public CanGo canGo;
    private GameObject [][] fields;
    private GameObject field;
    private GameObject pawn;
    private int xp,yp; /**wybierania pozycji na planszy*/
    private float xd, yd; /**określanie położenia planszy i pionów*/
    private boolean round; /** białe = true; czarne = false */
    private boolean firstclick; /**określam rodzaj kliknięcia 1=true, 2=false*/
    private int[] coordinates;
    private int[] index, index2;


    public Game(){
        board = new Board();
        canMove = new CanMove();
        canGo = new CanGo();
        fields = new GameObject[8][8];
        coordinates = new int[2];
        index = new int[]{0,0};
        index2 = new int[]{0,0};
        round = true;
        firstclick = true;
    }

    public void draw(Batch batch){

        fields = board.send();
        yp = 0;
        for(int i = 0; i <8; i++){
            xp = 0;

            for (int j = 0; j <8; j++) {

                fields[i][j].setPosition(xp, yp);
                fields[i][j].setSize(100,100);
                fields[i][j].draw(batch);
                pawn = board.getPawn(fields[i][j]);

                if(pawn != null){
                    pawn.setPosition(xp,yp);
                    pawn.setSize(100,100);
                    pawn.draw(batch);
                }
                xp = xp + 100;
            }
            yp = yp + 100;
        }
    }

    public GameObject checkPosition(int x, int y){ /**  sprawdzenie pozycji oraz przypisanie indeksów wybranej planszy to tablicy indeksów */
        fields = board.send();
        for (int i = 0; i<8; i++) {
            for (int j = 0; j <8; j++) {
                xd = fields[i][j].getX();
                yd = fields[i][j].getY();
                if (xd < x && x < (xd + 100) && yd < y && y < (yd + 100)) {
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
            if (pawn != null || pawn.iswhite != round){
                System.out.println("iswhite: " + pawn.iswhite);
                System.out.println("round: " + round);
                if(pawn.iswhite == round){
                    if (round) {
                        if (canMove.White(index[0], index[1])) {
                            coordinates[0] = x;
                            coordinates[1] = y;
                            index2[0] = index[0]; /** przypisuje indeksy poprzedniego ruchu do niezależnej tablicy */
                            index2[1] = index[1];
                            firstclick = false;
                        }
                    }
                    else {
                        if (canMove.Black(index[0],index[1])) {
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
                if (canGo.White(index2[0], index2[1], index[0], index[1], fields)) {
                    if (index[0]==7){
                        board.setQuenn(fields[index2[0]][index2[1]]);
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
                if(canGo.Black(index2[0], index2[1], index[0], index[1], fields)){
                    if (index[0]==0){

                        board.moveFigure(fields[index2[0]][index2[1]], fields[index[0]][index[1]]);
                        board.setQuenn(fields[index[0]][index[1]]);
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

    /**
    private boolean canmovewhite(int x, int y){ /**dostaję plansze
        checkPosition(x,y);
        System.out.println("Funkcja canmovewhite");
        /** index[0] = i; index[1] = j

        System.out.println("Plansza po tablicy indeksów: "+index[0]+index[1]);
        if (index[1]==0){
            System.out.println("Po 1 ifie");
            if (board.getPawn(fields[(index[0]+1)][1])== null){
                System.out.println("Może się ruszyć");
                return true;
            }
            else if(board.getPawn(fields[(index[0]+2)][(index[1]+2)]) ==null) {
                System.out.println("Wchodzę do pętli: else if(index2[0]+2==index[0] && index2[1]+2==index[1])");
                if(board.getPawn(fields[(index[0]+1)][1])!= null){
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        }
        else if (index[1] >0 && index[1] <7){
            System.out.println("Po 2 ifie");
            if (board.getPawn(fields[(index[0]+1)][(index[1]-1)])== null || board.getPawn(fields[(index[0]+1)][(index[1]+1)])== null){
                System.out.println("Może się ruszyć");
                return true;
            }
            else if(board.getPawn(fields[(index[0]+2)][(index[1]-2)])== null){
                System.out.println("Wchodzę do pętli:  else if(board.getPawn(tgo[(index[0]+2)][(index[1]-2)])== null)");
                if (board.getPawn(fields[(index[0]+1)][(index[1]-1)])!= null){
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
            else if(board.getPawn(fields[(index[0]+2)][(index[1]+2)])== null){
                System.out.println("Wchodzę do pętli:  else if(board.getPawn(tgo[(index[0]+2)][(index[1]-2)])== null)");
                if (board.getPawn(fields[(index[0]+1)][(index[1]+1)])!= null){
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        }
        else if (index[1] == 7){
            System.out.println("Po 3 ifie");
            if (board.getPawn(fields[(index[0]+1)][6])== null ){
                System.out.println("Może się ruszyć");
                return true;
            }
            else if(board.getPawn(fields[(index[0]+2)][(index[1]-2)]) ==null){
                if(board.getPawn(fields[(index[0]+1)][6])!= null){
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        }
        System.out.println("Nie może się ruszyć");
        return false;
    }
    private boolean canmoveblack(int x, int y){ /**dostaję plansze
        checkPosition(x,y);
        System.out.println("Funkcja canmoveblack");
        /** index[0] = i; index[1] = j

        System.out.println("Plansza po tablicy indeksów: "+index[0]+index[1]);
        if (index[1]==0){
            System.out.println("Po 1 ifie");
            if (board.getPawn(fields[(index[0]-1)][1])== null ){
                System.out.println("Może się ruszyć");
                return true;
            }
            else if(board.getPawn(fields[(index[0]-2)][(index[1]+2)]) ==null) {
                System.out.println("Wchodzę do pętli: else if(index2[0]-2==index[0] && index2[1]+2==index[1])");
                if(board.getPawn(fields[(index[0]-1)][1])!= null){
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        }
        else if (index[1] >0 && index[1] <7){
            System.out.println("Po 2 ifie");
            if (board.getPawn(fields[(index[0]-1)][(index[1]-1)])== null || board.getPawn(fields[(index[0]-1)][(index[1]+1)])== null){
                System.out.println("Może się ruszyć");
                return true;
            }
            else if(board.getPawn(fields[(index[0]-2)][(index[1]-2)])== null){
                System.out.println("Wchodzę do pętli:  else if(board.getPawn(tgo[(index[0]-2)][(index[1]-2)])== null)");
                if (board.getPawn(fields[(index[0]-1)][(index[1]-1)])!= null){
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
            else if(board.getPawn(fields[(index[0]-2)][(index[1]+2)])== null){
                System.out.println("Wchodzę do pętli:  else if(board.getPawn(tgo[(index[0]-2)][(index[1]-2)])== null)");
                if (board.getPawn(fields[(index[0]-1)][(index[1]+1)])!= null){
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        }
        else if (index[1] == 7){
            System.out.println("Po 3 ifie");
            if (board.getPawn(fields[(index[0]-1)][6])== null){
                System.out.println("Może się ruszyć");
                return true;
            }
            else if(board.getPawn(fields[(index[0]-2)][(index[1]-2)]) ==null){
                if(board.getPawn(fields[(index[0]-1)][6])!= null){
                    System.out.println("Można wykonać bicie");
                    return true;
                }
            }
        }
        System.out.println("Nie może się ruszyć");
        return false;
    }


    private boolean cangowhite(){  sprawdzam czy jest to plansza na którą mogę się ruszyć
        System.out.println("Funkcja cangowhite");
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
        else if(index2[0]+2==index[0]){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((index2[1]-2) == index[1]) {
                System.out.println("Po drugim ifie indeksowym (index2[0]-2) == index[0])");
                fields[(index2[0]+1)][(index2[1]-1)].pawn=null;
                return true;
            }
            else if ((index2[1]+2) ==index[1]){
                System.out.println("Po drugim ifie indeksowym (index2[0]+2) ==index[0])");
                fields[(index2[0]+1)][(index2[1]+1)].pawn=null;
                return true;
            }
        }
        System.out.println("Coś w ifach poszło nie tak");
        return false;
    }

    private boolean cangoblack(){ /** sprawdzam czy jest to plansza na którą mogę się ruszyć
        System.out.println("Funkcja cangoblack");
        System.out.println("index2[0]: "+ index2[0]+" index2[1]: "+ index2[1]);
        System.out.println("index[0]: "+index[0]+" index[1]: "+index[1]);
        if ((index2[0]-1) == index[0]){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((index2[1]+1) == index[1] || (index2[1]-1) ==index[1])
                System.out.println("Po drugim ifie indeksowym");
            return true;
        }
        else if(index2[0]-2==index[0]){
            System.out.println("Po pierwszym ifie indeksowym");
            if ((index2[1]-2) == index[1]) {
                System.out.println("Po drugim ifie indeksowym (index2[0]-2) == index[0])");
                fields[(index2[0]-1)][(index2[1]-1)].pawn=null;

                return true;
            }
            else if ((index2[1]+2) ==index[1]){
                System.out.println("Po drugim ifie indeksowym (index2[0]+2) ==index[0])");
                fields[(index2[0]-1)][(index2[1]+1)].pawn=null;
                return true;
            }
        }

        System.out.println("Coś w ifach poszło nie tak");
        return false;
    }
*/

}
