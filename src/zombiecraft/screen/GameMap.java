package zombiecraft.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import zombiecraft.*;
import zombiecraft.human.Base;
import zombiecraft.human.Human;
import zombiecraft.human.Scout;
import zombiecraft.player.HumanPlayer;
import zombiecraft.unit.MainBuilding;
import zombiecraft.unit.MovableUnit;
import zombiecraft.zombie.Brain;
import zombiecraft.zombie.Zombie;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The View and Model, need to separate
 * Created by David Park on 1/7/14.
 */
public class GameMap implements Screen, GameModel, ViewModel {
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private final float UPDATES_PER_SECOND = 25;
    public final float SECONDS_PER_UPDATE = 1 / UPDATES_PER_SECOND;
    private final int MAX_FRAME_SKIP = 5;
    public float accumalator;
    FPSLogger fpsLogger = new FPSLogger();
    private OrthographicCamera camera;
    private Map<String, Texture> textures;
    private ZombieCraft zombieCraft;
    private List<Player> players;
    private Map<Player, MainBuilding> mainBuildingMap;
    private List<MainBuilding> mainBuildings;
    private List<Unit> units;
    private Map<Unit, Player> playerMap;
    private int time;
    private Pixmap fogOfWar;
    private Texture texture;
    private int maxSize;

    public GameMap(ZombieCraft zombieCraft, List<Player> players) {
        this.spriteBatch = zombieCraft.getSpriteBatch();
        this.bitmapFont = zombieCraft.getBitmapFont();
        this.zombieCraft = zombieCraft;
        this.players = players;

        units = new ArrayList<Unit>();
        mainBuildings = new ArrayList<MainBuilding>();
        playerMap = new HashMap<Unit, Player>();
        textures = new HashMap<String, Texture>();
        mainBuildingMap = new HashMap<Player, MainBuilding>();
        camera = new OrthographicCamera();

        for (Player player : players) {
            MainBuilding mainBuilding = player.race.getMainBuilding();
            mainBuilding.setPosition((float) Math.random() * (480 - 64), (float) Math.random() * (480 - 64));
            mainBuildings.add(mainBuilding);
            units.add(mainBuilding);
            playerMap.put(mainBuilding, player);
            mainBuildingMap.put(player, mainBuilding);
        }
        System.out.println(mainBuildingMap);

        textures.put(Base.NAME, new Texture(Gdx.files.internal("assets/human/mainbuilding.png")));
        textures.put(Human.NAME, new Texture(Gdx.files.internal("assets/human/human.png")));
        textures.put(Scout.NAME, new Texture(Gdx.files.internal("assets/human/scout.png")));
        textures.put(Brain.NAME, new Texture(Gdx.files.internal("assets/zombie/mainbuilding.png")));
        textures.put(Zombie.NAME, new Texture(Gdx.files.internal("assets/zombie/zombie.png")));
        textures.put("Crasher", new Texture(Gdx.files.internal("assets/zombie/hulk.png")));
        Gdx.gl.glClearColor(1, 1, 1, 0);
    }

    @Override
    public Map<Player, MainBuilding> getMainBuildingMap() {
        return mainBuildingMap;
    }

    @Override
    public List<MainBuilding> getMainBuildings() {
        return mainBuildings;
    }

    @Override
    public List<Unit> getUnits() {
        return units;
    }

    @Override
    public Map<Unit, Player> getPlayerMap() {
        return playerMap;
    }

    @Override
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void render(float delta) {
        accumalator += delta;

        int loops = 0;
        while (accumalator > SECONDS_PER_UPDATE && loops < MAX_FRAME_SKIP) {
            update_game(time);

            accumalator -= SECONDS_PER_UPDATE;
            time++;
            loops++;
        }

        float interpolation = accumalator / SECONDS_PER_UPDATE;
        display_game(interpolation);
        fpsLogger.log();
    }

    @Override
    public void addUnit(Player player, Unit unit) {
        if (unit instanceof MainBuilding)
            throw new IllegalArgumentException("Why are you adding a Main Building, stop that");
        if (players.contains(player) == false)
            throw new IllegalArgumentException("This player does not exist in this map");

        if (unit instanceof MovableUnit) {
            MovableUnit movableUnit = (MovableUnit) unit;
            movableUnit.setTime(time);
        }
        units.add(unit);
        playerMap.put(unit, player);
    }

