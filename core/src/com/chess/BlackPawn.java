package com.chess;

import com.badlogic.gdx.Gdx;
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
        for(int j = 1; j <2; j++) {
            if(j%2==1){
            for (int i = 0; i < 4; i++) {
            /*pawn[i].setPosition(x, 100);
            pawn[i].setSize(100,100);
            pawn[i].draw(batch);*/

                    x=0;
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

    public GameObject checkPosition()
    {
        float[] position = ms.findPossition();
        for(int i = 0; i < 8; i++) {
            xp = blackpawn[i].getX();
            yp = blackpawn[i].getY();
            System.out.println("x1: " + xp);
            System.out.println("x2: " + (xp + 100));
            System.out.println("y1: " + yp);
            System.out.println("y2: " + (yp + 100));
            System.out.println("position0: " + position[0]);
            System.out.println("position1: " + position[1]);
            if (xp < position[0] && position[0] < (xp + 100) && yp < position[1] && position[1] < (yp + 100)) {
                System.out.println("Został kliknięty pionek " + i);
                return blackpawn[i];
            }
        }
        return null;
    }

    public void move(Batch batch){
        if(checkPosition() != null){
            isclick = Gdx.input.justTouched();
            if (isclick){
                float[] position = ms.findPossition();
                p = checkPosition();
                p.setPosition(position[0], position[1]);
                p.setSize(100,100);
                p.draw(batch);
            }
        }
}

    public void update(Batch batch) {
        isclick = Gdx.input.justTouched();
        if (isclick){
            move(batch);
        }
    }

    public boolean click(boolean bool){
        bool = false;
        return bool;

    }

}
