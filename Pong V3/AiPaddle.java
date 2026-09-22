import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * AiPaddle is the users opponent.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AiPaddle extends Paddle
{
    private int delay = 0;
    private int ballPos;
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public AiPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 1;
        createImage();
    }
    
    public void act() 
    {
        if(checkMoveToMiddle())
        {
            moveToMiddle();
        }
        else if (canTrackBall())
        {    
            moveToBall();
        }
    }    
    
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    public void createImage()
    {
        GreenfootImage image = getImage();
        image.scale(width, height);
        setImage(image);
    }
    
    /**
     * this checks if the ball has bounced off the AiPaddle.
     */
    private boolean checkMoveToMiddle()
    {
        if(!Ball.isMovingUp())
        {
            return true;
        }
        return false;
    }
    
    /**
     * moves the AiPaddle to the middle position.
     */
    private void moveToMiddle()
    {
        int x = getWorld().getWidth()/2;
        if(x < getX())
        {
            move(-1);
        }
        else
        {
            move(1);
        }
    }
    
    /**
     * the AiPaddle finds the x coordinate of the ball and moves towards it.
     */
    public void moveToBall()
    {
        if(delay <= 0)
        {
            ballPos = getWorld().getObjects(Ball.class).get(0).getX();
            delay = Greenfoot.getRandomNumber(20);
        }
        delay--;
        if(ballPos <= getX())
        {
            move(-2);
        }
        else
        {
            move(2);
        }
    }

    /**
     * Checks whether the paddle can track the ball or not.
     * Returns true if the paddle won't go off-screen when it tracks the ball, 
     * and returns false if the paddle would go out off-screen when tracking.
     */
    private boolean canTrackBall()
    {
        if (getX() <= width/2)
        {
            move(1);
            return false;
        }
        else if(getX() >= getWorld().getWidth() - width/2)
        {
            move(-1);
            return false;
        }
        else
        {
            return true;
        }
    }
    
}
