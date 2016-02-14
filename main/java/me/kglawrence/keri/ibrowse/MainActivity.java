package me.kglawrence.keri.ibrowse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }


  //Need to check for valid value before adding to db
  public void addStudent(View view) {
    Intent intent = new Intent(this, StudentList.class);

    boolean flag = false;

    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;

    EditText studentId= (EditText) findViewById(R.id.student_id);
    int id = Integer.parseInt(studentId.getText().toString());

    //Fix later if time
    //if (Users.find(Users.class, "id = ?", "id").size() < 1) {
    //  System.out.println(Users.find(Users.class, "number = ?", new String{id}));
    //  Toast.makeText(context, "Student id already in use.", duration).show();
    //  flag = true;
    //}

    EditText firstName = (EditText) findViewById(R.id.first_name);
    String fn = firstName.getText().toString();

    EditText lastName = (EditText) findViewById(R.id.last_name);
    String ln = lastName.getText().toString();

    EditText readingLevel = (EditText) findViewById(R.id.reading_level);
    int rl = (int) readingLevel.getText().toString().charAt(0);

    //modify if picture login is added
    Users user = new Users(id, fn, ln, rl, "red", "square");

    if (!flag) {

      user.save();

      startActivity(intent);
    }
  }
}
