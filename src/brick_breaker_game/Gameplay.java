package brick_breaker_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Gameplay extends JPanel implements KeyListener, ActionListener  {
	// game components
	private boolean play = true;
	private int score = 0;
	private int totalBricks = 32;
	private Timer timer;
	private int delay = 8;
	
	// player components
	private int player1 = 310;
	private int ballXcoord = 120;
	private int ballYcoord = 350;
	private int ballXdir = -2;
	private int ballYdir = -3;
	
	private Bricks bricks;
	

	public Gameplay() {
		bricks = new Bricks(4, 8);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		
		// set background color
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// paint the Bricks
		bricks.paint((Graphics2D)g);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// set Paddle Color
		g.setColor(Color.YELLOW);
		g.fillRect(player1, 550, 100, 12);
		
		// set Ball Color
		g.setColor(Color.white);  
		g.fillOval(ballXcoord, ballYcoord, 10, 10);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Sarif", Font.BOLD, 25));
		g.drawString("Score: " + score, 520, 30);
		
		// Win Condition
		if (totalBricks <= 0) { 
			// end the game
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			// Player Wins Screen
			g.setColor(Color.GREEN);
			g.setFont(new Font("Sarif", Font.BOLD, 30));
			g.drawString("You Won, Score: " + score, 190, 300);
			g.setFont(new Font("Sarif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart.", 230, 350);
		}
		
		// Game Over Condition
		if (ballYcoord > 570) { 
			// end the game
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			// Game Over Screen
			g.setColor(Color.RED);
			g.setFont(new Font("Sarif", Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score, 190, 300);
			g.setFont(new Font("Sarif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
				
		} 
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		// Check if the game is in play mode
		if (play) {
			// Interaction between Ball and Paddle
			if (new Rectangle(ballXcoord, ballYcoord, 20, 20).intersects(new Rectangle(player1, 550, 100, 8))) {
				ballYdir = - ballYdir;
			}
			
			// Interaction between Ball and Brick
			for ( int i = 0; i < bricks.map.length; i++) { 
				// 
				for (int j = 0; j < bricks.map[0].length; j++) { 
					if (bricks.map[i][j] > 0) {
						int brickX = j * bricks.brickWidth + 80;
						int brickY = i * bricks.brickHeight + 50;
						int brickWidth = bricks.brickWidth;
						int brickHeight = bricks.brickHeight;
						
						Rectangle rectangle = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRectangle = new Rectangle(ballXcoord, ballYcoord, 20,20);
						Rectangle brickRectangle = rectangle;
						
						if (ballRectangle.intersects(brickRectangle) ) {
							bricks.setBrickValue(0, i, j);
							totalBricks--;
							score += 10;
							
							if (ballXcoord + 19 <= brickRectangle.x || ballXcoord +1 >= brickRectangle.x + brickRectangle.width) 
								ballXdir = -ballXdir;
							 else {
								ballYdir = -ballYdir;
							}
						}
						
					}
					
				}
			}
			
			ballXcoord += ballXdir;
			ballYcoord += ballYdir;
			
			// determine ball action based on wall intersection
			// left wall intersection
			if (ballXcoord < 0) { 
				ballXdir = -ballXdir;
			}
			// top wall intersection
			if (ballYcoord < 0) {  
				ballYdir = -ballYdir;
			}
			// right wall intersection
			if (ballXcoord > 670) { 
				ballXdir = -ballXdir;  
			
			}
			
		}
		
		
		repaint();

	}
	

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// right arrow key pressed = move right
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) { 
			if (player1 >= 600) {
				player1 = 600;
			} else {
				moveRight();
					
			}
		}
		
		// left arrow key pressed = move left
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) { 
			if (player1 < 10) {
				player1 = 10;
			} else {
				moveLeft();
					
			}
		}
		
		// Enter key pressed = restart the game
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) { 
			if (!play) {
				play = true;
				ballXcoord = 120;
				ballYcoord = 350;
				ballXdir = -2;
				ballYdir = -3;
				score = 0;
				totalBricks = 32;
				bricks = new Bricks(4,8);
				
				repaint();
			}
		}
		
	}	
	
	// move the paddle right by 60 pixels
	public void moveRight() { 
			play = true;
			player1 += 60;
		}
		
	// move the paddle left by 60 pixels
	public void moveLeft() { 
			play = true;
			player1 -= 60;
		}
		
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}


}