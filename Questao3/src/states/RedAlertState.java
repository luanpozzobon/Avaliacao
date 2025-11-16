package states;

import plant.NuclearPlant;

public class RedAlertState implements IPlantState {
    @Override
    public void checkConditions(NuclearPlant plant) {
        if (plant.isCoolingFailed()) {
            plant.setState(new EmergencyState());
        } else if (plant.getTemperature() <= 400) {
            plant.setState(new YellowAlertState());
        }
    }
}
