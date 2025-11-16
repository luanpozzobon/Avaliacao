package states;

import plant.NuclearPlant;

public class NormalState implements IPlantState {
    @Override
    public void checkConditions(NuclearPlant plant) {
        if (plant.getTemperature() > 300) {
            plant.setState(new YellowAlertState());
        }
    }
}
