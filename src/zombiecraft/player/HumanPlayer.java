package zombiecraft.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import zombiecraft.Player;
import zombiecraft.Race;
import zombiecraft.screen.GameMap;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;


/**
 * Created by David Park on 1/7/14.
 */
public class HumanPlayer extends Player
{
    private int nextButton = Input.Buttons.LEFT;

    public HumanPlayer(Race race)
    {
        super(race);
    }
    public void poll(GameMap gameMap, MainBuilding mainBuilding)
    {
        if (!Gdx.input.isButtonPressed(nextButton))
        {
            return;
        }
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        nextButton = (nextButton + 1) % 2;
        GenericMovableUnit genericMovableUnit = new GenericMovableUnit(race.getAllUnits().get(0));
        float x = mainBuilding.getX()+32;
        float y = mainBuilding.getY()+32;
        float angle1 =
                (float) Math.atan2(new Vector2(mouseX - x, mouseY - y).y, new Vector2(mouseX - x, mouseY - y).x) *
                MathUtils.radiansToDegrees;
        if (angle1 < 0)
            angle1 += 360;
        float angle = angle1;
        genericMovableUnit.setDirection(angle);
        genericMovableUnit.setPosition(x-32, y-32);

        gameMap.addUnit(this, genericMovableUnit);
    }
}
