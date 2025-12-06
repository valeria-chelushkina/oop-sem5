package com.example.mazegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathFinder {
    private Maze maze;
    
    public PathFinder(Maze maze) {
        this.maze = maze;
    }
    
    public List<int[]> findPath(int startX, int startY, int endX, int endY) {
        int width = maze.getWidth();
        int height = maze.getHeight();
        
        // BFS to find shortest path
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[height][width];
        int[][][] parent = new int[height][width][2];
        
        queue.offer(new int[]{startX, startY});
        visited[startY][startX] = true;
        parent[startY][startX] = new int[]{-1, -1};
        
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}}; // up, right, down, left
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            
            if (x == endX && y == endY) {
                // reconstruct path
                List<int[]> path = new ArrayList<>();
                int[] node = new int[]{endX, endY};
                while (node[0] != -1 && node[1] != -1) {
                    path.add(node);
                    if (node[0] == startX && node[1] == startY) {
                        break;
                    }
                    node = parent[node[1]][node[0]];
                }
                Collections.reverse(path);
                return path;
            }
            
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                
                if (newX >= 0 && newX < width && newY >= 0 && newY < height &&
                    !visited[newY][newX] && maze.isValidMove(newX, newY)) {
                    visited[newY][newX] = true;
                    parent[newY][newX] = new int[]{x, y};
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        
        return new ArrayList<>(); // no path found
    }
}


