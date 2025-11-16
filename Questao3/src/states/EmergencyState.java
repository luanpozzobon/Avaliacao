package states;

import plant.NuclearPlant;

public class EmergencyState implements IPlantState {
    public EmergencyState() {
        System.out.println("ESTADO DE EMERGÊNCIA!!! TODOS DEVEM DEIXAR A USINA!!!");
    }

    @Override
    public void checkConditions(NuclearPlant plant) {

    }
}
