package ca.bcit.comp3717assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HealthCasesActivity extends AppCompatActivity {
    TextView healthCaseTv;

    private final String HA_CASE_FRASER = "Fraser";
    private final String HA_CASE_INTERIOR = "Interior";
    private final String HA_CASE_NORTHERN = "Northern";
    private final String HA_CASE_VAN_COAST = "Vancouver Coastal";
    private final String HA_CASE_VAN_ISLAND = "Vancouver Island";
    private final String HA_CASE_OUT_CANADA = "Out of Canada";

    int fraserCount = 0;
    int interiorCount = 0;
    int northernCount = 0;
    int vanCoastCount = 0;
    int vanIslandCount = 0;
    int outOfAreaCount = 0;

    DatabaseReference databasePersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_cases);

        databasePersons = FirebaseDatabase.getInstance().getReference();

        healthCaseTv = findViewById(R.id.health_cases_info);
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

                    switch (Objects.requireNonNull(person).getHA()) {
                        case HA_CASE_FRASER:
                            fraserCount++;
                            break;
                        case HA_CASE_INTERIOR:
                            interiorCount++;
                            break;
                        case HA_CASE_NORTHERN:
                            northernCount++;
                            break;
                        case HA_CASE_VAN_COAST:
                            vanCoastCount++;
                            break;
                        case HA_CASE_VAN_ISLAND:
                            vanIslandCount++;
                            break;
                        default:
                            outOfAreaCount++;
                    }
                }
                String healthCaseOut = HA_CASE_FRASER + ": " + fraserCount + "\n" +
                        HA_CASE_INTERIOR + ": " + interiorCount + "\n" +
                        HA_CASE_NORTHERN + ": " + northernCount + "\n" +
                        HA_CASE_VAN_COAST + ": " + vanCoastCount + "\n" +
                        HA_CASE_VAN_ISLAND + ": " + vanIslandCount + "\n" +
                        HA_CASE_OUT_CANADA + ": " + outOfAreaCount;
                healthCaseTv.setText(healthCaseOut);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}