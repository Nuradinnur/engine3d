/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Secret
 */
public class KeyInput implements KeyListener
{
    private boolean A = false;
    private boolean D = false;
    private boolean W = false;
    private boolean S = false;
    private boolean Q = false;
    private boolean E = false;
    private boolean R = false;
    private boolean F = false;
    private boolean T = false;
    private boolean G = false;
    private boolean Y = false;
    private boolean H = false;
    private boolean U = false;
    private boolean J = false;
    private boolean SPACE = false;
    
    public KeyInput()
    {
    }
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                A = true;
                break;
            case KeyEvent.VK_D:
                D = true;
                break;
            case KeyEvent.VK_W:
                W = true;
                break;
            case KeyEvent.VK_S:
                S = true;
                break;
            case KeyEvent.VK_Q:
                Q = true;
                break;
            case KeyEvent.VK_E:
                E = true;
                break;
            case KeyEvent.VK_R:
                R = true;
                break;
            case KeyEvent.VK_F:
                F = true;
                break;
            case KeyEvent.VK_T:
                T = true;
                break;
            case KeyEvent.VK_G:
                G = true;
                break;
            case KeyEvent.VK_Y:
                Y = true;
                break;
            case KeyEvent.VK_H:
                H = true;
                break;
            case KeyEvent.VK_U:
                U = true;
                break;
            case KeyEvent.VK_J:
                J = true;
                break;
            case KeyEvent.VK_SPACE:
                SPACE = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_A:
                A = false;
                break;
            case KeyEvent.VK_D:
                D = false;
                break;
            case KeyEvent.VK_W:
                W = false;
                break;
            case KeyEvent.VK_S:
                S = false;
                break;
            case KeyEvent.VK_Q:
                Q = false;
                break;
            case KeyEvent.VK_E:
                E = false;
                break;
            case KeyEvent.VK_R:
                R = false;
                break;
            case KeyEvent.VK_F:
                F = false;
                break;
            case KeyEvent.VK_T:
                T = false;
                break;
            case KeyEvent.VK_G:
                G = false;
                break;
            case KeyEvent.VK_Y:
                Y = false;
                break;
            case KeyEvent.VK_H:
                H = false;
                break;
            case KeyEvent.VK_U:
                U = false;
                break;
            case KeyEvent.VK_J:
                J = false;
                break;
            case KeyEvent.VK_SPACE:
                SPACE = false;
                break;
        }
    }
    
    public boolean getKeyStatus (char key)
    {
        switch (key)
        {
            case KeyEvent.VK_A:
                return A;
            case KeyEvent.VK_D:
                return D;
            case KeyEvent.VK_W:
                return W;
            case KeyEvent.VK_S:
                return S;
            case KeyEvent.VK_Q:
                return Q;
            case KeyEvent.VK_E:
                return E;
            case KeyEvent.VK_R:
                return R;
            case KeyEvent.VK_F:
                return F;
            case KeyEvent.VK_T:
                return T;
            case KeyEvent.VK_G:
                return G;
            case KeyEvent.VK_Y:
                return Y;
            case KeyEvent.VK_H:
                return H;
            case KeyEvent.VK_U:
                return U;
            case KeyEvent.VK_J:
                return J;
            case KeyEvent.VK_SPACE:
                return SPACE;
            default:
                return false;
        }
    }
}
