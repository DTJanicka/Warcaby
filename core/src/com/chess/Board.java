package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/**
 * author: dorota
 * date: 07.07.2016
 */

public class Board {
    private Texture black;
    private Texture white;
    private Texture bpawn;
    private Texture wpawn;
    private int x,y;
    private float xp, yp;
    private GameObject [][] tgo;
    private GameObject [] pawn;
    private GameObject p;
    private int j;


    public Board(){
        creat();
        x=0;
    }


    private void creat() {
        tgo = new GameObject[8][8];
        black = new Texture(Gdx.files.internal("black.jpg"));
        white = new Texture(Gdx.files.internal("white.jpg"));
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        tgo[i][j] = new GameObject(black);

                    } else {
                        tgo[i][j] = new GameObject(white);

                    }
                }
            } else {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        tgo[i][j] = new GameObject(white);
                    } else {
                        tgo[i][j] = new GameObject(black);
                    }
                }
            }
        }
        pawn = new GameObject[16];
        bpawn = new Texture(Gdx.files.internal("bpawn.jpg"));
        wpawn = new Texture(Gdx.files.internal("wpawn.jpg"));
        for (int i = 0; i < 16; i++) {
            if (i < 8) {

                pawn[i] = new GameObject(wpawn);
                pawn[i].iswhite = true;
                pawn[i].ispawn = true;

            } else {

                pawn[i] = new GameObject(bpawn);
                pawn[i].iswhite = false;
                pawn[i].ispawn = true;

            }
        }

        /** dodanie białych pionów do planszy */
        j = 0;
        for (int i = 0; i < 4; i++) {
            setPawn(tgo[0][j], pawn[i]);


            j = j + 2;
        }
        j = 1;
        for (int i = 4; i < 8; i++) {
            setPawn(tgo[1][j], pawn[i]);

            j = j + 2;
        }

        /** dodanie czarnych pionów do planszy */
        j = 0;
        for (int i = 8; i < 12; i++) {
            setPawn(tgo[6][j], pawn[i]);
            j = j + 2;
        }
        j = 1;
        for (int i = 12; i < 16; i++) {
            setPawn(tgo[7][j], pawn[i]);
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
    public void move(GameObject field, GameObject nextfield){
        p = getPawn(field);
        field.pawn = null;
        setPawn(nextfield, p);
    }

    /**przesyłanie do kontrolera aktualnego modelu*/
    public GameObject[][] send(){
        return tgo;
    }


}

