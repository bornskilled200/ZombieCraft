package craft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import craft.MainBuilding;
import craft.Player;
import craft.Unit;
import craft.ZombieCraft;
import craft.human.Base;
import craft.zombie.Brain;

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
    private ZombieCraft zombieCraft;
    private final List<Player> players;
    private List<Unit> mainBuildings;
    private List<Unit> units;
    private Map<Unit, Player> playerMap;
    private Map<Class<? extends Unit>, Texture> textures;
    private OrthographicCamera camera;


    public GameMap(ZombieCraft zombieCraft, List<Player> players) {
        this.spriteBatch = zombieCraft.getSpriteBatch();
        this.bitmapFont = zombieCraft.getBitmapFont();
        this.zombieCraft = zombieCraft;
        this.players = players;

        units = new ArrayList<Unit>();
        mainBuildings = new ArrayList<Unit>();
        playerMap = new HashMap<Unit, Player>();
        textures = new HashMap<Class<? extends Unit>, Texture>();
        camera = new OrthographicCamera();

        for (Player player : players) {
            Unit mainBuilding = player.race.getMainBuilding();
            mainBuilding.setPosition((float) Math.random() * 480, (float) Math.random() * 480);
            mainBuildings.add(mainBuilding);
            units.add(mainBuilding);
            playerMap.put(mainBuilding, player);
        }

        textures.put(Base.class, new Texture(Gdx.files.internal("assets/human/mainbuilding.png")));
        textures.put(Brain.class, new Texture(Gdx.files.internal("assets/zombie/mainbuilding.png")));
        Gdx.gl.glClearColor(1, 1, 1, 0);
    }

    public float accumalator;
    private final float TICKS_PER_SECOND = 25;
    private final float SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    private final int MAX_FRAME_SKIP = 5;
    private int time;

    @Override
    public void render(float delta) {
        accumalator += delta;

        int loops = 0;
        while (accumalator > SKIP_TICKS && loops < MAX_FRAME_SKIP) {
            update_game(time);

            accumalator -= SKIP_TICKS;
            time++;
            loops++;
        }

        float interpolation = accumalator / SKIP_TICKS;
        display_game(interpolation);
    }

    private void display_game(float interpolation) {

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        for (Unit unit : units) {
            spriteBatch.draw(textures.get(unit.getClass()), unit.getX(), unit.getY());
        }
        spriteBatch.end();
    }

    private void update_game(int time) {
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
