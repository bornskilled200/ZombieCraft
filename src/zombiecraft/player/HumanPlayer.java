package zombiecraft.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import zombiecraft.*;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;

import java.util.List;


/**
 * Created by David Park on 1/7/14.
 */
public class HumanPlayer extends Player
{


    public HumanPlayer(Race race)
    {
        super(race);
    }

    public void poll(GameModel gameModel, ViewModel viewModel)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            gameModel.getMainBuildingMap().get(this).setHealth(0);
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            setSelection(0);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
            setSelection(1);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
            setSelection(2);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_4))
            setSelection(3);

        MainBuilding mainBuilding = gameModel.getMainBuildingMap().get(this);
        List<UnitData> unitDatas = mainBuilding.buildableUnits();
        if (getSelection() >= unitDatas.size())
            setSelection(Math.max(unitDatas.size() - 1, 0));
        if (getProductionDelay() <= 0)
        {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            {
                viewModel.setUnprojectPosition(Gdx.input.getX(), Gdx.input.getY());
                GenericMovableUnit genericMovableUnit = new GenericMovableUnit(unitDatas.get(getSelection()));
                float x = mainBuilding.getX() + 32;
                float y = mainBuilding.getY() + 32;
                float angle = (float) Math.atan2(viewModel.getUnprojectY() - y, viewModel.getUnprojectX() - x) * MathUtils.radiansToDegrees;
                if (angle < 0)
                    angle += 360;
                genericMovableUnit.setDirection(angle);
                genericMovableUnit.setPosition(x - 32, y - 32);
                setProductionDelay(50);
                setProductionDelayLength(50);
                gameModel.addUnit(this, genericMovableUnit);
            }
        }
        else
            setProductionDelay(getProductionDelay() - 1);
    }
}
