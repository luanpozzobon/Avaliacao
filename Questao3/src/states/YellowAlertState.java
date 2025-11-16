package states;

import plant.NuclearPlant;

public class YellowAlertState implements IPlantState {
    private final long timestampStart;
    private boolean timer;

    public YellowAlertState() {
        this.timestampStart = System.currentTimeMillis();
        this.timer = false;
    }

    @Override
    public void checkConditions(NuclearPlant plant) {
        if (plant.getTemperature() > 400) {
            if (!timer) {
                timer = true;

                if (System.currentTimeMillis() - timestampStart > 30000) {
                    plant.setState(new RedAlertState());
                }
            }
        } else if (plant.getTemperature() < 300) {
            plant.setState(new NormalState());
        }
    }
}
