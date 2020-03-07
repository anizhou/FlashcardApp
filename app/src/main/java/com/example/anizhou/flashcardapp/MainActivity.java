package com.example.anizhou.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.answer_choice_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((TextView)findViewById(R.id.answer_choice_1)).getText().toString().equals(((TextView)findViewById(R.id.flashcard_answer)).getText().toString())) {
                    (findViewById(R.id.answer_choice_1)).setBackgroundColor(getResources().getColor(R.color.green));
                }
                else {
                    (findViewById(R.id.answer_choice_1)).setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        findViewById(R.id.answer_choice_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((TextView)findViewById(R.id.answer_choice_2)).getText().toString().equals(((TextView)findViewById(R.id.flashcard_answer)).getText().toString())) {
                    (findViewById(R.id.answer_choice_2)).setBackgroundColor(getResources().getColor(R.color.green));
                }
                else {
                    (findViewById(R.id.answer_choice_2)).setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        findViewById(R.id.answer_choice_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((TextView)findViewById(R.id.answer_choice_3)).getText().toString().equals(((TextView)findViewById(R.id.flashcard_answer)).getText().toString())) {
                    (findViewById(R.id.answer_choice_3)).setBackgroundColor(getResources().getColor(R.color.green));
                }
                else {
                    (findViewById(R.id.answer_choice_3)).setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (findViewById(R.id.answer_choice_1)).setBackgroundColor(getResources().getColor(R.color.purple));
                (findViewById(R.id.answer_choice_2)).setBackgroundColor(getResources().getColor(R.color.purple));
                (findViewById(R.id.answer_choice_3)).setBackgroundColor(getResources().getColor(R.color.purple));
            }
        });


        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.edit_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("question",((TextView)findViewById(R.id.flashcard_question)).getText().toString());
                intent.putExtra("answer",((TextView)findViewById(R.id.flashcard_answer)).getText().toString());
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            ((TextView)findViewById(R.id.flashcard_question)).setText(question);
            ((TextView)findViewById(R.id.flashcard_answer)).setText(answer);
        }
    }

}
