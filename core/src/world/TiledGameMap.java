package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap extends GameMap {

    TiledMap map;
    OrthogonalTiledMapRenderer renderer;

    public TiledGameMap () {
        map = new TmxMapLoader().load("assets/MapTilesFinal.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 7); // Unit scale 7
    }

    @Override
    public void render (OrthographicCamera cam, SpriteBatch batch) {
        renderer.setView(cam);
        renderer.render();

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        super.render(cam, batch);
        batch.end();
    }

    @Override
    public void update (float delta) {
        super.update(delta);
    }

    @Override
    public void dispose () {
        map.dispose();
    }

    @Override
    public TileType getTileTypeByCoordinate (int layer, int col, int row) {
        Cell cell = ((TiledMapTileLayer) map.getLayers().get(layer)).getCell(col, row);

        if (cell != null) {
            TiledMapTile tile = cell.getTile();
            if (tile != null) {
                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }
        }
        return null;
    }

    @Override
    public int getWidth () {
        return ((TiledMapTileLayer) map.getLayers().get(0)).getWidth();
    }

    @Override
    public int getHeight () {
        return ((TiledMapTileLayer) map.getLayers().get(0)).getHeight();
    }

    @Override
    public int getLayers () {
        return map.getLayers().getCount();
    }
}
