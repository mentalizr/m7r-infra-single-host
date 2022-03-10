package org.mentalizr.infra.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    public static final String DOCKER_LOGGER = "docker";

    private static String getLogFileName() {
        return "docker.log";
    }

    public static void initialize() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setPattern("%d{\"yyyy-MM-dd'T'HH:mm:ss,SSSXXX\", UTC} [%level] %logger{10} - %msg%n");
        patternLayoutEncoder.setContext(loggerContext);
        patternLayoutEncoder.start();

        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setFile(getLogFileName());
        fileAppender.setEncoder(patternLayoutEncoder);
        fileAppender.setContext(loggerContext);
        fileAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger("ROOT");
        // detach "console" which comes from logback default BasicConfigurator
        logger.detachAppender("console");
        logger.addAppender(fileAppender);
        logger.setLevel(Level.DEBUG);
//        logger.setAdditive(false); /* set to true if root should log too */

    }


    /**
     * see https://stackoverflow.com/questions/16910955/programmatically-configure-logback-appender
     *
     * @param loggerName
     * @param file
     * @return
     */
    public static Logger programmaticallyConfiguredLogger(String loggerName, String file) {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
//        patternLayoutEncoder.setPattern("%date %level [%thread] %logger{10} [%file:%line] %msg%n");
        patternLayoutEncoder.setPattern("%d{\"yyyy-MM-dd'T'HH:mm:ss,SSSXXX\", UTC} [%level] %logger{10} %msg%n");
        patternLayoutEncoder.setContext(lc);
        patternLayoutEncoder.start();

        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setFile(file);
        fileAppender.setEncoder(patternLayoutEncoder);
        fileAppender.setContext(lc);
        fileAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger(loggerName);
        logger.addAppender(fileAppender);
        logger.setLevel(Level.DEBUG);
        logger.setAdditive(false); /* set to true if root should log too */



        return logger;
    }
}
