package com.example.cat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class GameWon extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
        Button reset = findViewById(R.id.dialog_reset);
        Button keepPlaying = findViewById(R.id.dialog_keep_playing);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View args) {
                Intent intent = new Intent();
                setResult(2,intent);
                finish();
            }
        });
        keepPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View args) {
                finish();
            }
        });
    }

}
