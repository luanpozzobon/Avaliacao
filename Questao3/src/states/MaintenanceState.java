package states;

import plant.NuclearPlant;

public class MaintenanceState implements IPlantState {
    private final IPlantState previousState;

    public MaintenanceState(IPlantState previousState) {
        this.previousState = previousState;
        System.out.println("Manutenção em andamento...");
    }

    @Override
    public void checkConditions(NuclearPlant plant) {
        System.out.println("...Em manutenção!");
    }

    @Override
    public void leaveMaintenance(NuclearPlant plant) {
        System.out.println("Manutenção finalizada!");
        plant.setState(this.previousState);
    }
}
