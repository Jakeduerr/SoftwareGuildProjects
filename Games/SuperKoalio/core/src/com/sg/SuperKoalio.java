package com.sg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SuperKoalio extends ApplicationAdapter {

    SpriteBatch batch;
    TextureRegion stand, jump;

    static final int WIDTH = 18;
    static final int HEIGHT = 26;

    static final int DRAW_HEIGHT = HEIGHT * 3;
    static final int DRAW_WIDTH = WIDTH * 3;

    float x, y, xv, yv;
    static final float MAX_VELOCITY = 100;
    
    boolean faceRight = true;
    boolean canJump = true;
    static final float MAX_JUMPING_VELOCITY = 700;
    static final float GRAVITY = -50;
    
    Animation walk;
    float time;
    @Override
    public void create() {
        batch = new SpriteBatch();
        Texture sheet = new Texture("koalio.png");
        TextureRegion[][] tiles = TextureRegion.split(sheet, WIDTH, HEIGHT);
        stand = tiles[0][0];
        jump = tiles[0][1];
        walk = new Animation(0.2f, tiles[0][2], tiles[0][3], tiles[0][4]);
    }

    @Override
    public void render() {
        time += Gdx.graphics.getDeltaTime();
        move();
        
        TextureRegion img;
        if(y > 0) {
            img = jump;
        } else if (xv != 0) {
            img = (TextureRegion) walk.getKeyFrame(time, true);
        } else {
            img = stand;
        }
        
        Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        
        if(faceRight) {
            batch.draw(img, x, y, DRAW_WIDTH, DRAW_HEIGHT);
        } else {
            batch.draw(img, x + DRAW_WIDTH, y, DRAW_WIDTH * -1, DRAW_HEIGHT);
        }
        //batch.draw(img, x, y, DRAW_WIDTH, DRAW_HEIGHT);
        batch.end();
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
        if (Gdx.input.isKeyPressed(Input.Keys.W) && canJump) {
            yv = MAX_JUMPING_VELOCITY;
            canJump = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yv = MAX_VELOCITY * -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xv = MAX_VELOCITY;
            faceRight = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xv = MAX_VELOCITY * -1;
            faceRight = false;
        }
        
        yv += GRAVITY;
        
        y = y + (yv * Gdx.graphics.getDeltaTime());
        x = x + (xv * Gdx.graphics.getDeltaTime());
        
        if(y < 0) {
            y = 0;
            canJump = true;
        }
        
        yv = decelerate(yv);
        xv = decelerate(xv);
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
