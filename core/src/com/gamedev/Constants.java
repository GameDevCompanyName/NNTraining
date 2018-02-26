package com.gamedev;

import com.badlogic.gdx.Gdx;

public class Constants {
    public static final int GAME_WIDTH = Gdx.graphics.getWidth();
    public static final int GAME_HEIGHT = Gdx.graphics.getHeight();
    public static final String GAME_TITLE = "Arcanoid_V2.0";

    public static final float CAMERA_WIDTH = 8f;
    public static final float CAMERA_HEIGHT = 6f;
    //public static final float CORRECT_IN_METERS_X = (float)(GAME_WIDTH)/CAMERA_WIDTH;
    //public static final float CORRECT_IN_METERS_Y = (float)(GAME_HEIGHT)/CAMERA_HEIGHT;

    public static final float BRICK_HEIGHT = 1.5f;
    public static final float BRICK_WIDTH = 3.75f;

    public static final float PLATFORM_HEIGHT = 0.08f;
    public static final float PLTFORM_WIDTH = 0.75f;
    public static final float PLATFORM_SPEED = 3.0f;
    public static final float PLATFORM_START_POSITION_X = 2;
    public static final float PLATFORM_START_POSITION_Y = 0.16f;

    public static final float BALL_RADIUS = 0.08f;
    public static final float BALL_START_POSITION_X = 2;
    public static final float BALL_START_POSITION_Y = 0.35f;
    public static  float BALL_SPEED = 100f;
}
