package com.gamedev.neural.Parts;

import java.util.List;
import java.util.Random;

public class Neuron {

    protected List<Connection> inputs;
    protected int id = new Random().nextInt();

    public float getValue(){
        float sum = 0f;
        for (Connection con: inputs) {
            sum += con.getValue();
        }
        //return (float) (1 / (1 + Math.exp(-0.2 * sum)));
        return (float) Math.pow(Math.tanh(sum/2), 3);
    }

    public void addConnection(Connection connection){
        inputs.add(connection);
    }

}
