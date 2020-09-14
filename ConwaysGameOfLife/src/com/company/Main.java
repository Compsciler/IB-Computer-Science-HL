package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.generateRandomStates();
        grid.printConsole();
        System.out.println();
        for (int i = 0; i < 10; i++){
            grid.update();
            grid.printConsole();
            System.out.println();
        }
    }
}


class Grid {
    private int gridSize = 5;
    private boolean isWrapAround = false;
    private double aliveChance = 0.3;
    private Cell[][] cells;

    public Grid(){
        cells = new Cell[gridSize][gridSize];
    }

    public void generateRandomStates() {
        Random rand = new Random();
        for (int r = 0; r < gridSize; r++){
            for (int c = 0; c < gridSize; c++){
                Cell cell;
                if (rand.nextDouble() < aliveChance) {
                    cell = new Cell(true, r, c);
                } else {
                    cell = new Cell(false, r, c);
                }
                cells[r][c] = cell;
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