package com.example.cat;

import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.widget.TextView;
import 	android.util.TypedValue;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.transition.TransitionManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.content.DialogInterface;
import android.transition.Transition;
import android.transition.AutoTransition;
import android.view.Gravity;
import android.os.Handler;

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

        TextView highScore = findViewById(R.id.highScore);
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        int score = prefs.getInt("key", 0);
        highScore.setText("" + score);


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
                moveTiles(board, 4);

            }
        });
        findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                moveTiles(board, 3);

            }
        });
    }

    /**
     * Moves the tiles in the board array and in activity main
     * @param board
     * @param direction
     */
    void moveTiles(TextView[][] board, int direction) {

        final ConstraintLayout layout = findViewById(R.id.layout);

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
                    moveVertical(single, null, 0, j);
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

                    moveVertical(board[0][j], board[2][j], 2, j);

                } else if (board[0][j] == null && board[1][j] != null && board[2][j] != null && !board[1][j].getText().equals(board[2][j].getText())) {
                    //null/middle/bottom

                    moveVertical(board[1][j], board[2][j], 3, j);

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
                    moveVertical(single, null, 1, j);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[2][j] != null && board[1][j] != null && board[2][j].getText().equals(board[1][j].getText())) {
                    //bottom/middle

                    combineVertical(board[2][j], board[1][j], 4, j);

                } else if (board[2][j] != null && board[0][j] != null && board[1][j] == null && board[0][j].getText().equals(board[2][j].getText())) {
                    //bottom/null/top

                    combineVertical(board[2][j], board[0][j], 5, j);

                } else if (board[2][j] == null && board[1][j] != null && board[0][j] != null && board[1][j].getText().equals(board[0][j].getText())) {
                    //null/middle/top

                    combineVertical(board[1][j], board[0][j], 6, j);

                } else if (board[2][j] != null && board[1][j] != null && board[0][j] != null && board[1][j].getText().equals(board[0][j].getText())) {
                    //something/middle/top

                    combineVertical(board[1][j], board[0][j], 7, j);

                } else if (board[0][j] != null && board[2][j] != null && board[1][j] == null && !board[0][j].getText().equals(board[2][j].getText())) {
                    //bottom/null/top

                    moveVertical(board[2][j], board[0][j], 4, j);

                } else if (board[2][j] == null && board[1][j] != null && board[0][j] != null && !board[1][j].getText().equals(board[0][j].getText())) {
                    //null/middle/top

                    moveVertical(board[1][j], board[0][j], 5, j);

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
                    moveHorizontal(single, null, 0, i);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[i][0] != null && board[i][1] != null && board[i][0].getText().equals(board[i][1].getText())) {
                    //right/middle

                    combineHorizontal(board[i][0], board[i][1], 0, i);

                } else if (board[i][0] != null && board[i][2] != null && board[i][1] == null && board[i][0].getText().equals(board[i][2].getText())) {
                    //right/null/left

                    combineHorizontal(board[i][0], board[i][2], 1, i);

                } else if (board[i][0] == null && board[i][1] != null && board[i][2] != null && board[i][1].getText().equals(board[i][2].getText())) {
                    //null/middle/left

                    combineHorizontal(board[i][1], board[i][2], 2, i);

                } else if (board[i][0] != null && board[i][1] != null && board[i][2] != null && board[i][1].getText().equals(board[i][2].getText())) {
                    //something/middle/left

                    combineHorizontal(board[i][1], board[i][2], 3, i);

                } else if (board[i][0] != null && board[i][1] == null && board[i][2] != null && !board[i][0].getText().equals(board[i][2].getText())) {
                    //right/null/left
                    moveHorizontal(board[i][0], board[i][2], 2, i);

                } else if (board[i][0] == null && board[i][1] != null && board[i][2] != null && !board[i][1].getText().equals(board[i][2].getText())) {
                    //null/middle/left
                    moveHorizontal(board[i][1], board[i][2], 3, i);
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
                    moveHorizontal(single, null, 1, i);
                    continue;
                }
                //makes sure no null and then determine if equal or not
                if (board[i][2] != null && board[i][1] != null && board[i][2].getText().equals(board[i][1].getText())) {
                    //middle/left combining left

                    combineHorizontal(board[i][1], board[i][2], 4, i);

                } else if (board[i][2] != null && board[i][1] == null && board[i][0] != null && board[i][2].getText().equals(board[i][0].getText())) {
                    //right/null/left combining left

                    combineHorizontal(board[i][0], board[i][2], 5, i);

                } else if (board[i][1] != null && board[i][0] != null && board[i][2] == null && board[i][1].getText().equals(board[i][0].getText())) {
                    //right/middle/null combining left

                    combineHorizontal(board[i][0], board[i][1], 6, i);

                } else if (board[i][1] != null && board[i][0] != null && board[i][2] != null && board[i][1].getText().equals(board[i][0].getText())) {
                    //right/middle/something combining left

                    combineHorizontal(board[i][0], board[i][1], 7, i);

                } else if (board[i][2] != null && board[i][1] == null && board[i][0] != null && !board[i][2].getText().equals(board[i][0].getText())) {
                    //right/null/left moving left
                    moveHorizontal(board[i][0], board[i][2], 4, i);
                } else if (board[i][1] != null && board[i][0] != null && board[i][2] == null && !board[i][1].getText().equals(board[i][0].getText())) {
                    //right/middle/null moving left
                    moveHorizontal(board[i][0], board[i][1], 5, i);
                }

            }
        }

        TransitionManager.beginDelayedTransition(layout);

        ArrayList<Integer> valid = validAdd();


        if (isGameOver() == 1) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    wonDialog();
                }
            }, 1500);

        } else if (valid != null){
            addTile(0, valid.get(0), valid.get(1));
        }
        if (isGameOver() == 2) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    lostDialog();
                }
            }, 1500);
        }




    }

    /**
     * if player has won display this dialog
     */
    public void wonDialog() {
//        final Dialog dialog = new Dialog(MainActivity.this); // Context, this, etc.
//        dialog.setContentView(R.layout.game_end_dialogue);
//        dialog.setTitle("GAME OVER");
//        dialog.show();
//        findViewById(R.id.dialog_ok).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                resetBoard();
//            }
//        });
        DialogUtils.showPopUp(this, "Game Over", "You've Won!", "", "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBoard();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBoard();
            }
        });

    }

    /**
     * if player has lost game display this message
     */
    public void lostDialog() {
        DialogUtils.showPopUp(this, "Game Over", "You've Lost", "", "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBoard();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetBoard();
            }
        });

    }

    /**
     * checks if player has won or lost
     * @return
     */
    int isGameOver() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null && board[i][j].getText().equals("7")) {
                    return 1;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length - 1; j++) {
                if (board[i][j] == null || board[i][j + 1] == null) {
                    return 0;
                } else if (board[j][i] == null || board[j + 1][i] == null) {
                    return 0;
                } else if (board[i][j].getText().equals(board[i][j + 1].getText())) {
                    return 0;
                } else if (board[j][i].getText().equals(board[j + 1][i].getText())) {
                    return 0;
                }
            }
        }
        return 2;
    }
    /**
     * resets the Board.
     */
    void resetBoard(){

        final ConstraintLayout layout = findViewById(R.id.layout);
        TextView score = findViewById(R.id.score);
        score.setText("0");

        //set elements of board to null
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    layout.removeView(board[i][j]);
                    board[i][j] = null;
                }
            }
        }



    }

    /**
     * Determines an arraylist of coordinates for vcaild squares to add cats.
     * @return
     */
    ArrayList<Integer> validAdd() {
        ArrayList<ArrayList<Integer>>  valid = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) {
                    ArrayList<Integer> position = new ArrayList<>();
                    position.add(i);
                    position.add(j);
                    valid.add(position);
                }
            }
        }
        if (valid.size() != 0) {
            return valid.get((int) (Math.random() * valid.size()));
        } else {
            return null;
        }

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
        if (catType == 0) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "1";
        } else if (catType == 1) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat1);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "2";
        } else if (catType == 2) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat2);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "3";
        } else if (catType == 3) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat3);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "4";
        } else if (catType == 4) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat4);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "5";
        } else if (catType == 5) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat5);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "6";
        } else if (catType == 6) {
            drawableCat = ContextCompat.getDrawable(
                    mContext,
                    R.drawable.cat6);
            if (drawableCat != null) {
                drawableCat.setBounds(0, 0, drawableCat.getIntrinsicWidth(), drawableCat.getIntrinsicHeight());
            }
            textId = "7";
        }
        //make new tile
        TextView newTile = new TextView(layout.getContext());
        newTile.setId(View.generateViewId());
        newTile.setCompoundDrawables(drawableCat, null, null, null);
        newTile.setText(textId);
        newTile.setTextSize(0);
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
    void updateScore(int score) {
        TextView currentScore = findViewById(R.id.score);
        int newScore = Integer.parseInt(currentScore.getText().toString()) + score;
        currentScore.setText("" + newScore);
        TextView highScore = findViewById(R.id.highScore);
        int hScore = Integer.parseInt(highScore.getText().toString());
        if (newScore > hScore) {
            updateHighScore(newScore);

        }

    }
    /**
     * updates high score
     */
    void updateHighScore(int score) {
        TextView highScore = findViewById(R.id.highScore);
        highScore.setText("" + score);
        //UPDATE GLOBAL HIGH SCORE SO IT REMEMBERS NEXT TIME INCLUDE IN ONCREATE so it loads next time

        //Setting high score
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putInt("key", score);
        editor.commit();

    }



    /**
     * Starts the api call to the cat fact thing and updates text box
     */
    void startAPICall() {

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


        int catToAdd = Integer.parseInt(first.getText().toString()); //pass this into add
        int score = 3 * 2 * catToAdd;
        updateScore(score);

        if (type == 0) {
            //top/middle

            if (board[2][col] != null) {
                newSet.clear(board[2][col].getId(), ConstraintSet.TOP);
                newSet.connect(board[2][col].getId(), ConstraintSet.TOP,
                        R.id.guideline2, ConstraintSet.BOTTOM);
            }

            //remove tile at destination and then add new cat tile

            layout.removeView(first);
            layout.removeView(second);
            //second.setVisibility(View.INVISIBLE);
            addTile(catToAdd, 0, col);
            //update board state
            board[1][col] = board[2][col];
            board[2][col] = null;

        } else if (type == 1) {
            //top/null/bottom

            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, 0, col);
            //update board state
            board[2][col] = null;


        } else if (type == 2) {
            //null/middle/bottom
            //move tiles to destination tile
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, 0, col);
            //update board state
            board[1][col] = null;
            board[2][col] = null;

        } else if (type == 3) {
            //something/middle/bottom

            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, 1, col);
            //update board state
            board[2][col] = null;


        } else if (type == 4) {
            //bottom/middle
            if (board[0][col] != null) {
                newSet.clear(board[0][col].getId(), ConstraintSet.TOP);
                newSet.connect(board[0][col].getId(), ConstraintSet.TOP,
                        R.id.guideline2, ConstraintSet.BOTTOM);
            }

            //remove tile at destination and then add new cat tile
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, 2, col);
            //update board state
            board[1][col] = board[0][col];
            board[0][col] = null;

        } else if (type == 5) {
            //bottom/null/top
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, 2, col);

            //update board state
            board[0][col] = null;


        } else if (type == 6) {
            //null/middle/top
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, 2, col);

            //update board state
            board[0][col] = null;
            board[1][col] = null;


        } else {
            //something/middle/top
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, 1, col);

            //update board state
            board[0][col] = null;


        }
        //TransitionManager.beginDelayedTransition(layout);

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
        newSet.clone(layout);


        int catToAdd = Integer.parseInt(first.getText().toString());
        int score = 3 * 2 * catToAdd;
        updateScore(score);

        if (type == 0) {
            //right/middle
            if (board[row][2] != null) {
                newSet.clear(board[row][2].getId(), ConstraintSet.START);
                newSet.connect(board[row][2].getId(), ConstraintSet.START,
                        R.id.guideline6, ConstraintSet.END);
            }
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, row, 0);

            board[row][1] = board[row][2];
            board[row][2] = null;


        } else if (type == 1) {
            //right/null/left
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, row, 0);
            board[row][2] = null;

        } else if (type == 2) {
            //null/middle/left
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, row, 0);
            board[row][1] = null;
            board[row][2] = null;

        } else if (type == 3) {
            //something/middle/left
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, row, 1);
            board[row][2] = null;

        } else if (type == 4) {
            //middle/left combining left
            if (board[row][0] != null) {
                newSet.clear(board[row][0].getId(), ConstraintSet.START);
                newSet.connect(board[row][0].getId(), ConstraintSet.START,
                        R.id.guideline6, ConstraintSet.END);
            }
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, row, 2);

            board[row][1] = board[row][0];
            board[row][0] = null;


        } else if (type == 5) {
            //right/null/left combining left
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, row, 2);
            board[row][0] = null;

        } else if (type == 6) {
            //right/middle/null combining left
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, row, 2);
            board[row][0] = null;
            board[row][1] = null;


        } else {
            //right/middle/something combining left
            layout.removeView(first);
            layout.removeView(second);
            addTile(catToAdd, row, 1);
            board[row][0] = null;

        }
        //TransitionManager.beginDelayedTransition(layout);

        newSet.applyTo(layout);

    }

    /**
     * if columns has no equal tiles then just move
     *
     * @param tile1
     * @param tile2
     * @param type 0: single element moving up 1: single element moving down. 2: top/bottom 3: middle/bottom
     */
    void moveVertical(TextView tile1, TextView tile2, int type, int col) {
        //need to update BOARD[][]!!!!!

        final ConstraintLayout layout = findViewById(R.id.layout);
        ConstraintSet newSet = new ConstraintSet();
        newSet.clone(layout);

        if (type == 0) {
            //single tile going right
            newSet.clear(tile1.getId(), ConstraintSet.TOP);
            newSet.connect(tile1.getId(), ConstraintSet.TOP,
                    R.id.guideline, ConstraintSet.BOTTOM);
            board[0][col] = tile1;
            board[1][col] = null;
            board[2][col] = null;
        } else if (type == 1){
            //single tile going down
            newSet.clear(tile1.getId(), ConstraintSet.TOP);
            newSet.connect(tile1.getId(), ConstraintSet.TOP,
                    R.id.guideline3, ConstraintSet.BOTTOM);
            board[2][col] = tile1;
            board[1][col] = null;
            board[0][col] = null;
        } else if (type == 2) {
            //top/bottom going up
            newSet.clear(tile2.getId(), ConstraintSet.TOP);
            newSet.connect(tile2.getId(), ConstraintSet.TOP,
                    R.id.guideline2, ConstraintSet.BOTTOM);
            board[1][col] = tile2;
            board[2][col] = null;

        } else if (type == 3) {
            //null/middle/bottom going up
            newSet.clear(tile1.getId(), ConstraintSet.TOP);
            newSet.clear(tile2.getId(), ConstraintSet.TOP);
            newSet.connect(tile1.getId(), ConstraintSet.TOP,
                    R.id.guideline, ConstraintSet.BOTTOM);
            newSet.connect(tile2.getId(), ConstraintSet.TOP,
                    R.id.guideline2, ConstraintSet.BOTTOM);
            board[0][col] = tile1;
            board[1][col] = tile2;
            board[2][col] = null;


        } else if (type == 4) {
            //bottom/null/top going down
            newSet.clear(tile2.getId(), ConstraintSet.TOP);
            newSet.connect(tile2.getId(), ConstraintSet.TOP,
                    R.id.guideline2, ConstraintSet.BOTTOM);
            board[0][col] = null;
            board[1][col] = tile2;


        } else if (type == 5) {
            //null/middle/top going down
            newSet.clear(tile1.getId(), ConstraintSet.TOP);
            newSet.clear(tile2.getId(), ConstraintSet.TOP);
            newSet.connect(tile1.getId(), ConstraintSet.TOP,
                    R.id.guideline3, ConstraintSet.BOTTOM);
            newSet.connect(tile2.getId(), ConstraintSet.TOP,
                    R.id.guideline2, ConstraintSet.BOTTOM);
            board[2][col] = tile1;
            board[1][col] = tile2;
            board[0][col] = null;

        }
        //TransitionManager.beginDelayedTransition(layout);
        newSet.applyTo(layout);

        }


    /**
     * if rows has no equal tiles then just move
     * @param tile1
     * @param tile2
     * @param type
     */
    void moveHorizontal(TextView tile1, TextView tile2, int type, int row) {

        final ConstraintLayout layout = findViewById(R.id.layout);
        ConstraintSet newSet = new ConstraintSet();
        newSet.clone(layout);

        if (type == 0) {
            //single tile moving right
            newSet.clear(tile1.getId(), ConstraintSet.START);
            newSet.connect(tile1.getId(), ConstraintSet.START,
                    R.id.guideline5, ConstraintSet.END);
            board[row][0] = tile1;
            board[row][1] = null;
            board[row][2] = null;


        } else if (type == 1) {
            //single tile moving left
            newSet.clear(tile1.getId(), ConstraintSet.START);
            newSet.connect(tile1.getId(), ConstraintSet.START,
                    R.id.guideline7, ConstraintSet.END);
            board[row][2] = tile1;
            board[row][0] = null;
            board[row][1] = null;
        } else if (type == 2) {
            //right/null/left moving right
            newSet.clear(tile2.getId(), ConstraintSet.START);
            newSet.connect(tile2.getId(), ConstraintSet.START,
                    R.id.guideline6, ConstraintSet.END);
            board[row][1] = tile2;
            board[row][2] = null;


        } else if (type == 3) {
            //null/middle/left moving right
            newSet.clear(tile1.getId(), ConstraintSet.START);
            newSet.clear(tile2.getId(), ConstraintSet.START);
            newSet.connect(tile1.getId(), ConstraintSet.START,
                    R.id.guideline5, ConstraintSet.END);
            newSet.connect(tile2.getId(), ConstraintSet.START,
                    R.id.guideline6, ConstraintSet.END);
            board[row][0] = tile1;
            board[row][1] = tile2;
            board[row][2] = null;
        } else if (type == 4) {
            //right/null/left moving left
            newSet.clear(tile1.getId(), ConstraintSet.START);
            newSet.connect(tile1.getId(), ConstraintSet.START,
                    R.id.guideline6, ConstraintSet.END);
            board[row][1] = tile1;
            board[row][0] = null;



        } else if (type == 5) {
            //right/middle/null moving left
            newSet.clear(tile1.getId(), ConstraintSet.START);
            newSet.clear(tile2.getId(), ConstraintSet.START);
            newSet.connect(tile1.getId(), ConstraintSet.START,
                    R.id.guideline6, ConstraintSet.END);
            newSet.connect(tile2.getId(), ConstraintSet.START,
                    R.id.guideline7, ConstraintSet.END);
            board[row][1] = tile1;
            board[row][2] = tile2;
            board[row][0] = null;


        }

        //TransitionManager.beginDelayedTransition(layout);
        newSet.applyTo(layout);


    }






}
