package ca.bcit.JIN_SADOWSKI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class PersonListAdapter extends ArrayAdapter<Person> {
    private Activity context;
    private List<Person> personList;

    public PersonListAdapter(Activity context, List<Person> personList) {
        super(context, R.layout.list_layout, personList);
        this.context = context;
        this.personList = personList;
    }

    public PersonListAdapter(Context context, int resource, List<Person> objects, Activity context1, List<Person> personList) {
        super(context, resource, objects);
        this.context = context1;
        this.personList = personList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView ageGroup = listViewItem.findViewById(R.id.age_group);
        TextView classification = listViewItem.findViewById(R.id.classification);
        TextView healthAuthority = listViewItem.findViewById(R.id.health_authority);
        TextView date = listViewItem.findViewById(R.id.reported_date);
        TextView sex = listViewItem.findViewById(R.id.sex);

        Person person = personList.get(position);
        ageGroup.setText("Age: " + person.getAge_Group());
        classification.setText(person.getClassification_Reported());
        healthAuthority.setText(person.getHA());
        date.setText(person.getReported_Date());
        sex.setText("Sex: " + person.getSex());

        return listViewItem;
    }
}
