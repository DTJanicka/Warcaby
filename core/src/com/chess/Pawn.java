package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Pawn extends GameObject{

    public Pawn(){

    }
    public Pawn(Texture texture){
        super(texture);
    }

    Pawn []pawns;
    private Texture blackPawn;
    private Texture whitePawn;
    private Texture blackQueen;
    private Texture whiteQueen;
    public boolean isWhite;
    public boolean isPawn;

    public void create(){
        pawns = new Pawn[16];
        blackPawn = new Texture(Gdx.files.internal("bpawn.jpg"));
        whitePawn = new Texture(Gdx.files.internal("wpawn.jpg"));
        for (int i = 0; i < 16; i++) {
            if (i < 8) {

                pawns[i] = new Pawn(whitePawn);
                pawns[i].isWhite = true;
                pawns[i].isPawn = true;

            } else {

                pawns[i] = new Pawn(blackPawn);
                pawns[i].isWhite = false;
                pawns[i].isPawn = true;
            }
        }

    }

    public Pawn[]send(){
        return pawns;
    }


}
