package com.example.cat;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity{
    private int squareSide;
    private int margin;
    private TextView[][] board = new TextView[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Resources r = getResources();

        squareSide = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
        margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, r.getDisplayMetrics());
        TextView first = findViewById(R.id.cat1);
        board[1][1] = first;


        //What to do with each button click
        //
        //Move all tiles in a direction in the array
        //and check if the contentDescription are equal if so then make new tile where the two will go and "combine them"
        //Make new ImageView tile (cat picture) and put in a blank square
        //Add reference to that tile into the array for logical purposes
        //Increment score by 9 everytime a combination occurs
        //Display a new cat fact through API call to catfact thing everytime two squares combine
        //Check if all tiles are full, if so then display end game score as a toast and reset board
        //
        //
        //combining two tiles is basically just making one tile invisible and changing the text
        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                moveTiles(board, 1);


            }
        });
        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                moveTiles(board, 2);

            }
        });
        findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                moveTiles(board, 3);

            }
        });
        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                moveTiles(board, 4);

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
     * resets the Board.
     */
    void resetBoard(){

    }

    /**
     * Moves the tiles in the board array and in activity main
     * @param board
     * @param direction
     */
    void moveTiles(TextView[][] board, int direction) {

        //1: up 2: down 3: right 4: left
        //copies layout of the current app
        final ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);

        //makes a newTile
        TextView newTile = new TextView(layout.getContext());
        //gives new tile an id
        newTile.setId(View.generateViewId());

        //IF ONLY ONE ELEMENT IN ROW/COLUMN JUST MOVE IT

        //if direction is up
        if (direction == 1) {

            //determines what kind of vertical combination should be performed or not
            for (int j = 0; j < board[0].length; j++) {
                //checks for single element
                int count = 0;
                TextView single = null;
                for (int i = 0; i < board.length; i++) {
                    if (board[i][j] == null) {
                        count++;
                    } else {
                        single = board[i][j];
                    }

                }
                if (count == 2) {
                    moveUp(single);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[0][j] != null && board[1][j] != null && board[0][j].getText().equals(board[1][j].getText())) {

                    combineVertical(board[0][j], board[1][j], 0);

                } else if (board[0][j] != null && board[2][j] != null && board[0][j].equals(board[2][j].getText())) {

                    combineVertical(board[0][j], board[2][j], 1);

                } else if (board[1][j] != null && board[2][j] != null && board[1][j].equals(board[2][j].getText())) {

                    combineVertical(board[1][j], board[2][j], 2);

                }


            }
        }
        //if direction is down
        if (direction == 2) {

            //determines what kind of vertical combination should be performed
            for (int j = 0; j < board[0].length; j++) {
                //checks for single element
                int count = 0;
                TextView single = null;
                for (int i = 0; i < board.length; i++) {
                    if (board[i][j] == null) {
                        count++;
                    } else {
                        single = board[i][j];
                    }

                }
                if (count == 2) {
                    moveDown(single);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[2][j] != null && board[1][j] != null && board[0][j].getText().equals(board[1][j].getText())) {

                    combineVertical(board[2][j], board[1][j], 3);

                } else if (board[2][j] != null && board[0][j] != null && board[0][j].equals(board[2][j].getText())) {

                    combineVertical(board[2][j], board[0][j], 4);

                } else if (board[1][j] != null && board[0][j] != null && board[1][j].equals(board[2][j].getText())) {

                    combineVertical(board[1][j], board[0][j], 5);

                }

            }
        }
        //if direction is to the right
        if (direction == 3) {

            //determines what kind of vertical combination should be performed
            for (int i = 0; i < board.length; i++) {
                //checks for single element
                int count = 0;
                TextView single = null;
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == null) {
                        count++;
                    } else {
                        single = board[i][j];
                    }

                }
                if (count == 2) {
                    moveRight(single);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[i][0] != null && board[i][1] != null && board[i][0].getText().equals(board[i][1].getText())) {

                    combineHorizontal(board[i][0], board[i][1], 0);

                } else if (board[i][0] != null && board[i][2] != null && board[i][0].equals(board[i][2].getText())) {

                    combineHorizontal(board[i][0], board[i][2], 1);

                } else if (board[i][1] != null && board[i][2] != null && board[i][1].equals(board[i][2].getText())) {

                    combineHorizontal(board[i][1], board[i][2], 2);

                }

            }
        }
        //if direction is to the left
        if (direction == 4) {


            //determines what kind of vertical combination should be performed
            for (int i = 0; i < board.length; i++) {
                //checks for single element
                int count = 0;
                TextView single = null;
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == null) {
                        count++;
                    } else {
                        single = board[i][j];
                    }

                }
                if (count == 2) {
                    moveLeft(single);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[i][2] != null && board[i][1] != null && board[i][2].getText().equals(board[i][1].getText())) {

                    combineHorizontal(board[i][2], board[i][1], 3);

                } else if (board[i][2] != null && board[i][0] != null && board[i][2].equals(board[i][0].getText())) {

                    combineHorizontal(board[i][2], board[i][0], 4);

                } else if (board[i][1] != null && board[i][0] != null && board[i][1].equals(board[i][0].getText())) {

                    combineHorizontal(board[i][1], board[i][0], 5);

                }

            }
        }




    }

    /**
     * Adds a new tile to the board array and into activity main
     * @param tile
     * @param board
     */
    void addTile(TextView tile, TextView[][] board) {

    }

    /**
     * Updates the textView for score
     * @param score
     */
    void updateScore(String score) {

    }

    /**
     * handles the different types of vertical combinations.
     * 0 (top/middle), 1(top/bottom), 2(middle/bottom) are for up
     * 3(bottom/middle), 4(bottom/top), 5(middle/top) are for down
     * special case for middle/bottom and middle/top (if there is a tile above/below that is present
     * then it should combine to the middle not all the way
     * @param first
     * @param second
     * @param type
     */
    void combineVertical(TextView first, TextView second, int type) {
        //first determine what kind of cat it is by getText() and which cat to add into place
        //determine if it is the special case and execute that piece of code
        //otherwise put everything to bottom/top
        String catType = (String) first.getText();


        if (type == 0 || type == 1) {

        } else if (type == 2) {

        } else if (type == 3 || type == 4) {

        } else {

        }


    }

    /**
     * handles the different types of horizontal combinations.
     * 0(right/middle), 1(right/left), 2(middle/left) are for right
     * 3(left, middle), 4(left, right(, 5(middle, right) are for left
     * @param first
     * @param second
     * @param type
     */
    void combineHorizontal(TextView first, TextView second, int type) {
        if (type == 0 || type == 1) {

        } else if (type == 2) {

        } else if (type == 3 || type == 4) {

        } else {

        }

    }

    /**
     * if column only has one element
     */
    void moveUp(TextView tile) {


    }

    /**
     * if column only has one element
     */
    void moveDown(TextView tile) {

    }

    void moveRight(TextView tile) {

    }

    void moveLeft(TextView tile) {

    }


    /**
     * Starts the api call to the cat fact thing and updates text box
     */
    void startAPICall() {

    }





}
