package server.model;


import shared.Variables;

public class Paddle
{
    private final int WIDTH = Variables.PAD_WIDTH, HEIGHT = Variables.PAD_HEIGHT;
    private int id, x, y;

    public Paddle(int id)
    {
        this.id = id;

        y = (Variables.BOARD_HEIGHT-HEIGHT)/2;

        if(id == 1)
            x = Variables.PAD_PADDING;
        else if (id == 2)
            x = Variables.BOARD_WIDTH-this.WIDTH-Variables.PAD_PADDING;
    }

    void setPosition(int y)
    {
        this.y = y;
    }

    public int getY()
    {
        return (int)y;
    }

}
