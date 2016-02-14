package me.kglawrence.keri.ibrowse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        studentList.setOnItemClickListener(mMessageClickedHandler);

        Button addStudent = (Button)this.findViewById(R.id.add_student);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {

            Intent intent = new Intent(v.getContext(), EachStudentList.class);
            ///Need to change functionality to include multiple users with same name
            String selected = (String)parent.getItemAtPosition(position);
            String fn = "";
            int i = 0;
            while (selected.charAt(i)  != ' ') {
                fn += selected.charAt(i);
                i++;
            }
            intent.putExtra("fn", fn);
            startActivity(intent);

        }
    };

}
