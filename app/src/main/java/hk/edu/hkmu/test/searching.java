package hk.edu.hkmu.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class searching extends AppCompatActivity {

    public static ArrayList<HashMap<String, String>> searchlist = new ArrayList<>();

    static void ensearchname(String searchstr){
        searchlist.clear();
        Object[] strarray = SchoolInfo.eninfoList.get(0).keySet().toArray();
        for(int i =0; i< SchoolInfo.eninfoList.size();i++){
            if(SchoolInfo.eninfoList.get(i).get("English Name").toLowerCase().contains(searchstr.toLowerCase()) || SchoolInfo.chinfoList.get(i).get("名稱").contains(searchstr)){  //check if text match any school name
                HashMap<String, String> search = new HashMap<>();
                for(int j=0; j<SchoolInfo.eninfoList.get(0).keySet().toArray().length;j++){
                    search.put((String)(strarray[j]),SchoolInfo.eninfoList.get(i).get(SchoolInfo.eninfoList.get(i).keySet().toArray()[j])); //using another arraylist to store the filtered result
                }
                addarray(search);
            }
        }
    }

    static void chsearchname(String searchstr){
        searchlist.clear();
        Object[] strarray = SchoolInfo.chinfoList.get(0).keySet().toArray();
        for(int i =0; i< SchoolInfo.chinfoList.size();i++){
            if(SchoolInfo.chinfoList.get(i).get("名稱").contains(searchstr) || SchoolInfo.eninfoList.get(i).get("English Name").toLowerCase().contains(searchstr.toLowerCase())){  //check if text match any school name
                HashMap<String, String> search = new HashMap<>();
                for(int j=0; j<SchoolInfo.chinfoList.get(0).keySet().toArray().length;j++){
                    search.put((String)(strarray[j]),SchoolInfo.chinfoList.get(i).get(SchoolInfo.chinfoList.get(i).keySet().toArray()[j])); //using another arraylist to store the filtered result
                }
                addarray(search);
            }
        }
    }

    static void ensearchcrit(){

    }

    static void chsearchcrit(){

    }

    private static void addarray(HashMap hm){
        searchlist.add(hm);
    }
}