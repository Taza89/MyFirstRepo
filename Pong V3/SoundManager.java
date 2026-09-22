import greenfoot.*;

/**
 * SoundManager manages the music played throughout worlds.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SoundManager  
{
    // instance variables - replace the example below with your own
    private static SoundPlayer currentMusic;

    /**
     * Constructor for objects of class SoundManager
     */
    public static void playMusic(String filename, World world)
    {
        currentMusic = new SoundPlayer(filename);
        world.addObject(currentMusic, 0, 0);
        currentMusic.play();
    }
    
    /**
     * it stops the object it has created which stops the music currently being played, and also removes the object itself. 
     * stop() is used in case the object cant be removed.
     */
    public static void stopMusic()
    {
        if(currentMusic != null)
        {
            currentMusic.stop();
            if(currentMusic.getWorld() != null)
            {
                currentMusic.getWorld().removeObject(currentMusic);
            }
            currentMusic = null;
        }
    }
}
