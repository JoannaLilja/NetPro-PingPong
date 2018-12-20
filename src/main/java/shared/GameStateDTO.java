package shared;

public class GameStateDTO
{

    int paddleOnePos;
    int paddleTwoPos;
    int ballX, ballY;

    public GameStateDTO(int paddleOnePos, int paddleTwoPos, int ballX, int ballY)
    {
        this.paddleOnePos = paddleOnePos;
        this.paddleTwoPos = paddleTwoPos;
        this.ballX = ballX;
        this.ballY = ballY;
    }
}
