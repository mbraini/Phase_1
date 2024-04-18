package controller.helper;


import java.util.ArrayList;
import java.util.Random;

public class Helper {
    public static ArrayList<Double> giveMaxMin(ArrayList<Double> a){
        ArrayList<Double> answer = new ArrayList<>();
        double min = a.get(0);
        double max = a.get(0);
        for (int i = 0 ;i < a.size() ;i++){
            if (a.get(i) < min){
                min = a.get(i);
            }
            if (a.get(i) > max){
                max = a.get(i);
            }
        }
        answer.add(min);
        answer.add(max);
        return answer;
    }

    public static Double PointDistanceWithLine(Vector vert1 ,Vector vert2 ,Vector point){
        Vector u = Utils.VectorWithSize(Utils.VectorAdd(vert2 ,Utils.ScalarInVector(-1 ,vert1)) ,1);
        Vector a = Utils.ScalarInVector(0.5 ,Utils.VectorAdd(vert1 ,vert2));
        Vector aPoint = Utils.VectorAdd(Utils.ScalarInVector(-1 ,point) ,a);
        return Utils.VectorSize(Utils.CrossProduct(u ,aPoint));
    }

    public static String RandomStringGenerator(int n){
        byte[] str = new byte[n];
        new Random().nextBytes(str);
        return new String(str);
    }
}
