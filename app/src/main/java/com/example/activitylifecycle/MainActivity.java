package com.example.activitylifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "----------------";

    Button          mAlertDialog, mShare, mSecondActivity;
    private int     mRequestCode = 100;

    TextView        mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAlertDialog = findViewById(R.id.buttonAlertDialog);
        mShare = findViewById(R.id.buttonShareDialog);
        mSecondActivity = findViewById(R.id.buttonSecondActivity);
        mResponse = findViewById(R.id.tvResponse);

        Toast.makeText(MainActivity.this,"1. onCreate",Toast.LENGTH_SHORT).show();
        Log.i(TAG, "1. onCreate.");
        mAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareDialog(); // other app activity
            }
        });

        mSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
            }
        });
    }

    public void alertDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Good morning! Say yes or no?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                            }
                        });

                        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
    }

    public void shareDialog(){
        Intent intent=new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "extra text that you want to put");
        startActivity(Intent.createChooser(intent,"Share via"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if(resultCode == RESULT_OK) {

                String myResponse=data.getStringExtra("response");
                mResponse.setText(myResponse);
            }
        }
    }

    public void openSecondActivity(){
        String s = "Welcome second screen";
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("welcome",s);
        startActivityForResult(intent, mRequestCode);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this,"2. onStart",Toast.LENGTH_SHORT).show();
        Log.i(TAG, "2. onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this,"3. onResume",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "3. onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(MainActivity.this,"4. onPause",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "4. onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this,"5. onStop",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "5. onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this,"6. onRestart",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "6. onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(MainActivity.this,"7. onDestroy",Toast.LENGTH_SHORT).show();
        Log.e(TAG, "7. onDestroy");
    }
}