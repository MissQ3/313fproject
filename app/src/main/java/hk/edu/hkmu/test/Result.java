package hk.edu.hkmu.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Result extends AppCompatActivity {
    private String TAG = "Result";
    private ListView listView;
    private Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        listView = (ListView)findViewById(R.id.listview);
        backbutton = findViewById(R.id.back_button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        SchoolInfo.eninfoList.clear();
        SchoolInfo.chinfoList.clear();
        JsonHandlerThread jsonHandlerThread = new JsonHandlerThread();

        jsonHandlerThread.start();
        try {
            jsonHandlerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = getIntent();
        String searchstr = intent.getStringExtra(InputName.EXTRA_MESSAGE);

        if(Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
            searching.ensearchname(searchstr);
            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    searching.searchlist,
                    R.layout.list_view_layout,
                    new String[]{SchoolInfo.enname, SchoolInfo.entel, SchoolInfo.enaddress},
                    new int[]{R.id.name, R.id.tele, R.id.address}
            );
            listView.setAdapter(adapter);
        }else if(Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage())){
            searching.chsearchname(searchstr);
            SimpleAdapter adapter = new SimpleAdapter(
                    this,
                    searching.searchlist,
                    R.layout.list_view_layout,
                    new String[]{SchoolInfo.chname, SchoolInfo.chtel, SchoolInfo.chaddress},
                    new int[]{R.id.name,R.id.tele,R.id.address}
            );
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?>parent, View view, int position, long id){
                        HashMap<String,String> contact = searching.searchlist.get(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(Result.this);
                        builder.setTitle(contact.get(SchoolInfo.enname));
                        builder.setMessage(Html.fromHtml(
                                "<b>Mobile: </b>" + contact.get(SchoolInfo.entel) + "<br/>" +
                                        "<b>Location: </b>" + contact.get(SchoolInfo.enaddress)
                        ));

                        builder.setNeutralButton("Location",null);
                        builder.setPositiveButton("Details",null);

                        AlertDialog alertDialog = builder.create();

                        alertDialog.show();
                    }
                }
        );
    }
}