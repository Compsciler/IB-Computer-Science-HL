package com.company;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static Grid grid;
    public static final int GENERATION_TIME = 200;
    public static final int X_BUFFER = 12;
    public static final int Y_BUFFER = 36;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        grid = new Grid();
        // Grid.generateRandomStates(grid, grid.getGridSize());
        Grid.loadFile(grid, "C:\\Users\\roger\\OneDrive\\Desktop\\IB-Computer-Science-HL\\ConwaysGameOfLife\\src\\com\\company\\seed.txt");
        grid.printConsole();
        System.out.println();

        JFrame jFrame = new JFrame();
        GridPanel gridPanel = new GridPanel();
        jFrame.setPreferredSize(new Dimension((grid.getGridSize() * GridPanel.CELL_PIXEL_LENGTH + X_BUFFER), (grid.getGridSize() * GridPanel.CELL_PIXEL_LENGTH + Y_BUFFER)));
        jFrame.add(gridPanel);
        jFrame.pack();
        jFrame.setVisible(true);

        Thread.sleep(GENERATION_TIME);
        while (true){
            grid.update();
            Thread.sleep(GENERATION_TIME);
            // gridPanel.paintCells(grid, new Graphics());
            grid.printConsole();
            System.out.println();
            gridPanel.repaint();
        }
    }
}

class GridPanel extends JPanel {
    public static final int CELL_PIXEL_LENGTH = 16;

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        Main.grid.paintPanel(g);
    }
}


class Grid {
    private int gridSize;
    private boolean isWrapAround = false;
    private double aliveChance = 0.2;
    private Cell[][] cells;

    public Grid(){

    }
    public Grid(int gridSize){
        this.gridSize = gridSize;
        cells = new Cell[gridSize][gridSize];
    }

    public static void loadFile(Grid grid, String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        int lineLength = -1;
        int lineNum = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine().replace(" ", "");
            int newLineLength = line.length();
            if (newLineLength != lineLength && lineLength != -1){
                System.out.println("Non-square grid entered!");
                System.exit(1);
                // return;
            }
            if (lineLength == -1){
                lineLength = newLineLength;
                grid.setGridSize(lineLength);
            }
            for (int i = 0; i < lineLength; i++){
                char c = line.charAt(i);
                if (c == '.'){
                    grid.cells[lineNum][i] = new Cell(false, lineNum, i);
                } else {
                    grid.cells[lineNum][i] = new Cell(true, lineNum, i);
                }
            }
            lineNum++;
        }
        if (lineNum != lineLength){
            System.out.println("Non-square grid entered!");
            System.exit(1);
            // return;
        }
    }

    public static void generateRandomStates(Grid grid, int gridSize) {
        Random rand = new Random();
        grid.setGridSize(gridSize);
        for (int r = 0; r < gridSize; r++){
            for (int c = 0; c < gridSize; c++){
                Cell cell;
                if (rand.nextDouble() < grid.aliveChance) {
                    cell = new Cell(true, r, c);
                } else {
                    cell = new Cell(false, r, c);
                }
                grid.cells[r][c] = cell;
            }
        }
        /*
        for (Cell[] cellsRow : cells) {
            for (Cell c : cellsRow) {
                if (rand.nextDouble() < aliveChance) {
                    c = new Cell(true);
                } else {
                    c = new Cell(false);
                }
            }
        }
        */
    }

    public void update(){
        for (Cell[] cellsRow : cells) {
            for (Cell c : cellsRow) {
                int neighbors = countNeighbors(c);
                if (c.getIsAlive()){
                    if ((neighbors == 2) || (neighbors == 3)){
                        c.setIsNextGenAlive(true);
                    } else {
                        c.setIsNextGenAlive(false);
                    }
                } else {
                    if (neighbors == 3){
                        c.setIsNextGenAlive(true);
                    } else {
                        c.setIsNextGenAlive(false);
                    }
                }
            }
        }
        for (Cell[] cellsRow : cells) {
            for (Cell c : cellsRow) {
                c.setIsAlive(c.getIsNextGenAlive());
            }
        }
    }

    public int countNeighbors(Cell cell){
        return countNeighbors(cell.getRow(), cell.getCol());
    }

    public int countNeighbors(int row, int col){
        int neighbors = 0;
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0){
                    continue;
                }
                boolean isCellAlive = false;
                if (isWrapAround){
                    isCellAlive = cells[(row + i + gridSize) % gridSize][(col + j + gridSize) % gridSize].getIsAlive();
                } else {
                    if (!(((row + i) <= -1) || ((row + i) >= gridSize) || ((col + j) <= -1) || ((col + j) >= gridSize))){
                        isCellAlive = cells[(row + i)][(col + j)].getIsAlive();
                    }
                }
                if (isCellAlive){
                    neighbors++;
                }
            }
        }
        return neighbors;
    }

    public void printConsole(){
        for (Cell[] cellsRow : cells) {
            for (Cell c : cellsRow) {
                if (c.getIsAlive()){
                    System.out.print("x ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public void paintPanel(Graphics g){
        for (int r = 0; r < gridSize; r++){
            for (int c = 0; c < gridSize; c++){
                Cell cell = cells[r][c];
                if (cell.getIsAlive()) {
                    g.fillRect((c * GridPanel.CELL_PIXEL_LENGTH), (r * GridPanel.CELL_PIXEL_LENGTH), GridPanel.CELL_PIXEL_LENGTH, GridPanel.CELL_PIXEL_LENGTH);
                }
            }
        }
    }

    public int getGridSize(){
        return gridSize;
    }

    public void setGridSize(int gridSize){
        this.gridSize = gridSize;
        cells = new Cell[gridSize][gridSize];
    }
}


class Cell {
    private boolean isAlive;
    private boolean isNextGenAlive;

    private int row;
    private int col;

    public Cell(boolean isAlive){
        this.isAlive = isAlive;
    }
    public Cell(boolean isAlive, int row, int col){
        this.isAlive = isAlive;
        this.row = row;
        this.col = col;
    }

    public boolean getIsAlive(){
        return isAlive;
    }
    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
    public boolean getIsNextGenAlive(){
        return isNextGenAlive;
    }
    public void setIsNextGenAlive(boolean isNextGenAlive){
        this.isNextGenAlive = isNextGenAlive;
    }

    public int getRow(){
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
}