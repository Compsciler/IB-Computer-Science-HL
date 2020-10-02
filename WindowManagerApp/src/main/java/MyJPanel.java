/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roger
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyJPanel extends JPanel implements MouseListener, KeyListener {
    private WindowManager windowManager;
    /**
     * Creates new form MyJPanel
     */
    public MyJPanel() {
        initComponents();
        // this.setSize(WindowProject.FRAME_WIDTH, WindowProject.FRAME_HEIGHT);
        
        windowManager = new WindowManager();
        for (int i = 0; i < WindowManager.STARTING_WINDOW_TOTAL; i++){
            windowManager.addWindow();
        }
        addMouseListener(this);
        addKeyListener(this);
        // jFrame.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        windowManager.drawWindows(g);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Window clickedWindow = windowManager.findWindowByPosition(e.getX(), e.getY());
        if (clickedWindow == null){
            return;
        }
        for (int i = 0; i < windowManager.getWindows().size(); i++){
            System.out.println(i);
            if (windowManager.getWindows().get(i).getZOrder() == clickedWindow.getZOrder()){
                windowManager.bringToFront(i);
                WindowProject.jFrame.repaint();
                return;
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent key) {
        
    }

    @Override
    public void keyPressed(KeyEvent key) {
        if (key.getKeyCode() == KeyEvent.VK_N){
            windowManager.addWindow();
            WindowProject.jFrame.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent key) {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
