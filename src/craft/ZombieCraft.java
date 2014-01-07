package craft;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import craft.screen.Menu;

/**
 * Created by David Park on 1/7/14.
 */
public class ZombieCraft extends Game {
    private Screen menu;
    private SpriteBatch spriteBatch;
    private BitmapFont bitmapFont;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();

        menu = new Menu(spriteBatch, bitmapFont, this);

        setScreen(menu);
    }

    public static void main(String[] args) {
        new LwjglApplication(new ZombieCraft());
    }
}
