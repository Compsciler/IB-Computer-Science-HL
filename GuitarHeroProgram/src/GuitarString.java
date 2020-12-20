import java.io.*;
import java.util.*;

public class GuitarString {
    private Queue<Double> sampleQueue;
    private int samplingRateForNote;  /// Sampling rate (44100 samples/second) divided by note frequency in hertz
    private double randomDisplacement = 0.5;
    private double volumeFade = 0.996;

    public GuitarString(double frequency){
        samplingRateForNote = (int)(StdAudio.SAMPLE_RATE / frequency);
        sampleQueue = new LinkedList<>();
        for(int i = 0; i < samplingRateForNote; i++){
            sampleQueue.add(0.0);  /// Temporary 0's added as placeholders to keep queue size constant at the sampling rate per specific note
        }
    }

    public GuitarString(double[] arr){
        /// Only needed for testing
    }


    public void pluck(){
        for(int i = 0; i < samplingRateForNote; i++){
            Random rand = new Random();
            double displacement = rand.nextDouble() * randomDisplacement;
            boolean isNegative = rand.nextBoolean();
            sampleQueue.add((displacement * ((isNegative ? -1 : 1))));
            sampleQueue.remove();
        }
    }

    public void tic() {
        sampleQueue.add(volumeFade * (sampleQueue.remove() + sampleQueue.peek()) / 2);  /// Removes front of queue and adds faded average of previous first 2 doubles
    }

    public double sample(){
        return sampleQueue.peek();
    }
}

