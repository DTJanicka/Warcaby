package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/**
 * author: dorota
 */

public class Board {
    private Texture blackField;
    private Texture whiteField;
    private Texture blackpawn;
    private Texture whitepawn;
    private Texture blackqueen;
    private Texture whitequeen;
    private GameObject [][] fields;
    private GameObject [] pawns;
    private GameObject pawn;
    private GameObject [] queens;
    private int j;
    private int x;


    public Board(){
        create();
        x=0;
    }


    private void create() {
        fields = new GameObject[8][8];
        blackField = new Texture(Gdx.files.internal("black.jpg"));
        whiteField = new Texture(Gdx.files.internal("white.jpg"));
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        fields[i][j] = new GameObject(blackField);

                    } else {
                        fields[i][j] = new GameObject(whiteField);

                    }
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        fields[i][j] = new GameObject(whiteField);

                    } else {
                        fields[i][j] = new GameObject(blackField);
                    }
                }
            }
        }
        pawns = new GameObject[16];
        blackpawn = new Texture(Gdx.files.internal("bpawn.jpg"));
        whitepawn = new Texture(Gdx.files.internal("wpawn.jpg"));
        for (int i = 0; i < 16; i++) {
            if (i < 8) {

                pawns[i] = new GameObject(whitepawn);
                pawns[i].iswhite = true;
                pawns[i].ispawn = true;

            } else {

                pawns[i] = new GameObject(blackpawn);
                pawns[i].iswhite = false;
                pawns[i].ispawn = true;

            }
        }

        /** dodanie białych pionów do planszy */
        addWhitePawns();
        /** dodanie czarnych pionów do planszy */
        addBlackPawns();

        whitequeen = new Texture(Gdx.files.internal("wqueen.jpg"));
        blackqueen = new Texture(Gdx.files.internal("bqueen.jpg"));
        queens = new GameObject[2];
        queens[0] = new GameObject(whitequeen);
        queens[1] = new GameObject(blackqueen);
    }

    private void addWhitePawns(){
        j = 0;
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
        j = 0;
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

    public void setPawn(GameObject field, GameObject pawn){
        field.pawn = pawn;
    }

    public GameObject getPawn(GameObject field){
            return field.pawn;
    }

    /**funkcja rusz pionkiem*/
    public void moveFigure(GameObject field, GameObject nextfield){
        pawn = getPawn(field);
        field.pawn = null;
        setPawn(nextfield, pawn);
    }

    /**przesyłanie do kontrolera aktualnego modelu*/
    public GameObject[][] send(){
        return fields;
    }

    /**mianowanie na damkę*/
    public void setQuenn(GameObject field){
        pawn = getPawn(field);
        pawn.ispawn = false;
        if(pawn.iswhite){
            pawn = null;
            pawn = queens[0];
        }
        else {
            pawn = null;
            pawn = queens[1];
        }
    }
}

