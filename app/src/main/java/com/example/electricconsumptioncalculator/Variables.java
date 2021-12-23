package com.example.electricconsumptioncalculator;

import com.example.electricconsumptioncalculator.model.ElectricitySlab;

import java.util.ArrayList;

public class Variables {
    private static ArrayList<ElectricitySlab> slabs = new ArrayList<>();

    public static ArrayList<ElectricitySlab> getSlabs() {
        return slabs;
    }

    public static void setSlabs(ArrayList<ElectricitySlab> slabs) {
        Variables.slabs = slabs;
    }
}
