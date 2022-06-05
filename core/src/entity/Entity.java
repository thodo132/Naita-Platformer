package entity;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import world.GameMap;

import java.util.HashMap;

public abstract class Entity {

    protected Vector2 pos;
    protected EntityType type;
    protected float velocityY = 0.0f;
    protected GameMap map;
    protected boolean grounded = false;

    SpriteBatch batch;

    public void create (EntitySnapshot snapshot, EntityType type, GameMap map) {
        this.pos = new Vector2(snapshot.getX(), snapshot.getY());
        this.type = type;
        this.map = map;
    }

    public void update(float deltaTime, float gravity) {
        float newY = this.pos.y;
        this.velocityY += gravity * deltaTime * this.getWeight();
        newY += this.velocityY * deltaTime;
        if (this.map.doesRectCollideWithMap(this.pos.x, newY, this.getWidth(), this.getHeight())) {
            if (this.velocityY < 0.0F) {
                this.pos.y = (float) Math.floor((double) this.pos.y);
                this.grounded = true;
            }
            this.velocityY = 0.0F;
        } else {
            this.pos.y = newY;
            this.grounded = false;
        }
    }

    public abstract void render(SpriteBatch batch);

    protected void moveX(float amount) {
        float newX = this.pos.x + amount;
        if (!this.map.doesRectCollideWithMap(newX, this.pos.y, this.getWidth(), this.getHeight())) {
            this.pos.x = newX;
        }
        if (pos.x < 300) {
            pos.x = 300;
        }
        if (pos.x > 44000) {
            pos.x = 44000;
        }
        System.out.println(pos.x);
    }

    public EntitySnapshot getSaveSnapshot () {
        return new EntitySnapshot(type.getId(), pos.x, pos.y);
    }
    public Vector2 getPos () {
        return this.pos;
    }
    public float getX () {
        return this.pos.x;
    }
    public float getY () {
        return this.pos.y;
    }

    public EntityType getType () {
        return this.type;
    }

    public boolean isGrounded () {
        return this.grounded;
    }

    public int getWidth () {
        return this.type.getWidth();
    }

    public int getHeight () {
        return this.type.getHeight();
    }
    public float getWeight () {
        return this.type.getWeight();
    }
}