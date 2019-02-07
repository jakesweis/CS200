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
public class MyLevels {

    public static final char[][][] LEVELS = {{
        // {'#', '#', '#', '#', '#'},
        // {'#', ' ', ' ', ' ', '#'},
        // {'#', ' ', '=', ' ', '#'},
        // {'#', '.', ' ', '@', '#'},
        // {'#', '#', '#', '#', '#'}
        {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
        {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
        {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
        {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR, Config.WALL_CHAR},
        {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR}},
        {
            // {'#', '#', '#', '#', '#', '#', '#', '#'}
            // {'#', ' ', ' ', ' ', ' ', ' ', ' ', '#'}
            // {'#', ' ', ' ', ' ', ' ', ' ', ' ', '#'}
            // {'#', ' ', ' ', '#', '.', ' ', ' ', '#'}
            // {'#', ' ', '=', '.', '#', ' ', ' ', '#'}
            // {'#', ' ', '=', ' ', ' ', ' ', ' ', '#'}
            // {'#', ' ', ' ', ' ', ' ', ' ', '@', '#'}
            // {'#', '#', '#', '#', '#', '#', '#', '#'}
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR}},
        {
            // {'#', '#', '#', '#', '#', '#', '#', '#'},
            // {'#', ' ', '.', '.', '#', ' ', ' ', '#'},
            // {'#', ' ', '.', '.', '#', ' ', '=', '#', '#', '#'},
            // {'#', ' ', ' ', '#', '#', ' ', ' ', ' ', ' ', '#'},
            // {'#', '#', ' ', '=', ' ', ' ', ' ', '=', ' ', '#'},
            // {' ', '#', ' ', '#', '#', ' ', ' ', '#', '#', '#'},
            // {' ', '#', ' ', ' ', ' ', '=', '#', '#'},
            // {' ', '#', '#', '#', ' ', ' ', '#'},
            // {' ', ' ', ' ', '#', '@', ' ', '#'},
            // {' ', ' ', ' ', '#', '#', '#', '#'}
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
                Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
            {Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
                Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.WALL_CHAR,
                Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR},
            {Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR}}

    };

    public static final int[][] GOALS = {{3, 1}, {3, 4, 4, 3}, {1, 2, 1, 3, 2, 2, 2, 3}};

    public static void main(String[] args) {

    }

}
