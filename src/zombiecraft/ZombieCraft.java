package zombiecraft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import zombiecraft.screen.Menu;

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
        new LwjglApplication(new ZombieCraft(), "ZombieCraft", 1280, 720, true);
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public BitmapFont getBitmapFont() {
        return bitmapFont;
    }
}
