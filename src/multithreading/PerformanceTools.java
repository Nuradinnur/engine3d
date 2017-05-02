package multithreading;

public class PerformanceTools 
{
    private long startAbsoluteTime;
    private long functionAbsoluteTime;
    
    public PerformanceTools()
    {
        startAbsoluteTime = 0;
        functionAbsoluteTime = 0;
    }
    
    public void executionTimerStart()
    {
        startAbsoluteTime = System.nanoTime();
    }
    
    public void executionTimerEnd()
    {
        functionAbsoluteTime = System.nanoTime() - startAbsoluteTime;
        System.err.println("This function has taken " + (double) functionAbsoluteTime / 1000 + " usec.");
    }
}
