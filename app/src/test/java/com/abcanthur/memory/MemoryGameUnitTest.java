package com.abcanthur.memory;

/**
 * Created by paulmtz on 11/12/16.
 */

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MemoryGameUnitTest {
    private MemoryGame createGame() {
        // creates array {0, 3, 1, 2, 3, 0, 1, 2}
        return new MemoryGame(4, new Random(0));
    }

    @Test
    public void flipCardErrIndexOutOfBounds() throws Exception {
        MemoryGame game = createGame();
        assertEquals(MemoryGame.FlipResult.ERR_INDEX_OUT_OF_BOUNDS, game.flipCard(8));
        assertEquals(MemoryGame.FlipResult.ERR_INDEX_OUT_OF_BOUNDS, game.flipCard(-1));
    }

    @Test
    public void flipCardErrIsCurrentlyFlipped() throws Exception {
        MemoryGame game = createGame();
        game.flipCard(0);
        assertEquals(MemoryGame.FlipResult.ERR_IS_CURRENTLY_FLIPPED, game.flipCard(0));
    }

    @Test
    public void flipCardErrAlreadyMatched() throws Exception{
        MemoryGame game = createGame();
        game.flipCard(0);
        game.flipCard(5);
        assertEquals(MemoryGame.FlipResult.ERR_ALREADY_MATCHED, game.flipCard(0));
        assertEquals(MemoryGame.FlipResult.ERR_ALREADY_MATCHED, game.flipCard(5));
    }

    @Test
    public void flipCardFirstFlip() throws Exception {
        for (int i = 0; i < 8; i++) {
            MemoryGame game = createGame();
            assertEquals(MemoryGame.FlipResult.FIRST_FLIP, game.flipCard(i));
            game.flipCard((i + 1) % 8);
            assertEquals(MemoryGame.FlipResult.FIRST_FLIP, game.flipCard(i));
        }
    }

    @Test
    public void flipCardMatch() throws Exception {
        MemoryGame game = createGame();
        game.flipCard(0);
        assertEquals(MemoryGame.FlipResult.MATCH, game.flipCard(5));
        game.flipCard(1);
        assertEquals(MemoryGame.FlipResult.MATCH, game.flipCard(4));
        game.flipCard(2);
        assertEquals(MemoryGame.FlipResult.MATCH, game.flipCard(6));
        game.flipCard(3);
        assertEquals(MemoryGame.FlipResult.MATCH, game.flipCard(7));
    }

    @Test
    public void flipCardNoMatch() throws Exception {
        MemoryGame game = createGame();
        int[] secondFlips = new int[] {1, 2, 3, 4, 6, 7};
        for(int i = 0; i < 6; i++) {
            game.flipCard(0);
            assertEquals(MemoryGame.FlipResult.NO_MATCH, game.flipCard(secondFlips[i]));
        }
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

}
