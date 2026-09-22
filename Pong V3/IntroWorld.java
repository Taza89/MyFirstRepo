import greenfoot.*;

/**
 * Write a description of class IntroWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 400;
    private static final int WORLD_HEIGHT = 600;
    private boolean isRunning = false;

    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        Greenfoot.setSpeed(50);
        GreenfootImage background = new GreenfootImage("IntroWorldBackground.jpg");
        setBackground(background);
        isRunning = false;
        addObject(new TextManager("Hit ENTER to Start", 30), WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
    }
    
    public void act()   
    {
        if(!isRunning){
            SoundManager.playMusic("IntroPlayBack.mp3", this);
            isRunning = true;
        }
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            SoundManager.stopMusic();
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
    
}
