package zombiecraft.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import zombiecraft.*;
import zombiecraft.player.ComputerPlayer;
import zombiecraft.player.HumanPlayer;

import java.util.ArrayList;

/**
 * Created by David Park on 1/7/14.
 */
public class Menu implements Screen {
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private ZombieCraft zombieCraft;
    private Matrix4 matrix4;

    public Menu(ZombieCraft zombieCraft) {
        this.spriteBatch = zombieCraft.getSpriteBatch();
        this.bitmapFont = zombieCraft.getBitmapFont();
        this.zombieCraft = zombieCraft;
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            ArrayList<Player> players = new ArrayList<Player>();
            players.add(new ComputerPlayer(Race.HUMAN));
            players.add(new HumanPlayer(Race.ZOMBIE));
            zombieCraft.setScreen(new GameMap(zombieCraft, players));
        }

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        bitmapFont.drawMultiLine(spriteBatch, "Menu\nPress 1 to debug a Game Map", 0, 0);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        matrix4.setToOrtho2D(0, -height, width, height);
        spriteBatch.setProjectionMatrix(matrix4);
    }

    @Override
    public void show() {
        matrix4 = new Matrix4();
        Gdx.gl.glClearColor(1, 1, 1, 0);

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
