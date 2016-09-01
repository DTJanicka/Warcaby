package com.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * author: Dorota Janicka
 * date: 07.07.2016
 */

public class MainScreen implements Screen, InputProcessor {
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

        Gdx.input.setInputProcessor(this);

        isclick = true;

    }

    @Override
    public void render(float delta) {

        batch.begin();

        sprite.setSize(800,800);
        sprite.draw(batch);
        background.draw(batch);
        board.draw(batch);
        bp.create(batch);

        /*bp.creat();*/








        batch.end();


        //board.update(isclick);
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
        batch.dispose();

    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {


            System.out.println("Pozycja 2: " +screenX + " , "+screenY);

        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
