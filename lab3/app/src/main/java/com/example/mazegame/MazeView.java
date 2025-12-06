package com.example.mazegame;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.util.List;

public class MazeView extends View {
    private Maze maze;
    private PathFinder pathFinder;
    private int playerX, playerY;
    private float playerAnimX, playerAnimY;
    private float cellWidth, cellHeight;
    private Paint wallPaint;
    private Paint pathPaint;
    private Paint playerPaint;
    private Paint playerOutlinePaint;
    private Paint endPaint;
    private Paint solutionPaint;
    private List<int[]> solutionPath;
    private boolean showSolution = false;
    private GestureDetector gestureDetector;
    private ValueAnimator moveAnimator;
    private boolean isAnimating = false;
    private boolean gameWon = false;
    private int currentMazeSize = 37;
    private Runnable autoRestartRunnable;
    
    public MazeView(Context context) {
        super(context);
        init();
    }
    
    public MazeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);
        
        // detector for swipe controls
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (gameWon) {
                    return false; // when the game is won - don't allow swipes
                }
                
                float dx = e2.getX() - e1.getX();
                float dy = e2.getY() - e1.getY();
                
                // minimum swipe distance to trigger movement
                float minSwipeDistance = 50;
                if (Math.abs(dx) < minSwipeDistance && Math.abs(dy) < minSwipeDistance) {
                    return false;
                }
                
                if (Math.abs(dx) > Math.abs(dy)) {
                    // horizontal swipe
                    if (dx > 0) {
                        movePlayer(1, 0); // right
                    } else {
                        movePlayer(-1, 0); // left
                    }
                } else {
                    // vertical swipe
                    if (dy > 0) {
                        movePlayer(0, 1); // down
                    } else {
                        movePlayer(0, -1); // up
                    }
                }
                return true;
            }
            
            @Override
            public boolean onDown(MotionEvent e) {
                return true; // return true to receive subsequent events
            }
        });
        
        // initialize paints
        wallPaint = new Paint();
        wallPaint.setColor(Color.BLACK);
        wallPaint.setStyle(Paint.Style.STROKE);
        wallPaint.setStrokeWidth(2);
        wallPaint.setAntiAlias(true);
        
        pathPaint = new Paint();
        pathPaint.setColor(Color.WHITE);
        pathPaint.setStyle(Paint.Style.FILL);
        
        playerPaint = new Paint();
        playerPaint.setColor(Color.rgb(120, 81, 169));
        playerPaint.setStyle(Paint.Style.FILL);
        playerPaint.setAntiAlias(true);

        playerOutlinePaint = new Paint();
        playerOutlinePaint.setColor(Color.rgb(120, 81, 169));
        playerOutlinePaint.setStyle(Paint.Style.STROKE);
        playerOutlinePaint.setStrokeWidth(3);
        playerOutlinePaint.setAntiAlias(true);
        
        endPaint = new Paint();
        endPaint.setColor(Color.RED);
        endPaint.setStyle(Paint.Style.FILL);
        endPaint.setAntiAlias(true);
        
        solutionPaint = new Paint();
        solutionPaint.setColor(Color.RED);
        solutionPaint.setStyle(Paint.Style.FILL);
        solutionPaint.setAlpha(150);
        
        // create maze of default size
        createMaze(37);
    }
    
    public void createMaze(int size) {
        // ensure size is odd and within limits
        int finalSize = size;
        if (finalSize % 2 == 0) finalSize++;
        if (finalSize < 15) finalSize = 15; // min size

        int maxSize = 51;
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        if (viewWidth <= 0 || viewHeight <= 0) {
            // create maze with requested size
            createMazeInternal(finalSize);
            post(new Runnable() {
                @Override
                public void run() {
                    if (maze != null && getWidth() > 0 && getHeight() > 0) {
                        float maxCellWidth = (float) getWidth() / maze.getWidth();
                        float maxCellHeight = (float) getHeight() / maze.getHeight();
                        float cellSize = Math.min(maxCellWidth, maxCellHeight);
                        cellWidth = cellSize;
                        cellHeight = cellSize;
                        invalidate();
                    }
                }
            });
            return;
        } else {
            // ensure maze fits on screen
            float minCellSize = 10;
            int maxWidth = (int) (viewWidth / minCellSize);
            int maxHeight = (int) (viewHeight / minCellSize);
            maxSize = Math.min(51, Math.min(maxWidth, maxHeight));
        }
        if (finalSize > maxSize) finalSize = maxSize;
        
        createMazeInternal(finalSize);
    }
    
    private void createMazeInternal(int size) {
        if (moveAnimator != null && moveAnimator.isRunning()) {
            moveAnimator.cancel();
        }
        isAnimating = false;
        gameWon = false; // reset game won state
        currentMazeSize = size; // save current maze size
        
        maze = new Maze(size, size);
        pathFinder = new PathFinder(maze);
        playerX = maze.getStartX();
        playerY = maze.getStartY();
        playerAnimX = playerX;
        playerAnimY = playerY;
        solutionPath = null;
        showSolution = false;

        if (getWidth() > 0 && getHeight() > 0) {
            float maxCellWidth = (float) getWidth() / maze.getWidth();
            float maxCellHeight = (float) getHeight() / maze.getHeight();
            float cellSize = Math.min(maxCellWidth, maxCellHeight);
            cellWidth = cellSize;
            cellHeight = cellSize;
        } else {
            cellWidth = 20;
            cellHeight = 20;
        }

        requestLayout();
        invalidate();
    }
    
    public int getMazeSize() {
        return maze != null ? maze.getWidth() : 37;
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (maze != null && w > 0 && h > 0) {
            float maxCellWidth = (float) w / maze.getWidth();
            float maxCellHeight = (float) h / maze.getHeight();
            float cellSize = Math.min(maxCellWidth, maxCellHeight);
            cellWidth = cellSize;
            cellHeight = cellSize;
            invalidate();
        }
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (maze == null) return;

        if (cellWidth <= 0 || cellHeight <= 0) {
            int w = getWidth();
            int h = getHeight();
            if (w > 0 && h > 0 && maze != null) {
                float maxCellWidth = (float) w / maze.getWidth();
                float maxCellHeight = (float) h / maze.getHeight();
                float cellSize = Math.min(maxCellWidth, maxCellHeight);
                cellWidth = cellSize;
                cellHeight = cellSize;
            } else {
                return;
            }
        }
        
        int[][] grid = maze.getGrid();

        Paint wallFillPaint = new Paint();
        wallFillPaint.setColor(Color.BLACK);
        wallFillPaint.setStyle(Paint.Style.FILL);
        
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                float left = j * cellWidth;
                float top = i * cellHeight;
                float right = left + cellWidth;
                float bottom = top + cellHeight;
                
                if (grid[i][j] == Maze.WALL) {
                    canvas.drawRect(left, top, right, bottom, wallFillPaint);
                } else {
                    canvas.drawRect(left, top, right, bottom, pathPaint);
                    
                    if (grid[i][j] == Maze.END) {
                        canvas.drawRect(left, top, right, bottom, endPaint);
                    }
                }
            }
        }
        
        // draw solution path if enabled
        if (showSolution && solutionPath != null) {
            for (int[] point : solutionPath) {
                float left = point[0] * cellWidth;
                float top = point[1] * cellHeight;
                float right = left + cellWidth;
                float bottom = top + cellHeight;
                canvas.drawRect(left, top, right, bottom, solutionPaint);
            }
        }
        
        // draw player
        float playerCenterX = playerAnimX * cellWidth + cellWidth / 2f;
        float playerCenterY = playerAnimY * cellHeight + cellHeight / 2f;
        float playerRadius = Math.min(cellWidth, cellHeight) * 0.3f;
        canvas.drawCircle(playerCenterX, playerCenterY, playerRadius, playerPaint);
        canvas.drawCircle(playerCenterX, playerCenterY, playerRadius, playerOutlinePaint);
    }
    
    public interface GameListener {
        void onGameWon();
    }
    
    private GameListener gameListener;
    
    public void setGameListener(GameListener listener) {
        this.gameListener = listener;
    }
    
    public boolean movePlayer(int dx, int dy) {
        // block movement if game is won
        if (gameWon) {
            return false;
        }

        if (!hasFocus()) {
            requestFocus();
        }
        
        int newX = playerX + dx;
        int newY = playerY + dy;
        
        if (!maze.isValidMove(newX, newY)) {
            // ensure animation state is not stuck when hitting a wall
            if (isAnimating && moveAnimator != null && moveAnimator.isRunning()) {
                moveAnimator.cancel();
            }
            // always sync positions when movement is invalid
            playerAnimX = playerX;
            playerAnimY = playerY;
            isAnimating = false;
            requestFocus();
            return false;
        }
        
        // if animation is running, cancel it and sync positions
        if (isAnimating && moveAnimator != null && moveAnimator.isRunning()) {
            moveAnimator.cancel();
            // sync animated position with actual position
            playerAnimX = playerX;
            playerAnimY = playerY;
        }
        
        // update actual position
        playerX = newX;
        playerY = newY;
        
        // start animation to new position
        animatePlayerTo(newX, newY);
        
        // update solution path if it's being shown
        if (showSolution) {
            solutionPath = pathFinder.findPath(playerX, playerY, maze.getEndX(), maze.getEndY());
        }
        
        // check if game is won
        if (isGameWon() && !gameWon) {
            gameWon = true;
            if (gameListener != null) {
                gameListener.onGameWon();
            }
            // auto-restart after 3 seconds
            autoRestartRunnable = new Runnable() {
                @Override
                public void run() {
                    resetGame();
                }
            };
            postDelayed(autoRestartRunnable, 3000);
        }
        
        return true;
    }
    
    private void animatePlayerTo(int targetX, int targetY) {
        if (moveAnimator != null && moveAnimator.isRunning()) {
            moveAnimator.cancel();
            isAnimating = false;
        }
        
        isAnimating = true;
        float startX = playerAnimX;
        float startY = playerAnimY;
        float endX = targetX;
        float endY = targetY;
        
        moveAnimator = ValueAnimator.ofFloat(0f, 1f);
        moveAnimator.setDuration(150);
        moveAnimator.setInterpolator(new DecelerateInterpolator());
        
        moveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (Float) animation.getAnimatedValue();
                playerAnimX = startX + (endX - startX) * progress;
                playerAnimY = startY + (endY - startY) * progress;
                invalidate();
            }
        });
        
        // add listener for animation end to ensure isAnimating is reset
        moveAnimator.addListener(new android.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(android.animation.Animator animation) {
            }
            
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                isAnimating = false;
                // ensure final position is exact
                playerAnimX = endX;
                playerAnimY = endY;
                invalidate();
            }
            
            @Override
            public void onAnimationCancel(android.animation.Animator animation) {
                isAnimating = false;
                // ensure final position is exact
                playerAnimX = endX;
                playerAnimY = endY;
                invalidate();
            }
            
            @Override
            public void onAnimationRepeat(android.animation.Animator animation) {
            }
        });
        
        moveAnimator.start();
    }
    
    public boolean isGameWon() {
        return maze.isEnd(playerX, playerY);
    }
    
    public void toggleSolution() {
        showSolution = !showSolution;
        if (showSolution) {
            // always recalculate path from current position
            solutionPath = pathFinder.findPath(playerX, playerY, maze.getEndX(), maze.getEndY());
        }
        invalidate();
    }
    
    public boolean isShowingSolution() {
        return showSolution;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            requestFocus();
        }
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!hasFocus()) {
            requestFocus();
        }
        
        boolean moved = false;
        boolean isMovementKey = false;
        
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                moved = movePlayer(0, -1);
                isMovementKey = true;
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                moved = movePlayer(0, 1);
                isMovementKey = true;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                moved = movePlayer(-1, 0);
                isMovementKey = true;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                moved = movePlayer(1, 0);
                isMovementKey = true;
                break;
        }

        if (isMovementKey) {
            return true;
        }
        
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!hasFocus()) {
            requestFocus();
        }
        return super.onKeyUp(keyCode, event);
    }
    
    public void resetGame() {
        if (autoRestartRunnable != null) {
            removeCallbacks(autoRestartRunnable);
            autoRestartRunnable = null;
        }
        
        if (moveAnimator != null && moveAnimator.isRunning()) {
            moveAnimator.cancel();
        }
        isAnimating = false;
        gameWon = false;

        int sizeToUse = currentMazeSize > 0 ? currentMazeSize : (maze != null ? maze.getWidth() : 37);
        createMaze(sizeToUse);
    }
}