package model.objectsModel;

import controller.helper.Vector;

import java.util.ArrayList;

public abstract class OIGModel {
    Vector position;
    Vector velocity;
    Vector acceleration;
    double theta;
    double omega;
    double alpha;
    String id;
    public static ArrayList<OIGModel> OIGs = new ArrayList<>();

    public Vector getPosition() {
        return position;
    }

    public void setPosition(double x ,double y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    public void setPosition(Vector vector) {
        this.position.setX(vector.x);
        this.position.setY(vector.y);
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(double x ,double y) {
        this.velocity.setX(x);
        this.velocity.setY(y);
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double x ,double y) {
        this.acceleration.setX(x);
        this.acceleration.setY(y);
    }
    public void setAcceleration(Vector vector) {
        this.acceleration.setX(vector.x);
        this.acceleration.setY(vector.y);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getOmega() {
        return omega;
    }

    public void setOmega(double omega) {
        this.omega = omega;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }
}
