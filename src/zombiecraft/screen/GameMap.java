package zombiecraft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import zombiecraft.human.Human;
import zombiecraft.unit.MainBuilding;
import zombiecraft.Player;
import zombiecraft.Unit;
import zombiecraft.ZombieCraft;
import zombiecraft.human.Base;
import zombiecraft.unit.MovableUnit;
import zombiecraft.zombie.Brain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David Park on 1/7/14.
 */
public class GameMap implements Screen {
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private OrthographicCamera camera;

    private Map<String, Texture> textures;

    private ZombieCraft zombieCraft;
    private List<Player> players;
    private Map<Player, MainBuilding> mainBuildingMap;
    private List<Unit> mainBuildings;
    private List<Unit> units;
    private Map<Unit, Player> playerMap;


    public GameMap(ZombieCraft zombieCraft, List<Player> players) {
        this.spriteBatch = zombieCraft.getSpriteBatch();
        this.bitmapFont = zombieCraft.getBitmapFont();
        this.zombieCraft = zombieCraft;
        this.players = players;

        units = new ArrayList<Unit>();
        mainBuildings = new ArrayList<Unit>();
        playerMap = new HashMap<Unit, Player>();
        textures = new HashMap<String, Texture>();
        mainBuildingMap = new HashMap<Player, MainBuilding>();
        camera = new OrthographicCamera();

        for (Player player : players) {
            MainBuilding mainBuilding = player.race.getMainBuilding();
            mainBuilding.setPosition((float) Math.random() * 480, (float) Math.random() * 480);
            mainBuildings.add(mainBuilding);
            units.add(mainBuilding);
            playerMap.put(mainBuilding, player);
            mainBuildingMap.put(player, mainBuilding);
        }

        textures.put(Base.NAME, new Texture(Gdx.files.internal("assets/human/mainbuilding.png")));
        textures.put(Human.NAME, new Texture(Gdx.files.internal("assets/human/human.png")));
        textures.put(Brain.NAME, new Texture(Gdx.files.internal("assets/zombie/mainbuilding.png")));
        Gdx.gl.glClearColor(1, 1, 1, 0);
    }

    public float accumalator;
    private final float UPDATES_PER_SECOND = 25;
    private final float SECONDS_PER_UPDATE = 1 / UPDATES_PER_SECOND;
    private final int MAX_FRAME_SKIP = 5;
    private int time;


    FPSLogger fpsLogger = new FPSLogger();
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

    private void display_game(float interpolation) {

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        for (Unit unit : units) {
            float x = unit.getX();
            float y = unit.getY();
            if (unit instanceof MovableUnit) {
                MovableUnit movableUnit = (MovableUnit) unit;
                x = Interpolation.linear.apply(movableUnit.getPreviousX(), x, interpolation);//previousX + (x- previousX)*interpolation;
                y = Interpolation.linear.apply(movableUnit.getPreviousY(), y, interpolation);//previousY + (y- previousY)*interpolation;
                float velocityX = movableUnit.getVelocityX();
                float velocityY = movableUnit.getVelocityY();
                if (velocityX !=0 && velocityY !=0)
                {
                    float max = Math.min(x / (-velocityX), y / (-velocityY));
                    float ghostX = x - (max * -velocityX);
                    float ghostY = y - (max * -velocityY);
                    spriteBatch.setColor(1,1,1,.5f);
                    spriteBatch.draw(textures.get(unit.getName()),ghostX,ghostY);

                    spriteBatch.setColor(1,1,1,1);
                }
            }
            spriteBatch.draw(textures.get(unit.getName()), x, y);
        }
        spriteBatch.end();
    }

    private void update_game(int time) {
        for (Player player : players) {
            player.poll(this, mainBuildingMap.get(player));
        }

        for (Unit unit : units) {
            unit.act(time);
        }

        for (int i = units.size() - 1; i >= 0; i--) {
            Unit unit = units.get(i);

            if (!unit.isDead()) continue;

            units.remove(i);
            if (unit instanceof MainBuilding && mainBuildings.remove(unit) && mainBuildings.size() == 1) {
                zombieCraft.setScreen(zombieCraft.getMenu());
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(true, width, height);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

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
}
