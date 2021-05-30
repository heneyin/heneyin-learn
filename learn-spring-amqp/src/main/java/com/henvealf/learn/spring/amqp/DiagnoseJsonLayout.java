package com.henvealf.learn.spring.amqp;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import com.alibaba.fastjson.JSONObject;

/**
 * @author hongliang.yin/Henvealf
 * @date 2019-08-23
 */
public class DiagnoseJsonLayout extends LayoutBase<ILoggingEvent> {

    private static final String LOG_TYPE= "logType";
    private String logType;

    public DiagnoseJsonLayout() {
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Override
    public String doLayout(ILoggingEvent iLoggingEvent) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("logType", logType);
        jsonObject.put("logLevel", iLoggingEvent.getLevel().levelStr.toLowerCase());

        String message = iLoggingEvent.getFormattedMessage();
        jsonObject.put("logDesc", message);
        jsonObject.put("logTime", iLoggingEvent.getTimeStamp());
        String logDetail = generateLogDetail(iLoggingEvent);
        jsonObject.put("logDetail", logDetail);
        return jsonObject.toJSONString() + "\n";
    }

    public String generateLogDetail(ILoggingEvent iLoggingEvent) {
        IThrowableProxy throwableProxy = iLoggingEvent.getThrowableProxy();
        String formattedMessage = iLoggingEvent.getFormattedMessage();
        if (throwableProxy == null
                || throwableProxy.getStackTraceElementProxyArray() == null
                || throwableProxy.getStackTraceElementProxyArray().length == 0) {
            return formattedMessage;
        } else {
            return fullDump(iLoggingEvent);
        }
    }

    public String fullDump(ILoggingEvent iLoggingEvent) {
        StringBuilder builder = new StringBuilder();
        fullDump(iLoggingEvent.getThrowableProxy() , builder, "");
        return builder.toString();
    }

    public void fullDump(IThrowableProxy proxy, StringBuilder builder, String prefix) {
        if (proxy == null) return;
        builder.append(prefix)
                .append(proxy.getClassName())
                .append(": ")
                .append(proxy.getMessage()).append("\n");
        for (StackTraceElementProxy step : proxy.getStackTraceElementProxyArray()) {
            String string = step.toString();
            builder.append(CoreConstants.TAB).append(string);
            ThrowableProxyUtil.subjoinPackagingData(builder, step);
            builder.append(CoreConstants.LINE_SEPARATOR);
        }
        IThrowableProxy cause = proxy.getCause();
        fullDump(cause, builder, "Caused By: ");
    }
}
