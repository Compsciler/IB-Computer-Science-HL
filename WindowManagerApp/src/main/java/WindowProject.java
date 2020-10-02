/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roger
 */

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class WindowProject {
    public static final int FRAME_WIDTH = 1920;
    public static final int FRAME_HEIGHT = 1080;
    
    
    public static void main(String args[]) {
        JFrame jFrame = new JFrame();
        jFrame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        
        MyJPanel jPanel = new MyJPanel();
        jFrame.add(jPanel);
        jFrame.pack();
        jFrame.setVisible(true);
        jPanel.setVisible(true);
        
        System.out.println("HERE 2");
    }
}
