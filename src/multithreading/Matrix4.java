package multithreading;

public class Matrix4 
{
    private double [] elements = new double [16];
    
    public Matrix4 (double [] elements)
    {
        this.elements = elements;
    }
    
    public Matrix4 multiply(Matrix4 multiplicand)
    {
        double [] result = new double [16];
        
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                for (int k = 0; k < 4; k++)
                {
                    result[i * 4 + j] += this.elements[i * 4 + k] * multiplicand.getElements()[k * 4 + j];
                }
            }
        }
        
        return new Matrix4(result);
    }
    
    public Vertex transform(Vertex vertex)
    {
        return new Vertex(
                    vertex.x * elements[0] + vertex.y * elements[4] + vertex.z * elements[8] + vertex.w * elements[12],
                    vertex.x * elements[1] + vertex.y * elements[5] + vertex.z * elements[9] + vertex.w * elements[13],
                    vertex.x * elements[2] + vertex.y * elements[6] + vertex.z * elements[10] + vertex.w * elements[14],
                    vertex.x * elements[3] + vertex.y * elements[7] + vertex.z * elements[11] + vertex.w * elements[15]);
    }
    
    public double [] getElements()
    {
        return elements;
    }
    
    @Override
    public String toString()
    {
        String s = "[";
        
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (j == 3)
                {
                    if (i == 3)
                    {
                        s = s.concat(elements[i * 4 + j] + "]");
                    }
                    else
                    {
                        s = s.concat(elements[i * 4 + j] + "]\n[");
                    }
                }
                else
                {
                    s = s.concat(elements[i * 4 + j] + ", ");
                }
            }
        }
        return s;
    }
}
