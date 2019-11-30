package com.protocol.dbaccess;

import com.protocol.entity.AlarmTel;
import com.protocol.entity.DevSite;
import com.protocol.entity.Device;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName DevDataDao
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-15 13:55
 * @Version 1.0
 */
@Repository
public interface DatabaseDao {

    List<DevSite> getDevSite(DevSite devSite);
    List<AlarmTel> getAlarmTel(AlarmTel alarmTel);

    List<Device> getRealTimeData(Device device);
    List<Device> getHistory(Device device);

    void insertRealTimeData(Device device);
    void updateRealTimeData(Device device);

    void insertDevSite(DevSite devSite);
    void insertHistory(Device device);
    void insertWaringRecord(AlarmTel alarmTel);




}
