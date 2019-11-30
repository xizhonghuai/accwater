package com.controller;

import com.common.*;
import com.mina.MinaSys;
import com.protocol.AlarmParams;
import com.protocol.InitAccWater;
import com.protocol.entity.CmdMsg;
import com.protocol.entity.Device;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OpenApi
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-25 16:55
 * @Version 1.0
 */
@Controller
@RequestMapping("/data.api")
public class OpenApi {

    private Logger logger = Logger.getLogger(OpenApi.class);

    @RequestMapping("/getParams")
    @ResponseBody
    public Response getParams() {

        return new Response(InitAccWater.alarmParams);
    }


    @RequestMapping("/getOnlineDev")
    @ResponseBody
    public Response getOnlineDev(@RequestParam(value = "serviceId", required = false) String serviceId) {

        if (serviceId == null) {

            return new Response(100, "Parameters cannot be null");
        }

        TopiotService ts = null;

        try {
            ts = (TopiotService) SpringBeanUtils.getBean(serviceId);
        } catch (Exception e) {

            new Response(100, "No such service");
        }

        if (!ts.getStatus()) {
            return new Response(100, serviceId + " Server Stop state");
        }


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
        return new Response(sessionsPropertyList);
    }

    @RequestMapping(value = "/updatePrams", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Response updatePrams(AlarmParams alarmParams) {

        try {
            Conf conf = new Conf("alarmParams.properties");
            if (alarmParams.getVolume() != null) {
                conf.setKeyValue("volume", alarmParams.getVolume());
            }
            if (alarmParams.getVoiceType() != null) {
                conf.setKeyValue("voiceType", alarmParams.getVoiceType());
            }
            if (alarmParams.getWaterUpIncrement() != null) {
                conf.setKeyValue("waterUpIncrement", alarmParams.getWaterUpIncrement());
            }
            if (alarmParams.getWaterDnIncrement() != null) {
                conf.setKeyValue("waterDnIncrement", alarmParams.getWaterDnIncrement());
            }

            conf.subm();


            InitAccWater.alarmParams.setVolume(conf.getKeyValueAsString("volume", "N"));
            InitAccWater.alarmParams.setVoiceType(conf.getKeyValueAsInteger("voiceType", 2));
            InitAccWater.alarmParams.setWaterDnIncrement(conf.getKeyValueAsInteger("waterDnIncrement", 3));
            InitAccWater.alarmParams.setWaterUpIncrement(conf.getKeyValueAsInteger("waterUpIncrement", 5));

            return new Response();

        } catch (Exception e) {
            return new Response(100, e.toString());
        }
    }


    @RequestMapping(value = "/sendMsg", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Response sendMsg(CmdMsg cmdMsg) {




        if ((cmdMsg.getDeviceId() == null) || (cmdMsg.getServiceId() == null) || (cmdMsg.getMsg() == null)) {

            return new Response(100, "Parameters cannot be null");
        }

        TopiotService ts = null;

        try {
            ts = (TopiotService) SpringBeanUtils.getBean(cmdMsg.getServiceId());
        } catch (Exception e) {

            new Response(100, "No such service");
        }

        if (!ts.getStatus()) {
            return new Response(100, cmdMsg.getServiceId() + " Server Stop state");
        }


        if (cmdMsg.getDeviceId().equals("****")){


            MinaSys.SendMsg(ts.getManagedSessions(),cmdMsg.getMsg());
        }




        IoSession eqSSession = MinaSys.regIdToSession(ts.getManagedSessions(), cmdMsg.getDeviceId());

        if (eqSSession == null) {
            return new Response(100, cmdMsg.getDeviceId() + " Off-line status of equipment");
        }


        eqSSession.write(cmdMsg.getMsg());


        return new Response();

    }


    @RequestMapping(value = "/disconnect", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Response disconnect(CmdMsg cmdMsg) {

        if ((cmdMsg.getDeviceId() == null) || (cmdMsg.getServiceId() == null) ) {

            return new Response(100, "Parameters cannot be null");
        }

        TopiotService ts = null;

        try {
            ts = (TopiotService) SpringBeanUtils.getBean(cmdMsg.getServiceId());
        } catch (Exception e) {

            new Response(100, "No such service");
        }

        if (!ts.getStatus()) {
            return new Response(100, cmdMsg.getServiceId() + " Server Stop state");
        }

        IoSession eqSSession = MinaSys.regIdToSession(ts.getManagedSessions(), cmdMsg.getDeviceId());

        if (eqSSession == null) {
            return new Response(100, cmdMsg.getDeviceId() + " Off-line status of equipment");
        }


        eqSSession.close(true);


        return new Response();


    }


}



