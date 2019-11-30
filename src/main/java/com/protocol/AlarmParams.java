package com.protocol;

/**
 * @ClassName AlarmParams
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-15 16:28
 * @Version 1.0
 */


public class AlarmParams {

    private String volume;
    private Integer voiceType;
    private Integer waterDnIncrement; //水位下降增量
    private Integer waterUpIncrement;

    public AlarmParams() {
    }

    public AlarmParams(String volume, Integer voiceType, Integer waterDnIncrement, Integer waterUpIncrement) {
        this.volume = volume;
        this.voiceType = voiceType;
        this.waterDnIncrement = waterDnIncrement;
        this.waterUpIncrement = waterUpIncrement;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Integer getVoiceType() {
        return voiceType;
    }

    public void setVoiceType(Integer voiceType) {
        this.voiceType = voiceType;
    }

    public Integer getWaterDnIncrement() {
        return waterDnIncrement;
    }

    public void setWaterDnIncrement(Integer waterDnIncrement) {
        this.waterDnIncrement = waterDnIncrement;
    }

    public Integer getWaterUpIncrement() {
        return waterUpIncrement;
    }

    public void setWaterUpIncrement(Integer waterUpIncrement) {
        this.waterUpIncrement = waterUpIncrement;
    }

    @Override
    public String toString() {
        return "AlarmParams{" +
                "volume=" + volume +
                ", voiceType=" + voiceType +
                ", waterDnIncrement=" + waterDnIncrement +
                ", waterUpIncrement=" + waterUpIncrement +
                '}';
    }
}
