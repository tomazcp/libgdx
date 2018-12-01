package com.killbot.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.killbot.gameobject.Bird;
import com.killbot.helper.AssetLoader;

public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    private int midPointY;
    private int gameHeight;

    public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 136, 204);

        this.midPointY = midPointY;
        this.gameHeight = gameHeight;

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    public void render(float runtime) {
        Bird bird = world.getBird();
        /**
         * 1. draw a dark background to prevent flickering
         */
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /**
         * 2. draw the filled rectangle
         */
        // tells shapeRenderer to begin drawing filled shapes
        shapeRenderer.begin(ShapeType.Filled);

        drawBackground();
        drawGrass();
        drawDirt();

        shapeRenderer.end();
        // begin sprite batch
        batcher.begin();
        // disable transparency (improves performance)
        batcher.disableBlending();
        batcher.draw(AssetLoader.bg, 0, midPointY + 23, 136, 43);

        // bird needs transparency
        batcher.enableBlending();
        batcher.draw((TextureRegion) AssetLoader.birdAnimation.getKeyFrame(runtime), bird.getX(),
                bird.getY(), bird.getWidth(), bird.getHeight());
        batcher.end();
    }

    /**
     * Draws background
     */
    private void drawBackground() {
        shapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
        shapeRenderer.rect(0, 0, 136, midPointY + 66);
    }

    /**
     * Draws grass
     */
    private void drawGrass() {
        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 66, 136, 11);
    }

    private void drawDirt() {
        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
        shapeRenderer.rect(0, midPointY + 77, 136, 52);
    }
}
