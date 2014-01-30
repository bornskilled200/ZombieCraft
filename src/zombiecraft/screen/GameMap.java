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
import com.badlogic.gdx.math.Vector2;
import zombiecraft.Player;
import zombiecraft.Unit;
import zombiecraft.ZombieCraft;
import zombiecraft.human.Base;
import zombiecraft.human.Human;
import zombiecraft.unit.MainBuilding;
import zombiecraft.unit.MovableUnit;
import zombiecraft.zombie.Brain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by David Park on 1/7/14.
 */
public class GameMap implements Screen
{
    private final SpriteBatch spriteBatch;
    private final BitmapFont bitmapFont;
    private final float UPDATES_PER_SECOND = 25;
    private final float SECONDS_PER_UPDATE = 1 / UPDATES_PER_SECOND;
    private final int MAX_FRAME_SKIP = 5;
    public float accumalator;
    FPSLogger fpsLogger = new FPSLogger();
    private OrthographicCamera camera;
    private Map<String, Texture> textures;
    private ZombieCraft zombieCraft;
    private List<Player> players;
    private Map<Player, MainBuilding> mainBuildingMap;
    private List<Unit> mainBuildings;
    private List<Unit> units;
    private Map<Unit, Player> playerMap;
    private int time;


    public GameMap(ZombieCraft zombieCraft, List<Player> players)
    {
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

        for (Player player : players)
        {
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

    @Override
    public void render(float delta)
    {
        accumalator += delta;

        int loops = 0;
        while (accumalator > SECONDS_PER_UPDATE && loops < MAX_FRAME_SKIP)
        {
            update_game(time);

            accumalator -= SECONDS_PER_UPDATE;
            time++;
            loops++;
        }

        float interpolation = accumalator / SECONDS_PER_UPDATE;
        display_game(interpolation);
        //fpsLogger.log();
    }

    public void addUnit(Player player, Unit unit)
    {
        if (unit instanceof MainBuilding)
            throw new IllegalArgumentException("Why are you adding a Main Building, stop that");
        if (players.contains(player) == false)
            throw new IllegalArgumentException("This player does not exist in this map");

        if (unit instanceof MovableUnit)
        {
            MovableUnit movableUnit = (MovableUnit) unit;
            movableUnit.setTime(time);
        }
        units.add(unit);
        playerMap.put(unit, player);
    }

    private void display_game(float interpolation)
    {

        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


        spriteBatch.begin();
        for (Unit unit : units)
        {
            float x = unit.getX();
            float y = unit.getY();
            if (unit instanceof MovableUnit)
            {
                MovableUnit movableUnit = (MovableUnit) unit;
                x = Interpolation.linear.apply(movableUnit.getPreviousX(), x,
                                               interpolation);//previousX + (x- previousX)*interpolation;
                y = Interpolation.linear.apply(movableUnit.getPreviousY(), y,
                                               interpolation);//previousY + (y- previousY)*interpolation;

            }
            spriteBatch.draw(textures.get(unit.getName()), x, y);
        }
        spriteBatch.end();
    }

    private void update_game(int time)
    {
        for (Player player : players)
        {
            player.poll(this, mainBuildingMap.get(player));
        }

        for (Unit unit : units)
        {
            unit.act(time);
            if (unit instanceof MovableUnit)
            {
                MovableUnit movableUnit = (MovableUnit) unit;
                float velocityX = movableUnit.getVelocityX();
                float velocityY = movableUnit.getVelocityY();
                float x = movableUnit.getX();
                float y = movableUnit.getY();
                if ((velocityX != 0 && velocityY != 0) && ((x < 0 || x > 640 - 64) || (y < 0 || y > 480 - 64)))
                {
                    float f1 = x / (-velocityX);
                    float f2 = y / (-velocityY);
                    float f3 = (x - 640 + 64) / (-velocityX);
                    float f4 = (y - 480 + 64) / (-velocityY);
                    float back = Float.MIN_EXPONENT;
                    if (f1<=0)
                        back = Math.max(back,f1);
                    if (f2<=0)
                        back = Math.max(back,f2);
                    if (f3<=0)
                        back = Math.max(back,f3);
                    if (f4<=0)
                        back = Math.max(back,f4);
                    if (back <= 0 && back!=Float.MIN_EXPONENT)
                    {
                        float max1 = Math.max(1, Math.min(640 - 64 - 1, x - (back * -velocityX)));
                        float max2 = Math.max(1, Math.min(480 - 64 - 1, y - (back * -velocityY)));

                        float len = new Vector2(max1 - x, max2 - y).len();
                        if (len > 7)
                        {
                            System.out.println(len);
                            System.out.println(x + ", " + y);
                            System.out.println(max1 + ", " + max2);
                            System.out.println("back is " + back);
                            System.out.println(f1 + "\t" + (Math.max(1, Math.min(640 - 64 - 1, x - (f1 * -velocityX)))) + ", " +
                                               (Math.max(1, Math.min(640 - 64 - 1, y - (f1 * -velocityY)))));
                            System.out.println(f2 + "\t" + (Math.max(1, Math.min(640 - 64 - 1, x - (f2 * -velocityX)))) + ", " +
                                               (Math.max(1, Math.min(640 - 64 - 1, y - (f2 * -velocityY)))));
                            System.out.println(f3 + "\t" + (Math.max(1, Math.min(640 - 64 - 1, x - (f3 * -velocityX)))) + ", " +
                                               (Math.max(1, Math.min(640 - 64 - 1, y - (f3 * -velocityY)))));
                            System.out.println(f4 + "\t" + (Math.max(1, Math.min(640 - 64 - 1, x - (f4 * -velocityX)))) + ", " +
                                               (Math.max(1, Math.min(640 - 64 - 1, y - (f4 * -velocityY)))));
                            System.out.println();
                        }
                        unit.setX(max1);
                        unit.setY(max2);
                    }  else {
                        System.out.println("SOMETHING IS WRONG");
                        System.out.println(x + ", " + y);
                        System.out.println("back is " + back);
                        System.out.println(f1 + "\t" + (Math.max(1, Math.min(640 - 64 - 1, x - (f1 * -velocityX)))) + ", " +
                                           (Math.max(1, Math.min(640 - 64 - 1, y - (f1 * -velocityY)))));
                        System.out.println(f2 + "\t" + (Math.max(1, Math.min(640 - 64 - 1, x - (f2 * -velocityX)))) + ", " +
                                           (Math.max(1, Math.min(640 - 64 - 1, y - (f2 * -velocityY)))));
                        System.out.println(f3 + "\t" + (Math.max(1, Math.min(640 - 64 - 1, x - (f3 * -velocityX)))) + ", " +
                                           (Math.max(1, Math.min(640 - 64 - 1, y - (f3 * -velocityY)))));
                        System.out.println(f4 + "\t" + (Math.max(1, Math.min(640 - 64 - 1, x - (f4 * -velocityX)))) + ", " +
                                           (Math.max(1, Math.min(640 - 64 - 1, y - (f4 * -velocityY)))));
                        System.out.println();
                    }
                }
            }
        }

        for (int i = units.size() - 1; i >= 0; i--)
        {
            Unit unit = units.get(i);

            if (!unit.isDead())
                continue;

            units.remove(i);
            if (unit instanceof MainBuilding && mainBuildings.remove(unit) && mainBuildings.size() == 1)
            {
                zombieCraft.setScreen(zombieCraft.getMenu());
            }
        }
    }

    @Override
    public void resize(int width, int height)
    {
        camera.setToOrtho(true, width, height);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {

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
