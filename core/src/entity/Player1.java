package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Main;
import world.GameMap;
import entity.EntityType;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class Player1 extends Entity {
//------------------------------------------------------------------------------------------------------------------
    // Create Player
    // Jumping (holding jumps higher)
    // Apply gravity
    // User inputs for moving
    // Draw sprite on screen
//------------------------------------------------------------------------------------------------------------------
    private static final int SPEED = 700;
    private static final float JUMP_VELOCITY = 5.5f;
    final float imgScaling = 7; // same as Map Scaling Unit

    Texture img;
    SpriteBatch batch;
//------------------------------------------------------------------------------------------------------------------
    @Override
    public void create (EntitySnapshot snapshot, EntityType type, GameMap map) {
        // creating player
        super.create(snapshot, type, map);
        img = new Texture("Player.png");
    }
//------------------------------------------------------------------------------------------------------------------
    @Override
    public void update(float deltaTime, float gravity) {
        // Jumping (Holding jumps higher)
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && grounded)
            this.velocityY += JUMP_VELOCITY * getWeight();
        else if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && !grounded && this.velocityY > 0)
            this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;

        // Apply gravity
        super.update(deltaTime, gravity);

        // Move left and right
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            moveX(-SPEED * deltaTime);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            moveX(SPEED * deltaTime);
    }
//------------------------------------------------------------------------------------------------------------------
    // Drawing sprite on screen
    @Override
    public void render(SpriteBatch batch){
        batch.draw(img, pos.x, pos.y, getWidth() * imgScaling, getHeight() * imgScaling);
    }
//------------------------------------------------------------------------------------------------------------------
}
