package multithreading;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;

public class ThreadDispatcher implements Runnable
{
    private final int framerateSetting = 60;
    private final long startAbsoluteTime = System.nanoTime();
    
    private int frameCount;
    private long currentFrameRemainingTime;
    private long thisFrameAbsoluteTime;   
    private long lastFrameAbsoluteTime;
    private long lastFramerateUpdateAbsoluteTime;
    private String framerate;
    
    private Queue framerateQueue = new LinkedList();
    private DecimalFormat framerateFormatter = new DecimalFormat("0");
    private DecimalFormat timeElapsedFormatter = new DecimalFormat("00");
    private PerformanceTools tools = new PerformanceTools();
    private RenderingThread render = new RenderingThread();
    private TriangleComputingThread computer = new TriangleComputingThread();
    
    private Thread renderThread = new Thread(render);
    private Thread computerThread = new Thread(computer);
    
    public ThreadDispatcher()
    {
        frameCount = 0;
        for (int i = 0; i < 60; i++)
        {
            framerateQueue.add((double) framerateSetting);
        }
    }

    @Override
    public void run() 
    {
        while(true)
        {
            try 
            {
                lastFrameAbsoluteTime = thisFrameAbsoluteTime;
                beginNewFrame();
                calculateRemainingFrameTime();
                
                Thread.sleep(currentFrameRemainingTime / 1000000, (int) (currentFrameRemainingTime % 1000000));
  
                calculateFramerate();
                thisFrameAbsoluteTime = System.nanoTime();
            }
            catch(InterruptedException ex) 
            {
                System.err.println("InterruptedException caused by method \"run()\" in class \"ThreadDispatcher.\"");
                System.err.println(ex);
            }
        }
    }
    
    private void beginNewFrame()
    {   
        frameCount++;
        startThreads();
    }
    
    private long calculateTotalElapsedSeconds()
    {
         return (System.nanoTime() - startAbsoluteTime) / 1000000000;
    }
    
    private void calculateRemainingFrameTime()
    {
        currentFrameRemainingTime = 1000000000 / framerateSetting + lastFrameAbsoluteTime - System.nanoTime();
        
        if (currentFrameRemainingTime < 0)
        {
            currentFrameRemainingTime = 0;
        }
    }
    
    private void calculateFramerate()
    {
        double frametime = (double) 1000000000 / (System.nanoTime() - lastFrameAbsoluteTime);
        framerateQueue.add(frametime);
        if (framerateQueue.size() >= 60)
        {
            framerateQueue.remove();
        }
        
        if (System.nanoTime() > lastFramerateUpdateAbsoluteTime + 1000000000)
        {
            double average = 0;
            for (Object i : framerateQueue)
            {
                average += (double) i;
            }
            
            average /= framerateQueue.size();
            
            Window.getInstance().setTitle("Frames per second:  " + framerateFormatter.format(average) +
                                        "   Total time elapsed: " + timeElapsedFormatter.format(calculateTotalElapsedSeconds() / 60) +
                                        ":" + timeElapsedFormatter.format(calculateTotalElapsedSeconds() % 60));
            lastFramerateUpdateAbsoluteTime = System.nanoTime();
        }
        
    }
    
    private void startThreads()
    {
        computerThread.run();
        renderThread.run();
        
        try 
        {
            computerThread.join();
            renderThread.join();
        } 
        catch(InterruptedException ex) 
        {
            System.err.println("InterruptedException caused by method \"startThreads()\" in class \"ThreadDispatcher.\"");
            System.err.println(ex);
        }
    }
}