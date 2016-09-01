package com.chess;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author: DorotaJanicka
 * @since: 07.07.2016
 */
public class BlackPawn extends Piece {
    GameObject p;
    int x, y;
    float xp, yp;
    MainScreen ms;
    boolean isclick;

    public BlackPawn(){
        ms = new MainScreen();
        creat();

    }




    public GameObject getPawn(int i){
        p = blackpawn[i];
        return p;
    }

    public void create(Batch batch){

        y= 0;
        for(int j = 1; j <=2; j++) {
            if(j==1){
                x=0;
            for (int i = 0; i < 4; i++) {
            /*pawn[i].setPosition(x, 100);
            pawn[i].setSize(100,100);
            pawn[i].draw(batch);*/


                    draw(batch, blackpawn[i], x, y);
                    x = x + 200;
                }

            }
            else {
                x=100;
                for (int i = 0; i < 4; i++) {
                    draw(batch, blackpawn[i], x, y);
                    x = x + 200;
                }
            }
            y = y +100;
        }
    }

    public void draw(Batch batch, GameObject go, float x, float y){
        go.setPosition(x, y);
        go.setSize(100,100);
        go.draw(batch);

    }


    public GameObject checkPosition(int x, int y)
    {

        for(int i = 0; i < 8; i++) {
            xp = blackpawn[i].getX();
            yp = blackpawn[i].getY();
            System.out.println("x1: " + xp);
            System.out.println("x2: " + (xp + 100));
            System.out.println("y1: " + yp);
            System.out.println("y2: " + (yp + 100));
            System.out.println("position0: " + x);
            System.out.println("position1: " + y);
            if (xp < x && x < (xp + 100) && yp < y && y < (yp + 100)) {
                System.out.println("Został kliknięty pionek " + i);
                return blackpawn[i];
            }
        }
        return null;
    }



    public void move(Batch batch, int x){
        /*if(checkPosition() != null){

            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){



                p = checkPosition();
                if(x>(xp+100)) {
                    moveLeft(p, batch);
                }

                /*p.setPosition(position[0], position[1]);
                p.setSize(100,100);
                p.draw(batch);
            }
        }*/
    }

    public void moveLeft(GameObject p, Batch batch){
        p.setPosition(xp+100, yp+100);
        p.setSize(100,100);
        p.draw(batch);
    }

    public void moveRight() {

    }

    public void update(Batch batch) {




          //  move(batch);

    }

    public boolean click(boolean bool){
        bool = false;
        return bool;

    }

}
