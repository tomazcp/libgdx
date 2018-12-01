package com.killbot.gameworld;

import com.killbot.gameobject.Bird;

public class GameWorld {

    private Bird bird;

    public GameWorld(int midPointY) {
        bird = new Bird(Bird.X_POS, midPointY - 5, Bird.WIDTH, Bird.HEIGHT);
    }

    public void update(float delta) {
        bird.update(delta);
    }

    public Bird getBird() {
        return bird;
    }
}
