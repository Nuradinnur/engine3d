package multithreading;


public class Vertex 
{
    double x;
    double y;
    double z;
    double w;
    
    public Vertex(double x, double y, double z, double w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        normalize();
    }
    
    public Vertex(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }
    
    public boolean isOnScreen()
    {
        return x >= -Window.getInstance().getWidth() / 2 && x < Window.getInstance().getWidth() / 2 && 
                y >= -Window.getInstance().getHeight() / 2 && y < Window.getInstance().getHeight() / 2;
    }
    
    public void normalize()
    {
        if (w != 1)
        {
            x /= w;
            y /= w;
            z /= w;
            w = 1;
        }
    }
}
