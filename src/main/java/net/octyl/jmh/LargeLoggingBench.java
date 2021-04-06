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
public class LargeLoggingBench {

    Logger logger;

    int param1;
    int param2;
    int param3;
    int param4;
    int param5;
    int param6;
    int param7;
    int param8;
    int param9;
    int param10;
    int param11;

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

        param1 = 100;
        param2 = 234;
        param3 = 5345;
        param4 = 23412;
        param5 = 5347;
        param6 = 9798;
        param7 = 5675;
        param8 = 1045640;
        param9 = 108980;
        param10 = 567100;
        param11 = 78347;
    }

    @Benchmark
    public int implFormat_disabled() {
        var mod1 = param1 + 10;
        var mod2 = param2 + 10;
        var mod3 = param3 + 10;
        var mod4 = param4 + 10;
        var mod5 = param5 + 10;
        var mod6 = param6 + 10;
        var mod7 = param7 + 10;
        var mod8 = param8 + 10;
        var mod9 = param9 + 10;
        var mod10 = param10 + 10;
        var mod11 = param11 + 10;
        logger.debug(
            "My constant: {} {} {} {} {} {} {} {} {} {} {}",
            mod1, mod2, mod3, mod4, mod5, mod6, mod7, mod8, mod9, mod10, mod11
        );
        return mod1 + mod2 + mod3 + mod4 + mod5 + mod6 + mod7 + mod8 + mod9 + mod10 + mod11;
    }

    @Benchmark
    public int implFormat_enabled() {
        var mod1 = param1 + 10;
        var mod2 = param2 + 10;
        var mod3 = param3 + 10;
        var mod4 = param4 + 10;
        var mod5 = param5 + 10;
        var mod6 = param6 + 10;
        var mod7 = param7 + 10;
        var mod8 = param8 + 10;
        var mod9 = param9 + 10;
        var mod10 = param10 + 10;
        var mod11 = param11 + 10;
        logger.info(
            "My constant: {} {} {} {} {} {} {} {} {} {} {}",
            mod1, mod2, mod3, mod4, mod5, mod6, mod7, mod8, mod9, mod10, mod11
        );
        return mod1 + mod2 + mod3 + mod4 + mod5 + mod6 + mod7 + mod8 + mod9 + mod10 + mod11;
    }

    @Benchmark
    public int lambda_disabled() {
        var mod1 = param1 + 10;
        var mod2 = param2 + 10;
        var mod3 = param3 + 10;
        var mod4 = param4 + 10;
        var mod5 = param5 + 10;
        var mod6 = param6 + 10;
        var mod7 = param7 + 10;
        var mod8 = param8 + 10;
        var mod9 = param9 + 10;
        var mod10 = param10 + 10;
        var mod11 = param11 + 10;
        logger.debug(() ->
            "My constant: "
                + mod1 + " "
                + mod2 + " "
                + mod3 + " "
                + mod4 + " "
                + mod5 + " "
                + mod6 + " "
                + mod7 + " "
                + mod8 + " "
                + mod9 + " "
                + mod10 + " "
                + mod11
        );
        return mod1 + mod2 + mod3 + mod4 + mod5 + mod6 + mod7 + mod8 + mod9 + mod10 + mod11;
    }

    @Benchmark
    public int lambda_enabled() {
        var mod1 = param1 + 10;
        var mod2 = param2 + 10;
        var mod3 = param3 + 10;
        var mod4 = param4 + 10;
        var mod5 = param5 + 10;
        var mod6 = param6 + 10;
        var mod7 = param7 + 10;
        var mod8 = param8 + 10;
        var mod9 = param9 + 10;
        var mod10 = param10 + 10;
        var mod11 = param11 + 10;
        logger.info(() ->
            "My constant: "
                + mod1 + " "
                + mod2 + " "
                + mod3 + " "
                + mod4 + " "
                + mod5 + " "
                + mod6 + " "
                + mod7 + " "
                + mod8 + " "
                + mod9 + " "
                + mod10 + " "
                + mod11
        );
        return mod1 + mod2 + mod3 + mod4 + mod5 + mod6 + mod7 + mod8 + mod9 + mod10 + mod11;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(LargeLoggingBench.class.getSimpleName())
            .addProfiler(GCProfiler.class)
            .build();

        new Runner(opt).run();
    }
}
