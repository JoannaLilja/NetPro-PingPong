package shared;

public class GameStateDTO
{

    private int paddleOnePos;
    private int paddleTwoPos;
    private int ballX, ballY;

    public GameStateDTO(int paddleOnePos, int paddleTwoPos, int ballX, int ballY)
    {
        this.paddleOnePos = paddleOnePos;
        this.paddleTwoPos = paddleTwoPos;
        this.ballX = ballX;
        this.ballY = ballY;
    }
    
    public int getPaddleOnePos()
    {
    	return paddleOnePos;
    }
    
    public int getPaddleTwoPos()
    {
    	return paddleTwoPos;
    }
    
    public int getBallX()
    {
    	return ballX;
    }
    
    public int getBallY()
    {
    	return ballY;
    }
    
}
