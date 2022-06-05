package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SnapshotArray;
import world.GameMap;

public class EntityLoader {

    public static Json json = new Json();

    public static ArrayList<Entity> loadEntities (String id, GameMap map, ArrayList<Entity> currentEntities) {
        Gdx.files.internal(id + ".json");
        FileHandle file = Gdx.files.internal(id + ".json");

        if (file.exists()) {
            EntitySnapshot[] snapshots = json.fromJson(EntitySnapshot[].class, file.readString());
            ArrayList<Entity> entities = new ArrayList<Entity>();
            for (EntitySnapshot snapshot : snapshots) {
                entities.add(EntityType.createEntityUsingSnapshot(snapshot, map));
            }
            return entities;
        } else {
            return currentEntities;
        }
    }
}
