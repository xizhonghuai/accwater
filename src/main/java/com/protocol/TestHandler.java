package com.protocol;


import com.common.Methods;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @ClassName WaterHandler
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-12 14:34
 * @Version 1.0
 */
public class TestHandler extends IoHandlerAdapter {
    private Logger logger = Logger.getLogger(TestHandler.class);

    public void messageReceived(IoSession session, Object message) {

        String msg = message.toString();
        logger.info("收到数据(ASC)：" + msg);
        logger.info("收到数据(HEX)：" + Methods.toHexString(msg));

        session.setAttribute("regId","##");

        session.write(msg);


    }


    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        // Empty handler

        session.close();
    }

    public void sessionOpened(IoSession session) throws Exception {
        // Empty handler
        logger.info(session.getRemoteAddress() + "连接");
    }

    public void sessionClosed(IoSession session) throws Exception {
        // Empty handler
    }

}
