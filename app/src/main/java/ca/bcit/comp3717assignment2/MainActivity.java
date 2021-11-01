package ca.bcit.comp3717assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAgeClick(View v) {
        Intent intent = new Intent(this, AgeCasesActivity.class);
        startActivity(intent);
    }

    public void onDateClick(View v) {
        Intent intent = new Intent(this, DateCasesActivity.class);
        startActivity(intent);
    }

    public void onHealthClick(View v) {
        Intent intent = new Intent(this, HealthCasesActivity.class);
        startActivity(intent);
    }

    public void onGenderClick(View v) {
        Intent intent = new Intent(this, GenderCasesActivity.class);
        startActivity(intent);
    }
}