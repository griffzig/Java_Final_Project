package brick_breaker_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Bricks {
	
	public int map [][];
	public int brickWidth;
	public int brickHeight;
	
	//Creates bricks in a 4X8 grid
	public Bricks(int row, int col) {
		map = new int [row][col];
		for (int i = 0; i < map.length; i++) { 
			for (int j=0; j< map[0].length;j++) {
				map[i][j] = 1;
			}
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	
	//Draws Bricks
	public void paint(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j=0; j< map[0].length;j++) {
				if(map[i][j] > 0) {
					g.setColor(Color.WHITE); // brick color
					g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(4));
					g.setColor(Color.BLACK);
					g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
				}
			}
			
		}
	}
	
	//Sets value of brick to 0 when hit
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

}