package com.protocol.entity;

/**
 * @ClassName CmdMsg
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-26 10:11
 * @Version 1.0
 */
public class CmdMsg {

    private String serviceId;
    private String deviceId;
    private String password;
    private String msg;

    public CmdMsg() {
    }

    public CmdMsg(String serviceId, String deviceId, String password, String msg) {
        this.serviceId = serviceId;
        this.deviceId = deviceId;
        this.password = password;
        this.msg = msg;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CmdMsg{" +
                "serviceId='" + serviceId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", password='" + password + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
