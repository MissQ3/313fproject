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
        SchoolInfo.infoList.clear();
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
        }else if(checkSortingMethod.equals(("Sortbydistrict"))){
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
                        
                        ArrayList<JSONObject> result=Getdetail.getDetail(contact.get(SchoolInfo.schoolid),SchoolInfo.infoList);

                        if(Locale.getDefault().getLanguage().equals(new Locale("en").getLanguage())) {
                            resultSkuName.setText(engGetdetail.getName(result));
                            resultAddress.setText(engGetdetail.getAddress(result) + "\n");
                            resultTel.setText("Telephone: " + engGetdetail.getTel(result));
                            resultWeb.setText(Html.fromHtml("Website: " + "<a href='" + engGetdetail.getWeb(result) +
                                    "'> " + engGetdetail.getWeb(result) + " </a>"));
                            resultCat.setText("Category:"+engGetdetail.getCategory(result));
                            resultGender.setText("Student Gender: "+engGetdetail.getGender(result));
                            resultSession.setText("Session: "+engGetdetail.getSession(result));
                            resultDISTRICT.setText("District: "+engGetdetail.getDistrict(result));
                            resultFinance.setText("Finance Type: "+engGetdetail.getFinance(result));
                            resultLevel.setText("School Level: "+engGetdetail.getLevel(result));
                            resultFax.setText("Fax Number: "+engGetdetail.getFax(result));
                            resultReligion.setText("Religion: "+engGetdetail.getReligion(result));

                            locButton = "Show in map";
                        }else if(Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage())){
                           resultSkuName.setText(zhGetdetail.getName(result));
                            resultAddress.setText(zhGetdetail.getAddress(result) + "\n");
                            //String website=zhGetdetail.getWeb(result);(debug)
                            resultWeb.setText(Html.fromHtml("網站: " + "<a href='" + zhGetdetail.getWeb(result) +
                                    "'> " + zhGetdetail.getWeb(result) + " </a>"));
                            resultSkuName.setText(zhGetdetail.getName(result));
                            resultAddress.setText(zhGetdetail.getAddress(result) + "\n");
                            resultTel.setText("電話: " + zhGetdetail.getTel(result));
                            resultCat.setText("類型 :"+zhGetdetail.getCategory(result));
                            resultGender.setText("就讀學生性別: "+zhGetdetail.getGender(result));
                            resultSession.setText("學校授課時間: "+zhGetdetail.getSession(result));
                            resultDISTRICT.setText("分區: "+zhGetdetail.getDistrict(result));
                            resultFinance.setText("資助種類: "+zhGetdetail.getFinance(result));
                            resultLevel.setText("學校類型: "+zhGetdetail.getLevel(result));
                            resultFax.setText("傳真號碼: "+zhGetdetail.getFax(result));
                            resultReligion.setText("宗教: "+zhGetdetail.getReligion(result));

                            locButton = "顯示地圖";
                        }

                        builder.setNegativeButton(locButton, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query="+ contact.get(SchoolInfo.enlatitude) + "," + contact.get(SchoolInfo.enlongitude));
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
