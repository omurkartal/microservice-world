package edu.omur.msworld.corelib.loggingappender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.omur.msworld.corelib.model.Constants;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(name = "CustomLogAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class CustomLogAppender extends AbstractAppender {
    private static final Gson gson;
    private static final GsonBuilder gsonBuilder;

    static {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    protected CustomLogAppender(String name, Filter filter) {
        super(name, filter, null, true, Property.EMPTY_ARRAY);
    }

    @PluginFactory
    public static CustomLogAppender createAppender(@PluginAttribute("name") String name, @PluginElement("Filter") final Filter filter) {
        return new CustomLogAppender(name, filter);
    }

    @Override
    public void append(LogEvent logEvent) {
        try {
            if ((logEvent.getContextData() != null)
                    && (logEvent.getLoggerName() != null)
                    && (logEvent.getContextData().toMap().containsKey(Constants.KEY_APPLICATION_NAME))
                    && (logEvent.getLoggerName().startsWith("edu.omur"))) {
                LogData logData = LogDataBuilder.create(logEvent);
                //TODO: send log data to some other component such as common-logging-service, logstash, Elasticsearch or put it into to Kafka, etc.
                System.out.println(gson.toJson(logData));
            }
        } catch (Exception ex) {
            LOGGER.error("Could not write log event as JSON", ex);
        }
    }
}