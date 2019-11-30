package com.protocol.entity;

import java.util.Date;

/**
 * @ClassName DevSite
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-15 11:14
 * @Version 1.0
 */
public class DevSite {

    private Integer id;
    private String code;
    private String name;
    private String province;
    private String municipality;
    private String county;
    private String street;
    private String addr;
    private String longitude;
    private String latitude;
    private String management;
    private String leader;
    private String leader_phone;
    private Integer corrected_parameter;
    private Integer prewarning_max_value;
    private String work_begintime;
    private String work_endtime;
    private Integer is_work;

    public DevSite(String code) {
        this.code = code;
    }

    public DevSite() {
    }

    public DevSite(Integer id, String code, String name, String province, String municipality, String county, String street, String addr, String longitude, String latitude, String management, String leader, String leader_phone, Integer corrected_parameter, Integer prewarning_max_value, String work_begintime, String work_endtime, Integer is_work) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.province = province;
        this.municipality = municipality;
        this.county = county;
        this.street = street;
        this.addr = addr;
        this.longitude = longitude;
        this.latitude = latitude;
        this.management = management;
        this.leader = leader;
        this.leader_phone = leader_phone;
        this.corrected_parameter = corrected_parameter;
        this.prewarning_max_value = prewarning_max_value;
        this.work_begintime = work_begintime;
        this.work_endtime = work_endtime;
        this.is_work = is_work;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeader_phone() {
        return leader_phone;
    }

    public void setLeader_phone(String leader_phone) {
        this.leader_phone = leader_phone;
    }

    public Integer getCorrected_parameter() {
        return corrected_parameter;
    }

    public void setCorrected_parameter(Integer corrected_parameter) {
        this.corrected_parameter = corrected_parameter;
    }

    public Integer getPrewarning_max_value() {
        return prewarning_max_value;
    }

    public void setPrewarning_max_value(Integer prewarning_max_value) {
        this.prewarning_max_value = prewarning_max_value;
    }

    public String getWork_begintime() {
        return work_begintime;
    }

    public void setWork_begintime(String work_begintime) {
        this.work_begintime = work_begintime;
    }

    public String getWork_endtime() {
        return work_endtime;
    }

    public void setWork_endtime(String work_endtime) {
        this.work_endtime = work_endtime;
    }

    public Integer getIs_work() {
        return is_work;
    }

    public void setIs_work(Integer is_work) {
        this.is_work = is_work;
    }

    @Override
    public String toString() {
        return "DevSite{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", municipality='" + municipality + '\'' +
                ", county='" + county + '\'' +
                ", street='" + street + '\'' +
                ", addr='" + addr + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", management='" + management + '\'' +
                ", leader='" + leader + '\'' +
                ", leader_phone='" + leader_phone + '\'' +
                ", corrected_parameter=" + corrected_parameter +
                ", prewarning_max_value=" + prewarning_max_value +
                ", work_begintime='" + work_begintime + '\'' +
                ", work_endtime='" + work_endtime + '\'' +
                ", is_work=" + is_work +
                '}';
    }
}
