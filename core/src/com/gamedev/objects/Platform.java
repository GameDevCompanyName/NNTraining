package com.gamedev.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.*;

import static com.gamedev.Constants.*;

public class Platform {

    public static Body create(World world){
        Body platform;

        BodyDef bDef = new BodyDef();
        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(PLATFORM_START_POSITION_X, PLATFORM_START_POSITION_Y);
        platform = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLATFORM_WIDTH, PLATFORM_HEIGHT);
        fDef.shape = shape;

        platform.createFixture(fDef).setUserData("PLATFORM");
        shape.dispose();


        return platform;
    }

    public static void reposition(Body body) {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.setTransform(body.getPosition().x + PLATFORM_SPEED, body.getPosition().y, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.setTransform(body.getPosition().x - PLATFORM_SPEED, body.getPosition().y, 0);
        }
        if (body.getPosition().x > 80 - PLATFORM_WIDTH) {
            body.setTransform(80 - PLATFORM_WIDTH, body.getPosition().y, 0);
        }
        if (body.getPosition().x < 0 + PLATFORM_WIDTH) {
            body.setTransform(0 + PLATFORM_WIDTH, body.getPosition().y, 0);
        }
    }
}
