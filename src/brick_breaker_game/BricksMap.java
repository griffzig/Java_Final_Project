package brick_breaker_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// creates a map of the bricks and adds GUI elements of the bricks

public class BricksMap {

    public int map [][];
    public int brickWidth;
    public int brickHeight;

    // Constructor class, creates bricks 
    public BricksMap(int row, int col) {
        // instantiate 2D array 
        map = new int [row][col];
        // iterate through # of rows and columns 
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // notify when ball does not hit a brick
                map[i][j] = 1;
            }
        }
        
        // set the width and height of the bricks
        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    // paint the brick components
    public void draw(Graphics 2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    // set brick color

                    // set map of the border
                }
            }
        }
    }

    // set the value of a brick when it is hit
    public void setBrickValue (int value, int row, int col) {
        map[row][col] = value;
    }
}