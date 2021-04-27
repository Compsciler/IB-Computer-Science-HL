import java.io.IOException;

public class ConsoleOutput {
    public static final int TARGET_NUM = 70;
    public static final int MIN_RANGE = 40;
    public static final int MAX_RANGE = 100;
    public static final int INCREMENT = 1;
    public static final int PERIOD_GROUP_SIZE = 5;

    private static boolean isInvokeCanceled = false;

    public static void printNumberLine(int n){
        String numberLine = MIN_RANGE + " ";
        for (int i = MIN_RANGE; i <= MAX_RANGE; i += INCREMENT){
            if (i == n){
                numberLine += "X";
            } else if (i == TARGET_NUM){
                numberLine += "|";
            } else if (i % PERIOD_GROUP_SIZE == 0){
                numberLine += ":";
            } else {
                numberLine += ".";
            }
        }
        numberLine += " " + MAX_RANGE;
        System.out.println(numberLine);
    }

    public static void invokeRepeating(String methodName, long startTime, long repeatRate, Object... methodParams) throws InterruptedException, IOException {
        Thread.sleep(startTime);
        Runtime runtime = Runtime.getRuntime();
        while (true){
            if (isInvokeCanceled){
                isInvokeCanceled = false;
                break;
            }

            String methodCall = methodName + "(";
            for (int i = 0; i < methodParams.length; i++){
                methodCall += methodParams[i];
                if (i > 0){
                    methodCall += ", ";
                }
            }
            methodCall += ")";

            runtime.exec(methodCall);
            Thread.sleep(repeatRate);
        }
    }

    public static void cancelInvoke(){
        isInvokeCanceled = true;
    }
}
