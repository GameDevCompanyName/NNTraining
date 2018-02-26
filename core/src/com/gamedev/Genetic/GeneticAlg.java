package com.gamedev.Genetic;

import com.gamedev.NNTraining;
import com.gamedev.neural.NN;

import java.util.Queue;

public class GeneticAlg {

    private Generation generationNOTtested;
    private Individual currentTesting;
    private Generation generationTESTED;

    private NNTraining nnTraining;
    private NN nnet;

    public GeneticAlg(NNTraining nnTraining, NN skyNet) {
        this.nnTraining = nnTraining;
        nnet = skyNet;
        this.generationNOTtested = Generation.generateRandom(nnet);
        this.generationTESTED = Generation.createUpper(generationNOTtested);
        this.currentTesting = generationNOTtested.getNextIndividual();
    }

    public void individFailed(Long l) {
        currentTesting.setFitness(l);
        generationTESTED.addIndividual(currentTesting);
        if (generationNOTtested.isEmpty()){
            generationNOTtested = generationTESTED;
            generationNOTtested.evolute(nnet);
            generationTESTED = Generation.createUpper(generationNOTtested);
        }
        currentTesting = generationNOTtested.getNextIndividual();
        nnTraining.setNewNN(currentTesting.getChromosome());
        nnTraining.replay();
    }

    private void proceed(){

    }

    public int getGeneration() {
        return generationNOTtested.generation;
    }

    public int getID() {
        return currentTesting.getID();
    }

    public int getLeft() {
        return generationNOTtested.indToTest.size();
    }

    public int getQuantity() {
        return generationNOTtested.individuals.size();
    }

    public float[] getGenome() {
        return currentTesting.getChromosome();
    }
}
