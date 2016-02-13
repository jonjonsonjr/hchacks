package me.kglawrence.keri.ibrowse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void addStudent(View view) {
    EditText studentId= (EditText) findViewById(R.id.student_id);
    int id = Integer.parseInt(studentId.getText().toString());

    EditText firstName = (EditText) findViewById(R.id.first_name);
    String fn = firstName.getText().toString();

    EditText lastName = (EditText) findViewById(R.id.last_name);
    String ln = lastName.getText().toString();

    EditText readingLevel = (EditText) findViewById(R.id.reading_level);
    int rl = (int) readingLevel.getText().toString().charAt(0);

    Users user = new Users(id, fn, ln, rl, "red", "square");
    user.save();
  }
}
