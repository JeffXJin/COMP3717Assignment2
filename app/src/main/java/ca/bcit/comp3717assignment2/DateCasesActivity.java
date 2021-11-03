package ca.bcit.comp3717assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

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
        databasePersons.addValueEventListener(new ValueEventListener() {

            /**
             * Everytime there is a change in the list, the list will be uploaded
             * Method calls and clears list
             * @param dataSnapshot as an DataSnapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                personsList.clear();
                for (DataSnapshot personSnapshot : dataSnapshot.getChildren()) {
                    Person person = personSnapshot.getValue(Person.class);
                    personsList.add(person);
                }

                PersonListAdapter adapter = new PersonListAdapter(DateCasesActivity.this, personsList);
                lvPersons.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }
}