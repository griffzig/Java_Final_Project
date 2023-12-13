package brick_breaker_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Gameplay extends JPanel implements KeyListener, ActionListener  {
	private boolean play = true;
	private int score = 0;
	private int totalBricks = 32;
	
	private Timer timer;
	private int delay = 8;
	
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
		
		//background color
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		bricks.paint((Graphics2D)g);
		
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//Paddle Color
		g.setColor(Color.YELLOW);
		g.fillRect(player1, 550, 100, 12);
		
		//Ball Color
		g.setColor(Color.white);  
		g.fillOval(ballXcoord, ballYcoord, 10, 10);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Sarif", Font.BOLD, 25));
		g.drawString("Score: " + score, 520, 30);
		
		//Win Condition
		if (totalBricks <= 0) { 
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.GREEN);
			g.setFont(new Font("Sarif", Font.BOLD, 30));
			g.drawString("You Won, Score: " + score, 190, 300);
			
			g.setFont(new Font("Sarif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart.", 230, 350);
		}
		
		//Game Over Condition
		if(ballYcoord > 570) { 
			play = false;
			ballXdir = 0;
			ballYdir = 0;
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
		if(play) {
			// Ball - Paddle  interaction 
			if(new Rectangle(ballXcoord, ballYcoord, 20, 20).intersects(new Rectangle(player1, 550, 100, 8))) {
				ballYdir = - ballYdir;
			}
			for( int i = 0; i<bricks.map.length; i++) { // Ball - Brick interaction
				for(int j = 0; j<bricks.map[0].length; j++) {  // map.map[0].length is the number of columns
					if(bricks.map[i][j] > 0) {
						int brickX = j*bricks.brickWidth + 80;
						int brickY = i*bricks.brickHeight + 50;
						int brickWidth= bricks.brickWidth;
						int brickHeight = bricks.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballXcoord, ballYcoord, 20,20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect) ) {
							bricks.setBrickValue(0, i, j);
							totalBricks--;
							score+=10;
							
							if(ballXcoord + 19 <= brickRect.x || ballXcoord +1 >= brickRect.x + brickRect.width) 
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
			if(ballXcoord < 0) { // if ball hits the left wall then it bounces back
				ballXdir = -ballXdir;
			}
			if(ballYcoord < 0) {  // if ball hits the top wall then it bounces back
				ballYdir = -ballYdir;
			}
			if(ballXcoord > 670) { // if ball hits the right wall then it bounces back
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
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) { // right key pressed moves right
			if(player1 >= 600) {
				player1 = 600;
			} else {
				moveRight();
					
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) { // Left key pressed moves left
			if(player1 < 10) {
				player1 = 10;
			} else {
				moveLeft();
					
			}
		}
		
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) { // Enter pressed restart
			if(!play) {
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
		public void moveRight() { // paddle moves right by 50 pixels
			play = true;
			player1 += 60;
		}
		public void moveLeft() { // paddle moves left by 50 pixels
			play = true;
			player1 -= 60;
		}
		
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}


}