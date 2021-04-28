import java.util.*;

public class ThermostatSimulation {
    private double setpointTemp = 70;
    private double temp = 50;
    private double heatingPower = 0;
    private double deltaTime = 1;

    enum NoiseType {
        CONSTANT, PROPORTIONAL;
    }
    private NoiseType noiseType = NoiseType.PROPORTIONAL;
    private double kNoise = 1;
    private boolean isKNoiseRandom = true;

    private double error = setpointTemp - temp;

    private double proportional = error;
    private double integral = 0;
    private double derivative = 0;

    public void update(){
        double prevError = error;
        temp += heatingPower;

        double kNoiseVal = kNoise;
        if (isKNoiseRandom){
            Random rand = new Random();
            kNoiseVal = -kNoise + rand.nextDouble() * kNoise * 2;
        }
        switch (noiseType){
            case CONSTANT:
                temp += kNoiseVal;
                break;
            case PROPORTIONAL:
                temp += heatingPower * kNoiseVal;
        }

        error = setpointTemp - temp;

        proportional = error;
        integral += error * deltaTime;
        derivative = (error - prevError) / deltaTime;
    }

    public double getSetpointTemp() {
        return setpointTemp;
    }
    public void setSetpointTemp(double setpointTemp) {
        this.setpointTemp = setpointTemp;
    }

    public double getTemp() {
        return temp;
    }
    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHeatingPower() {
        return heatingPower;
    }
    public void setHeatingPower(double heatingPower) {
        this.heatingPower = heatingPower;
    }

    public double getProportional() {
        return proportional;
    }
    public void setProportional(double proportional) {
        this.proportional = proportional;
    }

    public double getIntegral() {
        return integral;
    }
    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public double getDerivative() {
        return derivative;
    }
    public void setDerivative(double derivative) {
        this.derivative = derivative;
    }
}
