package multithreading;

public class Matrix3 
{
    private double [] elements = new double [9];
    
    public Matrix3 (double [] elements)
    {
        this.elements = elements;
    }
    
    public Matrix3 multiply(Matrix3 multiplicand)
    {
        double [] result = new double [9];
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    result[i * 3 + j] += this.elements[i * 3 + k] * multiplicand.getElements()[k * 3 + j];
                }
            }
        }
        
        return new Matrix3(result);
    }
    
    public Vertex transform(Vertex vertex)
    {
        return new Vertex(
                    vertex.x * elements[0] + vertex.y * elements[3] + vertex.z * elements[6],
                    vertex.x * elements[1] + vertex.y * elements[4] + vertex.z * elements[7],
                    vertex.x * elements[2] + vertex.y * elements[5] + vertex.z * elements[8],
                    1
                );
    }
    
    public Matrix4 matrix3to4()
    {
        double [] newElements = new double[16];
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                newElements[i * 4 + j] = this.elements[i * 3 + j];
            }
            newElements[(i + 1) * 4 - 1] = 0;
            newElements[i + 12] = 0;
        }
        newElements[15] = 1;
        
        return new Matrix4(newElements);
    }
        
    public double [] getElements()
    {
        return elements;
    }
    
    @Override
    public String toString()
    {
        String s = "[";
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (j == 2)
                {
                    if (i == 2)
                    {
                        s = s.concat(elements[i * 3 + j] + "]");
                    }
                    else
                    {
                        s = s.concat(elements[i * 3 + j] + "]\n[");
                    }
                }
                else
                {
                    s = s.concat(elements[i * 3 + j] + ", ");
                }
            }
        }
        return s;
    }
}
