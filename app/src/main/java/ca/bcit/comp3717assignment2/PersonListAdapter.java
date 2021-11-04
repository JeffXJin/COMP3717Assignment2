package ca.bcit.comp3717assignment2;

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

        TextView tvPerson = listViewItem.findViewById(R.id.textViewPerson);

        Person person = personList.get(position);
        tvPerson.setText(person.getAge_Group()
                + " " + person.getClassification_Reported()
                + " " + person.getHA()
                + " " + person.getReported_Date()
                + " " + person.getSex());

        return listViewItem;
    }
}
