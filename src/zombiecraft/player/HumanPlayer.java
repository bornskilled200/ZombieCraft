package zombiecraft.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import zombiecraft.Player;
import zombiecraft.Race;
import zombiecraft.screen.GameMap;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;

/**
 * Created by David Park on 1/7/14.
 */
public class HumanPlayer extends Player {
    public HumanPlayer(Race race) {
        super(race);
    }

    public void poll(GameMap gameMap, MainBuilding mainBuilding) {
        if (Gdx.input.justTouched() && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            GenericMovableUnit genericMovableUnit = new GenericMovableUnit(race.getAllUnits().get(0));
            float x = mainBuilding.getX();
            float y = mainBuilding.getY();
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            //new Vector2(mouseX-x,mouseY-y).angle();
            genericMovableUnit.setPosition(x, y);
            gameMap.addUnit(this, genericMovableUnit);
        }
    }
}
