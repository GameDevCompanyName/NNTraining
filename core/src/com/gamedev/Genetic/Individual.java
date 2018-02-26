package com.gamedev.Genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Individual {

    public static final int CHROM_QUANTITY = 24;

    private float[] chromosome;
    private int ID;
    private Long fitness;

    public Individual(float[] chromosome) {
        this.chromosome = chromosome;
        fitness = null;
        ID = new Random().nextInt(100000);
    }

    public float[] getChromosome() {
        return chromosome;
    }

    public void setFitness(Long fitness) {
        this.fitness = fitness;
    }

    public Long getFitness() {
        return fitness;
    }

    public int getID() {
        return ID;
    }

    public static List<Individual> cross(Individual i1, Individual i2) {

        Random random = new Random();

        float[] newCrom1 = new float[i1.chromosome.length];
        float[] newCrom2 = new float[i1.chromosome.length];
        float[] newCrom3 = new float[i1.chromosome.length];
        float[] newCrom4 = new float[i1.chromosome.length];

        for (int i = 0; i < i1.chromosome.length; i++){
            newCrom2[i] = (i1.chromosome[i] + i2.chromosome[i])/2;

            if (random.nextBoolean())
                newCrom1[i] = i1.chromosome[i];
            else
                newCrom1[i] = i2.chromosome[i];

            if (random.nextBoolean())
                newCrom3[i] = i1.chromosome[i];
            else
                newCrom3[i] = (i1.chromosome[i] + random.nextFloat()*200-100)/2;

            if (random.nextBoolean())
                newCrom4[i] = i2.chromosome[i];
            else
                newCrom4[i] = (i2.chromosome[i] + random.nextFloat()*200-100)/2;

        }

        List<Individual> newInds = new ArrayList<Individual>();
        newInds.add(new Individual(newCrom1));
        newInds.add(new Individual(newCrom2));
        newInds.add(new Individual(newCrom3));
        newInds.add(new Individual(newCrom4));

        return newInds;

    }
}
