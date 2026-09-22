import greenfoot.*;
import java.util.List;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends SmoothMover
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 10;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;
    private double speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private int startDelay;
    private int bounceDelay;
    private static boolean movingUp = false;
    private static int bounceAmount = 0;
    
    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        createImage();
        init();
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        GreenfootImage image = getImage();
        image.scale(BALL_SIZE,BALL_SIZE);
        setImage(image);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (startDelay > 0)
        {
            startDelay--;
        }
        else
        {
            move(speed+Math.log(GameManager.getLevel() + 1));
            checkBounceOffWalls();
            checkBounceOffPaddle();
            checkRestart();
            bounceDelay--;
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }

    /**
     * Creates a list of each paddle to see if any of them are intersecting with the ball, returning true or false.
     * for PaddleTwo it's different because they are only allowed to touch the ball when the ball is moving up.
     */
    private boolean isTouchingPaddle()
    {
        List<AiPaddle> aipaddle = getIntersectingObjects(AiPaddle.class);
        List<PaddleTwo> paddletwo = getIntersectingObjects(PaddleTwo.class);
        List<PlayerPaddle> playerpaddle = getIntersectingObjects(PlayerPaddle.class);
        if(!playerpaddle.isEmpty())
        {
            movingUp = true;
            return true;
        }
        if(!aipaddle.isEmpty())
        {
            movingUp = false;
            return true;
        }
        if(!paddletwo.isEmpty())
        {
            return movingUp;
        }
        return false;
    }
    
    /**
     * checks to see if the ball is touching a paddle and bounces the ball off if it is true.
     */
    private void checkBounceOffPaddle()
    {
        if (isTouchingPaddle())
        {
            if (! hasBouncedVertically && bounceDelay < 75)
            {
                revertVertically();
                bounceDelay = DELAY_TIME;
                Greenfoot.playSound("BallHit.mp3");
                bounceAmount++;
                if (bounceAmount % 10 == 0) {
                GameManager.addLevel();
                Greenfoot.playSound("GameLvlUp.mp3");
                }
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }
    
    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
                Greenfoot.playSound("BallHit.mp3");
                bounceAmount++;
                if (bounceAmount % 10 == 0) {
                GameManager.addLevel();
                Greenfoot.playSound("GameLvlUp.mp3");
                }
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor or ceiling the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            init();
            Greenfoot.playSound("GameRestart.mp3");
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            GameManager.addScorePlayerTwo();
        }
        else if(isTouchingCeiling())
        {
            init();
            Greenfoot.playSound("GameRestart.mp3");
            setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            GameManager.addScorePlayerOne();
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX/2)- BOUNCE_DEVIANCE_MAX / 4;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
    }

    /**
     * Bounces the ball back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX) - BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        if(getRotation() <= 180 && getRotation() >=0)
        {
            if(getRotation() <= 25)
            {
                setRotation(getRotation() + 10);
            }
            if(getRotation() >= 155)
            {
                setRotation(getRotation() - 10);
            }
            if(getRotation() >= 70 && getRotation() <= 110)
            {
                int random = Greenfoot.getRandomNumber(2);
                if(random == 0)
                {
                    setRotation(getRotation() - 10);
                }
                if(random == 1)
                {
                    setRotation(getRotation() + 10);
                }
            }
        }
        hasBouncedVertically = true;
    }

    public static int getBounceAmount()
    {
        return bounceAmount;
    }
    
    public static void resetBounces()
    {
        bounceAmount = 0;
    }
    
    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 3;
        startDelay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
    
    public static boolean isMovingUp()
    {
        return movingUp;
    }
}
