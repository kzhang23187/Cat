package com.example.cat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class GameOver extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_end_dialogue);
        Button ok =findViewById(R.id.dialog_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View args) {
                Intent intent=new Intent();
                setResult(1,intent);
                finish();

            }
        });
    }

}
