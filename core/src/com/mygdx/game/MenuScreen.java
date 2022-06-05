package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import world.GameMap;

public class MenuScreen extends ScreenAdapter {

    SpriteBatch batch;
    GameMap gameMap;
    Main game;

    private final Sprite spriteBG;
    private final Sprite spriteS;
    private final Sprite spriteHS;
    private final Sprite spriteQ;
    private final Sprite spriteHQ;

    final float imgScaling = 7;
    private static final float moveSY = -20;
    private static final float moveQY = -120;

    OrthographicCamera cam;
    ShapeRenderer shapeRenderer;

    Texture background;
    Texture start;
    Texture hover_start;
    Texture quit;
    Texture hover_quit;

    public MenuScreen(Main game) {
        this.game = game;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        batch = new SpriteBatch();

        background = new Texture("menu_background.png");
        start = new Texture("start_game.png");
        hover_start = new Texture("hover_start.png");
        quit = new Texture("quit_game.png");
        hover_quit = new Texture("hover_quit.png");

        spriteS = new Sprite(start);
        spriteHS = new Sprite(hover_start);
        spriteQ = new Sprite(quit);
        spriteHQ = new Sprite(hover_quit);
        spriteBG = new Sprite(background);

        spriteBG.setCenter(0, 0);
        spriteS.setCenter(0, moveSY);
        spriteHS.setCenter(0, moveSY);
        spriteQ.setCenter(0, moveQY);
        spriteHQ.setCenter(0, moveQY);

        spriteBG.setScale(imgScaling);
        spriteS.setScale(imgScaling);
        spriteHS.setScale(imgScaling);
        spriteQ.setScale(imgScaling);
        spriteHQ.setScale(imgScaling);

        cam = new OrthographicCamera(w, h);
        cam.position.set(0, 0, 0);
    }


    @Override
    public void show () {

    }

    @Override
    public void render (float v) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_CONSTANT_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        ScreenUtils.clear(1, 0, 0, 1);

        batch.begin();
        spriteBG.draw(batch);

        float quit_width = 400;
        float x = w / 2 - quit_width / 2;
        float quit_height = 100;
        float y = h / 2 - quit_height / 2;



        if (Gdx.input.getX() < x + quit_width && Gdx.input.getX() > x && Gdx.input.getY() < y + quit_height && Gdx.input.getY() > y + 30) {
            spriteHS.draw(batch);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.setScreen(new GameScreen());
            }
        } else {
            spriteS.draw(batch);
        }

        if (Gdx.input.getX() < x + quit_width && Gdx.input.getX() > x && Gdx.input.getY() < y + quit_height * 2 && Gdx.input.getY() > y + 130) {
            spriteHQ.draw(batch);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        } else {
            spriteQ.draw(batch);
        }

        batch.setProjectionMatrix(cam.combined);
        cam.update();
        batch.end();
    }

    @Override
    public void resize (int width, int height) {
        cam.viewportWidth = width;
        cam.viewportHeight = height;
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose () {
    }
}
