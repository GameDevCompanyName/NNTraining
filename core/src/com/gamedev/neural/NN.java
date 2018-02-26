package com.gamedev.neural;

import com.gamedev.neural.Parts.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NN {

    public static final int INPUT_NEURONS = 4;
    public static final int HIDDEN_NEURONS = 4;

    private List<InputNeuron> inputNeurons;
    private SdvigNeuron sdvigNeuron;
    private List<HiddenNeuron> hiddenNeurons;
    private OutputNeuron outputNeuron;
    private List<Connection> connections;

    public NN(float[] weights) {

        inputNeurons = new ArrayList<>();
        for (int i = 0; i < INPUT_NEURONS; i++){
            inputNeurons.add(new InputNeuron());
        }

        sdvigNeuron = new SdvigNeuron();

        hiddenNeurons = new ArrayList<>();
        for (int i = 0; i < HIDDEN_NEURONS; i++){
            hiddenNeurons.add(new HiddenNeuron());
        }

        outputNeuron = new OutputNeuron();

        connections = new ArrayList<>();

        for (InputNeuron inpN: inputNeurons) {
            for (HiddenNeuron hidN: hiddenNeurons) {
                Connection connection = new Connection(inpN, hidN, 1);
                hidN.addConnection(connection);
                connections.add(connection);
            }
        }

        for (HiddenNeuron hidN: hiddenNeurons){
            Connection connection = new Connection(sdvigNeuron, hidN, 1);
            hidN.addConnection(connection);
            connections.add(connection);
        }

        for (HiddenNeuron hidN: hiddenNeurons){
            Connection connection = new Connection(hidN, outputNeuron, 1);
            outputNeuron.addConnection(connection);
            connections.add(connection);
        }

        int index = 0;

        for (Connection connection: connections) {
            connection.setWeight(weights[index]);
            index++;
        }

    }

    public NN() {

        inputNeurons = new ArrayList<>();
        for (int i = 0; i < INPUT_NEURONS; i++){
            inputNeurons.add(new InputNeuron());
        }

        sdvigNeuron = new SdvigNeuron();

        hiddenNeurons = new ArrayList<>();
        for (int i = 0; i < HIDDEN_NEURONS; i++){
            hiddenNeurons.add(new HiddenNeuron());
        }

        outputNeuron = new OutputNeuron();

        connections = new ArrayList<>();

        for (InputNeuron inpN: inputNeurons) {
            for (HiddenNeuron hidN: hiddenNeurons) {
                Connection connection = new Connection(inpN, hidN, 1);
                hidN.addConnection(connection);
                connections.add(connection);
            }
        }

        for (HiddenNeuron hidN: hiddenNeurons){
            Connection connection = new Connection(sdvigNeuron, hidN, 1);
            hidN.addConnection(connection);
            connections.add(connection);
        }

        for (HiddenNeuron hidN: hiddenNeurons){
            Connection connection = new Connection(hidN, outputNeuron, 1);
            outputNeuron.addConnection(connection);
            connections.add(connection);
        }

    }

    public float think(float X, float ballY, float ballSpeedX, float ballSpeedY){
        inputNeurons.get(0).changeState(X);
        inputNeurons.get(1).changeState(ballY);
        inputNeurons.get(2).changeState(ballSpeedX);
        inputNeurons.get(3).changeState(ballSpeedY);
        return outputNeuron.getValue();
    }

    public float[] generateRandomWeights(){

        Random random = new Random();

        float[] weights = new float[connections.size()];

        for (int i = 0; i < connections.size(); i++){
            weights[i] = random.nextFloat() * 200 - 100;
        }

        return weights;

    }

    public void setWeights(float[] weights){
        int index = 0;
        for (Connection connection: connections) {
            connection.setWeight(weights[index]);
            index++;
        }
    }

    public float[] getWeights(){

        float[] weights = new float[connections.size()];

        int index = 0;
        for (Connection connection: connections) {
            weights[index] = connection.getWeight();
            index++;
        }

        return weights;

    }

}