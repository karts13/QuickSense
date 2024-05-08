package com.code.quicksense;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start, stop, submit;
    LinearLayout linearLayout;
    TextView resultTime;
    EditText uname;
    View colorBlock;
    DatabaseConnection dbconnection;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            Random rand = new Random();
            int color = Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            colorBlock.setBackgroundColor(color);
            // get the system time in milli second when the block color is set
            final long time = System.currentTimeMillis();

            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get the system time in milli second when the stop button in clicked
                    long time1 = System.currentTimeMillis();

                    long reflexTime = time1 - time;
                    resultTime = findViewById(R.id.result);
                    resultTime.setText(String.valueOf(reflexTime));
                    colorBlock.setBackgroundResource(0);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.main);
        uname = findViewById(R.id.name);
        start = findViewById(R.id.btnStart);
        stop = findViewById(R.id.btnStop);
        submit = findViewById(R.id.btnSubmit);
        resultTime = findViewById(R.id.result);
        colorBlock = findViewById(R.id.colorBlock);
        dbconnection = new DatabaseConnection(this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int num = random.nextInt(10);

                // call the runnable function after a post dealy of num seconds
                Handler handler = new Handler();
                handler.postDelayed(runnable, num * 1000);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName =uname.getText().toString();
                String res = resultTime.getText().toString();

                boolean result = dbconnection.insertData(userName, res);
                if (result == true) {
                    uname.setText("");
                    resultTime.setText("");
                }
                else
                    Toast.makeText(getApplicationContext(),"Data Not Inserted", Toast.LENGTH_LONG).show();
            }
        });
    }
}