package com.chess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author: Dorota Janicka
 * @since: 07.07.2016
 */
public class GameObject extends Sprite {

    protected GameObject pawn;
    protected boolean iswhite;
    protected boolean ispawn;

    public GameObject() {
    }

    public GameObject(Texture texture) {
        super(texture);
    }

    public GameObject(Texture texture, int srcWidth, int srcHeight) {
        super(texture, srcWidth, srcHeight);
    }

    public GameObject(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
    }

    public GameObject(TextureRegion region) {
        super(region);
    }

    public GameObject(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
        super(region, srcX, srcY, srcWidth, srcHeight);
    }

    public GameObject(Sprite sprite) {
        super(sprite);
    }



    public void update(float time){

    }

    public void draw(Batch batch) {
        super.draw(batch);
    }


}
