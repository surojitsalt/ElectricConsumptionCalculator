package com.example.electricconsumptioncalculator.model;

public class ElectricitySlab extends BaseModel {
    private String slabStart;
    private String slabEnd;
    private Integer slabRate;

    public String getSlabStart() {
        return slabStart;
    }

    public void setSlabStart(String slabStart) {
        this.slabStart = slabStart;
    }

    public String getSlabEnd() {
        return slabEnd;
    }

    public void setSlabEnd(String slabEnd) {
        this.slabEnd = slabEnd;
    }

    public Integer getSlabRate() {
        return slabRate;
    }

    public void setSlabRate(Integer slabRate) {
        this.slabRate = slabRate;
    }

    public ElectricitySlab(String pSlabStart, String pSlabEnd, Integer pSlabRate){
        this.slabStart = pSlabStart;
        this.slabEnd = pSlabEnd;
        this.slabRate = pSlabRate;
    }
}
