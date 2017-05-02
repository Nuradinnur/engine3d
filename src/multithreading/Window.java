/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Secret
 */
public class Window 
{
    private String title = "See the world in three dimensions";
    
    private JFrame frame;
    private JPanel panel;
    private Graphics graphicsContext;
    private BufferStrategy bufferStrategy;
    
    private static final Window instance = new Window();
    private KeyInput keyInput = new KeyInput();
    
    private Window()
    {
        initializeWindow();
    }
    
    public static Window getInstance()
    {
        return instance;
    }
    
    public void initializeWindow()
    {
        frame = new JFrame();
        panel = new JPanel();
        
        frame.getContentPane().add(panel);
        frame.pack();
        
        frame.setTitle(title);
        frame.setSize(new java.awt.Dimension(1024, 640));
        frame.setDefaultCloseOperation(3);
        frame.createBufferStrategy(2);
        frame.setResizable(false);
        frame.setVisible(true);
        
        frame.addKeyListener(keyInput);
        
        bufferStrategy = frame.getBufferStrategy();
        graphicsContext = bufferStrategy.getDrawGraphics();
    }
    
    public Graphics getGraphicsContext()
    {
        return graphicsContext;
    }
    
    public KeyInput getKeyListener()
    {
        return keyInput;
    }
    
    public void swapBuffers()
    {
        bufferStrategy.show();
    }
    
    public int getWidth()
    {
        return frame.getWidth();
    }
    
    public int getHeight()
    {
        return frame.getHeight();
    }
    
    public void setTitle(String title)
    {
        this.title = title;
        frame.setTitle(this.title);
    }
}
