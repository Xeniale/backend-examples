package com.github.xeniale.backendexamples.testing.streaming.soap;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.OutputStreamAppender;
import com.google.common.collect.ImmutableMap;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class BackboneLauncher<T extends BackboneLauncher.State, S extends BackboneLauncher<T, S>> {

    public class CommandLineOption {

        public final String shortName;
        public final String longName;
        public final boolean hasArg;
        public final String description;

        public CommandLineOption(String shortName, String longName, boolean hasArg, String description) {
            this.shortName = shortName;
            this.longName = longName;
            this.hasArg = hasArg;
            this.description = description;
        }

    }

    public static class LauncherException extends Exception {

        public LauncherException() {
        }

        public LauncherException(String message) {
            super(message);
        }

        public LauncherException(String message, Throwable cause) {
            super(message, cause);
        }

        public LauncherException(Throwable cause) {
            super(cause);
        }

        public LauncherException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    private final static String OPT_version = "V";
    private final static String OPT_help = "h";
    private final static String OPT_config = "c";
    private final static String OPT_jmxhttp = "j";
    private final static String OPT_debug = "d";

    private Stream<String> args = Stream.empty();
    private Stream<CommandLineOption> customCliOptions = Stream.empty();
    private Stream<Map<String, String>> customLoggers = Stream.empty();

    private boolean ignoreConfig = false;

    public T launch(String appName) {
        State state = launch0(appName);
        return customLaunch(state);
    }

    protected abstract T customLaunch(State state);
    protected abstract S getThis();

    private State launch0(String appName) {

        // Manual binding of java.util.logging to SLF4J

        SLF4JBridgeHandler.install();

        // Process command-line arguments

        Stream<CommandLineOption> builtinCliOptions = Stream.of(
                new CommandLineOption(OPT_version, "version", false, "Print app version"),
                new CommandLineOption(OPT_help, "help", false, "Print app usage help"),
                new CommandLineOption(OPT_config, "config", true, "Full path to the app config file"),
                new CommandLineOption(OPT_jmxhttp, "jmxhttp", false, "Enable JMX over HTTP"),
                new CommandLineOption(OPT_debug, "debug", false, "Logs to stdout as well")
        );

        Options cliOptions = new Options();
        Stream.concat(builtinCliOptions, customCliOptions)
                .forEachOrdered(opt -> {
                            if (!cliOptions.hasShortOption(opt.shortName) && !cliOptions.hasLongOption(opt.longName)) {
                                cliOptions.addOption(
                                        new Option(opt.shortName, opt.longName, opt.hasArg, opt.description)
                                );
                            }
                        }
                );

        CommandLineParser cliParser = new DefaultParser();
        CommandLine cliParsed;
        try {
            cliParsed = cliParser.parse(cliOptions, args.toArray(String[]::new));
        } catch (ParseException e) {
            System.err.printf("command-line parsing failed: %s%n", e.getMessage());
            System.exit(1);
            throw new IllegalStateException("exception after System.exit");
        }

        if (cliParsed.hasOption(OPT_version)) {
            System.out.println(String.format("%s version is <version>", appName)); // TODO <version>
            System.exit(0);
            throw new IllegalStateException("exception after System.exit");
        }

        if (cliParsed.hasOption(OPT_help)) {
            HelpFormatter helpFormatter = new HelpFormatter();
            PrintWriter pw = new PrintWriter(System.out);
            helpFormatter.printHelp(pw, 80, String.format("%s [options]", appName), null, cliOptions, 2, 4, null);
            pw.flush();
            System.exit(0);
            throw new IllegalStateException("exception after System.exit");
        }

        //todo: Load configuration file

//        final File configFile = new File(cliParsed.getOptionValue(OPT_config, String.format("%s.conf", appName)));

        Config fallbackConfig = ConfigFactory.load("com/github/xeniale/backendexamples/testing/streaming/soap/launcher/launcher.conf");

//        final Config mutableConfig;
//        if (!ignoreConfig) {
//            mutableConfig = createSynchronizingMutableConfig(configFile, fallbackConfig);
//
//            if (mutableConfig.get().isEmpty()) {
//                System.err.printf("Config file %s is empty %n", configFile.getAbsolutePath());
//                System.exit(2);
//                throw new IllegalStateException("exception after System.exit");
//            }
//        } else {
//            try {
//                mutableConfig = new SimpleMutableConfig(SimpleMutableConfig.empty, fallbackConfig);
//            } catch (IOException e) {
//                System.err.printf("Internal error - can't load up dummy config source.%n");
//                System.exit(10);
//                throw new IllegalStateException("exception after System.exit");
//            }
//        }
//
//        // Configure logging
//
//        // -- customLoggers
//        customLoggers.forEach(logger -> logger.entrySet().forEach(entry -> {
//                    mutableConfig.set(Stream.of("logging", "file", "per-package", entry.getKey()), entry.getValue());
//                    mutableConfig.set(Stream.of("logging", "console", "per-package", entry.getKey()), entry.getValue());
//                }
//        ));

        //todo: -- mutableConfig loggers
//        try {
//            configureLogging(mutableConfig.get().getConfig("logging"));
//        } catch (Exception e) {
//            System.err.printf("Failed to configure logging: %s", e.getMessage());
//            System.exit(5);
//            throw new IllegalStateException("exception after System.exit");
//        }
//
//        // Merge command-line arguments with the configuration
//
//        if (cliParsed.hasOption(OPT_jmxhttp)) {
//            mutableConfig.setOverride("jmx-http.enabled", true);
//        }
//
//        if (cliParsed.hasOption(OPT_debug)) {
//            mutableConfig.setOverride("logging.console.enabled", true);
//        }

        /*
         * Start things up
         */

//        if (mutableConfig.get().getBoolean("jmx-http.enabled")) {
//            try {
//                startJmxHttp(mutableConfig.get().getConfig("jmx-http"));
//            } catch (LauncherException e) {
//                log().error("Failed to start JMX over HTTP", e);
//                System.err.printf("Failed to start JMX over HTTP: %s%n", e.getMessage());
//                System.exit(3);
//                throw new IllegalStateException("exception after System.exit");
//            }
//        }

        //todo: not fallback config
        return new State(appName, cliParsed, fallbackConfig);
    }

    protected static Logger log() {
        return LoggerFactory.getLogger(BackboneLauncher.class);
    }

    public S withArgs(String[] args, String... customArgs) {
        this.args = Stream.concat(Arrays.stream(args), Arrays.stream(customArgs));
        return getThis();
    }

    public S withArgs(String... args) {
        this.args = Arrays.stream(args);
        return getThis();
    }

    public S withCliOptions(CommandLineOption... customCliOptions) {
        this.customCliOptions = Arrays.stream(customCliOptions);
        return getThis();
    }

    public S withMainLogging(Object mainInstance) {
        String mainPackage = mainInstance.getClass().getPackage().getName();
        customLoggers = Stream.concat(customLoggers, Stream.of(ImmutableMap.of(mainPackage, "INFO")));
        return getThis();
    }

    public S ignoreConfig() {
        this.ignoreConfig = true;
        return getThis();
    }

    private static void configureLogging(Config config) {
        LoggerContext logback = (LoggerContext) LoggerFactory.getILoggerFactory();

        ch.qos.logback.classic.Logger root = rootLogger();
        root.detachAndStopAllAppenders();
        root.setLevel(Level.WARN);

        Config fileConfig = config.getConfig("file");
        Config consoleConfig = config.getConfig("console");

        createFileAppender(logback, fileConfig).ifPresent(appender -> addLoggersToAppender(appender, root, fileConfig));

        createConsoleAppender(logback, consoleConfig).ifPresent(appender -> addLoggersToAppender(appender, root, consoleConfig));
    }

    private static void addLoggersToAppender(OutputStreamAppender<ILoggingEvent> appender, ch.qos.logback.classic.Logger root, Config
            appenderConfig) {
        root.addAppender(appender);
        appenderConfig.getObject("per-package").entrySet().stream().forEach(entry ->
                addLoggerToAppender(appender, entry.getKey(), (String) entry.getValue().unwrapped()));
    }

    private static Optional<FileAppender<ILoggingEvent>> createFileAppender(LoggerContext loggerContext, Config fileConfig) {
        if (!fileConfig.getBoolean("enabled")) {
            return Optional.empty();
        }

        PatternLayoutEncoder fileEncoder = new PatternLayoutEncoder();
        fileEncoder.setContext(loggerContext);
        fileEncoder.setPattern("%date %level [%thread] %logger \\(%file:%line\\) %msg%n");
        fileEncoder.start();

        final FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setContext(loggerContext);
        fileAppender.setEncoder(fileEncoder);
        fileAppender.setFile(fileConfig.getString("name"));
        fileAppender.start();

        return Optional.of(fileAppender);
    }

    private static Optional<ConsoleAppender<ILoggingEvent>> createConsoleAppender(LoggerContext loggerContext, Config consoleConfig) {
        if (!consoleConfig.getBoolean("enabled")) {
            return Optional.empty();
        }

        PatternLayoutEncoder consoleEncoder = new PatternLayoutEncoder();
        consoleEncoder.setContext(loggerContext);
        consoleEncoder.setPattern("%.-1level %msg%n");
        consoleEncoder.start();

        final ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setContext(loggerContext);
        consoleAppender.setEncoder(consoleEncoder);
        consoleAppender.setTarget("System.err");
        consoleAppender.setWithJansi(true);
        consoleAppender.start();

        return Optional.of(consoleAppender);
    }

    private static ch.qos.logback.classic.Logger rootLogger() {
        return (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    }

    private static void addLoggerToAppender(Appender<ILoggingEvent> appender, String logger, String level) {
        ch.qos.logback.classic.Logger logger0 = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(logger);
        logger0.addAppender(appender);
        if (level != null) {
            logger0.setLevel(Level.toLevel(level));
            logger0.setAdditive(false);
        }
    }

    //todo:
//    private static void startJmxHttp(Config config) throws LauncherException {
//        int port = config.getInt("port");
//        String host = config.getString("interface");
//
//        // mx4j is crazy; tell it to use log4j.
//        Log.redirectTo(new Log4JLogger());
//        HttpAdaptorMBean adaptor = new HttpAdaptor(port, host);
//
//        ObjectName mbeanName;
//        String mbeanNameText = "mx4j.tools:type=HttpAdaptor";
//        try {
//            mbeanName = new ObjectName(mbeanNameText);
//        } catch (MalformedObjectNameException e) {
//            throw new LauncherException(String.format("Malformed mbean name `%s'", mbeanNameText), e);
//        }
//
//        try {
//            ManagementFactory.getPlatformMBeanServer().registerMBean(adaptor, mbeanName);
//        } catch (Exception e) {
//            throw new LauncherException(String.format("Failed to register %s as `%s' mbean", adaptor, mbeanName), e);
//        }
//
//        try {
//            adaptor.start();
//            log().info(String.format("Started JMX over HTTP at %s:%d", host, port));
//        } catch (IOException e) {
//            throw new LauncherException(String.format("Can't start JMX over HTTP at %s:%d", host, port));
//        }
//    }

    //todo:
//    public static Config createSynchronizingMutableConfig(File configFile, Config fallbackConfig) {
//        Config mutableConfig = null;
//        try {
//            final Path filesystemPath = configFile.getAbsoluteFile().toPath();
//            SynchronizableConfigSource configSource = new FileConfigSource(true, filesystemPath);
//            mutableConfig = new SynchronizingMutableConfig(configSource, fallbackConfig);
//        } catch (IOException | ConfigSourceException e) {
//            System.err.printf(e.getMessage());
//            System.exit(1);
//            throw new IllegalStateException("exception after System.exit");
//        }
//        return mutableConfig;
//    }


    public static class State {
        public final String appName;
        public final CommandLine parsedCli;
        public final Config config;

        public State(String appName, CommandLine parsedCli, Config config) {
            this.appName = appName;
            this.parsedCli = parsedCli;
            this.config = config;
        }
    }

}
