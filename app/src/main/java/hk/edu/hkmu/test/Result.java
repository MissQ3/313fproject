package hk.edu.hkmu.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import org.json.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Result extends AppCompatActivity {
    private String TAG = "Result";
    private ListView listView;
    private Button backbutton;
    private Button applybutton;
    private RadioButton sortid;
    private RadioButton sortname;
    private RadioButton sortdistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listView = (ListView)findViewById(R.id.listview);
        applybutton=findViewById(R.id.apply_button);
        backbutton = findViewById(R.id.back_button);
        sortid = findViewById(R.id.sortbyId);
        sortname = findViewById(R.id.sortbyName);
        sortdistrict = findViewById(R.id.sortbyDistrict);

        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        applybutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean sortbyid = sortid.isChecked();
                boolean sortbyname = sortname.isChecked();
                boolean sortbydistrict = sortdistrict.isChecked();
                if(sortbyid){
                    SchoolInfo.sortbyId();
                }else if(sortbyname){
                    SchoolInfo.sortbyName();
                }else if(sortbydistrict){
                    SchoolInfo.sortbyDistrict();
                }
                SchoolInfo.refresh();
                finish();
                startActivity(getIntent());
            }
        });


        String result = SchoolInfo.refresh;
        if(!result.equals("refresh")) {
            SchoolInfo.eninfoList.clear();
            SchoolInfo.chinfoList.clear();
        }
        SchoolInfo.reloadRefresh();

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
                        TextView resultTel = skuDetail.findViewById(R.id.tele);
                        TextView resultWeb = skuDetail.findViewById(R.id.web);
                        TextView resultCat = skuDetail.findViewById(R.id.skuCat);
                        TextView resultGender = skuDetail.findViewById(R.id.skuGender);
                        TextView resultSession = skuDetail.findViewById(R.id.skuSession);
                        TextView resultDISTRICT = skuDetail.findViewById(R.id.skuDistrict);
                        TextView resultFinance = skuDetail.findViewById(R.id.skuFinance);
                        TextView resultLevel = skuDetail.findViewById(R.id.skuLevel);
                        TextView resultFax = skuDetail.findViewById(R.id.skuFax);
                        TextView resultReligion = skuDetail.findViewById(R.id.skuReligion);


                        if(Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
                            resultSkuName.setText(contact.get(SchoolInfo.enname));
                            resultAddress.setText(contact.get(SchoolInfo.enaddress) + "\n");
                            resultTel.setText("Telephone: " + SchoolInfo.entel);
                            resultWeb.setText(Html.fromHtml("Website: " + "<a href='" + contact.get(SchoolInfo.enweb) +
                                    "'> " + contact.get(SchoolInfo.enweb) + " </a>"));
                            resultCat.setText("Category:"+contact.get(SchoolInfo.encat));
                            resultGender.setText("Student Gender: "+contact.get(SchoolInfo.engender));
                            resultSession.setText("Session: "+contact.get(SchoolInfo.ensession));
                            resultDISTRICT.setText("District: "+contact.get(SchoolInfo.endistrict));
                            resultFinance.setText("Finance Type: "+contact.get(SchoolInfo.enfintype));
                            resultLevel.setText("School Level: "+contact.get(SchoolInfo.enlevel));
                            resultFax.setText("Fax Number: "+contact.get(SchoolInfo.enfax));
                            resultReligion.setText("Religion: "+contact.get(SchoolInfo.ensession));

                            locButton = "Show in map";
                        }else if(Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage())){
                           resultSkuName.setText(contact.get(SchoolInfo.chname));
                            resultAddress.setText(contact.get(SchoolInfo.chaddress) + "\n");
                            //String website=zhGetdetail.getWeb(result);(debug)
                            resultWeb.setText(Html.fromHtml("網站: " + "<a href='" + contact.get(SchoolInfo.chweb) +
                                    "'> " + contact.get(SchoolInfo.chweb) + " </a>"));
                            resultAddress.setText(contact.get(SchoolInfo.chaddress) + "\n");
                            resultTel.setText("電話: " + contact.get(SchoolInfo.chtel));
                            resultCat.setText("類型 :"+contact.get(SchoolInfo.chcat));
                            resultGender.setText("就讀學生性別: "+contact.get(SchoolInfo.chgender));
                            resultSession.setText("學校授課時間: "+contact.get(SchoolInfo.chsession));
                            resultDISTRICT.setText("分區: "+contact.get(SchoolInfo.chsession));
                            resultFinance.setText("資助種類: "+contact.get(SchoolInfo.chfintype));
                            resultLevel.setText("學校類型: "+contact.get(SchoolInfo.chlevel));
                            resultFax.setText("傳真號碼: "+contact.get(SchoolInfo.chfax));
                            resultReligion.setText("宗教: "+contact.get(SchoolInfo.chreligion));

                            locButton = "顯示地圖";
                        }

                        builder.setNegativeButton(locButton, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query="+ contact.get(SchoolInfo.schLatitude) + "," + contact.get(SchoolInfo.schLongitude));
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                mapIntent.setPackage("com.google.android.apps.maps");
                                try
                                {
                                    startActivity(mapIntent);
                                }
                                catch(ActivityNotFoundException ex)
                                {
                                    try
                                    {
                                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                        startActivity(unrestrictedIntent);
                                    }
                                    catch(ActivityNotFoundException innerEx)
                                    {
                                        Toast.makeText(view.getContext(), "Please install a maps application", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });

                        AlertDialog alertDialog = builder.create();

                        alertDialog.show();
                    }
                }
        );
    }
}
