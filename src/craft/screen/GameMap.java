package craft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import craft.Player;
import craft.Unit;
import craft.ZombieCraft;
import craft.human.Base;
import craft.zombie.Brain;
import sun.net.www.content.text.plain;

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
    private Menu menu;
    private ZombieCraft zombieCraft;
    private final List<Player> players;
    private List<Unit> mainBuildings;
    private List<Unit> units;
    private Map<Unit, Player> playerMap;
    private Map<Class<? extends Unit>, Texture> textures;
    private OrthographicCamera camera;


    public GameMap(SpriteBatch spriteBatch, BitmapFont bitmapFont, Menu menu, ZombieCraft zombieCraft, List<Player> players) {
        this.spriteBatch = spriteBatch;
        this.bitmapFont = bitmapFont;
        this.menu = menu;
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


    @Override
    public void render(float delta) {
        for (Unit unit : units) {
            unit.act();
        }

        for (int i = mainBuildings.size() - 1; i >= 0; i--) {
            Unit base = mainBuildings.get(i);
            if (!base.isDead()) continue;

            mainBuildings.remove(i);
            if (mainBuildings.size() == 1) {
                zombieCraft.setScreen(menu);
            }
        }

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        for (Unit unit : units) {
            spriteBatch.draw(textures.get(unit.getClass()), unit.getX(), unit.getY());
        }
        spriteBatch.end();
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
