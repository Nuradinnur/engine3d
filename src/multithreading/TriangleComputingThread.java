package multithreading;

import java.awt.Color;
import java.util.ArrayList;

public class TriangleComputingThread implements Runnable
{
    private boolean cameraReset = true;
    private boolean cameraAngleHasChanged = false;
    private double xRotation = 0;
    private double yRotation = 0;
    private double zRotation = 0;
    private double zoom = 1;
    private double xTranslate = 0;
    private double yTranslate = 0;
    private double zTranslate = 0;
    
    private static boolean retrieved = false;
    
    private static ArrayList<Triangle> triangles;
    
    private PerformanceTools tools = new PerformanceTools();
    private Window window = Window.getInstance();
    
    public TriangleComputingThread()
    {
        this.triangles = new ArrayList();
        initializeTriangleList();
    }
    
    @Override
    public void run()
    {
        calculateScene();
    }
    
    private void calculateScene() 
    {
        retrieved = false;
        
        setRotationAngles();
        
        for (int i = 0; i < triangles.size(); i++)
        {
            Triangle triangle = (Triangle) triangles.get(i);
            Matrix4 camera = MatrixTransforms.identity4();
            
            if (xRotation != 0)
            {
                camera = MatrixTransforms.XRotate4(xRotation);
            }
            
            if (yRotation != 0)
            {
                camera = camera.multiply(MatrixTransforms.YRotate4(yRotation));
            }
            
            if (zRotation != 0)
            {
                camera = camera.multiply(MatrixTransforms.ZRotate4(zRotation));
            }
            
            if (zoom != 1)
            {
                camera = camera.multiply(MatrixTransforms.Zoom4(zoom));
            }
            
            if (xTranslate != 0 || yTranslate != 0 || zTranslate != 0)
            {
                camera = camera.multiply(MatrixTransforms.translate(xTranslate, yTranslate, zTranslate));
            }
            
            if (cameraReset == true)
            {
                camera = MatrixTransforms.identity4().multiply(MatrixTransforms.initialCameraAngle());
            }
            
            
            triangle.setVertex(camera.transform(triangle.getVertex(0)), 0);
            triangle.setVertex(camera.transform(triangle.getVertex(1)), 1);
            triangle.setVertex(camera.transform(triangle.getVertex(2)), 2);
        }
            
        if (cameraReset == true)
        {
            cameraReset = false;
        }
    }
    
