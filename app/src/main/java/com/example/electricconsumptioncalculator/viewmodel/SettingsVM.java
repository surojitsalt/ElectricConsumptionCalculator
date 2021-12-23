package com.example.electricconsumptioncalculator.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.electricconsumptioncalculator.Variables;
import com.example.electricconsumptioncalculator.model.Consumer;
import com.example.electricconsumptioncalculator.model.ElectricitySlab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SettingsVM extends BaseVM{
    private ArrayList<ElectricitySlab> slabs = new ArrayList<ElectricitySlab>();
    private MutableLiveData<String> observer = new MutableLiveData<String>();
    private String jsonString;

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String pJsonString) {
        this.jsonString = pJsonString;
        observer.setValue(pJsonString);
    }

    public SettingsVM(){
        observer = new MutableLiveData<>();
        jsonString = "{"
                + " \"slabdata\": ["
                + " {"
                + " \"slabStart\": \"0\","
                + " \"slabEnd\": \"100\","
                + " \"slabRate\" : \"5\""
                + " },"
                + " {"
                + " \"slabStart\": \"101\","
                + " \"slabEnd\": \"500\","
                + " \"slabRate\" : \"8\""
                + " },"
                + " {"
                + " \"slabStart\": \"500\","
                + " \"slabEnd\": \">\","
                + " \"slabRate\" : \"10\""
                + " }"
                + " ]"
                + "}";
        parseJson();
    }

    public LiveData<String> getObserver() {
        return observer;
    }

    public String getGeneratedJsonString(){
        String vRootJsonString = "{"
                + " \"slabdata\": ["
                + "BODY"
                + " ]"
                + "}";

        String vJsonBody = "";
        for(int i=0; i< Variables.getSlabs().size(); i++){
            ElectricitySlab vElectricitySlab = Variables.getSlabs().get(i);
            String vInternalBody = " {"
                    + " \"slabStart\": \"" + vElectricitySlab.getSlabStart() + "\","
                    + " \"slabEnd\": \"" + vElectricitySlab.getSlabEnd() + "\","
                    + " \"slabRate\" : \"" + vElectricitySlab.getSlabRate() + "\""
                    + " }";
            vJsonBody = vJsonBody + vInternalBody;
            if( i != Variables.getSlabs().size()-1)
                vJsonBody = vJsonBody + ",";
        }
        vRootJsonString = vRootJsonString.replace("BODY",vJsonBody);
        return vRootJsonString.replace("BODY",vJsonBody);
    }

    public void parseJson(){
        slabs.clear();
        try {
            JSONObject obj = new JSONObject(jsonString);
            JSONArray vSlabData = obj.getJSONArray("slabdata");

            for(int i=0; i<vSlabData.length(); i++) {
                JSONObject vSlabs = vSlabData.getJSONObject(i);

                String vSlabStart = vSlabs.getString("slabStart");
                String vSlabEnd = vSlabs.getString("slabEnd");
                int vSlabRate = vSlabs.getInt("slabRate");

                ElectricitySlab vSlab = new ElectricitySlab(vSlabStart, vSlabEnd, vSlabRate);
                slabs.add(vSlab);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Variables.setSlabs(slabs);

    }

}
