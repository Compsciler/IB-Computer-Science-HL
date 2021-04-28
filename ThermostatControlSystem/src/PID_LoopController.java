public class PID_LoopController implements Controller {
    public static final int HEATING_TIME = 30;

    private ThermostatSimulation thermostat;

    private double kP = 1;
    private double kI = 0.1;
    private double kD = 0.2;

    public static void main(String[] args) {
        PID_LoopController pid = new PID_LoopController();
        pid.thermostat = new ThermostatSimulation();
        ConsoleOutput.setTargetNum(ConsoleOutput.roundToInt(pid.thermostat.getSetpointTemp()));
        for (int i = 0; i <= HEATING_TIME; i++){
            ConsoleOutput.printNumberLine(ConsoleOutput.roundToInt(pid.thermostat.getTemp()));
            pid.nextLoop();
        }
    }

    public double calculateHeatingPower(){
        return thermostat.getProportional() * kP + thermostat.getIntegral() * kI + thermostat.getDerivative() * kD;
    }

    @Override
    public void nextLoop(){
        thermostat.setHeatingPower(calculateHeatingPower());
        thermostat.update();
    }
}
