package com.example.anizhou.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");
        ((EditText) findViewById(R.id.newQuestion)).setText(question);
        ((EditText) findViewById(R.id.newAnswer)).setText(answer);

        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("question", ((EditText) findViewById(R.id.newQuestion)).getText().toString());
                data.putExtra("answer", ((EditText) findViewById(R.id.newAnswer)).getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
