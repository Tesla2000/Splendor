package edu.ib.splendor;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        int trials = 100_000;
        int[] scores = new int[1000];
        for (int j=0;j<trials;j++){
            int score = 0;
            for (int i = 0; i < scores.length; i++) {
                if (random.nextDouble() < 1 / 4.0) score++;
            }
            scores[score]++;
        }
        for (int i=1;i<scores.length;i++) scores[i] += scores[i-1];
        for (int i=0;i< scores.length;i++){
            System.out.println(i + " " + (double)scores[i]/trials);
        }
    }
}
