package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author: DorotaJanicka
 * @since: 19.04.2016
 */
public abstract class Piece extends GameObject {
    Texture bpawn;
    Texture wpawn;
    GameObject [] blackpawn;
    GameObject [] whitepawn;


    public void creat(){

        blackpawn = new GameObject[8];
        whitepawn = new GameObject[8];


        bpawn = new Texture(Gdx.files.internal("bpawn.jpg"));

        wpawn = new Texture(Gdx.files.internal("wpawn.jpg"));


        for (int i = 0; i<8; i++){
            blackpawn[i] = new GameObject(bpawn);
        }

        for (int i = 0; i<8; i++){
            whitepawn[i] = new GameObject(wpawn);
        }

    }


    @Override
    public void update(float time) {
    }

    public void draw(Batch batch, GameObject go, float x, float y){}

    public abstract GameObject checkPosition();


}
