package controller;

import java.awt.*;

public class Constants {
    public static final int GAME_HEIGHT = 700;
    public static final int GAME_WIDTH = 700;
    public static final double EPSILON_ACCELERATION = 0.0015;
    public static final double EPSILON_DECELERATION = 0.002;
    public static final double EPSILON_MAX_SPEED = 0.5;
    public static final int FPS = 10;
    public static final int UPS = 10;
    public static final int EPSILON_DECELERATION_TIME = 10;
    public static final Dimension barD = new Dimension(14,38);
    public static final Dimension EPSILON_DIMENSION = new Dimension(25 ,25);
    public static final Dimension TRIGORATH_DIMENTION = new Dimension(50 ,50);
    public static final Dimension Squarantine_DIMENTION = new Dimension(50 ,50);
    public static final double BULLET_VELOCITY = 6;
    public static final double BULLET_DIAMETER = 10;
    public static final int ID_SIZE = 10;
    public static final double EPSILON_DAMAGE = 5;
    public static Image epsilonImage;
    public static Image trigorathImage;
    public static Image squarantineImage;
    public static Image empower;
    public static Image heal;
    public static Image banish;
    public static final double ENEMY_LINEAR_SPEED = 0.1;
    public static final double ENEMY_ROTATION_SPEED = 0.03;
    public static final int DASH_TIME = 200;
    public static final double e = 1;
    public static final double AIMING_PAUSE_TIME = 100;
    public static final int FRAME_ANIMATION_REFRESH_RATE = 10;
    public static final int FRAME_BULLET_RESIZE = 50;
    public static final int FRAME_BULLET_RESIZE_TIME = 50;
    public static final String GAME_DIFFICULTY = "HARD";
}
