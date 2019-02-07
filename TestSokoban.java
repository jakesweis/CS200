//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Sokoban
// Files: MyLevels.java
// Course: CS 200 Fall 2018
//
// Author: Jacob Sweis
// Email: jdsweis@wisc.edu
// Lecturer's Name: Jim Williams
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Galen Quinn
// Partner Email: gaquinn@wisc.edu
// Lecturer's Name: Jim Williams
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
/**
 * This file contains testing methods for the Sokoban project. These methods are intended to provide
 * an example of a way to incrementally test your code, and to provide example method calls for the
 * Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is to
 * write some tests and write header comments summarizing the tests that have been written. Specific
 * places are noted with FIXME but add any other comments you feel would be useful.
 */
/**
 * This file contains testing methods for the Sokoban project. These methods are intended to provide
 * an example of a way to incrementally test your code, and to provide example method calls for the
 * Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is to
 * write some tests and write header comments summarizing the tests that have been written. Specific
 * places are noted with FIXME but add any other comments you feel would be useful.
 */

/**
 * This file contains testing methods for the Sokoban project. These methods are intended to provide
 * an example of a way to incrementally test your code, and to provide example method calls for the
 * Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is to
 * write some tests and write header comments summarizing the tests that have been written. Specific
 * places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban class as they are developed.
 * These methods are all private as they are only intended for use within this class.
 * 
 * @author Marc Renault
 * @author Jacob Sweis
 *
 */
public class TestSokoban {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {
        // Milestone 1
        testCheckLevel();
        // Milestone 2
        testInitBoard();
        testCheckWin();
        testCalcDelta();
        testCheckDelta();
        // Milestone 3
        testTogglePos();
        testShiftBox();
        testDoMove();
        testProcessMove();
    }

