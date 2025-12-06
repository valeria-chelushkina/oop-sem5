package com.example.mazegame;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MazeView mazeView;
    private Button showPathButton;
    private Button resetButton;
    private Button sizeButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mazeView = findViewById(R.id.mazeView);
        showPathButton = findViewById(R.id.showPathButton);
        resetButton = findViewById(R.id.resetButton);
        sizeButton = findViewById(R.id.sizeButton);
        
        mazeView.setGameListener(new MazeView.GameListener() {
            @Override
            public void onGameWon() {
                Toast.makeText(MainActivity.this, "Вітаємо! Ви знайшли вихід!", Toast.LENGTH_LONG).show();
            }
        });
        
        showPathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mazeView.toggleSolution();
                if (mazeView.isShowingSolution()) {
                    showPathButton.setText("Приховати шлях");
                } else {
                    showPathButton.setText("Показати шлях");
                }
            }
        });
        
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mazeView.resetGame();
                showPathButton.setText("Показати шлях");
                Toast.makeText(MainActivity.this, "Лабіринт перезапущено!", Toast.LENGTH_SHORT).show();
            }
        });
        
        sizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSizeDialog();
            }
        });
        
        // Request focus for key events
        mazeView.requestFocus();
    }
    
    private void showSizeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Виберіть розмір лабіринту");
        
        // calculate max size based on screen
        int maxSize = 51;
        if (mazeView.getWidth() > 0 && mazeView.getHeight() > 0) {
            float minCellSize = 10;
            int maxWidth = (int) (mazeView.getWidth() / minCellSize);
            int maxHeight = (int) (mazeView.getHeight() / minCellSize);
            maxSize = Math.min(51, Math.min(maxWidth, maxHeight));
        }
        
        final EditText input = new EditText(this);
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        input.setHint("Розмір (15-" + maxSize + ")");
        input.setText(String.valueOf(mazeView.getMazeSize()));
        builder.setView(input);
        
        final int finalMaxSize = maxSize;
        builder.setPositiveButton("ОК", (dialog, which) -> {
            try {
                int size = Integer.parseInt(input.getText().toString());
                if (size < 15) {
                    Toast.makeText(this, "Мінімальний розмір: 15", Toast.LENGTH_SHORT).show();
                    size = 15;
                } else if (size > finalMaxSize) {
                    Toast.makeText(this, "Максимальний розмір: " + finalMaxSize, Toast.LENGTH_SHORT).show();
                    size = finalMaxSize;
                }
                mazeView.createMaze(size);
                showPathButton.setText("Показати шлях");
                Toast.makeText(this, "Розмір лабіринту: " + size + "x" + size, Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Введіть коректне число", Toast.LENGTH_SHORT).show();
            }
        });
        
        builder.setNegativeButton("Скасувати", (dialog, which) -> dialog.cancel());
        builder.show();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        mazeView.requestFocus();
    }
}

