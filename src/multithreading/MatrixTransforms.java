package multithreading;

public final class MatrixTransforms 
{
    private MatrixTransforms()
    {
    }
    
    public static Matrix3 identity3()
    {
        return new Matrix3(new double[]{1, 0, 0,
                                        0, 1, 0,
                                        0, 0, 1});
    }
    public static Matrix4 identity4()
    {
        return new Matrix4(new double[]{1, 0, 0, 0,
                                        0, 1, 0, 0,
                                        0, 0, 1, 0,
                                        0, 0, 0, 1});
    }
    
    /** YZ-plane rotation transform **/
    public static Matrix3 XRotate3(double a)
    {
        a /= 30;
        
        return new Matrix3(new double[]{1, 0, 0,
                            0, Math.cos(a), Math.sin(a),
                            0, -Math.sin(a), Math.cos(a)});
    }
    
    /** YZ-plane rotation transform **/
    public static Matrix4 XRotate4(double a)
    {
        a /= 30;
        
        return new Matrix4(new double[]{1, 0, 0, 0,
                            0, Math.cos(a), Math.sin(a), 0,
                            0, -Math.sin(a), Math.cos(a), 0,
                            0, 0, 0, 1});
    }
    
    /** XZ-plane rotation transform **/
    public static Matrix3 YRotate3(double a)
    {
        a /= 30;
        
        return new Matrix3(new double[]{Math.cos(a), 0, -Math.sin(a),
                            0, 1, 0,
                            Math.sin(a), 0, Math.cos(a)});
    }
    
    public static Matrix4 YRotate4(double a)
    {
        a /= 30;
        
        return new Matrix4(new double[]{Math.cos(a), 0, -Math.sin(a), 0,
                            0, 1, 0, 0,
                            Math.sin(a), 0, Math.cos(a), 0,
                            0, 0, 0, 1});
    }
    
    /** XY-plane rotation transform **/
    public static Matrix3 ZRotate3(double a)
    {
        a /= 30;
        
        return new Matrix3(new double[]{Math.cos(a), -Math.sin(a), 0,
                            Math.sin(a), Math.cos(a), 0,
                            0, 0, 1});
    }
    
    public static Matrix4 ZRotate4(double a)
    {
        a /= 30;
        
        return new Matrix4(new double[]{Math.cos(a), -Math.sin(a), 0, 0,
                            Math.sin(a), Math.cos(a), 0, 0, 
                            0, 0, 1, 0,
                            0, 0, 0, 1});
    }
    
    /** Zoom/scaling transforms **/
    public static Matrix3 Zoom3(double s)
    {
        return new Matrix3(new double[]{s, 0, 0,
                            0, s, 0,
                            0, 0, s});
    }
    
    public static Matrix4 Zoom4(double s)
    {
        return new Matrix4(new double[]{s, 0, 0, 0,
                            0, s, 0, 0,
                            0, 0, s, 0,
                            0, 0, 0, 1});
    }
    
    /** X,Y and Z translation transforms **/
    public static Matrix3 translate(double x, double y)
    {
        x /= 10000;
        y /= 10000;
        
        return new Matrix3(new double[]{1, 0, x,
                                        0, 1, y,
                                        0, 0, 1});
    }
    
    public static Matrix4 translate(double x, double y, double z)
    {
        x /= 10000;
        y /= 10000;
        z /= 10000;
        
        return new Matrix4(new double[]{1, 0, 0, x,
                                        0, 1, 0, y,
                                        0, 0, 1, z,
                                        0, 0, 0, 1});
    }
    
    
    /** Initial camera angle transform **/
    public static Matrix4 initialCameraAngle()
    {
        for (Triangle t : TriangleComputingThread.getTriangleList())
        {
            t.resetVertices(false);
        }
        
        Matrix4 camera = XRotate4(Math.PI / 8);
        camera = camera.multiply(YRotate4(Math.PI / 8));
        camera = camera.multiply(ZRotate4(Math.PI / 8));
        camera = camera.multiply(Zoom4(0.5));
        return camera;
    }
}
