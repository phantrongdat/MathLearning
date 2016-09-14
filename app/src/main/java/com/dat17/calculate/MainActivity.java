package com.dat17.calculate;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5;
    //    private ImageView imgl1, imgl2, imgl3, imgl4, imgl5, imgr1, imgr2, imgr3, imgr4, imgr5, imgSmile;
    private TextView txtQuestion;
    private SQLiteHandler db;
    private ActionBar actionBar;
    private String question;
    private int result;
    private ImageView imageView, imgSmile;
    private int[] idIMGL;
    private int[] idIMGR;
    private int[] anims;
    private Animation animation;
    private int point=0;
    private int questNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        define();
        setup();
        loadQuestion();
    }

    private void setup() {
        actionBar = getSupportActionBar();
        actionBar.setTitle("POINT: "+point);
        actionBar.setSubtitle("Question: "+questNumber);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result==1){
                    point+=10;
                    actionBar.setTitle("POINT: "+point);
                    imgSmile.setImageResource(R.drawable.correct);
                }
                else
                {
                    imgSmile.setImageResource(R.drawable.incorrect);
                }
                Animation animation1=AnimationUtils.loadAnimation(MainActivity.this,R.anim.smile_correct);
                imgSmile.startAnimation(animation1);
                loadQuestion();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result==2){
                    point+=10;
                    actionBar.setTitle("POINT: "+point);
                    imgSmile.setImageResource(R.drawable.correct);
                }
                else
                {
                    imgSmile.setImageResource(R.drawable.incorrect);
                }
                Animation animation1=AnimationUtils.loadAnimation(MainActivity.this,R.anim.smile_correct);
                imgSmile.startAnimation(animation1);
                loadQuestion();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result==3){
                    point+=10;
                    actionBar.setTitle("POINT: "+point);
                    imgSmile.setImageResource(R.drawable.correct);
                }
                else
                {
                    imgSmile.setImageResource(R.drawable.incorrect);
                }
                Animation animation1=AnimationUtils.loadAnimation(MainActivity.this,R.anim.smile_correct);
                imgSmile.startAnimation(animation1);
                loadQuestion();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result==4){
                    point+=10;
                    actionBar.setTitle("POINT: "+point);
                    imgSmile.setImageResource(R.drawable.correct);
                }
                else
                {
                    imgSmile.setImageResource(R.drawable.incorrect);
                }
                Animation animation1=AnimationUtils.loadAnimation(MainActivity.this,R.anim.smile_correct);
                imgSmile.startAnimation(animation1);
                loadQuestion();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result==5){
                    point+=10;
                    actionBar.setTitle("POINT: "+point);
                    imgSmile.setImageResource(R.drawable.correct);
                }
                else
                {
                    imgSmile.setImageResource(R.drawable.incorrect);
                }
                Animation animation1=AnimationUtils.loadAnimation(MainActivity.this,R.anim.smile_correct);
                imgSmile.startAnimation(animation1);
                loadQuestion();
            }
        });

    }

    public void define() {
        db = new SQLiteHandler(this);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        imgSmile = (ImageView) findViewById(R.id.imgSmile);
        idIMGL = new int[]{R.id.imgLeft1, R.id.imgLeft2, R.id.imgLeft3, R.id.imgLeft4, R.id.imgLeft5};
        idIMGR = new int[]{R.id.imgRight1, R.id.imgRight2, R.id.imgRight3, R.id.imgRight4, R.id.imgRight5};
        anims=new int[]{R.anim.blink, R.anim.fade_in,R.anim.rotate,R.anim.slide_down,R.anim.zoom_in};
    }

    public void loadQuestion() {

        Random r = new Random();
        Cursor c = db.rawQuery("SELECT * FROM tbl_additions");
        questNumber++;
        actionBar.setSubtitle("Question: " + questNumber);

        for (int id : idIMGL) {
            imageView = (ImageView) findViewById(id);
            imageView.setImageResource(android.R.drawable.screen_background_dark_transparent);
            int anim1=-1;
            while (anim1<0)anim1=r.nextInt(anims.length);
            animation= AnimationUtils.loadAnimation(MainActivity.this, anims[anim1]);
            imageView.startAnimation(animation);
        }
        for (int id : idIMGR) {
            imageView = (ImageView) findViewById(id);
            imageView.setImageResource(android.R.drawable.screen_background_dark_transparent);
            int anim1=-1;
            while (anim1<0)anim1=r.nextInt(anims.length);
            animation= AnimationUtils.loadAnimation(MainActivity.this, anims[anim1]);
            imageView.startAnimation(animation);
        }

        int questionIndex = 0;
        while (questionIndex < 1)
            questionIndex = r.nextInt(c.getCount());

        Cursor cursor = db.rawQuery("SELECT * FROM tbl_additions WHERE id=" + questionIndex);

        cursor.moveToFirst();

//        Toast.makeText(MainActivity.this, cursor.getInt(0) + "|" + cursor.getString(1) + "|"+ cursor.getInt(2), Toast.LENGTH_LONG).show();
        question = cursor.getString(1);
        int num1 = Integer.parseInt(question.substring(0, 1));
        int num2 = Integer.parseInt(question.substring(2, 3));
        result = cursor.getInt(2);

        txtQuestion.setText(cursor.getString(1) + " = ?");

        for (int i = 0; i < num1; i++) {
            imageView = (ImageView) findViewById(idIMGL[i]);
            imageView.setImageResource(R.drawable.mickey);
        }
        for (int i = 0; i < num2; i++) {
            imageView = (ImageView) findViewById(idIMGR[i]);
            imageView.setImageResource(R.drawable.mickey);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addition, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.help){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle(question+"="+result);
            builder.setMessage("Press ok and answer!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }
        return super.onOptionsItemSelected(item);
    }
}
