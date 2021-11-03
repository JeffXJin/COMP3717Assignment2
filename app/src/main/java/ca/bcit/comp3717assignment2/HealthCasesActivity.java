package ca.bcit.comp3717assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.LayoutInflater;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class HealthCasesActivity extends AppCompatActivity {
    DatabaseReference databasePersons;
    List<Person> personsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_cases);

        databasePersons = FirebaseDatabase.getInstance().getReference("comp3717assignment2-8265b-default-rtdb");
    }
}