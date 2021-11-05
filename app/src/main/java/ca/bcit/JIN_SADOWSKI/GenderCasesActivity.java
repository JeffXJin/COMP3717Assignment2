package ca.bcit.JIN_SADOWSKI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class GenderCasesActivity extends AppCompatActivity {
    TextView genderCasesTv;

    private final String GENDER_MALE = "M";
    private final String GENDER_FEMALE = "F";
    private final String GENDER_UNKNOWN = "Unknown";

    int genderFemaleCount = 0;
    int genderMaleCount = 0;
    int genderUnknownCount = 0;

    DatabaseReference databasePersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender_cases);

        databasePersons = FirebaseDatabase.getInstance().getReference();

        genderCasesTv = findViewById(R.id.gender_cases_info);
    }

    @Override
    protected void onStart() {
        super.onStart();

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        databasePersons.addValueEventListener(new ValueEventListener() {

            /**
             * Everytime there is a change in the list, the list will be uploaded
             * Method calls and clears list
             * @param dataSnapshot as an DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.VISIBLE);
                for (DataSnapshot personSnapshot : dataSnapshot.getChildren()) {
                    Person person = personSnapshot.getValue(Person.class);

                    switch (Objects.requireNonNull(person).getSex()) {
                        case GENDER_MALE:
                            genderMaleCount++;
                            break;
                        case GENDER_FEMALE:
                            genderFemaleCount++;
                            break;
                        default:
                            genderUnknownCount++;
                    }
                }
                String genderCaseOut = "Male: " + genderMaleCount + "\n" +
                        "Female: " + genderFemaleCount + "\n" +
                        GENDER_UNKNOWN + ": " + genderUnknownCount;
                progressBar.setVisibility(View.INVISIBLE);
                genderCasesTv.setText(genderCaseOut);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}