    private void initializeTriangleList()
    {
        /*triangles.add(new Triangle(new Vertex(100, 100, 0),
                                   new Vertex(0, 0, 0),
                                   new Vertex(0, 100, 0),
                                   new Color(255, 255, 255)));*/
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                createTile(triangles, i * 100, 0, j * 100);
            }
        }
    }
    
    private void createTriangle(Vertex v0, Vertex v1, Vertex v2, Color color)
    {
        triangles.add(new Triangle(v0, v1, v2, color));
    }
    
    private void createTile(ArrayList triangles, double x, double y, double z)
    {
        createTriangle(new Vertex(100 + x, 10 + y, 100 + z),
                       new Vertex(-100 + x, 10 + y, 100 + z),
                       new Vertex(-100 + x, 10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, 10 + y, 100 + z),
                       new Vertex(-100 + x, 10 + y, 100 + z),
                       new Vertex(-100 + x, 10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, 10 + y, 100 + z),
                       new Vertex(100 + x, 10 + y, -100 + z),
                       new Vertex(-100 + x, 10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, -10 + y, 100 + z),
                       new Vertex(-100 + x, -10 + y, 100 + z),
                       new Vertex(-100 + x, -10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, -10 + y, 100 + z),
                       new Vertex(100 + x, -10 + y, -100 + z),
                       new Vertex(-100 + x, -10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, 10 + y, 100 + z),
                       new Vertex(100 + x, -10 + y, 100 + z),
                       new Vertex(100 + x, -10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, 10 + y, 100 + z),
                       new Vertex(100 + x, 10 + y, -100 + z),
                       new Vertex(100 + x, -10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(-100 + x, 10 + y, 100 + z),
                       new Vertex(-100 + x, -10 + y, 100 + z),
                       new Vertex(-100 + x, -10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(-100 + x, 10 + y, 100 + z),
                       new Vertex(-100 + x, 10 + y, -100 + z),
                       new Vertex(-100 + x, -10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, 10 + y, -100 + z),
                       new Vertex(-100 + x, 10 + y, -100 + z),
                       new Vertex(-100 + x, -10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, 10 + y, -100 + z),
                       new Vertex(100 + x, -10 + y, -100 + z),
                       new Vertex(-100 + x, -10 + y, -100 + z),
                       new Color(255, 255, 255, 255));
        createTriangle(new Vertex(100 + x, 10 + y, 100 + z),
                       new Vertex(-100 + x, 10 + y, 100 + z),
                       new Vertex(-100 + x, -10 + y, 100 + z),
                       new Color(255, 255, 255, 255));
    }
    
    private void setRotationAngles()
    {
        if (window.getKeyListener().getKeyStatus('Q') && window.getKeyListener().getKeyStatus('A'))
        {
            xRotation = 0;
        }
        else if (window.getKeyListener().getKeyStatus('Q'))
        {
            xRotation = -1;
        }
        else if (window.getKeyListener().getKeyStatus('A'))
        {
            xRotation = 1;
        }
        else
        {
            xRotation = 0;
        }
        if (window.getKeyListener().getKeyStatus('W') && window.getKeyListener().getKeyStatus('S'))
        {
            yRotation = 0;
        }
        else if (window.getKeyListener().getKeyStatus('W'))
        {
            yRotation = -1;
        }
        else if (window.getKeyListener().getKeyStatus('S'))
        {
            yRotation = 1;
        }
        else
        {
            yRotation = 0;
        }
        if (window.getKeyListener().getKeyStatus('E') && window.getKeyListener().getKeyStatus('D'))
        {
            zRotation = 0;
        }
        else if (window.getKeyListener().getKeyStatus('E'))
        {
            zRotation = -1;
        }
        else if (window.getKeyListener().getKeyStatus('D'))
        {
            zRotation = 1;
        }
        else
        {
            zRotation = 0;
        }
        if (window.getKeyListener().getKeyStatus('R') && window.getKeyListener().getKeyStatus('F'))
        {
            zoom = 1;
        }
        else if (window.getKeyListener().getKeyStatus('R'))
        {
            zoom = 1.02;
        }
        else if (window.getKeyListener().getKeyStatus('F'))
        {
            zoom = 0.98;
        }
        else
        {
            zoom = 1;
        }
        if (window.getKeyListener().getKeyStatus('T') && window.getKeyListener().getKeyStatus('G'))
        {
            xTranslate = 0;
        }
        else if (window.getKeyListener().getKeyStatus('T'))
        {
            xTranslate = -1;
        }
        else if (window.getKeyListener().getKeyStatus('G'))
        {
            xTranslate = 1;
        }
        else
        {
            xTranslate = 0;
        }
        if (window.getKeyListener().getKeyStatus('Y') && window.getKeyListener().getKeyStatus('H'))
        {
            yTranslate = 0;
        }
        else if (window.getKeyListener().getKeyStatus('Y'))
        {
            yTranslate = -1;
        }
        else if (window.getKeyListener().getKeyStatus('H'))
        {
            yTranslate = 1;
        }
        else
        {
            yTranslate = 0;
        }
        if (window.getKeyListener().getKeyStatus('U') && window.getKeyListener().getKeyStatus('J'))
        {
            zTranslate = 0;
        }
        else if (window.getKeyListener().getKeyStatus('U'))
        {
            zTranslate = -1;
        }
        else if (window.getKeyListener().getKeyStatus('J'))
        {
            zTranslate = 1;
        }
        else
        {
            zTranslate = 0;
        }
        if (window.getKeyListener().getKeyStatus(' ') && cameraAngleHasChanged)
        {
            cameraReset = true;
            cameraAngleHasChanged = false;
        }
        if (!cameraAngleHasChanged)
        {
            if (xRotation != 0 || yRotation != 0 || zRotation != 0 || zoom != 1 ||
                xTranslate != 0 || yTranslate != 0 || zTranslate != 0)
            {
                cameraAngleHasChanged = true;
            }
        }
    }
    
    public static ArrayList<Triangle> getTriangleList()
    {
        retrieved = true;
        return triangles;
    }
}
