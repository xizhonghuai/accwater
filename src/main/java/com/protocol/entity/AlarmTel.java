package com.protocol.entity;

import java.util.Date;

/**
 * @ClassName AlarmTel
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-15 14:30
 * @Version 1.0
 */
public class AlarmTel {

    private String alarmcalluser_name;
    private String alarmcalluser_phone;
    private String dept_name;
    private String code;
    private String content;
    private Integer site_id;
    private Integer alarmcall_id;
    private Integer status;
    private Date created;




    public AlarmTel() {
    }

    public AlarmTel(String code) {
        this.code = code;
    }

    public AlarmTel(String alarmcalluser_name, String alarmcalluser_phone, String dept_name, String code, String content, Integer site_id, Integer alarmcall_id, Integer status, Date created) {
        this.alarmcalluser_name = alarmcalluser_name;
        this.alarmcalluser_phone = alarmcalluser_phone;
        this.dept_name = dept_name;
        this.code = code;
        this.content = content;
        this.site_id = site_id;
        this.alarmcall_id = alarmcall_id;
        this.status = status;
        this.created = created;
    }


    public String getAlarmcalluser_name() {
        return alarmcalluser_name;
    }

    public void setAlarmcalluser_name(String alarmcalluser_name) {
        this.alarmcalluser_name = alarmcalluser_name;
    }

    public String getAlarmcalluser_phone() {
        return alarmcalluser_phone;
    }

    public void setAlarmcalluser_phone(String alarmcalluser_phone) {
        this.alarmcalluser_phone = alarmcalluser_phone;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    public Integer getAlarmcall_id() {
        return alarmcall_id;
    }

    public void setAlarmcall_id(Integer alarmcall_id) {
        this.alarmcall_id = alarmcall_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }



    @Override
    public String toString() {
        return "AlarmTel{" +
                "alarmcalluser_name='" + alarmcalluser_name + '\'' +
                ", alarmcalluser_phone='" + alarmcalluser_phone + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", code='" + code + '\'' +
                ", content='" + content + '\'' +
                ", site_id=" + site_id +
                ", alarmcall_id=" + alarmcall_id +
                ", status=" + status +
                ", created=" + created +
                '}';
    }
}
