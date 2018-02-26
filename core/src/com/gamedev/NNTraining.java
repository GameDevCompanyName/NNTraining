package com.gamedev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gamedev.Genetic.GeneticAlg;
import com.gamedev.neural.NN;
import com.gamedev.objects.Ball;
import com.gamedev.objects.GameBox;
import com.gamedev.objects.Platform;

import java.util.Arrays;

import static com.gamedev.Constants.*;
import static com.gamedev.Constants.PLATFORM_START_POSITION_Y;

public class NNTraining extends ApplicationAdapter {

    private static byte direction = 0;
    private static boolean start = false;

    private NN skyNet;

    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private SpriteBatch labelBatch;

    private int counter = 1;

    private Long maxFitness = 0l;
    private float[] bestGenome = new float[21];

    private GeneticAlg geneticAlg;
    private long startTime, endTime;

    private Label label;

    private Body platform, ball, gameBox;

    @Override
    public void create() {
        skyNet = new NN();
        skyNet.setWeights(skyNet.generateRandomWeights());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 8f, 6f);
        renderer = new Box2DDebugRenderer();
        world = new World(new Vector2(0, 0), true);

        platform = Platform.create(world);
        ball = Ball.creat(world);
        label = new Label("0", labelStyle());
        label.setPosition(15, 550);
        labelBatch = new SpriteBatch();
        gameBox = GameBox.creat(world);
        startTime = System.currentTimeMillis();
        geneticAlg = new GeneticAlg(this, skyNet);
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
        labelBatch.begin();
        label.setText(Long.toString((endTime - startTime)/100) + " (" + geneticAlg.getGeneration() + " gen)" +
        "\nINDS: " + geneticAlg.getLeft() + "/" + geneticAlg.getQuantity() +
                "\nID = " + geneticAlg.getID());
        label.draw(labelBatch, 1.0f);
        labelBatch.end();
        camera.update();
        endTime = System.currentTimeMillis();

        if (ball.getPosition().y < 0){
            indDied();
        }

        float decision = skyNet.think(platform.getPosition().x,
                ball.getPosition().x - platform.getPosition().x,
                ball.getPosition().y,
                ball.getLinearVelocity().x,
                ball.getLinearVelocity().y);

        if (decision > 0.01) direction = 1;
        else
            if (decision < -0.01) direction = -1;
        else
            direction = 0;

        /*
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) direction = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) direction = -1;
        if (Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)) direction = 0;
        */

        if (direction != 0) go();
    }

    public void indDied(){
        Long fitness = (endTime - startTime)/20 - (long) (Math.abs(platform.getPosition().x - ball.getPosition().x))/2;
        if (fitness > maxFitness){
            maxFitness = fitness;
            bestGenome = geneticAlg.getGenome();
        }
        System.out.println("--------------");
        System.out.println("Тест: " + counter);
        System.out.println("Геном: " + Arrays.toString(geneticAlg.getGenome()));
        System.out.println("Fitness: " + fitness);
        System.out.println("!!!BEST: " + maxFitness);
        System.out.println("BEST_Геном: " + Arrays.toString(bestGenome));
        counter++;
        geneticAlg.individFailed(fitness);
    }

    @Override
    public void dispose() {
        renderer.dispose();
        world.dispose();
    }

    public void setNewNN(float[] weights){
        skyNet.setWeights(weights);
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

    private Label.LabelStyle labelStyle(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/captureit.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.color = Color.WHITE;
        p.size = 15;
        BitmapFont font = gen.generateFont(p);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        return labelStyle;
    }

    public void replay() {
        ball.setLinearVelocity(0,0);
        direction = 0;
        platform.setTransform(PLATFORM_START_POSITION_X, PLATFORM_START_POSITION_Y, 0);
        ball.setTransform(BALL_START_POSITION_X, BALL_START_POSITION_Y,0);
        start = false;
        startTime = System.currentTimeMillis();
    }
}
