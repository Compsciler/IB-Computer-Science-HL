public class ThermostatSimulation {
    private double setpointTemp = 70;
    private double startingTemp = 50;
    private double heatingPower = 0;
    private double deltaTime = 1;

    private double error = setpointTemp - startingTemp;
    private double proportional = 0;
    private double integral = 0;
    private double derivative = 0;

    private double kP = 1;
    private double kI = 0.1;
    private double kD = 0.2;

    public void update(){
        double prevError = error;
        startingTemp += heatingPower;
        error = setpointTemp - startingTemp;

        proportional = kP * error;
        integral += kI * error * deltaTime;
        derivative = kD * (error - prevError) / deltaTime;
    }

    public double getHeatingPower() {
        return heatingPower;
    }
    public void setHeatingPower(double heatingPower) {
        this.heatingPower = heatingPower;
    }
}
