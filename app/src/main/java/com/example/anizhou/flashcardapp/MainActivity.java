package com.example.anizhou.flashcardapp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        countDownTimer = new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.timer)).setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
            }
        };

        countDownTimer.start();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.answerChoice1)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.answerChoice2)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.answerChoice3)).setText(allFlashcards.get(0).getWrongAnswer2());
        }

        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final View questionSideView = findViewById(R.id.flashcard_question);
                final View answerSideView = findViewById(R.id.flashcard_answer);

                // get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;
                // get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);
                // create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

                // hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(800);
                anim.start();

                questionSideView.animate()
                        .rotationY(90)
                        .setDuration(200)
                        .withEndAction(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        questionSideView.setVisibility(View.INVISIBLE);
                                        findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
                                        // second quarter turn
                                        findViewById(R.id.flashcard_answer).setRotationY(-90);
                                        findViewById(R.id.flashcard_answer).animate()
                                                .rotationY(0)
                                                .setDuration(200)
                                                .start();
                                    }
                                }
                        ).start();

                findViewById(R.id.flashcard_question).setCameraDistance(25000);
                findViewById(R.id.flashcard_answer).setCameraDistance(25000);
            }
        });

        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final View questionSideView = findViewById(R.id.flashcard_question);
                final View answerSideView = findViewById(R.id.flashcard_answer);

                answerSideView.animate()
                        .rotationY(90)
                        .setDuration(200)
                        .withEndAction(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        answerSideView.setVisibility(View.INVISIBLE);
                                        findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                                        // second quarter turn
                                        findViewById(R.id.flashcard_question).setRotationY(-90);
                                        findViewById(R.id.flashcard_question).animate()
                                                .rotationY(0)
                                                .setDuration(200)
                                                .start();
                                    }
                                }
                        ).start();

                findViewById(R.id.flashcard_question).setCameraDistance(25000);
                findViewById(R.id.flashcard_answer).setCameraDistance(25000);
            }
        });

        findViewById(R.id.answerChoice1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((TextView) findViewById(R.id.answerChoice1)).getText().toString().equals(((TextView)(findViewById(R.id.flashcard_answer))).getText().toString())) {
                    (findViewById(R.id.answerChoice1)).setBackgroundColor(getResources().getColor(R.color.green));
                    new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                            .setSpeedRange(0.2f, 0.5f)
                            .oneShot(findViewById(R.id.answerChoice1), 100);
                }
                else {
                    (findViewById(R.id.answerChoice1)).setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        findViewById(R.id.answerChoice2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((TextView) findViewById(R.id.answerChoice2)).getText().toString().equals(((TextView)(findViewById(R.id.flashcard_answer))).getText().toString())) {
                    (findViewById(R.id.answerChoice2)).setBackgroundColor(getResources().getColor(R.color.green));
                    new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                            .setSpeedRange(0.2f, 0.5f)
                            .oneShot(findViewById(R.id.answerChoice2), 100);
                }
                else {
                    (findViewById(R.id.answerChoice2)).setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        findViewById(R.id.answerChoice3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((TextView) findViewById(R.id.answerChoice3)).getText().toString().equals(((TextView)(findViewById(R.id.flashcard_answer))).getText().toString())) {
                    (findViewById(R.id.answerChoice3)).setBackgroundColor(getResources().getColor(R.color.green));
                    new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                            .setSpeedRange(0.2f, 0.5f)
                            .oneShot(findViewById(R.id.answerChoice3), 100);
                }
                else {
                    (findViewById(R.id.answerChoice3)).setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });

        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (findViewById(R.id.answerChoice1)).setBackgroundColor(getResources().getColor(R.color.purple));
                (findViewById(R.id.answerChoice2)).setBackgroundColor(getResources().getColor(R.color.purple));
                (findViewById(R.id.answerChoice3)).setBackgroundColor(getResources().getColor(R.color.purple));
            }
        });

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.edit_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("question",((TextView)findViewById(R.id.flashcard_question)).getText().toString());
                intent.putExtra("answer",((TextView)findViewById(R.id.flashcard_answer)).getText().toString());
                intent.putExtra("wrongAnswer1",((TextView)findViewById(R.id.answerChoice2)).getText().toString());
                intent.putExtra("wrongAnswer2",((TextView)findViewById(R.id.answerChoice3)).getText().toString());
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.eye_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(((ImageView)findViewById(R.id.eye_button)).getDrawable().getConstantState().equals(getDrawable(R.drawable.eye_visible).getConstantState())) {
                    ((ImageView) findViewById(R.id.eye_button)).setImageResource(R.drawable.eye_invisible);
                    (findViewById(R.id.answerChoice1)).setVisibility(View.INVISIBLE);
                    (findViewById(R.id.answerChoice2)).setVisibility(View.INVISIBLE);
                    (findViewById(R.id.answerChoice3)).setVisibility(View.INVISIBLE);
                }
                else {
                    ((ImageView) findViewById(R.id.eye_button)).setImageResource(R.drawable.eye_visible);
                    (findViewById(R.id.answerChoice1)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.answerChoice2)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.answerChoice3)).setVisibility(View.VISIBLE);
                }
            }
        });


        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {

            public final int getRandomNumber(int minNumber, int maxNumber) {
                Random randomNum = new Random();
                return randomNum.nextInt((maxNumber - minNumber) + 1) + minNumber;
            }

            @Override
            public void onClick(View v) {

                (findViewById(R.id.answerChoice1)).setBackgroundColor(getResources().getColor(R.color.purple));
                (findViewById(R.id.answerChoice2)).setBackgroundColor(getResources().getColor(R.color.purple));
                (findViewById(R.id.answerChoice3)).setBackgroundColor(getResources().getColor(R.color.purple));

                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size());

                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                    countDownTimer.cancel();
                }

                if (allFlashcards.size() > 1) {
                    countDownTimer.cancel();
                    countDownTimer.start();

                    leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ArrayList<String> answersList = new ArrayList<String>();
                            answersList.add(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                            answersList.add(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                            answersList.add(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                            Collections.shuffle(answersList);

                            findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                            ((TextView) findViewById(R.id.answerChoice1)).setText(answersList.get(0));
                            ((TextView) findViewById(R.id.answerChoice2)).setText(answersList.get(1));
                            ((TextView) findViewById(R.id.answerChoice3)).setText(answersList.get(2));
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });

                    findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);
                    findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                    findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
                    }
            }
        });

        findViewById(R.id.trash_button).setOnClickListener(new View.OnClickListener() {
            public final int getRandomNumber(int minNumber, int maxNumber) {
                Random randomNum = new Random();
                return randomNum.nextInt((maxNumber - minNumber) + 1) + minNumber;
            }

            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                countDownTimer.start();

                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();

                currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size());

                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }

                if(allFlashcards.size() == 0) {
                    countDownTimer.cancel();

                    ((TextView) findViewById(R.id.flashcard_question)).setText("");
                    ((TextView) findViewById(R.id.flashcard_answer)).setText("");
                    ((TextView) findViewById(R.id.answerChoice1)).setText("");
                    ((TextView) findViewById(R.id.answerChoice2)).setText("");
                    ((TextView) findViewById(R.id.answerChoice3)).setText("");

                    ((TextView) findViewById(R.id.flashcard_question)).setHint("Add a card!");
                    ((TextView) findViewById(R.id.flashcard_answer)).setHint("No text yet.");
                    ((TextView) findViewById(R.id.answerChoice1)).setHint("Add an answer.");
                    ((TextView) findViewById(R.id.answerChoice2)).setHint("Add an answer.");
                    ((TextView) findViewById(R.id.answerChoice3)).setHint("Add an answer.");
                }

                if(allFlashcards.size() != 0) {
                    ArrayList<String> answersList = new ArrayList<String>();
                    answersList.add(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    answersList.add(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                    answersList.add(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                    Collections.shuffle(answersList);

                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    ((TextView) findViewById(R.id.answerChoice1)).setText(answersList.get(0));
                    ((TextView) findViewById(R.id.answerChoice2)).setText(answersList.get(1));
                    ((TextView) findViewById(R.id.answerChoice3)).setText(answersList.get(2));
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100 && resultCode == RESULT_OK) {
            countDownTimer.cancel();
            countDownTimer.start();

            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Card successfully created",
                    Snackbar.LENGTH_SHORT)
                    .show();

            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");
            String wrongAnswer1 = data.getExtras().getString("wrongAnswer1");
            String wrongAnswer2 = data.getExtras().getString("wrongAnswer2");

            ArrayList<String> answersList = new ArrayList<String>();
            answersList.add(answer);
            answersList.add(wrongAnswer1);
            answersList.add(wrongAnswer2);
            Collections.shuffle(answersList);

            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);
            ((TextView) findViewById(R.id.answerChoice1)).setText(answersList.get(0));
            ((TextView) findViewById(R.id.answerChoice2)).setText(answersList.get(1));
            ((TextView) findViewById(R.id.answerChoice3)).setText(answersList.get(2));

            //((TextView)findViewById(R.id.answerChoice1)).setText(answer);
            //((TextView)findViewById(R.id.answerChoice2)).setText(wrongAnswer1);
            //((TextView)findViewById(R.id.answerChoice3)).setText(wrongAnswer2);
            flashcardDatabase.insertCard(new Flashcard(question, answer, wrongAnswer1, wrongAnswer2));
            allFlashcards = flashcardDatabase.getAllCards();

        }
    }
}
