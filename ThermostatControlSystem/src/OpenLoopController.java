public class OpenLoopController implements Controller {
    public static final int HEATING_TIME = 10;

    private ThermostatSimulation thermostat;
    private double openLoopHeatingPower;

    public static void main(String[] args) {
        OpenLoopController open = new OpenLoopController();
        open.thermostat = new ThermostatSimulation();
        ConsoleOutput.setTargetNum(ConsoleOutput.roundToInt(open.thermostat.getSetpointTemp()));
        open.openLoopHeatingPower = open.calculateHeatingPower();
        for (int i = 0; i <= HEATING_TIME; i++){
            ConsoleOutput.printNumberLine(ConsoleOutput.roundToInt(open.thermostat.getTemp()));
            open.nextLoop();
        }
    }

    @Override
    public double calculateHeatingPower(){
        double deltaTempPerTime = (thermostat.getSetpointTemp() - thermostat.getTemp()) / HEATING_TIME;
        return (deltaTempPerTime - thermostat.getA0()) / thermostat.getA1();
    }

    @Override
    public void nextLoop(){
        thermostat.setHeatingPower(openLoopHeatingPower);
        thermostat.update();
    }
}
