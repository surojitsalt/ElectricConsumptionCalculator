package com.example.electricconsumptioncalculator.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.electricconsumptioncalculator.ValidationUtility;
import com.example.electricconsumptioncalculator.Variables;
import com.example.electricconsumptioncalculator.model.Consumer;
import com.example.electricconsumptioncalculator.model.ElectricitySlab;
import java.util.ArrayList;

public class ElectricityBillVM extends BaseVM {

    private Consumer consumer = new Consumer();
    private ArrayList<ElectricitySlab> slabs = new ArrayList<ElectricitySlab>();
    private MutableLiveData<Consumer> observer = new MutableLiveData<Consumer>();

    public ElectricityBillVM(){
        consumer = new Consumer();
        populateElectricityBill();
    }

    public LiveData<Consumer> getObserver() {
        return observer;
    }

    public void populateElectricityBill(){
        if(Variables.getSlabs().size() == 0) {
            ElectricitySlab vSlabDivision1 = new ElectricitySlab("0", "100", 5);
            slabs.add(vSlabDivision1);

            ElectricitySlab vSlabDivision2 = new ElectricitySlab("101", "500", 8);
            slabs.add(vSlabDivision2);

            ElectricitySlab vSlabDivision3 = new ElectricitySlab("500", ">", 10);
            slabs.add(vSlabDivision3);
            Variables.setSlabs(slabs);
        }
        else{
            slabs.clear();
            slabs.addAll(Variables.getSlabs());
        }
    }


    public void notifyChange(String pMeterReading, String pConsumerID){
        consumer.setConsumerId(pConsumerID);
        consumer.setMeterReading(pMeterReading);

        int vCurrentBill = calculateBill(consumer.getMeterReading());
        consumer.setBillAmount(String.valueOf(vCurrentBill));

        observer.setValue(consumer);
    }

    private int calculateBill(String pMeterReading){
        int vTotalBill = 0;
        if(!ValidationUtility.isStringEmpty(pMeterReading) && ValidationUtility.isNumber(pMeterReading)){
            int vNumMeterReading = Integer.parseInt(pMeterReading);
            if(vNumMeterReading > 0) {
                for (int i = 0; i < Variables.getSlabs().size(); i++) {

                    String vSlab1 = Variables.getSlabs().get(i).getSlabStart();
                    String vSlab2 = Variables.getSlabs().get(i).getSlabEnd();

                    int vNumSlab1 = 0;
                    if (!ValidationUtility.isStringEmpty(vSlab1) && ValidationUtility.isNumber(vSlab1))
                        vNumSlab1 = Integer.parseInt(vSlab1);

                    int vNumSlab2 = 0;
                    if (!ValidationUtility.isStringEmpty(vSlab2) && ValidationUtility.isNumber(vSlab2))
                        vNumSlab2 = Integer.parseInt(vSlab2);
                    else
                        vNumSlab2 = -1;

                    int vNumSlabRate = Variables.getSlabs().get(i).getSlabRate();
                    int vCurrentSlbBill = 0;
                    if(vNumMeterReading > vNumSlab1 && vNumMeterReading >= vNumSlab2 && vNumSlab2 != -1) {
                        if(isFirstSlab(i)) {
                            vCurrentSlbBill = (vNumSlab2 - vNumSlab1) * vNumSlabRate;
                        }
                        else{
                            vCurrentSlbBill = (vNumSlab2 - (vNumSlab1 - 1)) * vNumSlabRate;
                        }
                    }
                    else if(vNumMeterReading >= vNumSlab1 && vNumMeterReading <= vNumSlab2 && vNumSlab2 != -1) {
                        vCurrentSlbBill = (vNumMeterReading - (vNumSlab1 - 1))  * vNumSlabRate;
                    }
                    else if (vNumMeterReading>= vNumSlab1 && vNumSlab2 == -1) {
                        vCurrentSlbBill = (vNumMeterReading - vNumSlab1) * vNumSlabRate;
                    }
                    vTotalBill = vTotalBill + vCurrentSlbBill;
                }
            }

        }

        return vTotalBill;
    }

    private boolean isFirstSlab(int pSlabIndex){
        if(pSlabIndex == 0
                || Variables.getSlabs().get(pSlabIndex).getSlabStart().equals("0"))
            return true;

        return false;
    }
}
