package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import world.GameMap;
import world.TiledGameMap;

public class GameScreen extends ScreenAdapter {
//------------------------------------------------------------------------------------------------------------------
    // create width, height, camera, batch and gameMap
    // render screen utilities
    // render camera to follow the player
    // dispose of the batch and the gameMap
//------------------------------------------------------------------------------------------------------------------
    SpriteBatch batch;
    OrthographicCamera cam;
    GameMap gameMap;

    float PlayerPosX;
    float PlayerPosY;

    float deltaX, deltaY;
    float w, h;
//------------------------------------------------------------------------------------------------------------------
    public GameScreen() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        gameMap = new TiledGameMap();
    }
//------------------------------------------------------------------------------------------------------------------
    @Override
    public void render (float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_CONSTANT_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Vector3 pos = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        ScreenUtils.clear(1, 0, 0, 1);

        // Resizable Window
        this.cam = new OrthographicCamera();
        this.cam.setToOrtho(false);
        this.batch.setProjectionMatrix(cam.combined);
        this.cam.position.set(gameMap.PlayerPosX + 250, gameMap.PlayerPosY + 200, 0);
        this.cam.update();

        this.gameMap.update(Gdx.graphics.getDeltaTime());
        this.gameMap.render(cam, batch);
    }
//------------------------------------------------------------------------------------------------------------------
    @Override
    public void resize (int width, int height) {
        cam.viewportWidth = width;
        cam.viewportHeight = height;
    }
    @Override
    public void dispose () {
        this.batch.dispose();
        this.gameMap.dispose();
    }
    @Override
    public void hide () {
        super.hide();
    }
//------------------------------------------------------------------------------------------------------------------
}
