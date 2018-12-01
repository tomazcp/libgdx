package com.killbot.gameobject;

import com.badlogic.gdx.math.Vector2;

public class Bird {

    public static final float X_POS = 33;
    public static final int WIDTH = 17;
    public static final int HEIGHT = 12;


    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    public Bird(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
    }

    public void update(float delta) {
        // add our scaled acceleration vector
        velocity.add(acceleration.cpy().scl(delta)); // gives us our new velocity
        if (velocity.y > 200) {
            velocity.y = 200;
        }
        // add the updated scaled velocity to the bird's position
        // this gives us our new position
        position.add(velocity.cpy().scl(delta));
    }

    public void onClick() {
        velocity.y = -140;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }
}
