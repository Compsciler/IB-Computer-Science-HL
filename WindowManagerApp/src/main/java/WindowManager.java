/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roger
 */

import java.util.*;
import java.io.*;
import java.awt.*;

public class WindowManager {
    private ArrayList<Window> windows;

    public static final int STARTING_WINDOW_TOTAL = 10;
    
    public WindowManager() {
        windows = new ArrayList<Window>();
    }
    
    public void addWindow() {
        Random rand = new Random();
        int width = Window.MIN_WIDTH + rand.nextInt(Window.MAX_WIDTH - Window.MIN_WIDTH + 1);
        int height = Window.MIN_HEIGHT + rand.nextInt(Window.MAX_HEIGHT - Window.MIN_HEIGHT + 1);
        int x = rand.nextInt(WindowProject.FRAME_WIDTH - width);
        int y = rand.nextInt(WindowProject.FRAME_HEIGHT - height);
        Window window = new Window(x, y, width, height, 0);
        windows.add(window);
        bringToFront(windows.size() - 1);
    }
    
    public void drawWindows(Graphics g) {
        for (int i = windows.size() - 1; i >= 0; i--) {
            windows.get(i).drawWindow(g);
        }
    }
    
    public void bringToFront(int i) {
        for (Window w: windows){
            w.setZOrder(w.getZOrder() + 1);
        }
        windows.get(i).setZOrder(0);
        Collections.sort(windows);
    }
    
    public Window findWindowByPosition(int x, int y) {
        for (Window w: windows) {
            if ((x >= w.getX() && x < w.getX() + w.getWidth()) && (y >= w.getY() && y < w.getY() + w.getHeight())){
                return w;
            }
        }
        return null;
    }
    
    public ArrayList<Window> getWindows() {
        return windows;
    }

    public void setWindows(ArrayList<Window> windows) {
        this.windows = windows;
    }
}

class Window implements Comparable<Window> {
    private int x;
    private int y;
    private int width;
    private int height;
    private int zOrder;  // 0 is top window
    private Color color;
    
    public static final int MIN_WIDTH = 200;
    public static final int MAX_WIDTH = 600;
    public static final int MIN_HEIGHT = 200;
    public static final int MAX_HEIGHT = 400;

    public Window(int x, int y, int width, int height, int zOrder) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.zOrder = zOrder;
        
        Random rand = new Random();
        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }
    
    public void drawWindow(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    
    @Override
    public int compareTo(Window other) {
        return (zOrder - other.zOrder);
    }
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public int getZOrder() {
        return zOrder;
    }
    public void setZOrder(int zOrder) {
        this.zOrder = zOrder;
    }
}