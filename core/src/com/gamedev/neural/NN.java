package com.gamedev.neural;

import com.gamedev.neural.Parts.*;

import java.util.ArrayList;
import java.util.List;

public class NN {

    public static final int INPUT_NEURONS = 5;
    public static final int HIDDEN_NEURONS = 3;

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

    public float think(float plateX, float ballX, float ballY, float ballSpeedX, float ballSpeedY){
        inputNeurons.get(0).changeState(plateX);
        inputNeurons.get(1).changeState(ballX);
        inputNeurons.get(2).changeState(ballY);
        inputNeurons.get(3).changeState(ballSpeedX);
        inputNeurons.get(4).changeState(ballSpeedY);
        return outputNeuron.getValue();
    }

}