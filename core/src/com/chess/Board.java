package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * author: dorota
 * date: 07.07.2016
 */

public class Board {
    Texture black;
    Texture white;
    Texture greenframe;
    Texture redframe;
    int x,y;
    float xp, yp;
    GameObject [][] tgo;
    //MainScreen ms;


    public void creat(){
        //ms = new MainScreen();
        tgo = new GameObject[8][8];
        black = new Texture(Gdx.files.internal("black.jpg"));
        white = new Texture(Gdx.files.internal("white.jpg"));
        greenframe = new Texture(Gdx.files.internal("gframe.jpg"));
        redframe = new Texture(Gdx.files.internal("frame.jpg"));
        for (int i = 0; i<8; i++){
            if (i%2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        tgo[i][j] = new GameObject(black);
                    }
                    else {
                        tgo[i][j] = new GameObject(white);
                    }
                }
            }
            else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        tgo[i][j] = new GameObject(white);
                    }
                    else {
                        tgo[i][j] = new GameObject(black);
                    }
                }
            }

        }
    }



    public void update(boolean bool){
        if (bool){}
    }

    public void draw(Batch batch) {
        creat();
        x = 0;
        y = 0;

        for (int i = 0; i<8; i++) {
            x = 0;
            for (int j = 0; j <8; j++) {

                tgo[i][j].setPosition(x, y);
                tgo[i][j].setSize(100,100);
                tgo[i][j].draw(batch);
                x = x + 100;
            }
            y = y + 100;
        }

    }

    public void setGrid(){

    }
}

