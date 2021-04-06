package net.octyl.jmh;

import java.io.Serializable;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.openjdk.jmh.infra.Blackhole;

public class BlackHoleAppender extends AbstractAppender {
    private final Blackhole blackhole;

    public BlackHoleAppender(String name,
                             Filter filter,
                             Layout<? extends Serializable> layout,
                             boolean ignoreExceptions,
                             Blackhole blackhole) {
        super(name, filter, layout, ignoreExceptions, Property.EMPTY_ARRAY);
        this.blackhole = blackhole;
    }

    @Override
    public void append(LogEvent event) {
        // Incur the cost of evaluating the event
        final byte[] bytes = getLayout().toByteArray(event);
        if (bytes != null && bytes.length > 0) {
            blackhole.consume(bytes);
        }
    }
}
