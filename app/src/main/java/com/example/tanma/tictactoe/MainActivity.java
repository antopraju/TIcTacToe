package com.example.tanma.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean mSecond;

    private boolean mRunning = true;

    private Button mRestart;

    private Button[][] mGrid = new Button[3][3];

    private int[][] mIds = {{R.id.b1, R.id.b2, R.id.b3}, {R.id.b4, R.id.b5, R.id.b6}, {R.id.b7, R.id.b8, R.id.b9}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Log.d("values", i + ", " + j);
                mGrid[i][j] = (Button) findViewById(mIds[i][j]);

                    mGrid[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick (View v){

                            if(mRunning){

                            Button b = (Button) v;
                            if (mSecond) {

                                if (b.getText().toString().isEmpty()) {
                                    b.setText("O");
                                    mSecond = !mSecond;
                                }
                            } else {
                                // Log.d("values", i[0] + ", " + j[0]);
                                if (b.getText().toString().isEmpty()) {
                                    b.setText("X");
                                    mSecond = !mSecond;
                                }
                            }

                            //TODO check all rows and columns and diagonals for any winner
                        /* Checking all rows */
                            if (!mRunning) return;

                            boolean allTurnsCompleted = true;
                            outer:
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                    if (mGrid[i][j].getText().toString().isEmpty()) {
                                        allTurnsCompleted = false;
                                        break outer;
                                    }
                                }
                            }

                            if (allTurnsCompleted) {
                                Toast.makeText(getApplicationContext(), "Tie", Toast.LENGTH_SHORT).show();
                                mRunning = false;
                                return;
                            }

                            for (int i = 0; i < 3; i++) {
                                boolean row = true;
                                String initial = mGrid[i][0].getText().toString();
                                for (int j = 1; j < 3; j++) {
                                    if (!mGrid[i][j].getText().toString().equals(initial)) {
                                        row = false;
                                        break;
                                    }
                                }
                                if (row) {
                                    if (!initial.isEmpty()) {
                                        mRunning = false;
                                        Toast.makeText(getApplicationContext(), (initial.equals("X") ? "First" : "Second") + " player wins", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                }
                            }

                        /* Checking columns */

                            for (int i = 0; i < 3; i++) {
                                boolean cols = true;
                                String initial = mGrid[0][i].getText().toString();
                                for (int j = 1; j < 3; j++) {
                                    if (!mGrid[j][i].getText().toString().equals(initial)) {
                                        cols = false;
                                        break;
                                    }
                                }
                                if (cols) {
                                    if (!initial.isEmpty()) {
                                        mRunning = false;
                                        Toast.makeText(getApplicationContext(), (initial.equals("X") ? "First" : "Second") + " player wins", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                }
                            }

                        /* Checking diagonals */

                            Button[] primaryDiagonal = {mGrid[0][0], mGrid[1][1], mGrid[2][2]};
                            Button[] secondaryDiagonal = {mGrid[0][2], mGrid[1][1], mGrid[2][0]};

                            String initial = primaryDiagonal[0].getText().toString();
                            boolean flag = true;
                            for (Button button : primaryDiagonal) {
                                if (initial.isEmpty()) {
                                    flag = false;
                                    break;
                                }
                                if (!button.getText().toString().equals(initial)) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                mRunning = false;
                                Toast.makeText(getApplicationContext(), (initial.equals("X") ? "First" : "Second") + " player wins", Toast.LENGTH_SHORT).show();

                            }

                            initial = secondaryDiagonal[0].getText().toString();
                            flag = true;
                            for (Button button : secondaryDiagonal) {
                                if (initial.isEmpty()) {
                                    flag = false;
                                    break;
                                }
                                if (!button.getText().toString().equals(initial)) {
                                    flag = false;
                                    break;
                                }
                            }

                            if (flag) {
                                mRunning = false;
                                Toast.makeText(getApplicationContext(), (initial.equals("X") ? "First" : "Second") + " player wins", Toast.LENGTH_SHORT).show();
                            }

                        }
                        }
                    });

            }
        }

        mRestart = (Button) findViewById(R.id.restart);
        mRestart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        mGrid[i][j].setText(null);
                    }
                }
                mRunning = true;
            }
        });
    }
}
