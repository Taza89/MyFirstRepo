import greenfoot.*;

/**
 * Soundplayer creates an invisible object that plays a sound.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundPlayer extends Actor 
{
    // instance variables - replace the example below with your own
    private GreenfootSound sound;

    /**
     * Constructor for objects of class SoundPlayer
     */
    public SoundPlayer(String filename)
    {
        sound = new GreenfootSound(filename);
        getImage().setTransparency(0);
        sound.playLoop();
    }
    
    public void play()
    {
        sound.play();
    }
    
    public void stop()
    {
        sound.stop();
    }
}
