package com.example.anizhou.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");
        String wrongAnswer1 = getIntent().getStringExtra("wrongAnswer1");
        String wrongAnswer2 = getIntent().getStringExtra("wrongAnswer2");
        ((EditText) findViewById(R.id.newQuestion)).setText(question);
        ((EditText) findViewById(R.id.newAnswer)).setText(answer);
        ((EditText) findViewById(R.id.newWrongAnswer1)).setText(wrongAnswer1);
        ((EditText) findViewById(R.id.newWrongAnswer2)).setText(wrongAnswer2);

        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Must enter both Question and Answer", Toast.LENGTH_SHORT);

                if(((EditText) findViewById(R.id.newQuestion)).getText().toString().isEmpty() || ((EditText) findViewById(R.id.newAnswer)).getText().toString().isEmpty()) {
                    toast.show();
                }
                else {
                    Intent data = new Intent();
                    data.putExtra("question", ((EditText) findViewById(R.id.newQuestion)).getText().toString());
                    data.putExtra("answer", ((EditText) findViewById(R.id.newAnswer)).getText().toString());
                    data.putExtra("wrongAnswer1", ((EditText) findViewById(R.id.newWrongAnswer1)).getText().toString());
                    data.putExtra("wrongAnswer2", ((EditText) findViewById(R.id.newWrongAnswer2)).getText().toString());
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}
