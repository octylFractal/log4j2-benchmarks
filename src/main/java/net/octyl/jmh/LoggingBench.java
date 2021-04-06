package net.octyl.jmh;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class LoggingBench {

    Logger logger;

    @Param({"P1"})
    private static String aShortString;
    @Param({"This is a much larger string, so it is potentially less likely to be JIT-ed in interesting ways." +
        " It should probably stay on the heap, but there's really no way to make sure!"})
    private static String aLongString;
    @Param({"42"})
    private static int anInt;
    @Param({"00.42f"})
    private static float aFloat;

    @Setup
    public void setUp(Blackhole bh) {
        var logger = LoggerContext.getContext().getLogger("LoggingBench");
        var appender = new BlackHoleAppender(
            "BLACKHOLE",
            null,
            PatternLayout.createDefaultLayout(),
            false,
            bh
        );
        appender.start();
        // clear appenders
        logger.getAppenders().values().forEach(logger::removeAppender);
        logger.addAppender(appender);
        logger.setLevel(Level.INFO);
        this.logger = logger;
    }
    // All benchmarks are of the form `logStyle_content_enabledFlag`,
    // logStyle can be:
    //   - concat: concatenated and passed to a (String) method
    //   - concatGuarded: `concat` wrapped in an `if`-check for the level
    //   - implFormat: args passed and formatted by the logger implementation
    //   - lambda: concatenated inside a lambda

    @Benchmark
    public String concat_disabled_shortString() {
        var modified = aShortString + "!";
        logger.debug("My constant: " + modified);
        return modified;
    }

    @Benchmark
    public String concat_disabled_longString() {
        var modified = aLongString + "!";
        logger.debug("My constant: " + modified);
        return modified;
    }

    @Benchmark
    public int concat_disabled_int() {
        var modified = anInt + 10;
        logger.debug("My constant: " + modified);
        return modified;
    }

    @Benchmark
    public float concat_disabled_float() {
        var modified = aFloat + 0.14f;
        logger.debug("My constant: " + modified);
        return modified;
    }

    @Benchmark
    public String concat_enabled_shortString() {
        var modified = aShortString + "!";
        logger.info("My constant: " + modified);
        return modified;
    }

    @Benchmark
    public String concat_enabled_longString() {
        var modified = aLongString + "!";
        logger.info("My constant: " + modified);
        return modified;
    }

    @Benchmark
    public int concat_enabled_int() {
        var modified = anInt + 10;
        logger.info("My constant: " + modified);
        return modified;
    }

    @Benchmark
    public float concat_enabled_float() {
        var modified = aFloat + 0.14f;
        logger.info("My constant: " + modified);
        return modified;
    }

    @Benchmark
    public String concatGuarded_disabled_shortString() {
        var modified = aShortString + "!";
        if (logger.isDebugEnabled()) {
            logger.debug("My constant: " + modified);
        }
        return modified;
    }

    @Benchmark
    public String concatGuarded_disabled_longString() {
        var modified = aLongString + "!";
        if (logger.isDebugEnabled()) {
            logger.debug("My constant: " + modified);
        }
        return modified;
    }

    @Benchmark
    public int concatGuarded_disabled_int() {
        var modified = anInt + 10;
        if (logger.isDebugEnabled()) {
            logger.debug("My constant: " + modified);
        }
        return modified;
    }

    @Benchmark
    public float concatGuarded_disabled_float() {
        var modified = aFloat + 0.14f;
        if (logger.isDebugEnabled()) {
            logger.debug("My constant: " + modified);
        }
        return modified;
    }

    @Benchmark
    public String concatGuarded_enabled_shortString() {
        var modified = aShortString + "!";
        if (logger.isInfoEnabled()) {
            logger.info("My constant: " + modified);
        }
        return modified;
    }

    @Benchmark
    public String concatGuarded_enabled_longString() {
        var modified = aLongString + "!";
        if (logger.isInfoEnabled()) {
            logger.info("My constant: " + modified);
        }
        return modified;
    }

    @Benchmark
    public int concatGuarded_enabled_int() {
        var modified = anInt + 10;
        if (logger.isInfoEnabled()) {
            logger.info("My constant: " + modified);
        }
        return modified;
    }

    @Benchmark
    public float concatGuarded_enabled_float() {
        var modified = aFloat + 0.14f;
        if (logger.isInfoEnabled()) {
            logger.info("My constant: " + modified);
        }
        return modified;
    }

    @Benchmark
    public String implFormat_disabled_shortString() {
        var modified = aShortString + "!";
        logger.debug("My constant: {}", modified);
        return modified;
    }

    @Benchmark
    public String implFormat_disabled_longString() {
        var modified = aLongString + "!";
        logger.debug("My constant: {}", modified);
        return modified;
    }

    @Benchmark
    public int implFormat_disabled_int() {
        var modified = anInt + 10;
        logger.debug("My constant: {}", modified);
        return modified;
    }

    @Benchmark
    public float implFormat_disabled_float() {
        var modified = aFloat + 0.14f;
        logger.debug("My constant: {}", modified);
        return modified;
    }

    @Benchmark
    public String implFormat_enabled_shortString() {
        var modified = aShortString + "!";
        logger.info("My constant: {}", modified);
        return modified;
    }

    @Benchmark
    public String implFormat_enabled_longString() {
        var modified = aLongString + "!";
        logger.info("My constant: {}", modified);
        return modified;
    }

    @Benchmark
    public int implFormat_enabled_int() {
        var modified = anInt + 10;
        logger.info("My constant: {}", modified);
        return modified;
    }

    @Benchmark
    public float implFormat_enabled_float() {
        var modified = aFloat + 0.14f;
        logger.info("My constant: {}", modified);
        return modified;
    }

    @Benchmark
    public String lambda_disabled_shortString() {
        var modified = aShortString + "!";
        logger.debug(() -> "My constant: " + modified);
        return modified;
    }

    @Benchmark
    public String lambda_disabled_longString() {
        var modified = aLongString + "!";
        logger.debug(() -> "My constant: " + modified);
        return modified;
    }

    @Benchmark
    public int lambda_disabled_int() {
        var modified = anInt + 10;
        logger.debug(() -> "My constant: " + modified);
        return modified;
    }

    @Benchmark
    public float lambda_disabled_float() {
        var modified = aFloat + 0.14f;
        logger.debug(() -> "My constant: " + modified);
        return modified;
    }

    @Benchmark
    public String lambda_enabled_shortString() {
        var modified = aShortString + "!";
        logger.info(() -> "My constant: " + modified);
        return modified;
    }

    @Benchmark
    public String lambda_enabled_longString() {
        var modified = aLongString + "!";
        logger.info(() -> "My constant: " + modified);
        return modified;
    }

    @Benchmark
    public int lambda_enabled_int() {
        var modified = anInt + 10;
        logger.info(() -> "My constant: " + modified);
        return modified;
    }

    @Benchmark
    public float lambda_enabled_float() {
        var modified = aFloat + 0.14f;
        logger.info(() -> "My constant: " + modified);
        return modified;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(LoggingBench.class.getSimpleName())
            .addProfiler(GCProfiler.class)
            .build();

        new Runner(opt).run();
    }
}
