package com.protocol;

import com.common.Conf;
import org.springframework.stereotype.Component;

/**
 * @ClassName InitAccWaterPrams
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-16 15:38
 * @Version 1.0
 */
@Component
public class InitAccWater {

    public static AlarmParams alarmParams = new AlarmParams();

    public InitAccWater() {
        try {
            Conf conf = new Conf("alarmParams.properties");
            alarmParams.setVolume(conf.getKeyValueAsString("volume","N"));
            alarmParams.setVoiceType(conf.getKeyValueAsInteger("voiceType",1));
            alarmParams.setWaterDnIncrement(conf.getKeyValueAsInteger("waterDnIncrement",3));
            alarmParams.setWaterUpIncrement(conf.getKeyValueAsInteger("waterUpIncrement",5));

        } catch (Exception e) {
            System.out.println(e.toString());
        }





    }
}
