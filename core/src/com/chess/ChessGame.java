package com.chess;

import com.badlogic.gdx.Game;

public class ChessGame extends Game {

    @Override
    public void create() {
        setScreen(new MainScreen());

    }

}
