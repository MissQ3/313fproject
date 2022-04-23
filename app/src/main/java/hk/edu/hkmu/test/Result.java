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
import android.widget.RadioButton;
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

        String checkSortingMethod = intent.getStringExtra(InputName.EXTRA_CHECK);
        if(checkSortingMethod.equals("Sortbyid")){
            SchoolInfo.sortbyId();
        }else if(checkSortingMethod.equals("Sortbyname")){
            SchoolInfo.sortbyName();
        }else if(checkSortingMethod.equals("Sortbydistrict")){
            SchoolInfo.sortbyDistrict();
        }

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
                            if (!contact.get(SchoolInfo.entel).contains("N.A.") && !contact.get(SchoolInfo.entel).isEmpty()) {
                                resultTel.setText("Telephone: " + contact.get(SchoolInfo.entel));
                            }else{resultTel.setText("Telephone: N/A");}
                            if (!contact.get(SchoolInfo.enweb).contains("N.A.") && !contact.get(SchoolInfo.enweb).isEmpty()) {
                                resultWeb.setText(Html.fromHtml("Website: " + "<a href='" + contact.get(SchoolInfo.enweb) +
                                        "'> " + contact.get(SchoolInfo.enweb) + " </a>"));
                            }else{resultWeb.setText("Website: N/A");}
                            resultCat.setText("Category: "+contact.get(SchoolInfo.encat));
                            resultGender.setText("Student Gender: "+contact.get(SchoolInfo.engender));
                            resultSession.setText("Session: "+contact.get(SchoolInfo.ensession));
                            resultDISTRICT.setText("District: "+contact.get(SchoolInfo.endistrict));
                            resultFinance.setText("Finance Type: "+contact.get(SchoolInfo.enfintype));
                            resultLevel.setText("School Level: "+contact.get(SchoolInfo.enlevel));
                            if (!contact.get(SchoolInfo.entel).contains("N.A.") && !contact.get(SchoolInfo.entel).isEmpty()) {
                                resultFax.setText("Fax Number: "+contact.get(SchoolInfo.enfax));
                            }else{resultFax.setText("Fax Number: N/A");}
                            resultReligion.setText("Religion: "+contact.get(SchoolInfo.enreligion));

                            locButton = "Show on map";
                        }else if(Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage())){
                            resultSkuName.setText(contact.get(SchoolInfo.chname));
                            resultAddress.setText(contact.get(SchoolInfo.chaddress) + "\n");
                            //String website=zhGetdetail.getWeb(result);(debug)
                            if (!contact.get(SchoolInfo.chtel).contains("N.A.") && !contact.get(SchoolInfo.chtel).isEmpty()) {
                                resultTel.setText("電話: " + contact.get(SchoolInfo.chtel));
                            }else{resultTel.setText("電話: 不適用");}
                            if (!contact.get(SchoolInfo.chweb).contains("N.A.") && !contact.get(SchoolInfo.chweb).isEmpty()) {
                                resultWeb.setText(Html.fromHtml("網站: " + "<a href='" + contact.get(SchoolInfo.chweb) +
                                        "'> " + contact.get(SchoolInfo.chweb) + " </a>"));
                            }else{resultWeb.setText("網站: 不適用");}
                            resultCat.setText("類型 :"+contact.get(SchoolInfo.chcat));
                            resultGender.setText("就讀學生性別: "+contact.get(SchoolInfo.chgender));
                            resultSession.setText("學校授課時間: "+contact.get(SchoolInfo.chsession));
                            resultDISTRICT.setText("分區: "+contact.get(SchoolInfo.chsession));
                            resultFinance.setText("資助種類: "+contact.get(SchoolInfo.chfintype));
                            resultLevel.setText("學校類型: "+contact.get(SchoolInfo.chlevel));
                            if (!contact.get(SchoolInfo.chfax).contains("N.A.") && !contact.get(SchoolInfo.chfax).isEmpty()) {
                                resultFax.setText("傳真號碼: " + contact.get(SchoolInfo.chfax));
                            }else{resultFax.setText("傳真號碼: 不適用");}
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
