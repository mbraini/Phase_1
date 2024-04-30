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
    public static final Dimension barD = new Dimension(14,37);
    public static final Dimension EPSILON_DIMENSION = new Dimension(25 ,25);
    public static final Dimension TRIGORATH_DIMENTION = new Dimension(50 ,50);
    public static final Dimension Squarantine_DIMENTION = new Dimension(50 ,50);
    public static final double BULLET_VELOCITY = 6;
    public static final double BULLET_DIAMETER = 10;
    public static final int ID_SIZE = 10;
    public static final double EPSILON_DAMAGE = 5;
    public static final int MELEI_ATTACK = 5;
    public static final Dimension MINIMUM_FRAME_DIMENSION = new Dimension(200 ,200);
    public static final double IMPACT_AREA = 200;
    public static final int ENEMY_SPAWN_MARGIN = 500;
    public static Image epsilonImage;
    public static Image trigorathImage;
    public static Image squarantineImage;
    public static Image empower;
    public static Image heal;
    public static Image banish;
    public static final double ENEMY_LINEAR_SPEED = 0.2;
    public static final double COLLECTIVE_RADIOS = 5;
    public static final int COLLECTIVE_FADE = 7000;
    public static final double COLLECTIVE_VELOCITY = 0.6;
    public static final int COLLECTIVE_ABILITY_ACTIVATION_RADIOS = 100;

    public static final double ENEMY_ROTATION_SPEED = Math.PI * 2 / 1000;
    public static final int DASH_TIME = 500;
    public static final double AIMING_PAUSE_TIME = 100;
    public static final int FRAME_ANIMATION_REFRESH_RATE = 10;
    public static final int FRAME_BULLET_RESIZE = 50;
    public static final int FRAME_BULLET_RESIZE_TIME = 200;
    public static final double FRAME_PRESSURE_VELOCITY = 0.02;
    public static final Dimension COLLECTIVE_BOX_DIMENSION = new Dimension(100 ,100);
    public static final int EPSILON_VERTICES_RADIOS = 3;
    public static final String GAME_DIFFICULTY = "HARD";
}
