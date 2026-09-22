/**
 * GameManager manages the score and level
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager  
{
    // instance variables - replace the example below with your own
    private static int playerOneScore = 0;
    private static int playerTwoScore = 0;
    private static int level = 0;
    
    public static void resetScore()
    {
        playerOneScore = 0;
        playerTwoScore = 0;
    }
    
    public static void resetLevel()
    {
        level = 0;
    }
    
    public static void addScorePlayerOne()
    {
        playerOneScore++;
    }
    
    public static void addScorePlayerTwo()
    {
        playerTwoScore++;
    }
    
    public static void addLevel()
    {
        level++;
    }
    
    public static int getLevel()
    {
        return level;
    }
    
    public static int getPlayerOneScore()
    {
        return playerOneScore;
    }
    
    public static int getPlayerTwoScore()
    {
        return playerTwoScore;
    }
}
