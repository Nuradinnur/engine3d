package multithreading;

public class Edge 
{
    private Vertex v0;
    private Vertex v1;
    
    public Edge(Vertex v0, Vertex v1)
    {
        this.v0 = v0;
        this.v1 = v1;
    }
    
    public Vertex getVertex(Boolean upper)
    {
        if (upper)
        {
            return v1;
        }
        else
        {
            return v0;
        }
    }
    
    public boolean isOnScreen()
    {
        return v0.isOnScreen() || v1.isOnScreen() || getMidpoint().isOnScreen();
    }
    
    public Vertex getMidpoint()
    {
        return new Vertex((v0.x + v1.x) / 2, (v0.y + v1.y) / 2, (v0.z + v1.z) / 2, 1);
    }
    
    public double getHeight()
    {
        return Math.abs(v1.y - v0.y);
    }
    
    public double getWidth()
    {
        return Math.abs(v1.x - v0.x);
    }
}