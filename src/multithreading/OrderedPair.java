package multithreading;

public class OrderedPair 
{
    private int x;
    private int y;
    
    public OrderedPair(int x, int y)
    {
        if (x < 0 || y < 0 || x > Window.getInstance().getWidth() || y > Window.getInstance().getHeight())
        {
            //throw new IllegalArgumentException("OrderedPair.OrderedPair(): Coordinate pairs should correspond to pixel within the screen dimensions.");
        }
        this.x = x;
        this.y = y;
    }
    
    public int getCoordinate(int index)
    {
        if (index == 0)
        {
            return x;
        }
        else
        {
            return y;
        }
    }
    
    public void setCoordinate(int index, int value)
    {
        if (value < 0 || value > Window.getInstance().getWidth())
        {
            //throw new IllegalArgumentException("OrderedPair.setCoordinate(): Value of new coordinate should correspond to pixel within the screen dimensions.");
        }
        if (index == 0)
        {
            x = value;
        }
        else if (index == 1)
        {
            y = value;
        }
        else
        {
            throw new IllegalArgumentException("OrderedPair.setCoordinate(): Index of new coordinate must be 0 for x or 1 for y.");
        }
    }
}
