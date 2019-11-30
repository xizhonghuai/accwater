package com.protocol;

import com.common.SpringBeanUtils;
import com.mina.MinaSys;
import com.protocol.dbaccess.DataServer;
import com.protocol.entity.AlarmTel;
import com.protocol.entity.DevSite;
import com.protocol.entity.Device;
import com.sun.javafx.iio.ios.IosImageLoaderFactory;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @ClassName WaterProcess
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-12 14:50
 * @Version 1.0
 */

@Component("waterProcess")
public class WaterProcess {

    @Autowired
    private DataServer dataServer;

    @Autowired
    private SMessage sMessage;

    private Logger logger = Logger.getLogger(WaterProcess.class);




    public void controlMessage(IoSession session, String recMsg) throws Exception {

        System.out.println( this.getClass().getName()+ Thread.currentThread().getName());

        String msg = recMsg;

        String currentId = (String) session.getAttribute("regId", null);

        Map<String, String> cmd = new ConcurrentHashMap<String, String>();
        String s = msg.replace("(", "~");
        s = s.replace(")", "~");
        String msgArray[] = s.split("~");

        for (int i = 0; i < msgArray.length; i++) {

            if (msgArray[i].length() > 0) {

                cmd = getCMD(msgArray[i]);

                String cmdType = cmd.get("CmdType");

                /*报文解析开始*/

                if ("s".equals(cmdType)) {
                    String newId = cmd.get("NewId");
                    if (currentId == null || !newId.equals(currentId)) {
                        IoSession oldSession = MinaSys.regIdToSession(session.getService().getManagedSessions(),newId);

                        if (oldSession !=null){
                            oldSession.close(true);
                        }
                        session.write("(s)");

                        session.setAttribute("regId", newId);
                        logger.info(
                                "设备" + newId + session.getRemoteAddress() + "-----------------------------------注册");
                        // 绑定相关参数至session
                        if (dataServer.getDevSite(new DevSite(newId)).size() <= 0) {
                            //系统中无站点，自动添加站点

                            dataServer.insertDevSite(new DevSite(newId));
                            dataServer.insertRealTimeData(new Device(newId));//插入值守数据表

                        }
                        session.setAttribute("devSiteList", dataServer.getDevSite(new DevSite(newId)));//绑定站点基础信息
                        session.setAttribute("alarmTelList", dataServer.getAlarmTel(new AlarmTel(newId)));//绑定报警电话
                    }
                } else if ("p".equals(cmdType)) {

                    if (currentId != null) {
                        session.write("(p)");
                        logger.info("设备：" + currentId + "心跳------" + cmd.toString());

                        if ("RTU".equals(cmd.get("DeviceType"))) {

                            List<DevSite> devSiteList = (List<DevSite>) session.getAttribute("devSiteList", new ArrayList<DevSite>());

                            /* 数据入库*/
                            if (devSiteList.size() > 0) {
                                DevSite devSite = devSiteList.get(0);
                                Integer waterValue = Integer.parseInt(cmd.getOrDefault("WaterValue", "0")) - devSite.getCorrected_parameter();
                                Device device = new Device(currentId);
                                device.setSite_id(devSite.getId());
                                device.setWater_value(waterValue);
                                device.setWater_sensor_status(Integer.parseInt(cmd.getOrDefault("WaterSensorStatus", "1")));
                                device.setSiren1_sensor_status(Integer.parseInt(cmd.getOrDefault("Siren1SensorStatus", "1")));
                                device.setSiren2_sensor_status(Integer.parseInt(cmd.getOrDefault("Siren2SensorStatus", "1")));
                                device.setSiren3_sensor_status(Integer.parseInt(cmd.getOrDefault("Siren3SensorStatus", "1")));
                                device.setSiren4_sensor_status(Integer.parseInt(cmd.getOrDefault("Siren4SensorStatus", "1")));
                                device.setSiren_status(0);
                                if (waterValue > devSite.getPrewarning_max_value()) {
                                    device.setSiren_status(1);
                                }

//
                                dataServer.updateRealTimeData(device); //更新值守数据
                                dataServer.insertHistory(device);//插入数据


//                                if (devSite.getIs_work() == 1 ){ //判断时间是否启用
//                                    Date currentDate = new Date();
//                                    if ((currentDate.compareTo(devSite.getWork_begintime()) == 1) && (currentDate.compareTo(devSite.getWork_begintime()) == -1)){
//                                        return;
//                                    }
//                                }


                                /* 报警*/

                                if (waterValue > devSite.getPrewarning_max_value()) {
                                    String callCmd = "(w_1_" + InitAccWater.alarmParams.getVoiceType() + "_" + InitAccWater.alarmParams.getVolume() + ")";
                                    session.write(callCmd);
                                    /*存储报警器状态*/
                                    session.setAttribute("oldAlarmStatus", "1");

                                } else if (waterValue < (devSite.getPrewarning_max_value() - InitAccWater.alarmParams.getWaterDnIncrement())) {
                                    session.write("(w_0_0_0)");
                                    /*存储报警器状态*/
                                    session.setAttribute("oldAlarmStatus", "0");
                                } else {
                                    String oldAlarmStatus = (String) session.getAttribute("oldAlarmStatus", null);
                                    if (oldAlarmStatus != null) {
                                        String callCmd = "(w_" + oldAlarmStatus + "_" + InitAccWater.alarmParams.getVoiceType() + "_" + InitAccWater.alarmParams.getVolume() + ")";
                                        session.write(callCmd);
                                    }
                                }


                                /* 短信预警*/

                                if (waterValue >= devSite.getPrewarning_max_value()) {
                                    if (session.getAttribute("smsFlag", "1").equals("1")) {
                                        //sendsms  //发送短信

                                        session.setAttribute("oldWaterValue", waterValue);//记录短信预警水位

                                        try {
                                            sMessage.sendShortMessage(devSite, (List<AlarmTel>) session.getAttribute("alarmTelList", new ArrayList<AlarmTel>()), waterValue,dataServer);

                                        } catch (Exception e) {

                                            logger.info("短信发送错误:" + e.toString());

                                        }
                                        session.setAttribute("smsFlag", "0");
                                    }

                                    Integer oldWaterValue = (Integer) session.getAttribute("oldWaterValue", waterValue);
                                    Integer incrementWaterValue = waterValue - oldWaterValue;
                                    //水位上涨发送短信
                                    if (incrementWaterValue > InitAccWater.alarmParams.getWaterUpIncrement()) {
                                        try {

                                            session.setAttribute("oldWaterValue", waterValue);//记录短信预警水位

                                            SMessage sMessage = (SMessage) (SpringBeanUtils.getBean("sMessage"));
                                            sMessage.sendShortMessage(devSite, (List<AlarmTel>) session.getAttribute("alarmTelList", new ArrayList<AlarmTel>()), waterValue,dataServer);

                                        } catch (Exception e) {
                                            logger.info("短信发送错误:" + e.toString());
                                        }
                                    }

                                } else {
                                    session.setAttribute("smsFlag", "1");
                                }
                            }
                        }
                    }

                } else {
                    // 其他数据处理
                    logger.info("设备：" + currentId + "数据------" + cmd.toString());
//                    session.write("(" + cmd.get("CmdType") + ")");
                }
            }
        }
    }

