package craft.screen;

import com.badlogic.gdx.Screen;
import craft.Player;
import craft.Unit;
import craft.ZombieCraft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Park on 1/7/14.
 */
public class Map implements Screen {
    private Menu menu;
    private ZombieCraft zombieCraft;
    private final List<Player> players;
    private List<Unit> mainBuildings;
    private List<Unit> units;


    public Map(Menu menu, ZombieCraft zombieCraft, List<Player> players) {
        this.menu = menu;
        this.zombieCraft = zombieCraft;
        this.players = players;

        units = new ArrayList<Unit>();
        mainBuildings = new ArrayList<Unit>();

        for (Player player : players) {
            mainBuildings.add(player.race.getMainBuilding());
        }
        units.addAll(mainBuildings);
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
            if (mainBuildings.size()==1)
            {
                zombieCraft.setScreen(menu);
            }
        }
    }

    @Override
    public void resize(int width, int height) {

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
