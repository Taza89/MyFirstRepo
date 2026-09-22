import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * TextManager creates text that is able to be updated.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextManager extends Actor
{
    /**
     * the constructor
     */
    public TextManager(String text, int size)
    {
        setImage(new GreenfootImage(text, size, Color.WHITE, new Color(0,0,0,0)));
    }

    /**
     * changes the image of the object based on new parameters.
     */
    public void updateText(String text, int size)
    {
        setImage(new GreenfootImage(text, size, Color.WHITE, new Color(0,0,0,0)));
    }
}
