package multithreading;


public class Main
{
    public static void main(String[] args)
    {
        Window window = Window.getInstance();
        
        Runnable dispatcher = new ThreadDispatcher();
        Thread dispatcherThread = new Thread(dispatcher);
        dispatcherThread.start();
    }
}
