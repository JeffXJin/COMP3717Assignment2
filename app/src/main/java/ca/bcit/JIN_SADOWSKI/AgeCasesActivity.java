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

public class AgeCasesActivity extends AppCompatActivity {

    DatabaseReference databasePersons;

    TextView ageCasesTv;

    private final String AGE_CASE_10 = "<10";
    private final String AGE_CASE_10_19 = "10-19";
    private final String AGE_CASE_20_29 = "20-29";
    private final String AGE_CASE_30_39 = "30-39";
    private final String AGE_CASE_40_49 = "40-49";
    private final String AGE_CASE_50_59 = "50-59";
    private final String AGE_CASE_60_69 = "60-69";
    private final String AGE_CASE_70_79 = "70-79";
    private final String AGE_CASE_80_89 = "80-89";
    private final String AGE_CASE_90 = "90+";

    private final int CASES = 10;
    int[] ages = new int[CASES];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_cases);

        databasePersons = FirebaseDatabase.getInstance().getReference();

        ageCasesTv = findViewById(R.id.age_cases_info);

        // instantiate ages
        for (int i = 0; i < CASES; i++) {
            ages[i] = 0;
        }
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
                int count = 0;

                for (DataSnapshot personSnapshot : dataSnapshot.getChildren()) {
                    Person person = personSnapshot.getValue(Person.class);
                    count++;
                    if (count >= dataSnapshot.getChildrenCount()) {
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    switch (Objects.requireNonNull(person).getAge_Group()) {
                        case AGE_CASE_10:
                            ages[0]++;
                            break;
                        case AGE_CASE_10_19:
                            ages[1]++;
                            break;
                        case AGE_CASE_20_29:
                            ages[2]++;
                            break;
                        case AGE_CASE_30_39:
                            ages[3]++;
                            break;
                        case AGE_CASE_40_49:
                            ages[4]++;
                            break;
                        case AGE_CASE_50_59:
                            ages[5]++;
                            break;
                        case AGE_CASE_60_69:
                            ages[6]++;
                            break;
                        case AGE_CASE_70_79:
                            ages[7]++;
                            break;
                        case AGE_CASE_80_89:
                            ages[8]++;
                            break;
                        default:
                            ages[9]++;
                    }
                }
                String ageCaseOut = AGE_CASE_10 +": " + ages[0] + "\n" +
                        AGE_CASE_10_19 + ": " + ages[1] + "\n" +
                        AGE_CASE_20_29 + ": " + ages[2] + "\n" +
                        AGE_CASE_30_39 + ": " + ages[3] + "\n" +
                        AGE_CASE_40_49 + ": " + ages[4] + "\n" +
                        AGE_CASE_50_59 + ": " + ages[5] + "\n" +
                        AGE_CASE_60_69 + ": " + ages[6] + "\n" +
                        AGE_CASE_70_79 + ": " + ages[7] + "\n" +
                        AGE_CASE_80_89 + ": " + ages[8] + "\n" +
                        AGE_CASE_90 + ": " + ages[9] + "\n"
                        ;

                ageCasesTv.setText(ageCaseOut);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}