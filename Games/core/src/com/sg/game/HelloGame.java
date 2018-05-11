package com.sg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HelloGame extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    Sprite imgSprite;
    float x, y, xv, yv;
    static final float MAX_VELOCITY = 100;

    @Override
    public void create() {

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        imgSprite = new Sprite(img);
    }

    @Override
    public void render() {
        move();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        imgSprite.setBounds(x, y, imgSprite.getWidth(), imgSprite.getHeight());
        imgSprite.draw(batch);
        batch.end();

    }

    float accelerate(float velocity) {
        float acceleration = 1.55f;
        velocity *= acceleration;
        if (velocity == 0) {
            velocity = 1;
        }
        if (Math.abs(velocity) > MAX_VELOCITY) {
            velocity = MAX_VELOCITY;
        }
        return velocity;
    }

    float decelerate(float velocity) {
        float deceleration = 0.95f;
        velocity *= deceleration;
        if (Math.abs(velocity) < 1) {
            velocity = 0;
        }
        return velocity;
    }

    private void move() {
        
        if(Gdx.input.isKeyPressed(Input.Keys.COMMA)) {
            imgSprite.rotate(5);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.PERIOD)) {
            imgSprite.rotate(-5);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SEMICOLON)) {
            imgSprite.flip(true, false);
            imgSprite.scale(0.1f);
        }
        

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yv = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yv = -MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xv = MAX_VELOCITY;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xv = -MAX_VELOCITY;
        }

        y = y + (yv * Gdx.graphics.getDeltaTime());
        x = x + (xv * Gdx.graphics.getDeltaTime());

        if (!Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
            yv = decelerate(yv);
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            xv = decelerate(xv);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