    /**
     * This method runs 4 tests for the checkLevel method.
     */
    private static void testCheckLevel() {
        int numTests = 4; // Number of tests
        int passed = numTests; // Number of tests that passed
        int res; // Result of method call
        // Test 1: Method should return 0 if level is negative
        if ((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
            passed--;
        }

        // Test 2: Method should return -1 if level is not a valid index in levels, or if the 2-d
        // array at index level does not exist, or if level does not contain 1 row
        char[][][] lvl = new char[2][][];
        if ((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
            passed--;
        }

        // Test 3: Method should return -2 if level does not contain an even number of cells
        int[][] goals = new int[2][];
        if ((res = Sokoban.checkLevel(1, Config.LEVELS, goals)) != -2) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 3. Expected -3, but value returned" + res);
        }

        // Test 4: Method should return -3 if the number of boxes in level is less than or equal to
        // 0
        char[][][] test4 = new char[3][3][3];
        if ((res = Sokoban.checkLevel(1, test4, Config.GOALS)) != -3) {
            System.out.println(
                "FAILED: Sokoban.checkLevel Test 4. Expected -1, but value returned " + res);
            passed--;
        }

        System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * Returns true if the arrays are the same size and have the same contents.
     */
    private static boolean compBoards(char[][] a, char[][] b) {
        if (a == null || b == null)
            return false;
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method runs 2 tests for the initBoard method.
     */
    private static void testInitBoard() {
        int numTests = 2; // Number of tests
        int passed = numTests; // Number of tests that passed

        // Test 1: Method should pass if initial position is as expected
        int[] pTest1 = new int[2];
        char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);
        if (!Arrays.equals(pTest1, new int[] {4, 4})) {
            System.out.println(
                "FAILED: Sokoban.initBoard Test 1. Expected initial position: [4, 4] , but value after call "
                    + Arrays.toString(pTest1));
            passed--;
        } // This statement should return true.
        char[][] bCompTest1 = new char[][] { // Board used to compare
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};
        // If boards are not the same, test 1 fails.
        if (!compBoards(bTest1, bCompTest1)) {
            System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest1);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest1);
            passed--;
        } // compBoards should return true.

        // Test 2: Method should pass if initial position is as expected
        int[] pTest2 = new int[2];
        char[][] bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest2);
        if (!Arrays.equals(pTest2, new int[] {6, 6})) {
            System.out.println(
                "PASSED: Sokoban.initBoard Test 1. Expected failure. Expected initial position: {6, 6} , but value after call "
                    + Arrays.toString(pTest2));
        } // This statement should return false.
        char[][] bCompTest2 = new char[][] {
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.BOX_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
                Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
                Config.GOAL_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR,
                Config.GOAL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR}};
        // Test 3
        // If boards are not the same, test fails.
        if (!compBoards(bTest2, bCompTest2)) {
            System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
            System.out.println("Generated:");
            Sokoban.printBoard(bTest2);
            System.out.println("Expected:");
            Sokoban.printBoard(bCompTest2);
            passed--;
        } // compBoards should return true.

        System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method runs 2 tests for the checkWin method.
     */
    private static void testCheckWin() {
        int numTests = 2; // Number of tests
        int passed = numTests; // Number of tests that passed
        boolean check; // Check for method call

        char[][] bCompTest1 = new char[][] { // Board used to compare
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WORKER_CHAR}};

        // Test 1: If method returns false, test fails.
        if ((check = Sokoban.checkWin(bCompTest1)) != true) {
            System.out.println("FAILED: Sokoban.checkWin Test 1. All goals not covered by boxes!");
            passed--;
        } // Should return true

        // Test 2: If method returns false, test passes.
        if ((check = Sokoban.checkWin(Config.LEVELS[1])) != true) {
            System.out.println(
                "FAILED: Sokoban.checkWin Test 2. Expected failure. Not all goals covered by boxes. No tests passed reduction.");
        } // Should return false

        System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method runs 3 tests for the calcDelta method.
     */
    private static void testCalcDelta() {
        int numTests = 3; // Number of tests
        int passed = numTests; // Number of tests that passed

        // Test 1: Checks if input from calcDelta contains {-1, 0}
        String moveStr = "81"; // Move entered
        int[] mvInput1 = Sokoban.calcDelta(moveStr); // Value from calcDelta

        if (mvInput1[0] != -1 || mvInput1[1] != 0) {
            System.out
                .println("FAILED: Sokoban.calcDelta Test 1. Expected {-1, 0}. Instead, received:  "
                    + Arrays.toString(mvInput1));
            passed--;
        }

        // Test 2: Checks if input from calcDelta contains {2, 0}
        String moveStr2 = "22"; // Move entered
        int[] mvInput2 = Sokoban.calcDelta(moveStr2); // Value from calcDelta

        if (mvInput2[0] != 2 || mvInput2[1] != 0) {
            System.out
                .println("FAILED: Sokoban.calcDelta Test 2. Expected {2, 0}. Instead, received: "
                    + Arrays.toString(mvInput2));
            passed--;
        }

        // Test 3: Checks if input from calcDelta contains {0, 5}
        String moveStr3 = "65"; // Move entered
        int[] mvInput3 = Sokoban.calcDelta(moveStr3); // Value from calcDelta

        if (mvInput3[0] != 0 || mvInput3[1] != 5) {
            System.out
                .println("FAILED: Sokoban.calcDelta Test 3. Expected {0, 5}. Instead, received: "
                    + Arrays.toString(mvInput3));
            passed--;
        }

        System.out.println("testCalcDelta: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method runs 2 tests for the checkDelta method.
     */
    private static void testCheckDelta() {
        int numTests = 2; // Number of tests
        int passed = numTests; // Number of tests that passed

        char[][] board; // Board
        int[] delta = new int[2]; // Delta
        char[] validArray = {Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.GOAL_CHAR}; // Valid
                                                                                       // characters
        int[] pos = {0, 0}; // Position
        int lvl = 1; // Level 1
        String moveStr1 = "81"; // Move entered

        board = Sokoban.initBoard(lvl, Config.LEVELS, Config.GOALS, pos); // Board that is created
        delta = Sokoban.calcDelta(moveStr1); // Delta from moveStr1

        // Test 1: Checks if checkDelta returns -1. If not, test fails.
        int testCheck1 = Sokoban.checkDelta(board, pos, delta, validArray);

        if (testCheck1 != 1) {
            System.out.println(
                "FALIED: Sokoban.checkDelta Test 1. Expected 1. Instead, received: " + testCheck1);
            passed--;
        }

        // Test 2: Checks if checkDelta returns -3 for a null delta. If not, then test fails.
        delta = null; // Null delta

        pos[0] = -10; // Row position
        pos[1] = -8; // Column position
        board = Sokoban.initBoard(lvl, Config.LEVELS, Config.GOALS, pos); // Board that is created

        int testCheck2 = Sokoban.checkDelta(board, pos, delta, validArray);

        if (testCheck2 != -3) {
            System.out.println(
                "FALIED: Sokoban.checkDelta Test 2. Expected -3. Instead, received: " + testCheck2);
            passed--;
        }

        System.out.println("testCheckDelta: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method runs a test for the togglePos method.
     */
    private static void testTogglePos() {
        int numTests = 1; // Number of tests
        int passed = numTests; // Number of tests that passed

        char[][] board; // Board
        int[] pos = {0, 0}; // Position
        int lvl = 0; // Level 0

        board = Sokoban.initBoard(lvl, Config.LEVELS, Config.GOALS, pos); // Board that is created

        // Test 1: Checks if board[4][3] is a work goal Char
        Sokoban.togglePos(board, pos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);

        if (board[4][3] != Config.WORK_GOAL_CHAR) {
            System.out.println(
                "PASSED: Sokoban.togglePos Test 1. Expected failure. Expected WORK_GOAL_CHAR. Instead, received: "
                    + "#" + board[4][3] + "#");
        }

        System.out.println("testShiftBox: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method runs a test for the shiftBox method.
     */
    private static void testShiftBox() {
        int numTests = 1; // Number of tests
        int passed = numTests; // Number of tests that passed

        char[][] board; // Board
        int[] delta = new int[2]; // Delta
        int[] pos = {0, 0}; // Position
        int lvl = 1; // Level 1
        String moveStr1 = "81"; // Move entered

        board = Sokoban.initBoard(lvl, Config.LEVELS, Config.GOALS, pos); // Board that is created
        delta = Sokoban.calcDelta(moveStr1); // Delta from moveStr1

        // Test 1: Checks if shiftBox returned -2. If not, test fails.
        int testCheck1 = Sokoban.shiftBox(board, pos, delta);
        if (testCheck1 != -2) {
            System.out.println(
                "FALIED: Sokoban.shiftBox Test 1. Expected -2. Instead, received: " + testCheck1);
            passed--;
        }

        System.out.println("testShiftBox: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method runs a test for the doMove test.
     */
    private static void testDoMove() {
        int numTests = 1; // Number of tests
        int passed = numTests; // Number of tests that passed

        char[][] board; // Board
        int[] delta = new int[2]; // Delta
        int[] pos = {0, 0}; // Position
        int lvl = 1; // Level 1
        String moveStr1 = "81"; // Move entered

        board = Sokoban.initBoard(lvl, Config.LEVELS, Config.GOALS, pos); // Board that is created
        delta = Sokoban.calcDelta(moveStr1); // Delta from moveStr1

        // Test 1: Checks if doMove returns 1. If not, test fails.
        int testCheck1 = Sokoban.doMove(board, pos, delta);
        if (testCheck1 != 1) {
            System.out.println(
                "FALIED: Sokoban.doMove Test 1. Expected 1. Instead, received: " + testCheck1);
            passed--;
        }

        System.out.println("testDoMove: Passed " + passed + " of " + numTests + " tests.");
    }

    /**
     * This method runs a test for the processMove method.
     */
    private static void testProcessMove() {
        int numTests = 1; // Number of tests
        int passed = numTests; // Number of tests that passed

        char[][] board; // Board
        int[] delta = new int[2]; // Delta
        int[] pos = {0, 0}; // Position
        int lvl = 1; // Level 1
        String moveStr1 = "81"; // Move entered

        board = Sokoban.initBoard(lvl, Config.LEVELS, Config.GOALS, pos); // Board that is created
        delta = Sokoban.calcDelta(moveStr1); // Delta from moveStr1

        // Test 1: Checks if processMove returns 1. If not, test fails.
        int testCheck1 = Sokoban.processMove(board, pos, delta);
        if (testCheck1 != 1) {
            System.out.println(
                "FALIED: Sokoban.processMove Test 1. Expected 1. Instead, received: " + testCheck1);
            passed--;
        }

        System.out.println("testProcessMove: Passed " + passed + " of " + numTests + " tests.");
    }
}