    @Override
    public float getSecondsPerUpdate() {
        return SECONDS_PER_UPDATE;
    }

    private void display_game(float interpolation) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        Pixmap.setBlending(Pixmap.Blending.None);
        fogOfWar.setColor(Color.BLACK);
        fogOfWar.fillRectangle(0, 0, 640, 480);
        fogOfWar.setColor(Color.CLEAR);

        spriteBatch.begin();
        for (Unit unit : units) {
            float x = unit.getX();
            float y = unit.getY();
            float direction = 0;
            if (unit instanceof MovableUnit) {
                MovableUnit movableUnit = (MovableUnit) unit;
                x = Interpolation.linear.apply(movableUnit.getPreviousX(), x,
                        interpolation);//previousX + (x- previousX)*interpolation;
                y = Interpolation.linear.apply(movableUnit.getPreviousY(), y,
                        interpolation);//previousY + (y- previousY)*interpolation;
                direction = movableUnit.getDirection();

            }
            spriteBatch.draw(textures.get(unit.getName()), x, y, 32, 32, 64, 64, 1, 1, direction, 0, 0, 64, 64, false, false);
            if (playerMap.get(unit) instanceof HumanPlayer)
                fogOfWar.fillCircle((int) x + 32, 480 - (int) y - 32, 96);
            if (unit instanceof MainBuilding) {
                Player player = playerMap.get(unit);
                bitmapFont.draw(spriteBatch, "" + (player.getSelection() + 1), unit.getX(), unit.getY());
            }
        }
        spriteBatch.enableBlending();
        texture.draw(fogOfWar, 0, 1024 - 480);
        spriteBatch.draw(texture, 0, 0);   //Comment this out to see the whole map
        spriteBatch.end();

    }

    private void update_game(int time) {
        for (Player player : players) {
            player.poll(this, mainBuildingMap.get(player));
        }

        for (Unit unit : units) {
            unit.act(time, this);
            if (unit instanceof MovableUnit) {
                MovableUnit movableUnit = (MovableUnit) unit;

                float velocityX = movableUnit.getVelocityX();
                float velocityY = movableUnit.getVelocityY();
                float x = unit.getX();
                float y = unit.getY();
                if (velocityX != 0 && velocityY != 0 &&
                        (x < 0 || x > camera.viewportWidth - 64 || y < 0 || y > camera.viewportHeight - 64)) {
                    float f1 = x / (-velocityX);
                    float f2 = y / (-velocityY);
                    float f3 = (x - camera.viewportWidth + 64) / (-velocityX);
                    float f4 = (y - camera.viewportHeight + 64) / (-velocityY);
                    float back = Float.MIN_EXPONENT;
                    if (f1 <= 0)
                        back = Math.max(back, f1);
                    if (f2 <= 0)
                        back = Math.max(back, f2);
                    if (f3 <= 0)
                        back = Math.max(back, f3);
                    if (f4 <= 0)
                        back = Math.max(back, f4);
                    if (back <= 0 && back != Float.MIN_EXPONENT) {
                        float max1 = Math.max(0, Math.min(camera.viewportWidth - 64, x - (back * -velocityX)));
                        float max2 = Math.max(0, Math.min(camera.viewportHeight - 64, y - (back * -velocityY)));

                        unit.setX(max1);
                        unit.setY(max2);
                    }
                }
            }
        }

        for (int i = units.size() - 1; i >= 0; i--) {
            Unit unit = units.get(i);

            if (!unit.isDead())
                continue;

            units.remove(i);
            if (unit instanceof MainBuilding && mainBuildings.remove(unit) && mainBuildings.size() == 1) {
                zombieCraft.setScreen(zombieCraft.getMenu());
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        if (fogOfWar != null)
            fogOfWar.dispose();
        fogOfWar = new Pixmap(width, height, Pixmap.Format.RGBA8888);
    }

    @Override
    public void show() {
        IntBuffer buf = BufferUtils.newIntBuffer(16);
        Gdx.gl.glGetIntegerv(GL10.GL_MAX_TEXTURE_SIZE, buf);
        maxSize = buf.get();

        System.out.println(maxSize);
        texture = new Texture(1024, 1024, Pixmap.Format.RGBA8888);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void unproject(Vector3 mouse) {
        camera.unproject(mouse);
    }
}
