package model.logic;

import controller.Application;
import controller.Constants;
import controller.helper.Helper;
import controller.helper.Utils;
import controller.helper.Vector;
import model.interfaces.HasVertices;
import model.interfaces.IsCircle;
import model.interfaces.IsPolygon;
import model.objectsModel.OIGModel;
import view.game.GameFrame;
import view.game.WindowKill;

import java.util.ArrayList;

public class Collision {
    static Vector collisionPoint;

    public static boolean IsColliding(OIGModel a ,OIGModel b){
        if (a instanceof IsPolygon && b instanceof IsPolygon){
            return PolygonSAT((IsPolygon) a ,(IsPolygon) b);
        }
        else if (a instanceof IsCircle && b instanceof IsPolygon){
            return CircleSAT((IsCircle) a, (IsPolygon) b);
        }
        else if (a instanceof IsPolygon && b instanceof IsCircle){
            return CircleSAT((IsCircle) b, (IsPolygon) a);
        }
        else if (a instanceof IsCircle && b instanceof IsCircle){
            return TwoCirclesCheck((IsCircle) a ,(IsCircle) b);
        }
        return false;
    }

    private static boolean TwoCirclesCheck(IsCircle circle1 ,IsCircle circle2) {
        double r1 = circle1.getRadios();
        double r2 = circle2.getRadios();
        Vector position1 = ((OIGModel)circle1).getPosition();
        Vector position2 = ((OIGModel)circle2).getPosition();
        if (Utils.VectorSize(Utils.VectorAdd(position1 ,Utils.ScalarInVector(-1 ,position2))) <= r1 + r2){
            return true;
        }
        return false;
    }

    public static void CollisionResponse(OIGModel a, OIGModel b) {

    }

    private static void PullOut(OIGModel attacker, OIGModel defender) {

    }

