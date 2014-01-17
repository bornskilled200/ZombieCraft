package craft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import craft.*;

import java.util.ArrayList;

/**
 * Created by David Park on 1/7/14.
 */
public class Menu implements Screen {
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private ZombieCraft zombieCraft;

    public Menu(ZombieCraft zombieCraft) {
        this.spriteBatch = zombieCraft.getSpriteBatch();
        this.bitmapFont = zombieCraft.getBitmapFont();
        this.zombieCraft = zombieCraft;
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            ArrayList<Player> players = new ArrayList<Player>();
            players.add(new ComputerPlayer(Race.ZOMBIE));
            players.add(new HumanPlayer(Race.HUMAN));
            zombieCraft.setScreen(new GameMap(zombieCraft, players));
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