    private Map<String, String> getCMD(String msg) {

        Map<String, String> CMD = new ConcurrentHashMap<String, String>();
        String arr[] = msg.split("_");
        String cmdtype = arr[0];
        if (cmdtype.equals("m")) {
            cmdtype = "p";
        }

        int value = cmdtype.charAt(0);
        switch (value) {

            case 0x73: {
                CMD.put("CmdType", "s");
                CMD.put("NewId", arr[1]);
            }

            break;
            case 0x70: {
                CMD.put("CmdType", "p");
                CMD.put("DeviceType", arr[1]);

                if (arr[1].equals("RTU") && arr.length == 9) {

                    CMD.put("WaterValue", arr[2]);
                    CMD.put("WaterSensorStatus", arr[3]);
                    CMD.put("AlarmStatus", arr[4]);
                    CMD.put("Siren1SensorStatus", arr[5]);
                    CMD.put("Siren2SensorStatus", arr[6]);
                    CMD.put("Siren3SensorStatus", arr[7]);
                    CMD.put("Siren4SensorStatus", arr[8]);

                }
            }

            break;

            default: {
                CMD.put("CmdType", cmdtype);
                for (int i = 1; i < arr.length; i++) {
                    CMD.put(String.valueOf(i), arr[i]);
                }
            }
            break;
        }
        return CMD;
    }


}
