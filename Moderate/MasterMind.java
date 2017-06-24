package Moderate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by sycai on 6/24/2017.
 * CCI 16.15 The Game of Master Mind is played as follows:
 *
 * The computer has four slots, and each slot will contain a ball that is red(R), yellow(Y), green(G) or blue(B). For
 * example, the computer might have RGGB (Slot #1 is red, Slots #2 and #3 are green, Slot #4 is blue).
 *
 * You, the user, are trying to guess the solution. You might, for example, guess YRGB.
 *
 * When you guess the correct color for the correct slot, you get a "hit". If you guess a color that exists but is in
 * the wrong slot, you get a "pseudo-hit". Note that a slot that is a hit can never count as a pseudo-hit.
 *
 * For Example, if the actual solution is RGBY and you guess GGRR, you have one hit and one pseudo-hits. Write a method
 * that, given a guess and a solution, returns the number of hits and pseudo-hits.
 */
public class MasterMind {
    enum Colors {R, Y, G, B}
    public static void playMasterMind(Colors[] guess, Colors[] solution) {
        if (guess.length != solution.length)    return;

        HashMap<Colors, Integer> frequencies = new HashMap<>();
        int hitCount = 0, pseudoHitCount = 0;

        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == solution[i]) {
                hitCount++;
            } else {
                frequencies.put(solution[i], frequencies.getOrDefault(solution[i], 0) + 1);
            }
        }

        for (int i = 0; i < guess.length; i++) {
            Colors g = guess[i];
            if (frequencies.containsKey(g) && frequencies.get(g) > 0 && g != solution[i]) {
                pseudoHitCount++;
                frequencies.put(g, frequencies.get(g) - 1);
            }
        }

        System.out.println("Hit: " + hitCount);
        System.out.println("Pseudo-hit: " + pseudoHitCount);
    }

    public static void main(String[] args) {
        Colors[] guess = {Colors.G, Colors.G, Colors.R, Colors.R};
        Colors[] solution = {Colors.R, Colors.G, Colors.B, Colors.Y};
        playMasterMind(guess, solution);
    }
}
