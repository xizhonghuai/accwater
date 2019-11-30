package com.protocol.entity;

import java.util.Date;

/**
 * @ClassName Device
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-17 10:56
 * @Version 1.0
 */
public class Device {

    private Integer site_id;
    private String site_code;
    private String name;
    private Integer water_value;
    private Integer water_sensor_status;
    private Integer siren_status;
    private Integer siren1_sensor_status;
    private Integer siren2_sensor_status;
    private Integer siren3_sensor_status;
    private Integer siren4_sensor_status;
    private Date created;
    private Integer netStatus;


    public Device() {
    }

    public Device(String site_code) {
        this.site_code = site_code;
        this.created = new Date();
    }

    public Device(Integer site_id, String site_code, String name, Integer water_value, Integer water_sensor_status, Integer siren_status, Integer siren1_sensor_status, Integer siren2_sensor_status, Integer siren3_sensor_status, Integer siren4_sensor_status, Date created, Integer netStatus) {
        this.site_id = site_id;
        this.site_code = site_code;
        this.name = name;
        this.water_value = water_value;
        this.water_sensor_status = water_sensor_status;
        this.siren_status = siren_status;
        this.siren1_sensor_status = siren1_sensor_status;
        this.siren2_sensor_status = siren2_sensor_status;
        this.siren3_sensor_status = siren3_sensor_status;
        this.siren4_sensor_status = siren4_sensor_status;
        this.created = created;
        this.netStatus = netStatus;
    }

    public String getSite_code() {
        return site_code;
    }

    public void setSite_code(String site_code) {
        this.site_code = site_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWater_value() {
        return water_value;
    }

    public void setWater_value(Integer water_value) {
        this.water_value = water_value;
    }

    public Integer getWater_sensor_status() {
        return water_sensor_status;
    }

    public void setWater_sensor_status(Integer water_sensor_status) {
        this.water_sensor_status = water_sensor_status;
    }

    public Integer getSiren_status() {
        return siren_status;
    }

    public void setSiren_status(Integer siren_status) {
        this.siren_status = siren_status;
    }

    public Integer getSiren1_sensor_status() {
        return siren1_sensor_status;
    }

    public void setSiren1_sensor_status(Integer siren1_sensor_status) {
        this.siren1_sensor_status = siren1_sensor_status;
    }

    public Integer getSiren2_sensor_status() {
        return siren2_sensor_status;
    }

    public void setSiren2_sensor_status(Integer siren2_sensor_status) {
        this.siren2_sensor_status = siren2_sensor_status;
    }

    public Integer getSiren3_sensor_status() {
        return siren3_sensor_status;
    }

    public void setSiren3_sensor_status(Integer siren3_sensor_status) {
        this.siren3_sensor_status = siren3_sensor_status;
    }

    public Integer getSiren4_sensor_status() {
        return siren4_sensor_status;
    }

    public void setSiren4_sensor_status(Integer siren4_sensor_status) {
        this.siren4_sensor_status = siren4_sensor_status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getNetStatus() {
        return netStatus;
    }

    public void setNetStatus(Integer netStatus) {
        this.netStatus = netStatus;
    }


    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    @Override
    public String toString() {
        return "Device{" +
                "site_id=" + site_id +
                ", site_code='" + site_code + '\'' +
                ", name='" + name + '\'' +
                ", water_value=" + water_value +
                ", water_sensor_status=" + water_sensor_status +
                ", siren_status=" + siren_status +
                ", siren1_sensor_status=" + siren1_sensor_status +
                ", siren2_sensor_status=" + siren2_sensor_status +
                ", siren3_sensor_status=" + siren3_sensor_status +
                ", siren4_sensor_status=" + siren4_sensor_status +
                ", created=" + created +
                ", netStatus=" + netStatus +
                '}';
    }
}

