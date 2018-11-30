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
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.support.v4.content.ContextCompat;
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

        squareSide = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 130, r.getDisplayMetrics());
        margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, r.getDisplayMetrics());
        TextView first = findViewById(R.id.cat1);
        board[1][1] = first;

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
                    moveVertical(single, null, 0);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[0][j] != null && board[1][j] != null && board[0][j].getText().equals(board[1][j].getText())) {
                    //top/middle

                    combineVertical(board[0][j], board[1][j], 0, j);

                } else if (board[0][j] != null && board[2][j] != null && board[1][j] == null && board[0][j].getText().equals(board[2][j].getText())) {
                    //top/null/bottom

                    combineVertical(board[0][j], board[2][j], 1, j);

                } else if (board[0][j] == null && board[1][j] != null && board[2][j] != null && board[1][j].getText().equals(board[2][j].getText())) {
                    //null/middle/bottom

                    combineVertical(board[1][j], board[2][j], 2, j);

                } else if (board[0][j] != null && board[1][j] != null && board[2][j] != null && board[1][j].getText().equals(board[2][j].getText())) {
                    //something/middle/bottom

                    combineVertical(board[1][j], board[2][j], 3, j);

                } else if (board[0][j] != null && board[2][j] != null && board[1][j] == null && !board[0][j].getText().equals(board[2][j].getText())) {
                    //top/null/bottom

                    moveVertical(board[0][j], board[2][j], 2);

                } else if (board[0][j] == null && board[1][j] != null && board[2][j] != null && !board[1][j].getText().equals(board[2][j].getText())) {
                    //null/middle/bottom

                    moveVertical(board[1][j], board[2][j], 3);

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
                    moveVertical(single, null, 1);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[2][j] != null && board[1][j] != null && board[0][j].getText().equals(board[1][j].getText())) {
                    //bottom/middle

                    combineVertical(board[2][j], board[1][j], 4, j);

                } else if (board[2][j] != null && board[0][j] != null && board[1][j] == null && board[0][j].getText().equals(board[2][j].getText())) {
                    //bottom/null/top

                    combineVertical(board[2][j], board[0][j], 5, j);

                } else if (board[0][j] == null && board[1][j] != null && board[0][j] != null && board[1][j].getText().equals(board[2][j].getText())) {
                    //null/middle/top

                    combineVertical(board[1][j], board[0][j], 6, j);

                } else if (board[0][j] != null && board[1][j] != null && board[2][j] != null && board[1][j].getText().equals(board[2][j].getText())) {
                    //something/middle/top

                    combineVertical(board[1][j], board[0][j], 7, j);

                } else if (board[0][j] != null && board[2][j] != null && board[1][j] == null && !board[0][j].getText().equals(board[2][j].getText())) {
                    //bottom/null/top

                    moveVertical(board[2][j], board[0][j], 4);

                } else if (board[0][j] == null && board[1][j] != null && board[0][j] != null && !board[1][j].getText().equals(board[0][j].getText())) {
                    //null/middle/top

                    moveVertical(board[1][j], board[0][j], 5);

                }

            }
        }
        //if direction is to the right
        if (direction == 3) {

            //determines what kind of horizontal combination should be performed
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
                    moveHorizontal(single, null, 0);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[i][0] != null && board[i][1] != null && board[i][0].getText().equals(board[i][1].getText())) {

                    combineHorizontal(board[i][0], board[i][1], 0, i);

                } else if (board[i][0] != null && board[i][2] != null && board[i][0].getText().equals(board[i][2].getText())) {

                    combineHorizontal(board[i][0], board[i][2], 1, i);

                } else if (board[i][1] != null && board[i][2] != null && board[i][1].getText().equals(board[i][2].getText())) {

                    combineHorizontal(board[i][1], board[i][2], 2, i);

                }

            }
        }
        //if direction is to the left
        if (direction == 4) {


            //determines what kind of horizontal combination should be performed
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
                    moveHorizontal(single, null, 1);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[i][2] != null && board[i][1] != null && board[i][2].getText().equals(board[i][1].getText())) {

                    combineHorizontal(board[i][2], board[i][1], 3, i);

                } else if (board[i][2] != null && board[i][0] != null && board[i][2].getText().equals(board[i][0].getText())) {

                    combineHorizontal(board[i][2], board[i][0], 4, i);

                } else if (board[i][1] != null && board[i][0] != null && board[i][1].getText().equals(board[i][0].getText())) {

                    combineHorizontal(board[i][1], board[i][0], 5, i);

                }

            }
        }

        int[] valid = validAdd();
        if (valid == null) {

            //DO SOMETHING INDICATING GAME IS OVER
        } else {
            addTile(-1, valid[0], valid[1]);
        }




    }
    int[] validAdd() {
        int[] valid = new int[2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    valid[0] = i;
                    valid[1] = j;
                    return valid;
                }
            }
        }
        return null;

    }

    /**
     * Adds a new tile to the board array and into activity main. Called by combine methods
     * @param catType the type of cat to add
     * @param row which row to add
     * @param col which col to add to
     */
    void addTile(int catType, int row, int col) {
        final ConstraintLayout layout = findViewById(R.id.layout);
        ConstraintSet newSet = new ConstraintSet();
        newSet.clone(layout);

        Drawable drawableCat = null;

        String textId = "";
        ///Make a new Cat based off old cats
        //ADD MORE CONDITIONS FOR MORE CATS
        Context mContext = getApplicationContext();
        if (catType == -1) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "0";
        }
        if (catType == 0) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat1);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "1";
        }
        //make new tile
        TextView newTile = new TextView(layout.getContext());
        newTile.setId(View.generateViewId());
        newTile.setCompoundDrawables(drawableCat, null, null, null);
        newTile.setText(textId);
        newTile.setVisibility(View.INVISIBLE);
        newTile.setGravity(Gravity.CENTER);

        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(squareSide, squareSide);
        newTile.setLayoutParams(lp);
        layout.addView(newTile);

        newSet.clone(layout);

        //determines which position to add tile to
        if (row == 0) {
            if (col == 0) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline5, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[0][0] = newTile;
            } else if (col == 1) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline6, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[0][1] = newTile;

            } else if (col == 2) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline7, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[0][2] = newTile;

            }
        } else if (row == 1) {
            if (col == 0) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline2, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline5, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[1][0] = newTile;
            } else if (col == 1) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline2, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline6, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[1][1] = newTile;

            } else if (col == 2) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline2, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline7, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[1][2] = newTile;

            }


        } else if (row == 2) {
            if (col == 0) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline3, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline5, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[2][0] = newTile;
            } else if (col == 1) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline3, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline6, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[2][1] = newTile;

            } else if (col == 2) {
                newSet.connect(newTile.getId(), ConstraintSet.TOP,
                        R.id.guideline3, ConstraintSet.BOTTOM);
                newSet.connect(newTile.getId(), ConstraintSet.START,
                        R.id.guideline7, ConstraintSet.END);

                newSet.applyTo(layout);

                newTile.setVisibility(View.VISIBLE);
                board[2][2] = newTile;

            }

        }

    }

    /**
     * Updates the textView for score
     * @param score
     */
    void updateScore(String score) {

    }

    /**
     * handles the different types of vertical combinations. and calls updateScore
     * special case for middle/bottom and middle/top (if there is a tile above/below that is present
     * then it should combine to the middle not all the way
     * @param first
     * @param second
     * @param type
     * @param col which column is being combined (0: right, 1: middle, 2: left)
     */
    void combineVertical(TextView first, TextView second, int type, int col) {
        //first determine what kind of cat it is by getText() and which cat to add into place
        //determine if it is the special case and execute that piece of code
        //otherwise put everything to bottom/top

        final ConstraintLayout layout = findViewById(R.id.layout);
        ConstraintSet newSet = new ConstraintSet();
        newSet.clone(layout);


        String catType = (String) first.getText();
        int catToAdd = Integer.parseInt(catType) + 1; //pass this into add

            if (type == 0) {
                //top/middle
                //remove destination tile
                layout.removeView(first);
                first.setVisibility(View.INVISIBLE);
                //move tiles to destination tile
                newSet.clear(second.getId(), ConstraintSet.TOP);
                newSet.connect(second.getId(), ConstraintSet.TOP,
                        R.id.guideline, ConstraintSet.BOTTOM);

                if (board[2][col] != null) {
                    newSet.clear(board[2][col].getId(), ConstraintSet.TOP);
                    newSet.connect(board[2][col].getId(), ConstraintSet.TOP,
                            R.id.guideline2, ConstraintSet.BOTTOM);
                }

                //remove tile at destination and then add new cat tile
                layout.removeView(second);
                second.setVisibility(View.INVISIBLE);
                addTile(catToAdd, 0, col);
                //update board state
                board[1][col] = board[2][col];
                board[2][col] = null;
                //call update score;

            } else if (type == 1) {
                //top/null/bottom
                //remove destination tile
                layout.removeView(first);
                //move tiles to destination tile
                newSet.clear(second.getId(), ConstraintSet.TOP);
                newSet.connect(second.getId(), ConstraintSet.TOP,
                        R.id.guideline, ConstraintSet.BOTTOM);

                //remove tile at destination and then add new cat tile
                layout.removeView(second);
                addTile(catToAdd, 0, col);
                //update board state
                board[2][col] = null;
                //call update score;


            } else if (type == 2) {
                //null/middle/bottom
                //move tiles to destination tile
                newSet.clear(first.getId(), ConstraintSet.TOP);
                newSet.connect(first.getId(), ConstraintSet.TOP,
                        R.id.guideline, ConstraintSet.BOTTOM);

                newSet.clear(second.getId(), ConstraintSet.TOP);
                newSet.connect(second.getId(), ConstraintSet.TOP,
                        R.id.guideline, ConstraintSet.BOTTOM);

                //remove tile at destination and then add new cat tile
                layout.removeView(first);
                layout.removeView(second);
                addTile(catToAdd, 0, col);
                //update board state
                board[1][col] = null;
                board[2][col] = null;
                //call update score;

            } else if (type == 3) {
                //something/middle/bottom
                //move tiles to destination tile
                layout.removeView(first);

                newSet.clear(second.getId(), ConstraintSet.TOP);
                newSet.connect(second.getId(), ConstraintSet.TOP,
                        R.id.guideline2, ConstraintSet.BOTTOM);

                //remove tile at destination and then add new cat tile
                layout.removeView(second);
                addTile(catToAdd, 1, col);
                //update board state
                board[2][col] = null;
                //call update score;

            } else if (type == 4) {
                //bottom/middle
                //move tiles to destination tile
                layout.removeView(second);

                newSet.clear(first.getId(), ConstraintSet.TOP);
                newSet.connect(first.getId(), ConstraintSet.TOP,
                        R.id.guideline3, ConstraintSet.BOTTOM);

                if (board[0][col] != null) {
                    newSet.clear(board[0][col].getId(), ConstraintSet.TOP);
                    newSet.connect(board[0][col].getId(), ConstraintSet.TOP,
                            R.id.guideline2, ConstraintSet.BOTTOM);
                }

                //remove tile at destination and then add new cat tile

                layout.removeView(first);
                addTile(catToAdd, 2, col);
                //update board state
                board[1][col] = board[0][col];
                board[0][col] = null;
                //call update score;

            } else if (type == 5) {
                //bottom/null/top
                //move tiles to destination tile
                layout.removeView(first);

                newSet.clear(second.getId(), ConstraintSet.TOP);
                newSet.connect(second.getId(), ConstraintSet.TOP,
                        R.id.guideline3, ConstraintSet.BOTTOM);

                //remove tile at destination and then add new cat tile
                layout.removeView(second);
                addTile(catToAdd, 2, col);

                //update board state
                board[0][col] = null;
                //call update score;


            } else if (type == 6) {
                //null/middle/top
                //move tiles to destination tile

                newSet.clear(first.getId(), ConstraintSet.TOP);
                newSet.connect(first.getId(), ConstraintSet.TOP,
                        R.id.guideline3, ConstraintSet.BOTTOM);

                newSet.clear(second.getId(), ConstraintSet.TOP);
                newSet.connect(second.getId(), ConstraintSet.TOP,
                        R.id.guideline3, ConstraintSet.BOTTOM);

                //remove tile at destination and then add new cat tile
                layout.removeView(first);
                layout.removeView(second);
                addTile(catToAdd, 2, col);

                //update board state
                board[0][col] = null;
                board[1][col] = null;
                //call update score;


            } else {
                //something/middle/top
                //move tiles to destination tile
                layout.removeView(first);
                newSet.clear(second.getId(), ConstraintSet.TOP);
                newSet.connect(second.getId(), ConstraintSet.TOP,
                        R.id.guideline2, ConstraintSet.BOTTOM);

                //remove tile at destination and then add new cat tile
                layout.removeView(second);
                addTile(catToAdd, 1, col);

                //update board state
                board[0][col] = null;
                //call update score;


            }
        TransitionManager.beginDelayedTransition(layout);

        newSet.applyTo(layout);

        }

    /**
     * handles the different types of horizontal combinations.
     * 0(right/middle), 1(right/left), 2(middle/left) are for right
     * 3(left, middle), 4(left, right(, 5(middle, right) are for left
     * @param first
     * @param second
     * @param type
     * @param row which row is being combined (0: top, 1: middle, 2: bottom)
     */
    void combineHorizontal(TextView first, TextView second, int type, int row) {

        final ConstraintLayout layout = findViewById(R.id.layout);
        ConstraintSet newSet = new ConstraintSet();
        if (type == 0 || type == 1) {

        } else if (type == 2) {

        } else if (type == 3 || type == 4) {

        } else {

        }

    }

    /**
     * if columns has no equal tiles then just move
     *
     * @param tile1
     * @param tile2
     * @param type 0: single element moving up 1: single element moving down. 2: top/bottom 3: middle/bottom
     */
    void moveVertical(TextView tile1, TextView tile2, int type) {

        final ConstraintLayout layout = findViewById(R.id.layout);
        ConstraintSet newSet = new ConstraintSet();
        newSet.clone(layout);

        if (type == 0) {
            //single tile going up
            newSet.clear(tile1.getId(), ConstraintSet.TOP);
            newSet.connect(tile1.getId(), ConstraintSet.TOP,
                    R.id.guideline, ConstraintSet.BOTTOM);
        } else if (type == 1){
            //single tile going down
            newSet.clear(tile1.getId(), ConstraintSet.TOP);
            newSet.connect(tile1.getId(), ConstraintSet.TOP,
                    R.id.guideline3, ConstraintSet.BOTTOM);
        } else if (type == 2) {
            //top/bottom going up
            newSet.clear(tile2.getId(), ConstraintSet.TOP);
            newSet.connect(tile2.getId(), ConstraintSet.TOP,
                    R.id.guideline2, ConstraintSet.BOTTOM);

        } else if (type == 3) {
            //null/middle/bottom going up
            newSet.clear(tile1.getId(), ConstraintSet.TOP);
            newSet.clear(tile2.getId(), ConstraintSet.TOP);
            newSet.connect(tile1.getId(), ConstraintSet.TOP,
                    R.id.guideline, ConstraintSet.BOTTOM);
            newSet.connect(tile2.getId(), ConstraintSet.TOP,
                    R.id.guideline2, ConstraintSet.BOTTOM);


        } else if (type == 4) {
            //bottom/null/top going down
            newSet.clear(tile2.getId(), ConstraintSet.TOP);
            newSet.connect(tile2.getId(), ConstraintSet.TOP,
                    R.id.guideline2, ConstraintSet.BOTTOM);


        } else if (type == 5) {
            //null/middle/top going down
            newSet.clear(tile1.getId(), ConstraintSet.TOP);
            newSet.clear(tile2.getId(), ConstraintSet.TOP);
            newSet.connect(tile1.getId(), ConstraintSet.TOP,
                    R.id.guideline3, ConstraintSet.BOTTOM);
            newSet.connect(tile2.getId(), ConstraintSet.TOP,
                    R.id.guideline2, ConstraintSet.BOTTOM);

        }
        TransitionManager.beginDelayedTransition(layout);
        newSet.applyTo(layout);

        }


    /**
     * if rows has no equal tiles then just move
     * @param tile1
     * @param tile2
     * @param type
     */
    void moveHorizontal(TextView tile1, TextView tile2, int type) {

    }


    /**
     * Starts the api call to the cat fact thing and updates text box
     */
    void startAPICall() {

    }





}
