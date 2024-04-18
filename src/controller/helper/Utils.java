package controller.helper;

public class Utils {
    public static Vector VectorAdd(Vector a ,Vector b){
        Vector answer = new Vector();
        answer.setX(a.getX() + b.getX());
        answer.setY(a.getY() + b.getY());
        return answer;
    }
    public static double VectorSize(Vector vector){
        return Math.sqrt(Math.pow(vector.x ,2) + Math.pow(vector.y ,2));
    }
    public static Vector ScalarInVector(double a ,Vector vector){
        return new Vector(a * vector.getX() ,a * vector.getY());
    }
    public static Vector VectorWithSize(Vector vector ,double size){
        return ScalarInVector((1 / VectorSize(vector) * size) ,vector);
    }
    public static Vector RotateByTheta(Vector vector ,Vector origin ,double theta){
        /////xy Axis cause
        theta = -theta;
        /////
        Vector trans = new Vector(-origin.x ,-origin.y);
        vector = Utils.VectorAdd(trans ,vector);
        Vector e1 = new Vector(Math.cos(theta) ,Math.sin(theta));
        Vector e2 = new Vector(-Math.sin(theta) ,Math.cos(theta));
        Vector answer = new Vector();
        answer.x = e1.x * vector.x + e2.x * vector.y;
        answer.y = e1.y * vector.x + e2.y * vector.y;
        answer = Utils.VectorAdd(answer ,Utils.ScalarInVector(-1 ,trans));
        return answer;
    }
    public static double DotProduct(Vector a , Vector b){
        return a.x * b.x + a.y * b.y;
    }
    public static Vector NormalWithSize(Vector vector ,double size){
        Vector vector2 = new Vector(-vector.y ,vector.x);
        return Utils.VectorWithSize(vector2 ,size);
    }
    public static Vector NormalRelativeTo(Vector vector ,Double relative){
        Vector answer = new Vector();
        if (relative < 0){
            answer.setX(vector.y);
            answer.setY(-vector.x);
        }
        else {
            answer.setX(-vector.y);
            answer.setY(vector.x);
        }
        return answer;
    }

    public static Vector CrossProduct(Vector a, Vector b) {
        Vector answer = new Vector();
        answer.setX(0);
        answer.setY(a.x * b.y - b.x * a.y);
        return answer;
    }
}

