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
import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Sokoban {

    /**
     * Prompts the user for a value by displaying prompt. Note: This method should not add a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will consume an entire line of input while reading an
     * int. If the value read is between min and max (inclusive), that value is returned. Otherwise,
     * "Invalid value." terminated by a new line is output to the console and the user is prompted
     * again.
     *
     * @param sc The Scanner instance to read from System.in.
     * @param prompt The name of the value for which the user is prompted.
     * @param min The minimum acceptable int value (inclusive).
     * @param max The maximum acceptable int value (inclusive).
     * @return Returns the value read from the user.
     */
    public static int promptInt(Scanner sc, String prompt, int min, int max) {
        int userInput = 0; // User level input
        boolean checkVal = false; // Check for userInput

        System.out.print(prompt + max + ": ");
        while (checkVal == false) {

            // If user's input is an int, then userInput is assigned to the input. Otherwise, the
            // value is invalid.
            if (sc.hasNextInt()) {
                userInput = sc.nextInt();
                sc.nextLine();

                // If userInput is within max level and min level, then userInput is returned.
                // Otherwise, the value is invalid.
                if ((userInput >= min) && (userInput <= max)) {
                    return userInput;
                } else {
                    System.out.println("Invalid value.");
                    checkVal = false;
                    System.out.print(prompt + max + ": ");
                }
            }
            if (Character.isWhitespace(sc.next().charAt(0)) == true) {
                System.out.println("Invalid value.");
                checkVal = false;
            } else {
                System.out.println("Invalid value.");
                checkVal = false;
            }
            System.out.print(prompt + max + ": "); // Prompts the user to choose a level
            sc.nextLine();

        }

        return userInput;
    }

    /**
     * Prompts the user for a char value by displaying prompt. Note: This method should not be a new
     * line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input and return the first
     * non-whitespace character converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the first non-whitespace character (in lower case) read from the user. If
     *         there are no non-whitespace characters read, the null character is returned.
     */
    public static char promptChar(Scanner sc, String prompt) {
        char userChar; // Char that the user inputs

        System.out.print(prompt);

        String inputChar = sc.nextLine(); // Scans for the char the user entered
        inputChar.trim();
        inputChar.toLowerCase();

        // If input length is 0, then return null character. Otherwise, return char user entered.
        if (inputChar.length() == 0) {
            return '\u0000';
        } else {
            userChar = inputChar.charAt(0);
            return userChar;
        }
    }

    /**
     * Prompts the user for a string value by displaying prompt. Note: This method should not be a
     * new line to the output of prompt.
     *
     * After prompting the user, the method will read an entire line of input, remove any leading
     * and trailing whitespace, and return the input converted to lower case.
     *
     * @param sc The Scanner instance to read from System.in
     * @param prompt The user prompt.
     * @return Returns the string entered by the user, converted to lower case with leading and
     *         trailing whitespace removed.
     */
    public static String promptString(Scanner sc, String prompt) {
        System.out.print(prompt); // Prompts the user to enter a move

        String inputString = sc.nextLine(); // Scans for user input
        String finalString = inputString.toLowerCase().trim();

        return finalString;
    }

    /**
     * Initializes the game board to a given level. You can assume that the level at lvl has been
     * successfully verified by the checkLevel method and that pos is an array of length 2.
     *
     * 1 - The game board should be created row-by-row. a - For each row, copy the values from the
     * corresponding row in the 2-d array contained at index lvl in levels. b - When the worker is
     * located, it's position should be recorded in the pos parameter. 2 - For each goal described
     * in the array at index lvl of goals, convert the character at the goal coordinate to: -
     * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if it contains a box -
     * Config.GOAL_CHAR otherwise
     * 
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @param pos The starting pos of the worker. A length 2 array, where index 0 is the row and
     *        index 1 is the column.
     * @return A two dimension array representing the initial configuration for the given level.
     */
    public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
        char[][] board = new char[levels[lvl].length][]; // Creates board with the length of levels w/ lvl value
        for (int i = 0; i < levels[lvl].length; ++i) {
            board[i] = new char[levels[lvl][i].length]; // Creates board with the length of levels w/ lvl and i values
            for (int j = 0; j < levels[lvl][i].length; ++j) {
                board[i][j] = levels[lvl][i][j]; // Creates board with the length of levels w/ lvl and i values
                if (levels[lvl][i][j] == Config.WORKER_CHAR) { // Finds the worker char in levels
                                                               // and assigns changes the position
                                                               // to be where worker char is
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        for (int i = 0; i < goals[lvl].length; i += 2) { // Runs through goal array and places the
                                                         // goals on the board depending on whether
                                                         // a worker, box, or goal are present
            int c = i + 1;
            if (levels[lvl][goals[lvl][i]][goals[lvl][c]] == Config.WORKER_CHAR) {
                board[goals[lvl][i]][goals[lvl][c]] = Config.WORK_GOAL_CHAR;
            } else if (levels[lvl][goals[lvl][i]][goals[lvl][c]] == Config.BOX_CHAR) {
                board[goals[lvl][i]][goals[lvl][c]] = Config.BOX_GOAL_CHAR;
            } else {
                board[goals[lvl][i]][goals[lvl][c]] = Config.GOAL_CHAR;
            }
        }

        return board;
    }

    /**
     * Prints out the game board.
     * 
     * 1 - Since the game board does not contain the outer walls, print out a sequence of
     * Config.WALL_CHAR with a length equal to that of the first row of board, plus the outer wall
     * to the left and the right. 2 - For each row in board, print out a Config.WALL_CHAR, followed
     * by the contents of the row, followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence
     * of Config.WALL_CHAR with a length equal to that of the last row of board, plus the outer wall
     * to the left and the right.
     *
     * Note: each row printed out should be terminated by a new line.
     *
     * @param board The board to print.
     */
    public static void printBoard(char[][] board) {
        // Prints out the top row of walls
        for (int i = 0; i < board[0].length + 2; i++) {
            System.out.print(Config.WALL_CHAR);
        }

        System.out.println("");

        // Prints out the outer walls on the left and right of the level
        for (int i = 0; i < board.length; i++) {
            System.out.print(Config.WALL_CHAR);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println(Config.WALL_CHAR);
        }

        // Prints out the bottom row of walls
        for (int i = 0; i < board[board.length - 1].length + 2; i++) {
            System.out.print(Config.WALL_CHAR);
        }

        System.out.println("");
    }

    /**
     * Runs a given level through some basic sanity checks.
     *
     * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl is a valid index in
     * levels, that the 2-d array at index lvl exists and that it contains at least 1 row. 3 - lvl
     * is a valid index in goals, the 1-d array at index lvl exists and that it contains an even
     * number of cells. 4 - the number of boxes is less than 0. 5 - the number of boxes equals the
     * number of goals. 6 - the coordinate of each goal is valid for the given lvl and does not
     * correspond to a wall cell. 7 - the number of workers is exactly 1. 8 - check for duplicate
     * goals.
     *
     * @param lvl The index of the level to load.
     * @param levels The array containing the levels.
     * @param goals The parallel array to levels, containing the goals for the levels.
     * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test 2 fails: -1 - Test 3
     *         fails: -2 - Test 4 fails: -3 - Test 5 fails: -4 - Test 6 fails: -5 - Test 7 fails: -6
     *         - Test 8 fails: -7
     * 
     */
    public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {
        // Test 1: Checks if lvl is negative
        if (lvl < 0) {
            return 0;
        }
        // Test 2: Checks if lvl is valid index in levels, and if lvl index exists in levels, and if
        // lvl index contains at least one row
        if (lvl < 0 || lvl >= levels.length || levels[lvl] == null || levels[lvl].length < 1) {

            return -1;
        }

        // Test 3: Checks if lvl index contains an even number of cells
        if (lvl < 0 || lvl >= goals.length || goals[lvl] == null || goals[lvl].length % 2 == 1) {
            return -2;
        }

        // Test 4: Checks if the number of boxes is less or equal to 0
        int numBoxes = 0; // Number of boxes
        int numWorkers = 0; // Number of workers
        // Runs through all the elements in lvl index and counts the number of boxes
        for (int row = 0; row < levels[lvl].length; ++row) {
            for (int col = 0; col < levels[lvl][row].length; ++col) {
                if (levels[lvl][row][col] == Config.BOX_CHAR) {
                    numBoxes++;
                }
                // Runs through all elements in lvl index and counts the number of workers
                if (levels[lvl][row][col] == Config.WORKER_CHAR) {
                    numWorkers++;
                }

            }

        }
        if (numBoxes <= 0) {
            return -3;
        }

        // Test 5: Checks if number of boxes equals number of goals
        if (numBoxes != goals[lvl].length / 2) {
            return -4;
        }

        // Test 6: Checks if coordinates of goal are a wall character
        for (int i = 0; i < goals[lvl].length; i += 2) {
            int rowIndex = goals[lvl][i]; // Index of the row
            int colIndex = goals[lvl][i + 1]; // Index of the column

            // Checks if row & column indexes are valid
            if (rowIndex < 0 || rowIndex >= levels[lvl].length || colIndex < 0
                || colIndex >= levels[lvl][rowIndex].length) {
                return -5;
            }
            // Checks if lvl row & column indexes are a wall character
            if (levels[lvl][rowIndex][colIndex] == Config.WALL_CHAR) {
                return -5;
            }
        }

        // Test 7: Checks if number of workers is exactly 1
        if (numWorkers != 1) {
            return -6;
        }

        // Test 8: Checks for duplicates of goals within the levels
        for (int i = 0; i < goals[lvl].length - 1; i += 2) {
            for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {
                if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
                    return -7;
                }
            }
        }

        return 1;
    }

    /**
     * This method builds an int array with 2 cells, representing a movement vector, based on the
     * String parameter.
     *
     * The rules to create the length 2 int array are as follows: - The 1st character of the String
     * represents the direction. - The remaining characters (if there are any) are interpreted as
     * integer and represent the magnitude or the number of steps to take.
     *
     * The cell at index 0 represents movement in the rows. Hence, a negative value represents
     * moving up the rows and a positive value represents moving down the rows.
     *
     * The cell at index 1 represents movement in the columns. Hence, a negative value represents
     * moving left in the columns and a positive value represents moving right in the columns.
     *
     * If the first character of moveStr does not match on of Config.UP_CHAR, Config.DOWN_CHAR,
     * Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an array with 0 in both cells.
     *
     * If there are no characters after the first character of moveStr or the characters cannot be
     * interpreted as an int, set the magnitude of the movement to 1.
     *
     * Hint: Use Scanner to parse the magnitude.
     *
     * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would represent moving up
     * by one character. - If the parameter moveStr is "65": An array {0, 5} would represent moving
     * right by 5 characters.
     *
     * @param moveStr The string to parse.
     * @return The calculated movement vector as a 2 cell int array.
     */
    public static int[] calcDelta(String moveStr) {

        int[] moveOrder = new int[2]; // Movement instructions for game stored in int array
        int magVal = 0; // Magnitude of movement
        // If moveStr length is 1, magnitude is 1.
        if (moveStr.length() == 1) {
            magVal = 1;
        }
        // If moveStr length is greater than 1, magnitude is parsed by scanner.
        if (moveStr.length() > 1) {
            magVal = Integer.parseInt(moveStr.substring(1, moveStr.length())); // The number of
                                                                               // steps to take
        }
        // If moveStr length is not 0, there are 4 possible cases for each of the movement
        // characters.
        if (moveStr.length() != 0) {
            switch (moveStr.charAt(0)) { // The direction the player wants to go
                case Config.UP_CHAR: // for reference: UP_CHAR = '8'
                    moveOrder[0] = -magVal;

                    break;
                case Config.DOWN_CHAR: // for reference: DOWN_CHAR = '2'
                    moveOrder[0] = magVal;

                    break;
                case Config.LEFT_CHAR: // for reference: LEFT_CHAR = '4'
                    moveOrder[1] = -magVal;

                    break;
                case Config.RIGHT_CHAR: // for reference: RIGHT_CHAR = '6'
                    moveOrder[1] = magVal;

                    break;
                default:
                    return moveOrder;

            }
        }

        return moveOrder;
    }

    /**
     * This method checks that moving from one position to another position is a valid move.
     *
     * To validate the move, the method should (in order) check: 1 - that pos is valid. 2 - that the
     * character at pos in board is in the valid array. 3 - that the delta is valid. 4 - that the
     * new position is valid and not a wall character. 5 - that the new position is not a box
     * character For what makes each test invalid, see the return details below.
     *
     * @param board The current board.
     * @param pos The position to move from. A length 2 array, where index 0 is the row and index 1
     *        is the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @param valid A character array containing the valid characters for the cell at pos.
     * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2, or not on the
     *         board. -2 : if the character at pos is not valid (not in the valid array). -3 : if
     *         delta is null or not length 2. -4 : if the new position is off the board or a wall
     *         character -5 : if the new position is a box character
     */
    public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {

        // Check 1 : Return -1 if pos is null, not length 2, or not on the board.
        if (pos == null || pos.length != 2 || pos[0] < 0 || pos[1] < 0 || pos[0] > board.length - 1
            || pos[1] > board[pos[0]].length - 1) {

            // The end part of this should check if the initial position row value is
            // greater than the max index of the boards rows.
            // It should also check the same for columns.
            return -1;
        }
        if (board == null) {
            return -1;
        }

        // Check 2: Return -2 if the character at pos is not valid (not in the valid
        // array).
        boolean isArrayValid = false; // Valid array
        // Runs through positions in board. If valid, isArrayValid is assigned to true.
        for (int i = 0; i < valid.length; i++) {
            if (board[pos[0]][pos[1]] == valid[i]) {
                isArrayValid = true;
            }

        }
        if (isArrayValid == false) {
            return -2;
        }

        // Check 3: Return -3 if delta is null or not length 2.
        if (delta == null || delta.length != 2) {
            return -3;
        }

        // Check 4: Return -4 if the new position is off the board or a wall character
        if ((pos[0] + delta[0]) < 0 || (pos[1] + delta[1]) < 0) {
            return -4;
        } else if ((pos[0] + delta[0]) > board.length - 1
            || (pos[1] + delta[1]) > board[(pos[0] + delta[0])].length - 1) {
            return -4;
        } else if (board[(pos[0] + delta[0])][(pos[1] + delta[1])] == Config.WALL_CHAR) {
            return -4;
        }

        // Check 5 : return -5 if the new position is a box character
        if (board[(pos[0] + delta[0])][(pos[1] + delta[1])] == Config.BOX_CHAR
            || board[(pos[0] + delta[0])][(pos[1] + delta[1])] == Config.BOX_GOAL_CHAR) {
            return -5;
        }

        return 1;
    }

    /**
     * Changes a character on the board to one of two characters (opt1 or opt2), depending on the
     * value of the cell.
     *
     * Check the cell at position pos. If the character is val, change it to opt1. Otherwise, change
     * it to opt2.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param val The value to check for in the board.
     * @param opt1 The character to change to if the value is val.
     * @param opt2 The character to change to if the value is not val.
     */
    public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
        // If position in board is a given char, then the char will change to the char defined as
        // option 1. Otherwise, char will change to char defined by option 2.
        if (board[pos[0]][pos[1]] == val) {
            board[pos[0]][pos[1]] = opt1;
        } else {
            board[pos[0]][pos[1]] = opt2;
        }

    }

    /**
     * Moves a box on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent a box. Step 2: Use your togglePos method to correctly change
     * the character at the new position to the appropriate box character. Step 3: Again use your
     * togglePos method to correctly change the character at pos to the the appropriate character
     * without a box.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return The return value of checkDelta if less than 1. Otherwise 1.
     */
    public static int shiftBox(char[][] board, int[] pos, int[] delta) {
        char[] valid = {Config.BOX_CHAR, Config.BOX_GOAL_CHAR}; // Characters that are valid for
                                                                // boxes

        // Step 1:
        int chkDeltVal = checkDelta(board, pos, delta, valid); // Check for delta value
        // If checkDelta does not return 1, then return value that was returned from checkDelta.
        if (chkDeltVal != 1) {
            return chkDeltVal;
        }

        int[] newPos = {pos[0] + delta[0], pos[1] + delta[1]}; // New position of box
        // If checkDelta returns 1, then call togglePos for new position and position.
        if (chkDeltVal == 1) {

            // Step 2:
            togglePos(board, newPos, Config.GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_CHAR);

            // Step 3:
            togglePos(board, pos, Config.BOX_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);

        }

        return 1;
    }

    /**
     * Processes a move of the worker step-by-step.
     *
     * Go through the delta step-by-step, calling doMove for each step. That is, if the delta is {0,
     * -3}, your method should call doMove three times with an argument of {0, -1} for the delta
     * parameter of doMove. Or, if the delta is {6, 0}, it would call the doMove six times with an
     * argument of {1, 0} for the delta parameter of the doMove method.
     *
     * During the processing of the move, if ever a call to doMove returns a value less than 1, your
     * method should stop processing and return that value.
     *
     * Note: You can assume that one of the cells of delta will be 0.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If both of the cells of delta are 0, return 0. If the call to doMove returns a value
     *         less than 1, return that value. Otherwise, return 1.
     */
    public static int processMove(char[][] board, int[] pos, int[] delta) {
        int moveVal; // Value of move
        int deltaRowVal = delta[0]; // Delta value for rows
        int deltaColVal = delta[1]; // Delta value for columns
        int[] newDelta = new int[2]; // New delta value

        if (deltaRowVal != 0) {

            // If delta row value is negative, call doMove to perform move.
            if (deltaRowVal < 0) {
                int rowNegMoveVal = -(deltaRowVal / deltaRowVal); // Value for negative row movement
                newDelta[0] = rowNegMoveVal;
                for (int i = 0; i < -deltaRowVal; i++) {
                    moveVal = doMove(board, pos, newDelta);
                    if (moveVal < 1) {
                        return moveVal;
                    }
                }
                // Otherwise, if delta row value is positive, call doMove to perform move.
            } else if (deltaRowVal > 0) {
                int rowPosMoveVal = (deltaRowVal / deltaRowVal); // Value for positive row movement
                newDelta[0] = rowPosMoveVal;
                for (int i = 0; i < deltaRowVal; i++) {
                    moveVal = doMove(board, pos, newDelta);
                    if (moveVal < 1) {
                        return moveVal;
                    }
                }
            }
        }

        if (deltaColVal != 0) {
            // If delta column value is negative, call doMove to perform move.
            if (deltaColVal < 0) {
                int colNegMoveVal = -(deltaColVal / deltaColVal); // Value for negative column
                                                                  // movement
                newDelta[1] = colNegMoveVal;
                for (int i = 0; i < -deltaColVal; i++) {
                    moveVal = doMove(board, pos, newDelta);
                    if (moveVal < 1) {
                        return moveVal;
                    }
                }
                // Otherwise, if delta column value is positive, call doMove to perform move.
            } else if (deltaColVal > 0) {
                int colPosMoveVal = (deltaColVal / deltaColVal); // Value for positive column
                                                                 // movement
                newDelta[1] = colPosMoveVal;
                for (int i = 0; i < deltaColVal; i++) {
                    moveVal = doMove(board, pos, newDelta);
                    if (moveVal < 1) {
                        return moveVal;
                    }
                }
            }
            // Otherwise, if row delta and column delta are 0, return 0.
        } else if (delta[0] == 0 && delta[1] == 0) {
            return 0;
        }

        return 1;
    }

    /**
     * Moves the worker on the board.
     *
     * Step 1: Use your checkDelta method to check that the move is valid. Recall that there are 2
     * characters that can represent the worker. Step 2: If checkDelta returns -5, use your shiftBox
     * method to move the box by delta before moving the worker. Step 3: Use your togglePos method
     * to correctly change the character at the new position to the appropriate worker character.
     * Step 4: Again use your togglePos method to correctly change the character at pos to the
     * appropriate character without a worker. Step 5: Update the position of the worker in pos.
     *
     * @param board The current board.
     * @param pos The position to change. A length 2 array, where index 0 is the row and index 1 is
     *        the column.
     * @param delta The move distance. A length 2 array, where index 0 is the change in row and
     *        index 1 is the change in column.
     * @return If checkDelta returns a value less than 1 that is not -5, return that value. If
     *         checkDelta returns -5 and shiftBox returns a value less than 0, return 0. Otherwise,
     *         return 1.
     */
    public static int doMove(char[][] board, int[] pos, int[] delta) {
        char[] valid = {Config.WORKER_CHAR, Config.WORK_GOAL_CHAR}; // Valid characters worker can
                                                                    // be
        int shiftBoxVal = 0; // Value that box was shifted

        // Step 1:
        int checkDeltVal = checkDelta(board, pos, delta, valid); // Value of method call to
                                                                 // checkDelta
        int[] newPos = {pos[0] + delta[0], pos[1] + delta[1]}; // New position

        // Step 2:
        if (checkDeltVal == -5) { // Move box before character if new position is a box character
            shiftBoxVal = shiftBox(board, newPos, delta);
        }
        if (checkDeltVal < 0 && checkDeltVal != -5) {
            return checkDeltVal;
        }
        if (shiftBoxVal < 0 && checkDeltVal == -5) {
            return 0;
        }

        // Step 3:
        togglePos(board, newPos, Config.GOAL_CHAR, Config.WORK_GOAL_CHAR, Config.WORKER_CHAR);

        // Step 4:
        togglePos(board, pos, Config.WORK_GOAL_CHAR, Config.GOAL_CHAR, Config.EMPTY_CHAR);

        // Step 5:
        // Runs through board, and updates the position of the worker.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == Config.WORKER_CHAR || board[i][j] == Config.WORK_GOAL_CHAR) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }

        return 1;
    }

    /**
     * Checks all the cells in board and ensures that there are no goals that are not covered by
     * boxes.
     *
     * @param board The current board.
     * @return true if all the goals are covered by boxes. Otherwise, false.
     */
    public static boolean checkWin(char[][] board) {
        // Runs through board, and checks that all goals are covered by boxes.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // If there is a goal char, then user did not win yet.
                if (board[i][j] == Config.GOAL_CHAR) {
                    return false;
                }
                // If there is a worker on a goal, then user did not win yet.
                if (board[i][j] == Config.WORK_GOAL_CHAR) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This is the main method for the Sokoban game. It consists of the main game and play again
     * loops with calls to the various supporting methods. The details of the main method for each
     * milestone can be found in the BP1 - Sokoban write-up on the CS 200 webpage:
     * https://cs200-www.cs.wisc.edu/wp/programs/
     *
     * For all milestones, you will need to create a Scanner object to read from System.in that you
     * will pass to the helper methods.
     *
     * For milestone 3, you will need to create a Random object using Config.SEED as the seed. This
     * object should be create at the beginning of the method, outside of any loops.
     *
     * @param args Unused.
     */

    public static void main(String[] args) {
        Random lvlGenerator = new Random(Config.SEED); // Random generator for levels
        Scanner sc = new Scanner(System.in); // Scanner for user inputs
        String userPrompt = "Choose a level between 0 and "; // Prompt for choosing a level
        String playAgain = "Play again? (y/n) "; // Prompt for playing again
        String postBoard = ": "; // Prompt for entering a move
        String moveStr; // Move user inputs
        int minLvl = -1; // Minimum level value
        int maxLvl = Config.LEVELS.length - 1; // Maximum level value
        int moveCount = 0; // Move count
        char charVal = 'y'; // Yes to play again
        int[] pos = new int[2]; // Position

        Boolean loopVal = true;
        Boolean willPlayAgain = true; // User will play again

        System.out.println("Welcome to Sokoban!");

        do { // Begins play again loop
            int lvlInput = promptInt(sc, userPrompt, minLvl, maxLvl); // Declares and initializes
                                                                      // variable to be the output
                                                                      // of promptInt
            if (lvlInput == -1) {
                lvlInput = lvlGenerator.nextInt(maxLvl + 1); // If the level selected is -1, the
                                                             // level gets randomized between the
                                                             // max and the min values
            }
            int chkLevelVal = checkLevel(lvlInput, Config.LEVELS, Config.GOALS);

            switch (chkLevelVal) { // This switch statement checks to make sure that checkLevel
                                   // returns a value which confirms the level is ok to use
                case 1:
                    char[][] board = initBoard(lvlInput, Config.LEVELS, Config.GOALS, pos); // Initializes
                                                                                            // the
                                                                                            // board
                                                                                            // and
                                                                                            // assigns
                                                                                            // it to
                                                                                            // 2D
                                                                                            // board
                                                                                            // array
                    System.out.println("Sokoban Level " + lvlInput);
                    do { // Begins game loop
                        printBoard(board);
                        moveStr = promptString(sc, postBoard);

                        if (moveStr == null || moveStr.length() == 0) {
                            continue;
                        }

                        else if (moveStr.charAt(0) == Config.QUIT_CHAR) {
                            moveCount = 0; // Resets the move counter if player quit
                            break;
                        } else {
                            int[] delta = calcDelta(moveStr); // The move delta is assigned to the
                                                              // calculated delta using the players
                                                              // move input
                            if (delta[0] != 0 || delta[1] != 0) {
                                processMove(board, pos, delta); // Processes moves
                                moveCount += Math.abs(delta[0] + delta[1]); // Adds a single
                                                                            // positive value
                                                                            // to a moveCount when a
                                                                            // single 1 space change
                                                                            // in delta occurs
                            }

                            continue;
                        }

                    } while (checkWin(board) == false);
                    if (checkWin(board) == true) {
                        System.out.println("Congratulations! You won in " + moveCount + " moves!");
                        moveCount = 0; // Resets the move counter if player won
                        printBoard(board);
                    }
                    charVal = promptChar(sc, playAgain);
                    if (charVal == 'y') {
                        break;
                    }
                    if (charVal != 'y') {
                        willPlayAgain = false;
                        break;
                    }
                    break;

                case 0:
                    System.out.println("Error loading level!");
                    System.out.println("Level " + lvlInput + " must be 0 or greater!");
                    break;
                case -1:
                    System.out.println("Error loading level!");
                    System.out.println("Error with Config.LEVELS");
                    break;
                case -2:
                    System.out.println("Error loading level!");
                    System.out.println("Error with Config.GOALS");
                    break;
                case -3:
                    System.out.println("Error loading level!");
                    System.out.println("Level " + lvlInput + " does not contain any boxes.");
                    char[][] board1 = initBoard(lvlInput, Config.LEVELS, Config.GOALS, pos);
                    printBoard(board1);
                    break;
                case -4:
                    System.out.println("Error loading level!");
                    System.out.println(
                        "Level " + lvlInput + " does not have the same number of boxes as goals.");
                    break;
                case -5:
                    System.out.println("Error loading level!");
                    System.out
                        .println("Level " + lvlInput + " has a goal location that is a wall.");
                    break;
                case -6:
                    System.out.println("Error loading level!");
                    System.out.println("Level " + lvlInput + " has 0 or more than 1 worker(s).");
                    break;
                case -7:
                    System.out.println("Error loading level!");
                    System.out.println("Level " + lvlInput + " contains duplicate goals.");
                    break;
                default:
                    System.out.println("Error loading level!");
                    System.out.println("Unknown Error");
                    break;
            }

        } while (willPlayAgain); // Runs as long as the player wants to play again.

        System.out.println("Thanks for playing!");
    }
}
