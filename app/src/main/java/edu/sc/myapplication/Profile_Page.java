package edu.sc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile_Page extends Activity {

    private Integer Pweight = 0;
    private Integer Pweight2 = 345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__page);

        Button yourButton10 = (Button)findViewById(R.id.button10);

        yourButton10.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                EditText editNumber;

                int a = 1;
                int b = 2;
                int c = a + b;
                editNumber = (EditText)findViewById(R.id.editText10);



                TextView t = (TextView) findViewById(R.id.textView15);
                t.setText(editNumber.getText());
                String value = editNumber.getText().toString();
                Pweight = Integer.parseInt(value);
                Intent intent = new Intent(Profile_Page.this, BMICAL.class);
                intent.putExtra("some_key", value);
                startActivity(intent);

            }
        });

        Button yourButton11 = (Button)findViewById(R.id.button11);
        yourButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Profile_Page.this, BMICAL.class));
            }
        });


    }

    public Integer getPweight() {
        return Pweight;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile__page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
