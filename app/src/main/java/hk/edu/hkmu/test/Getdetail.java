package hk.edu.hkmu.test;

import android.util.Log;

import org.json.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class Getdetail {
    public static ArrayList<JSONObject> getDetail(String id, ArrayList<JSONObject> list) {
        ArrayList<JSONObject> result = new ArrayList<>();
        JSONObject obj1;
        String resultid;
        for (int i = 0; i <= list.size(); i++) {
            obj1 = list.get(i);
            try {
                resultid = (String) obj1.get("A");
                if (resultid.compareTo(id) == 0) {
                    result.add(obj1);
                    return result;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

class zhGetdetail{

    public static String getId(ArrayList<JSONObject> resultList) {
        JSONObject obj = resultList.get(0);
        String string = "";
        try {
            string = (String) obj.get("A");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String getCategory(ArrayList<JSONObject> resultList) {
        JSONObject obj = resultList.get(0);
        String string = "";
        try {
            string = (String) obj.get("C");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String getName(ArrayList<JSONObject> resultList) {
        JSONObject obj = resultList.get(0);
        String string = "";
        try {
            string = (String) obj.get("E");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String getAddress(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("G");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getGender(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("Q");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getSession(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("S");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getDistrict(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("U");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getFinance(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("W");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getLevel(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("Y");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getTel(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("AA");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getFax(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("AC");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getWeb(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("AE");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getReligion(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("AG");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }
}

class engGetdetail{

    public static String getId(ArrayList<JSONObject> resultList) {
        JSONObject obj = resultList.get(0);
        String string = "";
        try {
            string = (String) obj.get("A");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String getCategory(ArrayList<JSONObject> resultList) {
        JSONObject obj = resultList.get(0);
        String string = "";
        try {
            string = (String) obj.get("B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String getName(ArrayList<JSONObject> resultList) {
        JSONObject obj = resultList.get(0);
        String string = "";
        try {
            string = (String) obj.get("D");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static String getAddress(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("F");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getGender(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("P");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getSession(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("R");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getDistrict(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("T");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getFinance(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("V");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getLevel(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("X");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getTel(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("Z");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getFax(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("AB");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getWeb(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("AD");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }

    public static String getReligion(ArrayList<JSONObject> resultList){
        JSONObject obj=resultList.get(0);
        String string="";
        try {
            string = (String) obj.get("AF");
        }catch (Exception e){
            e.printStackTrace();
        }
        return string;
    }
}
