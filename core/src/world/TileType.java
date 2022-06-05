package world;

import java.util.HashMap;

public enum TileType {
    //------------------------------------------------------------------------------------------------------------------
    // Tile Id:
    GRASS(1, true, "Grass"),
    PLATFORM(2, true, "Platform"),
    GRASS_END(3, true, "Grass End"),
    GRASS_BEGIN(4, true, "Grass Begin"),
    GRASS_HIGH(5, true, "Grass High"),
    GRASS_HIGH_BEGIN(6, true, "Grass High Begin"),
    GRASS_HIGH_END(7, true, "Grass High End"),
    PLATFORM_HIGH(8, true, "Platform High"),
    SKY(9, false, "SKY"),
    BUSHES(10, false, "Bushes"),
    DOCK_BEGIN(11, true, "Dock Begin"),
    DOCK_END(12, true, "Dock End"),
    DOCK(13, true, "Dock"),
    GRASS_DOWN(14, true, "Grass Down"),
    GRASS_UP(15, true, "Grass UP"),
    DIRT(16, true, "Dirt");
    //------------------------------------------------------------------------------------------------------------------
    public static final int TILE_SIZE = 128;
    //------------------------------------------------------------------------------------------------------------------
    private int id;
    private boolean collidable;
    private String name;
    private float damage;
    //------------------------------------------------------------------------------------------------------------------
    TileType (int id, boolean collidable, String name) {
        this(id, collidable, name, 0);
    }
    //------------------------------------------------------------------------------------------------------------------
    TileType (int id, boolean collidable, String name, float damage) {
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
    }
    //------------------------------------------------------------------------------------------------------------------
    public int getId() {
        return id;
    }
    //------------------------------------------------------------------------------------------------------------------
    public boolean isCollidable() {
        return collidable;
    }
    //------------------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }
    //------------------------------------------------------------------------------------------------------------------
    public float getDamage() {
        return damage;
    }
    //------------------------------------------------------------------------------------------------------------------
    private static HashMap<Integer, TileType> map;

    static {
        map = new HashMap<Integer, TileType>();
        for (TileType tileType : TileType.values()) {
            assert false;
            map.put(tileType.getId(), tileType);
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public static TileType getTileTypeById (int id) {
        return map.get(id);
    }
}
