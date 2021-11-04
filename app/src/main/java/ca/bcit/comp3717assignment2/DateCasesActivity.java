package ca.bcit.comp3717assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class DateCasesActivity extends AppCompatActivity {
    DatabaseReference databasePersons;
    List<Person> personsList;
    ListView lvPersons;

    EditText monthEditText;
    EditText yearEditText;
    String monthString;
    String yearString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_cases);

        databasePersons = FirebaseDatabase.getInstance().getReference();
        lvPersons = findViewById(R.id.lvPersons);
        personsList = new ArrayList<Person>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void onFilterDate(View view) {
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        final TextView dateInfoTv = findViewById(R.id.date_info_text);
        dateInfoTv.setVisibility(View.INVISIBLE);

        monthEditText = findViewById(R.id.date_month);
        yearEditText = findViewById(R.id.date_year);

        monthString = monthEditText.getText().toString();
        yearString = yearEditText.getText().toString();

        databasePersons.addValueEventListener(new ValueEventListener() {

            /**
             * Everytime there is a change in the list, the list will be uploaded
             * Method calls and clears list
             * @param dataSnapshot as an DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                personsList.clear();
                int count = 0;
                for (DataSnapshot personSnapshot : dataSnapshot.getChildren()) {
                    Person person = personSnapshot.getValue(Person.class);

                    assert person != null;
                    String dateYear = person.Reported_Date.substring(0,4);
                    String dateMonth = person.Reported_Date.substring(5,7);
                    if (dateMonth.equals(monthString) && dateYear.equals(yearString)) {
                        personsList.add(person);
                    }
                    count++;
                    if (count >= dataSnapshot.getChildrenCount()) {
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }

                PersonListAdapter adapter = new PersonListAdapter(DateCasesActivity.this, personsList);
                lvPersons.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}