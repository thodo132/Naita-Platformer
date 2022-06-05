package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entity.Entity;
import entity.EntityLoader;
import entity.Player1;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;

public abstract class GameMap extends ScreenAdapter {

    protected ArrayList<Entity> entities;

    public float PlayerPosX;
    public float PlayerPosY;

    public GameMap() {
        entities = new ArrayList<Entity>();
        entities.addAll(EntityLoader.loadEntities("entities", this, entities));
    }

    public void render (OrthographicCamera cam, SpriteBatch batch) {
        for (Entity entity : entities) {
            entity.render(batch);
            PlayerPosX = entity.getX();
            PlayerPosY = entity.getY();
        }
    }

    public void update (float delta) {
        for (Entity entity : entities) {
            entity.update(delta, -9.8f);
        }
    }

    public void dispose () {
    }

    // Tile by pixel position at a specific layer in the world
    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
    }

     //Tile at coordinates of specific layers in the world
     public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    // Collision

    public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
        if (x < 0 || y < 0) // add: || x + width > getPixelWidth() || y + height > getPixelHeight()
            return true;

        for (int row = (int) (y / TileType.TILE_SIZE); row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) { // 128 instead of TileType.TILE_SIZE
            for (int col = (int) (x / TileType.TILE_SIZE); col < Math.ceil((x + width) / TileType.TILE_SIZE); col++) {
                for (int layer = 0; layer < getLayers(); layer++) {
                    TileType type = getTileTypeByCoordinate(layer, col, row);
                    if (type != null && type.isCollidable())
                        return true;
                }
            }
        }
        return false;
    }


    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();

    public int getPixelWidth() {
        return this.getWidth(); // * 500
    }

    public int getPixelHeight() {
        return this.getHeight();
    }
}
