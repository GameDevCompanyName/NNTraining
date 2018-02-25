package com.gamedev.neural.NNTest;

import com.gamedev.neural.NN;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        NN nnet = new NN();
        Random random = new Random();

        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;
        float sumOfAll = 0;
        int numberOfTests = 100000;

        for (int i = 0; i < numberOfTests; i++){
            float val1 = random.nextFloat() * 10000 - 5000;
            float val2 = random.nextFloat() * 10000 - 5000;
            float val3 = random.nextFloat() * 10000 - 5000;
            float val4 = random.nextFloat() * 10000 - 5000;
            float val5 = random.nextFloat() * 10000 - 5000;
            System.out.println("---------");
            System.out.println("Values: " + val1 + "  " + val2 + "  " + val3 + "  " + val4 + "  " + val5);
            float think = nnet.think(val1,val2,val3,val4,val5);
            sumOfAll += think;
            if (think < min)
                min = think;
            if (think > max)
                max = think;
            System.out.println("Think: " + think);
            System.out.println("---------");
        }

        System.out.println("=============\n\n");
        System.out.println("MAX = " + max);
        System.out.println("MIN = " + min);
        System.out.println("MIDDLE = " + sumOfAll/numberOfTests);
        System.out.println("\nFROM MAX TO MID = " + (max - sumOfAll/numberOfTests));
        System.out.println("FROM MIN TO MID = " + (sumOfAll/numberOfTests - min));
        System.out.println("INEQUALITY = " + Math.abs((max - sumOfAll/numberOfTests) - (sumOfAll/numberOfTests - min))/2);
    }


}
