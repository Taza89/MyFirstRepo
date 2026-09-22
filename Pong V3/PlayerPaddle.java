import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * PlayerPaddle is the paddle the user controls.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerPaddle extends Paddle
{
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public PlayerPaddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        createImage();
    }

    public void act() 
    {
        if (!checkMoveOffSide())
        {
            playerMove();
        }
    }
    
    /**
     * moves the player paddle left or right using the a and d keys.
     */
    private void playerMove()
    {
        playerMoveRight();
        playerMoveLeft();
    }
    
    private void playerMoveRight()
    {
        if(Greenfoot.isKeyDown("d"))
        {
            move(3);
        }
    }
    
    private void playerMoveLeft()
    {
        if(Greenfoot.isKeyDown("a"))
        {
            move(-3);
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
     * This checks if the PlayerPaddle would go off-screen when going left or right.
     * If the x-coordinate is less or equal to half the width of the Paddle itself, then it can only go right.
     * If the x-coordinate is greater or equal to the world width minus by half the width of the Paddle itself,
     * then it can only go left.
     */
    private boolean checkMoveOffSide()
    {
        if (getX() <= width/2)
        {
            playerMoveRight();
            return true;
        }
        else if (getX() >= getWorld().getWidth() - width/2)
        {
            playerMoveLeft();
            return true;
        }
        else
        {
            return false;
        }
    }
}
