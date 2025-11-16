package states;

import plant.NuclearPlant;

public interface IPlantState {
    void checkConditions(NuclearPlant plant);

    default void leaveMaintenance(NuclearPlant plant) {
        System.out.println("A usina não está em manutenção!");
    }
}
