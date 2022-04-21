package hk.edu.hkmu.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

                        LayoutInflater inflater = LayoutInflater.from(view.getContext());
                        View skuDetail = inflater.inflate(R.layout.sku_details, null);
                        String locButton = "";
                        builder.setView(skuDetail);

                        TextView resultSkuName = skuDetail.findViewById(R.id.name);
                        TextView resultAddress = skuDetail.findViewById(R.id.address);
                        TextView resultGender = skuDetail.findViewById(R.id.gender);
                        TextView resultTel = skuDetail.findViewById(R.id.tele);
                        TextView resultWeb = skuDetail.findViewById(R.id.web);
                        TextView resultRelig = skuDetail.findViewById(R.id.religion);

                        if(Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
                            resultSkuName.setText(contact.get(SchoolInfo.enname));
                            resultAddress.setText(contact.get(SchoolInfo.enaddress) + "\n");

                            resultGender.setText("Students Gender: " + contact.get(SchoolInfo.engender));

                            if (contact.get(SchoolInfo.entel).isEmpty()) {
                                resultTel.setText("Telephone: N/A");
                            }
                            else {
                                resultTel.setText("Telephone: " + contact.get(SchoolInfo.entel));
                            }

                            if (contact.get(SchoolInfo.enweb).isEmpty()){
                                resultWeb.setText("Website: N/A");
                            }
                            else {
                                resultWeb.setText(Html.fromHtml("Website: " + "<a href='" + contact.get(SchoolInfo.chweb) +
                                        "'> " + contact.get(SchoolInfo.enweb) + " </a>"));
                            }

                            resultRelig.setText("Religion: " + contact.get(SchoolInfo.enreligion));
                            locButton = "Show in map";
                        }else if(Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage())){
                            resultSkuName.setText(contact.get(SchoolInfo.chname));
                            resultAddress.setText(contact.get(SchoolInfo.chaddress) + "\n");

                            resultGender.setText("就讀學生性別: " + contact.get(SchoolInfo.chgender));

                            if (contact.get(SchoolInfo.chtel).isEmpty()) {
                                resultTel.setText("聯絡電話: N/A");
                            }
                            else {
                                resultTel.setText("聯絡電話: " + contact.get(SchoolInfo.chtel));
                            }

                            if (contact.get(SchoolInfo.chweb).isEmpty()){
                                resultWeb.setText("網站: N/A");
                            }
                            else {
                                resultWeb.setText(Html.fromHtml("網站: " + "<a href='" + contact.get(SchoolInfo.chweb) +
                                        "'> " + contact.get(SchoolInfo.chweb) + " </a>"));
                            }

                            resultRelig.setText("宗教: " + contact.get(SchoolInfo.chreligion));
                            locButton = "顯示地圖";
                        }

                        builder.setNegativeButton(locButton,null);
                        /*builder.setPositiveButton(detailButton,new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setContentView(R.layout.sku_details);
                                TextView err = (TextView)findViewById(R.id.name);
                                err.setText(SchoolInfo.enname);
                            }
                        });*/

                        AlertDialog alertDialog = builder.create();

                        alertDialog.show();
                    }
                }
        );
    }
}