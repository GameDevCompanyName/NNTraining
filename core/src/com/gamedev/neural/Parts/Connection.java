package com.gamedev.neural.Parts;

public class Connection {

    private Neuron inputNeuron;
    private Neuron outputNeuron;
    private float weight;

    public Connection(Neuron inputNeuron, Neuron outputNeuron, float weight) {
        this.inputNeuron = inputNeuron;
        this.outputNeuron = outputNeuron;
        this.weight = weight;
    }

    public float getValue() {
        return inputNeuron.getValue() * weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight){
        this.weight = weight;
    }

}
