package me.kglawrence.keri.ibrowse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class StudentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        ListView studentList = (ListView) findViewById(R.id.student_list);

        List<Users> students = Users.listAll(Users.class);
        String[] studentArr = new String[students.size()];
        int index = 0;
        for (Users i : students) {
            String val = "";
            val += i.firstName + " " + i.lastName;
            studentArr[index] = val;
            index++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, studentArr);

        studentList.setAdapter(adapter);


    }
}
