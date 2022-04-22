package hk.edu.hkmu.test;

import org.apache.commons.text.WordUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Collectors;

public class SchoolInfo {
    public static String schoolid = "ID";
    public static String encat = "Category";
    public static String chcat = "類別";
    public static String enname = "English Name";
    public static String chname = "名稱";
    public static String enaddress = "Address";
    public static String chaddress = "地址";
    public static String engender = "Students Gender";
    public static String chgender = "就讀學生性別";
    public static String ensession = "Session";
    public static String chsession = "學校授課時間";
    public static String endistrict = "District";
    public static String chdistrict = "分區";
    public static String enfintype = "Finance Type";
    public static String chfintype = "資助種類";
    public static String enlevel = "School Level";
    public static String chlevel = "學校類型";
    public static String entel = "Telephone";
    public static String chtel = "聯絡電話";
    public static String enfax = "Fax Number";
    public static String chfax = "傳真號碼";
    public static String enweb = "Website";
    public static String chweb = "網頁";
    public static String enreligion = "Religion";
    public static String chreligion = "宗教";
    public static String schLatitude = "Latitude";
    public static String schLongitude = "Longitude";


    public static ArrayList<HashMap<String, String>> eninfoList = new ArrayList<>();
    public static ArrayList<HashMap<String, String>> chinfoList = new ArrayList<>();
    public static ArrayList<JSONObject> infoList = new ArrayList<>();

    // Creates and add contact to contact list
    public static void enaddContact(String id, String cat, String name, String address, String gender, String session, String district, String fintype, String level, String tele, String fax, String website, String religion, String latitude, String longitude) {

        HashMap<String, String> info = new HashMap<>();
        cat = WordUtils.capitalizeFully(cat);
        name = cap(name);
        address = cap(address);
        gender = cap(gender);
        session = cap(session);
        district = cap(district);
        fintype = cap(fintype);
        level = cap(level);
        religion = cap(religion);
        info.put(schoolid, id);
        info.put(encat, cat);
        info.put(enname, name);
        info.put(enaddress, address);
        info.put(engender, gender);
        info.put(ensession, session);
        info.put(endistrict, district);
        info.put(enfintype, fintype);
        info.put(enlevel, level);
        info.put(entel, tele);
        info.put(enfax, fax);
        info.put(enweb, website);
        info.put(enreligion, religion);
        info.put(schLatitude, latitude);
        info.put(schLongitude, longitude);

        eninfoList.add(info);
    }
    public static void chaddContact(String id, String cat, String name, String address, String gender, String session, String district, String fintype, String level, String tele, String fax, String website, String religion, String latitude, String longitude) {

        HashMap<String, String> info = new HashMap<>();
        info.put(schoolid,id);
        info.put(chcat, cat);
        info.put(chname, name);
        info.put(chaddress, address);
        info.put(chgender, gender);
        info.put(chsession, session);
        info.put(chdistrict, district);
        info.put(chfintype, fintype);
        info.put(chlevel, level);
        info.put(chtel, tele);
        info.put(chfax, fax);
        info.put(chweb, website);
        info.put(chreligion, religion);
        info.put(schLatitude, latitude);
        info.put(schLongitude, longitude);

        chinfoList.add(info);
    }

    private static String cap(String s){
        char[] name = s.trim().toLowerCase().toCharArray();
        boolean first = true;
        for(int i = 0; i < name.length; i++)
        {
            if(name[i] < 'a' || name[i] > 'z')
                first = true;
            else
            {
                if(first)
                {
                    first = false;
                    name[i] = Character.toUpperCase(name[i]);
                }
            }
        }
        s = String.valueOf(name);
        return s;
    };
    public static void getlist(JSONObject obj){
        infoList.add((JSONObject) obj);
    };
    public static void sortbyId(){
        Collections.sort(eninfoList, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                String v1,v2;
                try{
                    v1 =o1.get(schoolid);
                    v2 =o2.get(schoolid);
                    return v1.compareTo(v2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 2;
            }
        });
        Collections.sort(chinfoList, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                String v1,v2;
                try{
                    v1 =o1.get(schoolid);
                    v2 =o2.get(schoolid);
                    return v1.compareTo(v2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 2;
            }
        });
    }
    public static void sortbyName(){
        Collections.sort(eninfoList, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                String v1,v2;
                try{
                    v1 =o1.get(enname);
                    v2 =o2.get(enname);
                    return v1.compareTo(v2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 2;
            }
        });
        Collections.sort(chinfoList, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                String v1,v2;
                try{
                    v1 =o1.get(chname);
                    v2 =o2.get(chname);
                    return v1.compareTo(v2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 2;
            }
        });
    }
    public static void sortbyDistrict(){
        Collections.sort(eninfoList, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                String v1,v2;
                try{
                    v1 =o1.get(endistrict);
                    v2 =o2.get(endistrict);
                    return v1.compareTo(v2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 2;
            }
        });
        Collections.sort(chinfoList, new Comparator<HashMap<String, String>>() {
            @Override
            public int compare(HashMap<String, String> o1, HashMap<String, String> o2) {
                String v1,v2;
                try{
                    v1 =o1.get(chdistrict);
                    v2 =o2.get(chdistrict);
                    return v1.compareTo(v2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 2;
            }
        });
    }
}
