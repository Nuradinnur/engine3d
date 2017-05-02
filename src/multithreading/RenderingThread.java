package multithreading;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

public class RenderingThread implements Runnable
{
    private BufferedImage backBuffer;
    private Graphics2D graphicsContext;
    private GraphicsConfiguration graphicsConfiguration;
    
    private Window window = Window.getInstance();
    private PerformanceTools tools = new PerformanceTools();
    
    public RenderingThread()
    {
        System.setProperty("sun.java2d.opengl","true");
        graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        backBuffer = graphicsConfiguration.createCompatibleImage(window.getWidth(), window.getHeight());
        backBuffer.setAccelerationPriority(1);
    }
    
    @Override
    public void run() 
    {
        //tools.executionTimerStart();
        render();
        //tools.executionTimerEnd();
    }
    
    private void render()
    {
        graphicsContext = (Graphics2D) backBuffer.createGraphics();
        graphicsContext.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        graphicsContext.setRenderingHints(new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC));
        draw();
        window.swapBuffers();
    }
    
    private void draw()
    {
        graphicsContext.setColor(new Color(31, 31, 31, 255));
        graphicsContext.fillRect(0, 0, window.getWidth(), window.getHeight());
        
        ArrayList<Triangle> triangles = TriangleComputingThread.getTriangleList();
        
        for (Triangle t : triangles)
        {
            if (t.isOnScreen())
            {
                rasterizeTriangle(t);
                //fillTriangle(t, Color.red);
            }
        }
        
        graphicsContext = (Graphics2D) window.getGraphicsContext();
        graphicsContext.drawImage(backBuffer, 0, 0, null);
    }
    
    private void rasterizeTriangle (Triangle t)
    {
        rasterizeLine(t.getVertex(0), t.getVertex(1), t.getColor());
        rasterizeLine(t.getVertex(1), t.getVertex(2), t.getColor());
        rasterizeLine(t.getVertex(2), t.getVertex(0), t.getColor());
    }
    
    private void rasterizeLine(int x1, int y1, int x2, int y2, Color color)
    {
        int d = 0;
        
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        
        int dx2 = dx << 1;
        int dy2 = dy << 1;
        
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        
        if (dy <= dx)
        {
            while (true)
            {
                if (x1 > 0 && x1 < window.getWidth() && y1 > 0 && y1 < window.getHeight())
                {
                    backBuffer.setRGB(x1, y1, color.getRGB());
                }
                else
                {
                    break;
                }
                if (x1 == x2)
                {
                    break;
                }
                x1 += sx;
                d += dy2;
                if (d > dx)
                {
                    y1 += sy;
                    d -= dx2;
                }
            }
        }
        else
        {
            while (true)
            {
                if (x1 > 0 && x1 < window.getWidth() && y1 > 0 && y1 < window.getHeight())
                {
                    backBuffer.setRGB(x1, y1, color.getRGB());
                }
                else
                {
                    break;
                }
                if (y1 == y2)
                {
                    break;
                }
                y1 += sy;
                d += dx2;
                if (d > dy)
                {
                    x1 += sx;
                    d -= dy2;
                }
            }
        }
    }
    
    private void rasterizeLine(Vertex v1, Vertex v2, Color color)
    {
        int d = 0;
        
        int x1 = (int) v1.x + window.getWidth() / 2;
        int y1 = (int) v1.y + window.getHeight() / 2;
        int x2 = (int) v2.x + window.getWidth() / 2;
        int y2 = (int) v2.y + window.getHeight() / 2;
        
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        
        int dx2 = dx << 1;
        int dy2 = dy << 1;
        
        int sx = v1.x < v2.x ? 1 : -1;
        int sy = v1.y < v2.y ? 1 : -1;
        
        if (dy <= dx)
        {
            while (true)
            {
                if (x1 >= 0 && x1 < window.getWidth() && y1 >= 0 && y1 < window.getHeight())
                {
                    backBuffer.setRGB(x1, y1, color.getRGB());
                }
                if (x1 == x2)
                {
                    break;
                }
                x1 += sx;
                d += dy2;
                if (d > dx)
                {
                    y1 += sy;
                    d -= dx2;
                }
            }
        }
        else
        {
            while (true)
            {
                if (x1 > 0 && x1 < window.getWidth() && y1 > 0 && y1 < window.getHeight())
                {
                    backBuffer.setRGB(x1, y1, color.getRGB());
                }
                if (y1 == y2)
                {
                    break;
                }
                y1 += sy;
                d += dx2;
                if (d > dy)
                {
                    x1 += sx;
                    d -= dy2;
                }
            }
        }
    }
    
    private void fillTriangle(Triangle t, Color color)
    {
        ArrayList<OrderedPair> edgeCoordinates = new ArrayList();
        
        Vertex v0 = t.getGreatestHeightEdge().getVertex(false);
        Vertex v1 = t.getGreatestHeightEdge().getVertex(true);
        Vertex v2 = t.getOpposingVertex();
        
        int d = 0;

        int x0 = (int) v0.x + window.getWidth() / 2;
        int x1 = (int) v1.x + window.getWidth() / 2;
        int y0 = (int) v0.y + window.getHeight() / 2;
        int y1 = (int) v1.y + window.getHeight() / 2;

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int dx2 = dx << 1;
        int dy2 = dy << 1;

        int sx = v0.x < v1.x ? 1 : -1;
        int sy = v0.y < v1.y ? 1 : -1;

        if (dy <= dx)
        {
            while (true)
            {
                edgeCoordinates.add(new OrderedPair(x0, y0));
                
                if (x0 >= 0 && x0 < window.getWidth() && y0 >= 0 && y0 < window.getHeight())
                {
                    backBuffer.setRGB(x0, y0, color.getRGB());
                }
                if (x0 == x1)
                {
                    break;
                }
                x0 += sx;
                d += dy2;
                if (d > dx)
                {
                    y0 += sy;
                    d -= dx2;
                }
            }
        }
        else
        {
            while (true)
            {
                edgeCoordinates.add(new OrderedPair(x0, y0));
                
                if (x0 > 0 && x0 < window.getWidth() && y0 > 0 && y0 < window.getHeight())
                {
                    backBuffer.setRGB(x0, y0, color.getRGB());
                }
                if (y0 == y1)
                {
                    break;
                }
                y0 += sy;
                d += dx2;
                if (d > dy)
                {
                    x0 += sx;
                    d -= dy2;
                }
            }
        }
        
        d = 0;

        x0 = (int) v0.x + window.getWidth() / 2;
        x1 = (int) v2.x + window.getWidth() / 2;
        y0 = (int) v0.y + window.getHeight() / 2;
        y1 = (int) v2.y + window.getHeight() / 2;

        dx = Math.abs(x1 - x0);
        dy = Math.abs(y1 - y0);

        dx2 = dx << 1;
        dy2 = dy << 1;

        sx = v0.x < v2.x ? 1 : -1;
        sy = v0.y < v2.y ? 1 : -1;
            
        int rh = 0;

        if (dy <= dx)
        {
            while (true && rh < edgeCoordinates.size())
            {
                int scs = x0;
                int sce = edgeCoordinates.get(rh).getCoordinate(0);
                
                if (scs >= sce)
                {
                    for (int i = scs; i > sce; i--)
                    {
                        if (i >= 0 && i < window.getWidth() && y0 >= 0 && y0 < window.getHeight())
                        {
                            backBuffer.setRGB(i, y0, color.getRGB());
                        }
                    }
                }
                else
                {
                    for (int i = scs; i < sce; i++)
                    {
                        if (i >= 0 && i < window.getWidth() && y0 >= 0 && y0 < window.getHeight())
                        {
                            backBuffer.setRGB(i, y0, color.getRGB());
                        }
                    }
                }
                if (x0 == x1)
                {
                    break;
                }
                x0 += sx;
                d += dy2;
                if (d > dx)
                {
                    y0 += sy;
                    d -= dx2;
                }
                rh++;
                edgeCoordinates.remove(0);
            }
        }
        else
        {
            while (true)
            {
                int scs = x0;
                int sce = edgeCoordinates.get(rh).getCoordinate(0);
                
                if (scs >= sce)
                {
                    for (int i = scs; i > sce; i--)
                    {
                        if (i >= 0 && i < window.getWidth() && y0 >= 0 && y0 < window.getHeight())
                        {
                            backBuffer.setRGB(x0, i, color.getRGB());
                        }
                    }
                }
                else
                {
                    for (int i = scs; i < sce; i++)
                    {
                        if (i >= 0 && i < window.getWidth() && y0 >= 0 && y0 < window.getHeight())
                        {
                            backBuffer.setRGB(i, y0, color.getRGB());
                        }
                    }
                }
                if (y0 == y1)
                {
                    break;
                }
                y0 += sy;
                d += dx2;
                if (d > dy)
                {
                    x0 += sx;
                    d -= dy2;
                }
                rh++;
                edgeCoordinates.remove(0);
            }
        }
    }
}