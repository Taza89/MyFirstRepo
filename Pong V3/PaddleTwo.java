import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PaddleTwo moves across the screen randomly.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PaddleTwo extends Paddle
{
    public PaddleTwo (int width, int height){
        this.width = width;
        this.height = height;
        createImage();
    }
    
    public void act()
    {
        setLocation (getX() + 1, getY());
        checkPaddleReachWall();
    }
    
    /**
     * When the paddle hits the right wall it removes itself then creates another one of itself on the left side.
     */
    public void checkPaddleReachWall(){
        if (getX() + width /2 >= getWorld().getWidth()){
            PingWorld world = (PingWorld) getWorld();
            world.removeObject(this);
            world.addObject(new PaddleTwo(width,height), 0, Greenfoot.getRandomNumber(200) + 100);
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
}
