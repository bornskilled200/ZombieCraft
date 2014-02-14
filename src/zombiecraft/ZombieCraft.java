package zombiecraft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zombiecraft.screen.Menu;

import java.util.Arrays;

/**
 * Created by David Park on 1/7/14.
 */
public class ZombieCraft extends Game {
    private Screen menu;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    public Screen getMenu() {
        return menu;
    }

    @Override
    public void dispose() {
        super.dispose();

        spriteBatch.dispose();
        bitmapFont.dispose();
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        menu = new Menu(this);

        setScreen(menu);
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.vSyncEnabled = false; // Setting to false disables vertical sync
        config.foregroundFPS = 0; // Setting to 0 disables foreground fps throttling
        config.backgroundFPS = 25; // Setting to 0 disables background fps throttling
        config.title = "ZombieCraft";
        config.width = 1905;
        config.height = 400;
        config.x = 0;
        config.y = (1080 - config.height) / 2;
        new LwjglApplication(new ZombieCraft(), config);
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public BitmapFont getBitmapFont() {
        return bitmapFont;
    }
}
