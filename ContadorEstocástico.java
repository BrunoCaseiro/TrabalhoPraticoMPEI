import java.util.*;

public class ContadorEstocástico{
    static double probAdd = 1;
    static int n = 0;

    public static void add(){
        if(probAdd <= Math.random()){
            n++;
            updateProbability();
        }
    }

    public static void updateProbability(){
        probAdd = 1/(Math.pow(2,n));
    }
}