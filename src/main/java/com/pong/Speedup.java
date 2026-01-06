package com.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Speedup {
	private int x;
	private int y;
	private int height;
	private int width;
	private Color color = Color.GREEN;
	
	
	
	public Speedup(int x, int y, int height, int width) {
		this.x = x;
        this.y = y;
        this.height = height;
        this.width=width;

	}
	
//	precondition: g is a non-null value
//	postcondition: draws the Speedup on the screen
		public void draw(Graphics g) {
			g.setColor(color);
			
			g.fillRect(x, y, width, height);
			
		}
	
		//precondition: none
		//postcondition: returns the Speedup's y value
			public int getY() {
				  return y;
			  }
			  
			//precondition: none
			//postcondition: returns the Speedup's x value
			  public int getX() {
				  return x;
			  }
			  
			//precondition: ball is a non-null value
			//postcondition: returns true if the Speedup is touching one the paddle this is called on
			  public boolean isTouching(Ball ball) {
					 int rightX= x+width;
					 int bottomY = y+height;
					
					 if(x <= ball.getX() && ball.getX()<=rightX) {
						 
						 if(bottomY >= ball.getY() && ball.getY()>=y) {
							
							 return true;
						 }
				 }
					 return false;
				 }
}

