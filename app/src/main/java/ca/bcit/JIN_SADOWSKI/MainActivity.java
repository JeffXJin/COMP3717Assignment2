package ca.bcit.JIN_SADOWSKI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

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

    public void onLogoutClick(View v) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}