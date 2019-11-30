package com.controller;


import com.common.SessionsProperty;
import com.common.SpringBeanUtils;
import com.common.TopiotService;
import com.protocol.dbaccess.DataServer;
import com.protocol.entity.DevSite;
import com.protocol.entity.Device;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-08 10:55
 * @Version 1.0
 */

@Controller
public class DataController {

    @Autowired
    private DataServer dataServer;

    @RequestMapping("/getRealTimeData")
    @ResponseBody
    public List<Device> getRealTimeData(Device device) {

        return dataServer.getRealTimeData(device);
    }


    @RequestMapping("/getHistory")
    @ResponseBody
    public List<Device> getHistory(Device device) {

        return dataServer.getHistory(device);
    }






    @RequestMapping("/getSessionsProperty")
    @ResponseBody
    public List<SessionsProperty> getSessionsProperty(@RequestParam(value = "serviceId", required = false) String serviceId) {

        try {

            if (serviceId == null) {

                return null;
            }

            TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);

            Map<Long, IoSession> sessionsList = ts.getManagedSessions();

            List<SessionsProperty> sessionsPropertyList = new ArrayList<SessionsProperty>();

            for (Map.Entry<Long, IoSession> entry : sessionsList.entrySet()) {

                SessionsProperty sessionsProperty = new SessionsProperty();

                IoSession session = entry.getValue();

                if ((String) session.getAttribute("regId", null) == null) {
                    continue;
                }
                sessionsProperty.setRegId((String) session.getAttribute("regId", null));
                sessionsProperty.setRegisterTime(new Date(session.getCreationTime()));
                sessionsProperty.setActivityTime(new Date(session.getLastReadTime()));
                sessionsProperty.setAddress(session.getRemoteAddress().toString());
                sessionsPropertyList.add(sessionsProperty);
            }
            return sessionsPropertyList;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }





}
