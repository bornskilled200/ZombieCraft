package zombiecraft.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import util.Enums;
import zombiecraft.Player;
import zombiecraft.Race;
import zombiecraft.ZombieCraft;
import zombiecraft.player.ComputerPlayer;
import zombiecraft.player.HumanPlayer;

import java.util.ArrayList;


/**
 * Created by David Park on 1/7/14.
 */
public class Menu implements Screen
{
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private final InputAdapter inputAdapter;
    private ZombieCraft zombieCraft;
    private Matrix4 matrix4;
    private Race computerRace;
    private Race humanRace;
    private boolean randomPlacement;
    private boolean debug;

    public Menu(ZombieCraft zombieCraft)
    {
        this.spriteBatch = zombieCraft.getSpriteBatch();
        this.bitmapFont = zombieCraft.getBitmapFont();
        this.zombieCraft = zombieCraft;
        computerRace = Race.ZOMBIE;
        humanRace = Race.HUMAN;
        inputAdapter = new InputAdapter()
        {
            @Override
            public boolean keyTyped(char character)
            {
                switch (character)
                {
                    case '1':
                        humanRace = Enums.next(humanRace);
                        break;
                    case '2':
                        computerRace = Enums.next(computerRace);
                        break;
                    case '3':
                        randomPlacement = !randomPlacement;
                        break;
                    case '4':
                        debug = !debug;
                        break;
                    case '\r':
                    case '\n':
                        ArrayList<Player> players = new ArrayList<Player>();
                        players.add(new ComputerPlayer(computerRace));
                        players.add(new HumanPlayer(humanRace));
                        Menu.this.zombieCraft
                                .setScreen(new GameMap(Menu.this.zombieCraft, players, randomPlacement, debug));
                        break;
                    default:
                        System.out.println(character);
                        return false;

                }
                return true;
            }
        };
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        bitmapFont.drawMultiLine(spriteBatch, "Menu\n" +
                                              "Press 1 to change your race to " + Enums.next(humanRace) +
                                              ", current race is " + humanRace + "\n" +
                                              "Press 2 to change computer's race " + Enums.next(computerRace) +
                                              ", current race is " + computerRace + "\n" +
                                              "Press 3 to make base placement random\n" +
                                              "Press 4 to turn debug mode " + (!debug) + ", current debug mode is " +
                                              debug + "\n" +
                                              "Press Enter to start the game", 0, 0);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        matrix4.setToOrtho2D(0, -height, width, height);
        spriteBatch.setProjectionMatrix(matrix4);
    }

    @Override
    public void show()
    {
        matrix4 = new Matrix4();
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.input.setInputProcessor(inputAdapter);
    }

    @Override
    public void hide()
    {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
