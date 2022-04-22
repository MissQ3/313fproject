package hk.edu.hkmu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

public class InputName extends AppCompatActivity {
    static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    static String EXTRA_CHECK = "EXTRA_CHECK" ;
    private EditText EditText;
    private Button SubmitButton;
    private RadioButton sortid;
    private RadioButton sortname;
    private RadioButton sortdistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        EditText = findViewById(R.id.name);
        SubmitButton = findViewById(R.id.submitbutton);
        sortid = findViewById(R.id.sortbyId);
        sortname = findViewById(R.id.sortbyName);
        sortdistrict = findViewById(R.id.sortbyDistrict);

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String returnString = "return";
                boolean sortbyid = sortid.isChecked();
                boolean sortbyname = sortname.isChecked();
                boolean sortbydistrict = sortdistrict.isChecked();

                if(sortbyid){
                    returnString = "Sortbyid";
                }else if(sortbyname){
                    returnString = "Sortbyname";
                }else if(sortbydistrict){
                    returnString = "Sortbydistrict";
                }

                String Str = EditText.getText().toString();
                Intent intent = new Intent(InputName.this, Result.class);
                intent.putExtra(EXTRA_CHECK, returnString);
                intent.putExtra(EXTRA_MESSAGE, Str);
                startActivity(intent);
            }
        });
    }
}
