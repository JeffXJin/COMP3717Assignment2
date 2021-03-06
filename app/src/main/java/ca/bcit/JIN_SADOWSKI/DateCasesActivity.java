package ca.bcit.JIN_SADOWSKI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
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

    Spinner monthSpinner;
    Spinner yearSpinner;
    String monthString;
    String yearString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_cases);

        databasePersons = FirebaseDatabase.getInstance().getReference();
        lvPersons = findViewById(R.id.lvPersons);
        personsList = new ArrayList<Person>();

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
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

        monthSpinner = findViewById(R.id.date_month);
        yearSpinner = findViewById(R.id.date_year);

        int month = monthSpinner.getSelectedItemPosition() + 1;
        if (month + 1 < 9) {
            monthString = "0" + new String(Integer.toString(month + 1));
        } else {
            monthString = new String(Integer.toString(month + 1));
        }
        yearString = yearSpinner.getSelectedItem().toString();
        databasePersons.addValueEventListener(new ValueEventListener() {

            /**
             * Everytime there is a change in the list, the list will be uploaded
             * Method calls and clears list
             * @param dataSnapshot as an DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                personsList.clear();
                progressBar.setVisibility(View.VISIBLE);
                for (DataSnapshot personSnapshot : dataSnapshot.getChildren()) {
                    Person person = personSnapshot.getValue(Person.class);

                    assert person != null;
                    String dateYear = person.Reported_Date.substring(0,4);
                    String dateMonth = person.Reported_Date.substring(5,7);
                    if (dateMonth.equals(monthString) && dateYear.equals(yearString)) {
                        personsList.add(person);
                    }
                }
                progressBar.setVisibility(View.GONE);

                PersonListAdapter adapter = new PersonListAdapter(DateCasesActivity.this, personsList);
                lvPersons.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}