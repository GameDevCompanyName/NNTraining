package com.gamedev.neural.Parts;

public class InputNeuron extends Neuron {

    private float currentInputState;

    @Override
    public float getValue(){
        return (float) Math.tan(currentInputState * 0.8);
    }

    public void changeState(float val){
        this.currentInputState = val;
    }

}
