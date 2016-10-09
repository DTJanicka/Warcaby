package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Field extends GameObject{

    Pawn pawn;
    private Texture blackField;
    private Texture whiteField;
    private Field [][] fields;
    public static final int SQUARES_IN_ROW = 8;

    public Field(){

    }
    public Field(Texture texture){
        super(texture);
    }

    public void create(){
        fields = new Field[Field.SQUARES_IN_ROW][Field.SQUARES_IN_ROW];
        blackField = new Texture(Gdx.files.internal("black.jpg"));
        whiteField = new Texture(Gdx.files.internal("white.jpg"));
        for (int i = 0; i < Field.SQUARES_IN_ROW; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < Field.SQUARES_IN_ROW; j++) {
                    fields[i][j] = new Field(j % 2 == 0 ? blackField : whiteField);
                }
            } else {
                for (int j = 0; j < Field.SQUARES_IN_ROW; j++) {
                    fields[i][j] = new Field(j % 2 == 0 ? whiteField : blackField);
                }
            }
        }
    }

    public Field[][] send(){
        return fields;
    }

}
