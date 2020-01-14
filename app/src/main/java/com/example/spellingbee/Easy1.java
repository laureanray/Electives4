package com.example.spellingbee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Easy1 extends AppCompatActivity {
    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private String rightAnswer;
    private int rightAnswerCount =0;
    private int quizCount =1;
    static final private int QUIZ_COUNT=5;

    ArrayList<ArrayList<String>>quizArray = new ArrayList<>();

    String quizData[][]={
            //words, right answer, wrong answer
            {"acommodate" , "incorrect" , "correct", "Accommodate"},
            {"onomatopeia" , "Correct" , "Incorrect", "Onomatopoeia"},
            {"nauseous" , "correct" , "incorrect" , "Nauseous"},
            {"paraphernalia" , "correct" , "incorrect", "Paraphernalia"},
            {"entreprenure" , "incorrect" , "correct", "Entrepreneur"},
            {"apostrophe" , "correct" ,"incorrect", "Apostrophe"},
            {"bankrupcy" , "incorrect", "correct", "Bankruptcy"},
            {"bureaucratic" , "correct" , "incorrect", "Bureaucratic"},
            {"caumoflage" , "incorrect" , "correct", "Camouflage"},
            {"champagne" , "correct" , "incorrect", "Champagne"},
            {"holocaust" , "correct" , "incorrect", "Holocaust"},
            {"manuever" , "incorrect" , "correct", "Maneuver"},
            {"mistletoe" , "correct" , "incorrect", "Mistletoe"},
            {"neumonia" , "incorrect" , "correct", "Pneumonia"},
            {"rendezvous" , "correct" , "incorrect", "Rendezvous"},
            {"reminicent" , "incorrect" , "correct", "Reminiscent"},
            {"silhoette" , "incorrect" , "correct", "Silhouette"},
            {"syringe" , "correct" , "incorrect", "Syringe"},
            {"souveneir" , "incorrect" , "correct", "Souvenir"},
            {"argument" , "correct" , "incorrect", "Argument"},
            {"receive" , "correct" , "incorrect", "Receive"},
            {"judgemental" , "incorrect" , "correct", "Judgmental"},
            {"judgment" , "correct" , "incorrect", "Judgmental"},
            {"perceive" , "correct" , "incorrect", "Perceive"},
            {"proffessor" , "incorrect" , "correct", "Professor"},
            {"suprise" , "incorrect" , "correct", "Surprise"},
            {"upholstery" , "correct" , "incorrect", "Upholstery"},
            {"tyranny" , "correct" , "incorrect", "Tyranny"},
            {"minuscule" , "correct" , "incorrect", "Minuscule"},
            {"liaison" , "correct" , "incorrect", "Liaison"},
            {"jewelery" , "incorrect" , "correct", "Jewelry"},
            {"height" , "correct" , "incorrect", "Height"},
            {"dissappoint" , "incorrect" , "correct", "Disappoint"},
            {"conceed" , "incorrect" , "correct", "Concede"},
            {"collegue" , "incorrect" , "correct", "Colleague"},
            {"Caribbean" , "correct" , "incorrect", "Caribbean"},
            {"agressive" , "incorrect" , "correct", "Aggressive"},
            {"adress" , "incorrect" , "correct", "Address"},
            {"imitate" , "correct" , "incorrect", "Imitate"},
            {"mispell" , "incorrect" , "correct", "Misspell"},
            {"privillege" , "incorrect" , "correct", "Privilege"},
            {"goverment" , "incorrect" , "correct", "Government"},
            {"wednesday" , "correct" , "incorrect", "Wednesday"},
            {"absence" , "correct" , "incorrect", "Absence"},
            {"bureau" , "correct" , "incorrect", "Bureau"},
            {"cematery" , "incorrect" , "correct", "Cemetery"},
            {"oddysey" , "incorrect" , "correct", "Odyssey"},
            {"excercise" , "incorrect" , "correct", "Exercise"},
            {"truly" , "correct" , "incorrect" , "Truly"},
            {"enormouse" , "incorrect" , "correct", "Enormous"},
            {"omission" , "correct" , "incorrect", "Omission"}

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy1);
        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);

        //create quiz array from quiz data
        for(int i =0; i < quizData.length; i++){

            //prepare array
            ArrayList<String>tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //words
            tmpArray.add(quizData[i][1]); //right answer
            tmpArray.add(quizData[i][2]); //wrong answer


            //add tmpArray to quizArray
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz(){

        //update quizCountLabel
        countLabel.setText("Question " + quizCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //pick a quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        //set question and right answer
        questionLabel.setText(quiz.get(0));
        rightAnswer=quiz.get(1);
        quiz.remove(0);
        Collections.shuffle(quiz);

        //set choices
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));

        //remove from quiz array
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view){

        //Get pushed button
        Button answerBtn = (Button)findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if(btnText.equals(rightAnswer)){
            //correct
            alertTitle="Correct!";
            rightAnswerCount++;
        }

        else{
            //Wrong
            alertTitle="Incorrect!";
        }

        //create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(quizCount == QUIZ_COUNT){
                    //Show resullt
                    Intent intent =new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                }
                else{
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed(); // this can go before or after your stuff below
        // do your stuff when the back button is pressed
        Intent intent = new Intent(getApplicationContext(), Levels.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        // super.onBackPressed(); calls finish(); for you

        // clear your SharedPreferences
        getSharedPreferences("preferenceName",0).edit().clear().commit();
    }
}
