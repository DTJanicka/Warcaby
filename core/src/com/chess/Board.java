package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Board {
    private Texture blackQueen;
    private Texture whiteQueen;
    private Field [][] fields;
    private Pawn [] pawns;
    private Pawn pawn;
    private Field field;
    private GameObject [] queens;
    private int x;

    public Board(){
        create();
        x=0;
    }

    private void create() {
        field = new Field();
        field.create();
        pawn = new Pawn();
        pawn.create();
        fields = field.send();
        pawns = pawn.send();

        addWhitePawns();

        addBlackPawns();

        whiteQueen = new Texture(Gdx.files.internal("wqueen.jpg"));
        blackQueen = new Texture(Gdx.files.internal("bqueen.jpg"));
        queens = new GameObject[2];
        queens[0] = new GameObject(whiteQueen);
        queens[1] = new GameObject(blackQueen);
    }

    private void addWhitePawns(){
         int j = 0;
        for (int i = 0; i < 4; i++) {
            setPawn(fields[0][j], pawns[i]);
            j = j + 2;
        }
        j = 1;
        x = x+1;
        for (int i = 4; i < 8; i++) {
            setPawn(fields[1][j], pawns[i]);
            j = j + 2;
        }
    }

    private void addBlackPawns(){
        int j = 0;
        for (int i = 8; i < 12; i++) {
            setPawn(fields[6][j], pawns[i]);
            j = j + 2;
        }
        j = 1;
        for (int i = 12; i < 16; i++) {
            setPawn(fields[7][j], pawns[i]);
            j = j + 2;
        }
    }

    public void setPawn(Field field, Pawn pawn){
        field.pawn = pawn;
    }

    public Pawn getPawn(Field field){
            return field.pawn;
    }

    public void moveFigure(Field field, Field nextfield){
        pawn = getPawn(field);
        field.pawn = null;
        setPawn(nextfield, pawn);
    }

    /**
     * przesyłanie do kontrolera aktualnego modelu
     */
    public Field[][] send(){
        return fields;
    }

    /**
     * mianowanie na damkę
     */
    /*
    public void setQueen(GameObject field){
        pawn = getPawn(field);
        pawn.ispawn = false;
        if(pawn.iswhite){
            setPawn(field, queens[0]);

        }
        else {
            setPawn(field, queens[1]);
        }
    }
    */
}

