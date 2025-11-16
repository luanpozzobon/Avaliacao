package plant;

import states.EmergencyState;
import states.IPlantState;
import states.NormalState;
import states.OffState;

public class NuclearPlant {
    private IPlantState state;

    private double temperature;
    private double pressure;
    private double radiation;

    private boolean coolingFailed;

    public NuclearPlant() {
        this.state = new OffState();
    }

    public IPlantState getState() {
        return state;
    }

    public void setState(final IPlantState state) {
        if (state.getClass() != this.state.getClass()) {
            this.state = state;
        }
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getRadiation() {
        return radiation;
    }

    public void setRadiation(double radiation) {
        this.radiation = radiation;
    }

    public boolean isCoolingFailed() {
        return coolingFailed;
    }

    public void setCoolingFailed(boolean coolingFailed) {
        this.coolingFailed = coolingFailed;
    }

    public void checkConditions() {
        this.state.checkConditions(this);
    }

    public void enterMaintenance() {
        if (this.state instanceof EmergencyState) {
            System.out.println("USINA EM EMERGÊNCIA!!! NÃO É POSSÍVEL ENTRAR EM MANUTENÇÃO!!!");
        }

        this.state.checkConditions(this);
    }

    public void leaveMaintenance() {
        this.state.leaveMaintenance(this);
    }

    public void powerOn() {
        if (this.state instanceof OffState) this.setState(new NormalState());
    }
}
