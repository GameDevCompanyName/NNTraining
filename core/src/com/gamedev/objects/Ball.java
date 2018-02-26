package com.gamedev.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import static com.gamedev.Constants.*;
import static com.gamedev.Constants.BALL_RADIUS;

public class Ball {

    public static Body creat(World world){
        Body ball;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(BALL_START_POSITION_X, BALL_START_POSITION_Y);
        ball = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(BALL_RADIUS);
        fDef.shape = shape;
        fDef.restitution = 1;
        fDef.friction = 0;
        ball.createFixture(fDef);
        shape.dispose();

        return ball;
    }

}
