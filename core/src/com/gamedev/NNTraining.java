package com.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.gamedev.objects.Ball;
import com.gamedev.objects.GameBox;
import com.gamedev.objects.Platform;

import static com.gamedev.Constants.*;
import static com.gamedev.Constants.PLATFORM_START_POSITION_Y;

public class NNTraining extends ApplicationAdapter {

    private static byte direction = 0;
    private static boolean start = false;

    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Body platform, ball, gameBox;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 80f, 60f);
        renderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);

        platform = Platform.create(world);
        ball = Ball.creat(world);
        gameBox = GameBox.creat(world);
    }

    @Override
    public void render() {
        //Gdx.gl.glClearColor(1, 0, 0, 1);

        if (!start){
            ball.setLinearVelocity(BALL_SPEED, BALL_SPEED);
            start = true;
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1/60f, 6, 2);
        renderer.render(world, camera.combined);
        camera.update();

        if (ball.getPosition().y < 0){
            ball.setLinearVelocity(0,0);
            direction = 0;
            platform.setTransform(PLATFORM_START_POSITION_X, PLATFORM_START_POSITION_Y, 0);
            ball.setTransform(BALL_START_POSITION_X, BALL_START_POSITION_Y,0);
            start = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) direction = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) direction = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) direction = 0;

        if (direction != 0) go();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        world.dispose();
    }

    private void go(){
        if (platform.getPosition().x > 80 - PLTFORM_WIDTH) {
            platform.setTransform(80 - PLTFORM_WIDTH, platform.getPosition().y, 0);
        }
        if (platform.getPosition().x < 0 + PLTFORM_WIDTH) {
            platform.setTransform(0 + PLTFORM_WIDTH, platform.getPosition().y, 0);
        }
        if (direction == 1){
            platform.setTransform(platform.getPosition().x + PLATFORM_SPEED, platform.getPosition().y, 0);
        }
        if (direction == -1){
            platform.setTransform(platform.getPosition().x - PLATFORM_SPEED, platform.getPosition().y, 0);
        }
    }
}
