package edu.omur.msworld.corelib.loggingappender;

import edu.omur.msworld.corelib.model.Constants;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.util.ReadOnlyStringMap;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LogDataBuilder {
    public static LogData create(LogEvent logEvent) {
        LogData logData = new LogData();
        logData.setServiceName(getAppName(logEvent));
        logData.setLevel(logEvent.getLevel().toString());
        logData.setLogger(logEvent.getLoggerName());
        logData.setMessage(logEvent.getMessage().getFormattedMessage());
        logData.setTimestamp(getGmtTimestampAsString());
        return logData;
    }

    private static String getAppName(LogEvent logEvent) {
        String appName = null;
        ReadOnlyStringMap logContext = logEvent.getContextData();
        if (logContext != null) {
            appName = logContext.toMap().get(Constants.KEY_APPLICATION_NAME);
        }
        return appName;
    }

    private static String getGmtTimestampAsString() {
        ZonedDateTime time = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Z"));
        return time.toString();
    }
}
