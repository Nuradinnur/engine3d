package multithreading;

import java.awt.Color;

public class Triangle
{
    private Vertex v0;
    private Vertex v1;
    private Vertex v2;
    private Color color;
    
    private final Vertex v00;
    private final Vertex v01;
    private final Vertex v02;
    private final Color color0;
    
    public Triangle(Vertex v0, Vertex v1, Vertex v2, Color color)
    {
        this.v0 = this.v00 = v0;
        this.v1 = this.v01 = v1;
        this.v2 = this.v02 = v2;
        this.color = this.color0 = color;
    }
    
    public Triangle(Edge e0, Edge e1, Edge e2, Color color)
    {
        this.v0 = this.v00 = e0.getVertex(false);
        this.v1 = this.v01 = e1.getVertex(false);
        this.v2 = this.v02 = e2.getVertex(false);
        this.color = this.color0 = color;
    }
    
    public Vertex getVertex(int i)
    {
        switch (i) 
        {
            case 0:
                return v0;
            case 1:
                return v1;
            case 2:
                return v2;
            default:
                System.err.println("Parameter i of \"getVertex\" in Triangle.java is out of bounds.  Acceptable values: 0, 1, 2.");
                System.err.println("Vertex v0 returned instead of Vertex v" + i + ".");
                return v0;
        }
    }
    
    public void resetVertices(boolean resetColor)
    {
        v0 = v00;
        v1 = v01;
        v2 = v02;
        
        if (resetColor)
        {
            color = color0;
        }
    }
    
    public void setVertex (Vertex v, int i)
    {
        switch (i) 
        {
            case 0:
                v0 = v;
                break;
            case 1:
                v1 = v;
                break;
            case 2:
                v2 = v;
                break;
            default:
                System.err.println("Parameter i of \"setVertex\" in Triangle.java is out of bounds.  Acceptable values: 0, 1, 2.");
                System.err.println("Vertices remain unchanged.");
                break;
        }
    }
    
    public boolean isOnScreen()
    {
        return getEdge(0).isOnScreen() || getEdge(1).isOnScreen() || getEdge(2).isOnScreen();
    }
    
    public Edge getEdge(int i)
    {
        switch (i) 
        {
            case 0:
                return new Edge(v0, v1);
            case 1:
                return new Edge(v1, v2);
            case 2:
                return new Edge(v2, v0);
            default:
                System.err.println("Parameter i of \"getEdge\" in Triangle.java is out of bounds.  Acceptable values: 0, 1, 2.");
                System.err.println("Edge e0 returned instead of Edge e" + i + ".");
                return new Edge(v0, v1);
        }
    }
    
    public Edge getGreatestHeightEdge()       
    {
        if (getEdge(0).getHeight() >= getEdge(1).getHeight() && getEdge(0).getHeight() >= getEdge(2).getHeight())
        {
            return getEdge(0);
        }
        else if (getEdge(1).getHeight() >= getEdge(0).getHeight() && getEdge(1).getHeight() >= getEdge(2).getHeight())
        {
            return getEdge(1);
        }
        else
        {
            return getEdge(2);
        }
    }
    
    public Vertex getOpposingVertex()
    {
        if (getEdge(0) == getGreatestHeightEdge())
        {
            return v2;
        }
        else if (getEdge(1) == getGreatestHeightEdge())
        {
            return v0;
        }
        else
        {
            return v1;
        }
    }
    
    public Triangle divideEdge(int i)
    {
        Vertex leftEndpoint;
        Vertex divisor = getEdge(i).getMidpoint();
        Vertex rightEndpoint;

        switch (i)
        {
            case 0: 
                leftEndpoint = v0;
                rightEndpoint = v1;
                this.v1 = divisor;
                return new Triangle(divisor, v1, v2, this.color);

            case 1:
                leftEndpoint = v1;
                rightEndpoint = v2;
                this.v2 = divisor;
                return new Triangle(v0, divisor, v2, this.color);

            case 2:
                leftEndpoint = v2;
                rightEndpoint = v0;
                this.v0 = divisor;
                return new Triangle(v0, v1, divisor, this.color);
            default:
                System.err.println("Parameter i of \"divideEdge\" in Triangle.java is out of bounds.  Acceptable values: 0, 1, 2.");
                System.err.println("Edges remain undivided and a pointer to null has been returned.");
                return null;
        }
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setColor(Color c)
    {
        color = c;
    }
}
