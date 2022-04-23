package hk.edu.hkmu.test;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class JsonHandlerThread extends Thread {
    private static final String TAG = "JsonHandlerThread";
    private static String jsonUrl = "https://www.edb.gov.hk/attachment/en/student-parents/sch-info/sch-search/sch-location-info/SCH_LOC_EDB.json";

    public static String makeRequest() {
        String response = null;
        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = inputStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private static String inputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            }
        }
        return sb.toString();
    }
    public void run() {
        String infoStr = makeRequest();
        Log.e(TAG, "Response from url: " + infoStr);

        if (infoStr != null) {
            try {
                JSONArray info = new JSONArray(infoStr);
                    // looping through All Infos
                    for (int i = 1; i < info.length(); i++) {
                        JSONObject c = info.getJSONObject(i);
                        String schoolid = c.getString("A");
                        String encat = c.getString("B");
                        String enname = c.getString("D");
                        String enaddress = c.getString("F");
                        String engender = c.getString("P");
                        String ensession = c.getString("R");
                        String endistrict = c.getString("T");
                        String enfintype = c.getString("V");
                        String enlevel = c.getString("X");
                        String entele = c.getString("Z");
                        String enfax = c.getString("AB");
                        String enwebsite = c.getString("AD");
                        String enreligion = c.getString("AF");
                        String enlatitude = c.getString("J");
                        String enlongitude = c.getString("H");
                        SchoolInfo.enaddContact(schoolid, encat, enname, enaddress, engender, ensession, endistrict, enfintype, enlevel, entele, enfax, enwebsite, enreligion, enlatitude, enlongitude);
                    }
                    for (int i = 1; i < info.length(); i++) {
                        JSONObject c = info.getJSONObject(i);
                        String schoolid = c.getString("A");
                        String chcat = c.getString("C");
                        String chname = c.getString("E");
                        String chaddress = c.getString("G");
                        String chgender = c.getString("Q");
                        String chsession = c.getString("S");
                        String chdistrict = c.getString("U");
                        String chfintype = c.getString("W");
                        String chlevel = c.getString("Y");
                        String chtele = c.getString("AA");
                        String chfax = c.getString("AC");
                        String chwebsite = c.getString("AE");
                        String chreligion = c.getString("AG");
                        String chlatitude = c.getString("J");
                        String chlongitude = c.getString("H");
                        SchoolInfo.chaddContact(schoolid, chcat, chname, chaddress, chgender, chsession, chdistrict, chfintype, chlevel, chtele, chfax, chwebsite, chreligion, chlatitude, chlongitude);
                }
            }catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        }else {
            Log.e(TAG, "Couldn't get json from server.");
        }
    }
}
