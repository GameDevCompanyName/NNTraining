package com.gamedev.Genetic;

import com.gamedev.neural.NN;

import java.util.*;

public class Generation {

    public static final int START_INDIVS = 20;
    public static final int BEST_INDS = 5;

    public List<Individual> individuals;
    public Queue<Individual> indToTest;
    int generation;

    public Generation(List<Individual> individuals, int generation) {
        this.individuals = individuals;
        this.generation = generation;
    }

    public static Generation generateRandom(NN nnet) {

        Generation newGen = new Generation(new ArrayList<Individual>(), 1);
        newGen.indToTest = new ArrayDeque<>();
        for (int i = 0; i < START_INDIVS; i++){
            Individual ind = new Individual(nnet.generateRandomWeights());
            newGen.individuals.add(ind);
            newGen.indToTest.add(ind);
        }
        return newGen;

    }

    public static Generation createUpper(Generation generation) {


        Generation generation1 = new Generation(new ArrayList<Individual>(), generation.generation+1);
        generation1.indToTest = new ArrayDeque<>();
        return generation1;

    }

    public void addIndividual(Individual currentTesting) {
        individuals.add(currentTesting);
    }

    public boolean isEmpty() {
        return indToTest.isEmpty();
    }

    public Individual getNextIndividual() {
        return indToTest.poll();
    }

    public void evolute(NN nnet) {

        Collections.sort(individuals, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                long f1 = o1.getFitness();
                long f2 = o2.getFitness();
                if (f1 < f2)
                    return -1;
                else
                if (f1 > f2)
                    return 1;
                else
                    return 0;
            }
        });
        Collections.reverse(individuals);

        Individual[] bestOfInds = new Individual[BEST_INDS];
        individuals.subList(0, BEST_INDS).toArray(bestOfInds);

        List<Individual> newInds = new ArrayList<>();

        for (int i = 0; i < bestOfInds.length-1; i++){
            for (int j = i+1; j < bestOfInds.length; j++){
                newInds.addAll(Individual.cross(bestOfInds[i], bestOfInds[j]));
                newInds.addAll(Individual.cross(bestOfInds[i], new Individual(nnet.generateRandomWeights())));
                newInds.addAll(Individual.cross(new Individual(nnet.generateRandomWeights()), bestOfInds[j]));
            }
            bestOfInds[i].setFitness(null);
            newInds.add(bestOfInds[i]);
            newInds.add(new Individual(nnet.generateRandomWeights()));
            newInds.add(new Individual(nnet.generateRandomWeights()));
            newInds.add(new Individual(nnet.generateRandomWeights()));

        }

        individuals = newInds;
        indToTest.addAll(individuals);

    }

}
