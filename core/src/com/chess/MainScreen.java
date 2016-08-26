package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * author: Dorota Janicka
 * date: 07.07.2016
 */

public class MainScreen implements Screen {
    SpriteBatch batch;
    GameObject background;
    Board board;
    Sprite sprite;
    BlackPawn bp;
    float[] possition;
    int x,y;
    boolean isclick;





    @Override
    public void show() {
        board = new Board();
        batch = new SpriteBatch();
        background = new GameObject(new Texture(Gdx.files.internal("board.jpg")));
        sprite = new Sprite(new Texture(Gdx.files.internal("board.jpg")));

        bp = new BlackPawn();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        isclick = true;
    }

    @Override
    public void render(float delta) {

        batch.begin();




        /*bp.creat();*/


        if(isclick) {
            sprite.setSize(800,800);
            sprite.draw(batch);
            background.draw(batch);
            board.draw(batch);
            bp.create(batch);
        }

        isclick =bp.click(isclick);
        bp.update(batch);






        batch.end();


        //board.update(isclick);
    }


    public float[] findPossition(){
        possition = new float[2];
        x = Gdx.input.getX();
        y= Gdx.input.getY();
        possition[0] = x;
        possition[1] = 800 - y;
        return possition;
    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
