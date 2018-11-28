package com.example.cat;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import 	android.util.TypedValue;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.transition.TransitionManager;
import android.transition.Transition;
import android.transition.AutoTransition;
import android.view.Gravity;
import android.view.MotionEvent;
import android.support.v4.view.MotionEventCompat;
import android.view.View.OnTouchListener;

public class MainActivity extends AppCompatActivity{
    private int squareSide;
    private int margin;
    private ImageView[][] board = new ImageView[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Resources r = getResources();

        squareSide = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
        margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, r.getDisplayMetrics());

        //What to do with each button click
        //Check if all tiles are full, if so then display end game score as a toast and reset board
        //
        //Move all tiles in a direction in the array
        //and check if the contentDescription are equal if so then make new tile where the two will go and "combine them"
        //Make new ImageView tile (cat picture) and put in a blank square
        //Add reference to that tile into the array for logical purposes
        //Increment score by 9 everytime a combination occurs
        //Display a new cat fact through API call to catfact thing everytime two squares combine
        //
        //
        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            }
        });
        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            }
        });
        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            }
        });
        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

            }
        });
    }

    /**
     * Checks if the game is over
     * @return
     */
    boolean isGameOver() {
        return false;
    }

    /**
     * Moves the tiles in the board array and in activity main
     * @param board
     * @param direction
     */
    void moveTiles(ImageView[][] board, int direction) {

    }

    /**
     * Adds a new tile to the board array and into activity main
     * @param tile
     * @param board
     */
    void addTile(ImageView tile, ImageView[][] board) {

    }

    /**
     * Updates the textView for score
     * @param score
     */
    void updateScore(String score) {

    }

    /**
     * Starts the api call to the cat fact thing and updates text box
     */
    void startAPICall() {

    }





}
