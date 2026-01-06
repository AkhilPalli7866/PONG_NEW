package com.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JPanel implements MouseMotionListener {
    static int width = 640; // this is the amount of pixels to the right side of the screen
    static int height = 480; // this is the amount of pixels to the top of the screen.
    private int userMouseY;
    private Paddle aiPaddle;
    private int playerScore;
    private int aiScore;
    private Ball ball;
    // step 1 add any other private variables you may need to play the game.
    private Paddle USERPADDLe;
    private Speedup Greenwall;
    private SlowDown Redwall;
    private Wall Walal;


    public PongGame() {

        aiPaddle = new Paddle(610, 240, 50, 3, Color.WHITE);
        JLabel pScore = new JLabel("0");
        JLabel aiScore = new JLabel("0");
        pScore.setBounds(280, 440, 20, 20);
        aiScore.setBounds(360, 440, 20, 20);
        pScore.setVisible(true);
        aiScore.setVisible(true);
        userMouseY = 0;
        addMouseMotionListener(this);
        ball = new Ball(200, 200, 10, 3, Color.RED, 10);

        //create any other objects necessary to play the game.
        USERPADDLe = new Paddle( 20, 250, 50 ,  4, Color.BLUE);
        Greenwall = new Speedup( 280,  330, 90, 20);
        Redwall = new SlowDown( 280 , 40, 60 , 20);
        Walal = new Wall(280 , 180 , 100,10 ,Color.YELLOW );
        

        

    }

    // precondition: None
    // postcondition: returns playerScore
    public int getPlayerScore() {
        return playerScore;
    }

    // precondition: None
    // postcondition: returns aiScore
    public int getAiScore() {
        return aiScore;
    }

    //precondition: All visual components are initialized, non-null, objects 
    //postcondition: A frame of the game is drawn onto the screen.
    public void paintComponent(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.drawString("The Score is User:" + playerScore + " vs Ai:" + aiScore, 240, 20);
        ball.draw(g);
        aiPaddle.draw(g);
        
        
        //call the "draw" function of any visual component you'd like to show up on the screen.
        USERPADDLe.draw(g);
        Greenwall.draw(g);
        Redwall.draw(g);
        Walal.draw(g);
    }











    // precondition: all required visual components are intialized to non-null
    // values
    // postcondition: one frame of the game is "played"
    public void gameLogic() {

        //add commands here to make the game play propperly

        aiPaddle.moveY(ball.getY());

        ball.moveBall();// called move ball function to make ball move. 


        if (aiPaddle.isTouching(ball)){ball.reverseX();} //  ball bounces when it comes in contact with the ai paddle 
        if (USERPADDLe.isTouching(ball)){ball.reverseX();} // ball bounces on impact with the user paddle. 
         
        ball.bounceOffwalls( 10, 450); // this part ensures that ball bounces off the walls. 
        pointScored();  // calling pointScored function to make keep a register of the score. 
        
        USERPADDLe.moveY(userMouseY); // this is one of the key factors of making the game funciton properly, it controls the user paddle's position by moving mouse up and down.

        if (Walal.isTouching(ball)){ball.reverseX(); ball.reverseY();} // Yellow wall collision makes the ball boucne off it.
        
        if (Greenwall.isTouching(ball)){ // Speed Boost conditional, when ball touches the green block it goes through and speeds up in the process.
            if (Greenwall.isTouching(ball)) {
                ball.setChangeX(ball.getChangeX() * 1.3);
                 ball.setChangeY(ball.getChangeY() * 1.3);
            }
        }
        
        if (Redwall.isTouching(ball)){ // Slowdown conditional, when ball touches the red block it bounces back and slows the ball down.
            if (Redwall.isTouching(ball)) {
                ball.setChangeX(ball.getChangeX() * -0.3);
                ball.setChangeY(ball.getChangeY() * 0.6);
            }
        }
       

    }












    




    // precondition: ball is a non-null object that exists in the world
    // postcondition: determines if either ai or the player score needs to be
    // updated and re-sets the ball
    // the player scores if the ball moves off the right edge of the screen (640
    // pixels) and the ai scores
    // if the ball goes off the left edge (0)
    public void pointScored() {
        if (ball.getX()> 640){
            playerScore += 1;
            ball.sety(200);
            ball.setX(200);

            ball.setChangeX(-10);
            ball.setChangeY(3);
        }
        if (ball.getX() < 0 ){
            aiScore +=1; 
            ball.sety(200);
            ball.setX(200);
            ball.setChangeX(10);
            ball.setChangeY(3);
        }
    }





    // you do not need to edit the below methods, but please do not remove them asv
    // they are required for the program to run.
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        userMouseY = e.getY();
    }

}
