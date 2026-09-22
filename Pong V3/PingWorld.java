import greenfoot.*;


/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 400;
    private static final int WORLD_HEIGHT = 600;
    private TextManager scoreText;
    private TextManager levelText;
    private TextManager bounceText;
    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            GreenfootImage background = new GreenfootImage("NeonBackground.jpg");
            setPaintOrder(Ball.class, PaddleTwo.class, TextManager.class);
            setBackground(background);
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new PlayerPaddle(100,20), 200, WORLD_HEIGHT - 25);
            addObject(new AiPaddle(100,20), 200, 25);
            addObject(new PaddleTwo (100,20), 60, Greenfoot.getRandomNumber(200) + 100);
            
            
            SoundManager.playMusic("MainPlayBack.mp3", this);
            
            
            GameManager.resetLevel();
            GameManager.resetScore();
            Ball.resetBounces();
            
            
            addObject(new TextManager("Player" + "                   " + "Ai", 24), WORLD_WIDTH / 2, 225);
            scoreText = new TextManager(GameManager.getPlayerOneScore() +"                    "+ GameManager.getPlayerTwoScore(), 24);
            levelText = new TextManager("Level" + "  " + GameManager.getLevel(), 24);
            bounceText = new TextManager("Bounces" + "  " + Ball.getBounceAmount(), 24);
            
            addObject(scoreText, WORLD_WIDTH / 2, 250);
            addObject(levelText,  WORLD_WIDTH / 2, 400);
            addObject(bounceText, WORLD_WIDTH / 2, 425);
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld());
        }
    }
    
    /**
     * updates the score, level and amount of bounces every act.
     */
    public void act()
    {
        scoreText.updateText(GameManager.getPlayerOneScore() +"                    "+ GameManager.getPlayerTwoScore(), 24);
        levelText.updateText("Level" + "  " + GameManager.getLevel(), 24);
        bounceText.updateText("Bounces" + "  " + Ball.getBounceAmount(), 24);
    }

}