    private static Vector FindCollisionPoint(OIGModel a, OIGModel b) {
        if (a instanceof IsPolygon && b instanceof IsPolygon) {
            IsPolygon polygon = (IsPolygon) a;
            IsPolygon target = (IsPolygon) b;
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < polygon.getVertices().size(); i++) {
                    if (IsInPolygon(target, polygon.getVertices().get(i))) {
                        return polygon.getVertices().get(i);
                    }
                }
                polygon = (IsPolygon) b;
                target = (IsPolygon) a;
            }
        }
        else if (a instanceof IsCircle && b instanceof IsPolygon){
            Vector direction = Utils.VectorWithSize(Utils.VectorAdd(Utils.ScalarInVector(-1 ,a.getPosition()) ,b.getPosition()) ,Constants.EPSILON_DIMENSION.width);
            return Utils.VectorAdd(direction ,a.getPosition());
        }
        return null;
    }

    private static boolean PolygonSAT(IsPolygon a, IsPolygon b) {
        IsPolygon polygon;
        polygon = a;
        for (int j = 0 ;j < 2 ;j++) {
            for (int i = 0; i < polygon.getVertices().size(); i++) {
                int c = i + 1;
                if (i + 1 == polygon.getVertices().size())
                    c = 0;
                Vector edge = Utils.VectorAdd(Utils.ScalarInVector(-1, polygon.getVertices().get(i)), polygon.getVertices().get(c));
                Vector normal = Utils.NormalWithSize(edge, 1);
                Vector origin = Utils.ScalarInVector(0.5, Utils.VectorAdd(polygon.getVertices().get(i), polygon.getVertices().get(c)));
                ArrayList<Double> aProj = GivePolygonProj(a, normal, origin);
                ArrayList<Double> bProj = GivePolygonProj(b, normal, origin);
                aProj = Helper.giveMaxMin(aProj);
                bProj = Helper.giveMaxMin(bProj);
                if (CheckGap(aProj, bProj))
                    return false;
            }
            polygon = b;
        }
        return true;
    }

    private static boolean CircleSAT(IsCircle circle ,IsPolygon polygon){
        for (int i = 0 ;i < polygon.getVertices().size() ;i++){
            int c =i + 1;
            if (c == polygon.getVertices().size())
                c = 0;
            Vector normal = Utils.NormalWithSize( Utils.VectorAdd(Utils.ScalarInVector(-1 ,polygon.getVertices().get(i)) ,polygon.getVertices().get(c)),1);
            ArrayList<Double> polygonProj = GivePolygonProj(polygon ,normal ,polygon.getVertices().get(i));
            ArrayList<Double> circleProj = GiveCircleProj(circle ,normal ,polygon.getVertices().get(i));
            polygonProj = Helper.giveMaxMin(polygonProj);
            circleProj = Helper.giveMaxMin(circleProj);
            if (CheckGap(polygonProj, circleProj))
                return false;
        }
        ArrayList<Double> polygonVerticesDistance = new ArrayList<>();
        Vector center = ((OIGModel)circle).getPosition();
        for (int i = 0 ;i < polygon.getVertices().size() ;i++){
            polygonVerticesDistance.add(Utils.VectorSize(Utils.VectorAdd(center ,Utils.ScalarInVector(-1 ,polygon.getVertices().get(i)))));
        }
        ArrayList<Double> ordered = Helper.giveMaxMin(polygonVerticesDistance);
        int index = polygonVerticesDistance.indexOf(ordered.get(0));

        Vector normal = Utils.VectorWithSize(Utils.VectorAdd(Utils.ScalarInVector(-1 ,center) ,polygon.getVertices().get(index)) ,1);
        ArrayList<Double> polygonProj = GivePolygonProj(polygon ,normal ,center);
        ArrayList<Double> circleProj = GiveCircleProj(circle ,normal ,center);
        polygonProj = Helper.giveMaxMin(polygonProj);
        circleProj = Helper.giveMaxMin(circleProj);
        if (CheckGap(polygonProj, circleProj))
            return false;
        return true;
    }

    private static boolean CheckGap(ArrayList<Double> aMaxMin ,ArrayList<Double> bMaxMin) {
        if ((aMaxMin.get(1) < bMaxMin.get(0)) || (bMaxMin.get(1) < aMaxMin.get(0)))
            return true;
        return false;
    }

    private static ArrayList<Double> GivePolygonProj(IsPolygon polygon , Vector b , Vector origin){
        ArrayList<Double> answer = new ArrayList<>();
        Vector a;
        for (int i = 0 ;i < polygon.getVertices().size() ;i++){
            a = Utils.VectorAdd(Utils.ScalarInVector(-1 ,origin) ,polygon.getVertices().get(i));
            answer.add(Utils.DotProduct(a ,b));
        }
        return answer;
    }

    private static ArrayList<Double> GiveCircleProj(IsCircle circle ,Vector normal ,Vector origin){
        ArrayList<Double> answer = new ArrayList<>();
        Vector position = ((OIGModel)circle).getPosition();
        double r = circle.getRadios();
        Vector point1 = Utils.VectorAdd(position ,Utils.VectorWithSize(normal ,r));
        Vector point2 = Utils.VectorAdd(position ,Utils.VectorWithSize(normal ,-r));
        Vector vector1 = Utils.VectorAdd(Utils.ScalarInVector(-1 ,origin) ,point1);
        Vector vector2 = Utils.VectorAdd(Utils.ScalarInVector(-1 ,origin) ,point2);
        answer.add(Utils.DotProduct(vector1 ,normal));
        answer.add(Utils.DotProduct(vector2 ,normal));
        return answer;
    }

    private static boolean IsInPolygon(IsPolygon polygon ,Vector a){
        int num_vertices = polygon.getVertices().size();
        double x = a.x, y = a.y;
        boolean inside = false;
        Vector p1 = polygon.getVertices().get(0), p2;
        for (int i = 1; i <= num_vertices; i++) {
            p2 = polygon.getVertices().get(i % num_vertices);
            if (y > Math.min(p1.y, p2.y)) {
                if (y <= Math.max(p1.y, p2.y)) {
                    if (x <= Math.max(p1.x, p2.x)) {
                        double x_intersection
                                = (y - p1.y) * (p2.x - p1.x)
                                / (p2.y - p1.y)
                                + p1.x;
                        if (p1.x == p2.x
                                || x <= x_intersection) {
                            inside = !inside;
                        }
                    }
                }
            }
            p1 = p2;
        }
        return inside;
    }
}
