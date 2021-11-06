package ca.bcit.JIN_SADOWSKI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    int imgHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img = findViewById(R.id.covid_img);
        imgHeight = img.getDrawable().getIntrinsicHeight();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
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

    public void onLogoutClick(MenuItem menu) {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        fAuth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        TextView stu1 = findViewById(R.id.student1);
        TextView stu2 = findViewById(R.id.student2);
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            img.getLayoutParams().height = 0;
            stu1.setVisibility(View.INVISIBLE);
            stu2.setVisibility(View.INVISIBLE);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            img.getLayoutParams().height = imgHeight;
            stu1.setVisibility(View.VISIBLE);
            stu2.setVisibility(View.VISIBLE);
        }
    }
}