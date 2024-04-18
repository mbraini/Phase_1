package view.objectsView;

import controller.helper.Vector;

import java.awt.*;
import java.util.ArrayList;

public abstract class OIGView {
    String id;
    Vector position;
    double theta;
    public static ArrayList<OIGView> OIGs = new ArrayList<>();

    abstract public void draw(Graphics2D g2d);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

}