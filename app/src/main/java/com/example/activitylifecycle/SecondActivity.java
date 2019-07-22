package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView mWelcome;
    Button mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Bundle bundle = getIntent().getExtras();

        String str = bundle.getString("welcome");
        mWelcome = findViewById(R.id.tvWelcome);
        mReply = findViewById(R.id.buttonReply);
        mWelcome.setText(str);

        mReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userResponse();
            }
        });
    }

    public void userResponse(){
        String response = "Hi Ramesh!";
        Intent intent = new Intent();
        intent.putExtra("response",response);
        setResult(RESULT_OK, intent);
        finish();
    }
}
