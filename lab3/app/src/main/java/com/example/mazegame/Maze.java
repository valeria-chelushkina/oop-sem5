package com.example.mazegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Maze {
    public static final int WALL = 1;
    public static final int PATH = 0;
    public static final int START = 2;
    public static final int END = 3;
    
    private int[][] grid;
    private int width;
    private int height;
    private int startX, startY;
    private int endX, endY;
    private Random random;
    
    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[height][width];
        this.random = new Random();
        generateMaze();
    }
    
    private void generateMaze() {
        // initialize all cells as walls
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = WALL;
            }
        }
        
        // generate maze using recursive backtracking
        int startRow = 1;
        int startCol = 1;
        carvePassage(startRow, startCol);
        
        // set start position (top-left area)
        startX = 1;
        startY = 1;
        grid[startY][startX] = START;
        
        // find a suitable end position (bottom-right area)
        for (int i = height - 2; i > height / 2; i--) {
            for (int j = width - 2; j > width / 2; j--) {
                if (grid[i][j] == PATH) {
                    endX = j;
                    endY = i;
                    grid[endY][endX] = END;
                    return;
                }
            }
        }
        
        // fallback: set end at bottom-right
        endX = width - 2;
        endY = height - 2;
        grid[endY][endX] = END;
    }
    
    private void carvePassage(int row, int col) {
        grid[row][col] = PATH;
        
        // directions: up, right, down, left
        int[][] directions = {{-2, 0}, {0, 2}, {2, 0}, {0, -2}};
        List<int[]> dirs = new ArrayList<>();
        for (int[] dir : directions) {
            dirs.add(dir);
        }
        Collections.shuffle(dirs, random);
        
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow > 0 && newRow < height - 1 && 
                newCol > 0 && newCol < width - 1 && 
                grid[newRow][newCol] == WALL) {

                grid[row + dir[0] / 2][col + dir[1] / 2] = PATH;
                carvePassage(newRow, newCol);
            }
        }
    }
    
    public int[][] getGrid() {
        return grid;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getStartX() {
        return startX;
    }
    
    public int getStartY() {
        return startY;
    }
    
    public int getEndX() {
        return endX;
    }
    
    public int getEndY() {
        return endY;
    }
    
    public boolean isValidMove(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return grid[y][x] != WALL;
    }
    
    public boolean isEnd(int x, int y) {
        return x == endX && y == endY;
    }
}


