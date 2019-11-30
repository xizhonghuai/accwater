package com.protocol.dbaccess;

import com.protocol.entity.AlarmTel;
import com.protocol.entity.DevSite;
import com.protocol.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DevDataServer
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-15 13:56
 * @Version 1.0
 */
@Service
public class DataServer {

    @Autowired
    private DatabaseDao databaseDao;


    public List<DevSite> getDevSite(DevSite devSite) {
        return databaseDao.getDevSite(devSite);
    }


    public List<AlarmTel> getAlarmTel(AlarmTel alarmTel) {
        return databaseDao.getAlarmTel(alarmTel);
    }

    public void insertRealTimeData(Device device) {
        databaseDao.insertRealTimeData(device);
    }

    public void updateRealTimeData(Device device) {
        databaseDao.updateRealTimeData(device);
    }

    public List<Device> getRealTimeData(Device device) {

        return databaseDao.getRealTimeData(device);
    }

    public List<Device> getHistory(Device device){
        return databaseDao.getHistory(device);

    }

    public  void insertDevSite(DevSite devSite){
        databaseDao.insertDevSite(devSite);
    }

    public   void insertHistory(Device device){
        databaseDao.insertHistory(device);
    }

    public void insertWaringRecord(AlarmTel alarmTel){
        databaseDao.insertWaringRecord(alarmTel);
    }


}
