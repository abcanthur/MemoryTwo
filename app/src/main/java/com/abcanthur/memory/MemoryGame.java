package com.abcanthur.memory;

import android.util.Log;

import java.util.Random;

/**
 * Created by paulmtz on 11/12/16.
 */

public class MemoryGame {

    public enum FlipResult {
        FIRST_FLIP,
        NO_MATCH,
        MATCH,
        ERR_IS_CURRENTLY_FLIPPED,
        ERR_ALREADY_MATCHED,
        ERR_INDEX_OUT_OF_BOUNDS,
    }

    int[] cards;
    boolean[] isMatched;
    int currentlyFlippedIndex;

    public MemoryGame(int numPairs) {
        this(numPairs, new Random());
    }

    public MemoryGame(int numPairs, Random generator) {
        cards = new int[2 * numPairs];
        isMatched = new boolean[2 * numPairs];
        for (int i = 0; i < 2 * numPairs; i++) {
            cards[i] = i / 2;
        }
        shuffle(cards, generator);
        currentlyFlippedIndex = -1;
    }

    private void shuffle(int[] arr, Random generator) {
        for (int i = arr.length - 1; i > 0; i--) {
            int index = generator.nextInt(i + 1);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }

    }

    public FlipResult flipCard(int pos) {
        if (pos < 0 || cards.length <= pos) {
            return FlipResult.ERR_INDEX_OUT_OF_BOUNDS;
        }
        if (pos == currentlyFlippedIndex) {
            return FlipResult.ERR_IS_CURRENTLY_FLIPPED;
        }
        if (isMatched[pos]) {
            return FlipResult.ERR_ALREADY_MATCHED;
        }
        if (currentlyFlippedIndex == -1) {
            currentlyFlippedIndex = pos;
            return FlipResult.FIRST_FLIP;
        }
        if (cards[pos] == cards[currentlyFlippedIndex]) {
            isMatched[pos] = true;
            isMatched[currentlyFlippedIndex] = true;
            currentlyFlippedIndex = -1;
            return FlipResult.MATCH;
        } else {
            currentlyFlippedIndex = -1;
            return FlipResult.NO_MATCH;
        }

    }
}
