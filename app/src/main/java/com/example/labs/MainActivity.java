package com.example.labs;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    //Variables
    float[] hsv = new float[3];
    private static int SPLASH_DURATION = 5000;//splash screen time
    Animation animation;
    ImageView logo;
    TextView name;
    ConstraintLayout layout;
    Activity activity = (this);

    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hideSystemUI();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hsv[0] = 0.0f; // Hue
        hsv[1] = 0.0f; // Saturation
        hsv[2] = 1.0f; // Value

        //Animations
        animation = AnimationUtils.loadAnimation(this, R.anim.animation);

        //Hooks
        logo = findViewById(R.id.imageView);
        name = findViewById(R.id.textView);

        logo.setAnimation(animation);
        name.setAnimation(animation);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.second);
                layout = findViewById(R.id.layout_second);
                Button btn1 = findViewById(R.id.button_1);
                btn1.setOnClickListener(MainActivity.this);
                btn1.setOnTouchListener(MainActivity.this);
                Button btn2 = findViewById(R.id.button_2);
                btn2.setOnClickListener(MainActivity.this);
                Button btn3 = findViewById(R.id.button_3);
                btn3.setOnClickListener(MainActivity.this);
                Button btn4 = findViewById(R.id.button_4);
                btn4.setOnClickListener(MainActivity.this);
                Button btnexit = findViewById(R.id.button_exit);
                btnexit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Exit");
                        builder.setMessage("Do you want to exit ??");
                        builder.setPositiveButton("Yes. Exit now!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                System.exit(0);
                            }

                            ;

                        });
                        builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                                hideSystemUI();
                            }
                        });


                        AlertDialog dialog = builder.create();
                        dialog.show();

                    }
                });
                //showSystemUI();
            }
        }, SPLASH_DURATION);   //5 seconds


    }

    public void changeBackgroundColour(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        float height = layout.getHeight(); // make sure the ref is declared and initialised (this is a reference to your root layout)
        float width = layout.getWidth();
        hsv[0] = eventY / height * 360; // (0 to 360)
        hsv[1] = eventX / width + 0.1f; // (0.1 to 1)
        layout.setBackgroundColor(Color.HSVToColor(hsv));
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the navigation bar
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                // View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        //| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Button btn = findViewById(R.id.button_1);
        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                btn.setText("DOWN");
                return true;
            case (MotionEvent.ACTION_UP):
                btn.setText("UP");
                return true;
            case (MotionEvent.ACTION_MOVE):
                btn.setText("MOVE");
                changeBackgroundColour(event);//
                return true;
            default:
                return super.onTouchEvent(event); // always do this!
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                Toast.makeText(getApplicationContext(), "Button 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_2:
                Toast.makeText(getApplicationContext(), "Button 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_3:
                Toast.makeText(getApplicationContext(), "Button 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_4:
                Toast.makeText(getApplicationContext(), "Button 4", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Button btn = findViewById(R.id.button_1);
        if (v.getId() == R.id.button_1) {
            switch (event.getAction()) {
                case (MotionEvent.ACTION_DOWN):
                    btn.setBackgroundColor(Color.RED);
                    btn.setText("DOWN");
                    return true;
                case (MotionEvent.ACTION_UP):
                    btn.setBackgroundColor(Color.GREEN);
                    btn.setText("UP");
                    return true;
                case (MotionEvent.ACTION_MOVE):
                    btn.setBackgroundColor(Color.YELLOW);
                    btn.setText("MOVE");
                    return true;
                default:
                    return super.onTouchEvent(event); // always do this!
            }
        } else {
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(hsv[2]<1){
                    hsv[2] = hsv[2] + 0.1f;
                }
                layout.setBackgroundColor(Color.HSVToColor(hsv));
                return false;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(hsv[2]>0){
                    hsv[2] = hsv[2] - 0.1f;
                }
                layout.setBackgroundColor(Color.HSVToColor(hsv));
                return false;
            default:
                return false; // don’t block default behaviour by default!
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.second);
        } else {
            setContentView(R.layout.second);
        }
        layout = findViewById(R.id.layout_second);
        layout.setBackgroundColor(Color.HSVToColor(hsv));
        Button btn1 = findViewById(R.id.button_1);
        btn1.setOnClickListener(MainActivity.this);
        btn1.setOnTouchListener(MainActivity.this);
        Button btn2 = findViewById(R.id.button_2);
        btn2.setOnClickListener(MainActivity.this);
        Button btn3 = findViewById(R.id.button_3);
        btn3.setOnClickListener(MainActivity.this);
        Button btn4 = findViewById(R.id.button_4);
        btn4.setOnClickListener(MainActivity.this);
        Button btnexit = findViewById(R.id.button_exit);
        btnexit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Do you want to exit ??");
                builder.setPositiveButton("Yes. Exit now!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        System.exit(0);
                    }

                    ;

                });
                builder.setNegativeButton("Not now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                        hideSystemUI();
                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putFloatArray("hsv",hsv);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        hsv = savedInstanceState.getFloatArray("hsv");

    }
}

