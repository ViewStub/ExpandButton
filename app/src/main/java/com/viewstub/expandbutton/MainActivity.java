package com.viewstub.expandbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ExpandButtonLayout target;
    private Button testButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        target = (ExpandButtonLayout) findViewById(R.id.expandedButton);
        testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        target.toggle();
    }
}
