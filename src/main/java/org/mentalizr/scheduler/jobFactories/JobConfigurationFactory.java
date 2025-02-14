package org.mentalizr.scheduler.jobFactories;

import de.arthurpicht.configuration.Configuration;
import de.arthurpicht.configuration.ConfigurationFactory;
import de.arthurpicht.configuration.ConfigurationFileNotFoundException;
import org.mentalizr.scheduler.M7rSchedulerConfigurationException;
import org.mentalizr.scheduler.M7rSchedulerException;
import org.mentalizr.scheduler.jobs.BaseConfiguration;
import org.mentalizr.scheduler.jobs.BaseConfigurationParser;
import org.mentalizr.scheduler.jobs.JobConfiguration;
import org.mentalizr.scheduler.jobs.activityStatWeekly.ActivityStatWeeklyConfiguration;
import org.mentalizr.scheduler.jobs.activityStatWeekly.ActivityStatWeeklyConfigurationParser;
import org.mentalizr.scheduler.jobs.heartbeat.HeartbeatConfiguration;
import org.mentalizr.scheduler.jobs.heartbeat.HeartbeatConfigurationParser;
import org.mentalizr.scheduler.jobs.watchdog.WatchdogConfiguration;
import org.mentalizr.scheduler.jobs.watchdog.WatchdogConfigurationParser;

import java.io.IOException;
import java.nio.file.Path;

public class JobConfigurationFactory {

    public static JobConfiguration create(Path configurationFile) {

        ConfigurationFactory configurationFactory = obtainConfigurationFactory(configurationFile);
        BaseConfiguration baseConfiguration = getBaseConfiguration(configurationFile, configurationFactory);

        if (configurationFactory.hasSection(ActivityStatWeeklyConfiguration.SECTION_NAME)) {

            Configuration activityStatWeeklySection
                    = configurationFactory.getConfiguration(ActivityStatWeeklyConfiguration.SECTION_NAME);
            ActivityStatWeeklyConfigurationParser activityStatWeeklyConfigurationParser
                    = new ActivityStatWeeklyConfigurationParser(
                    baseConfiguration,
                    activityStatWeeklySection,
                    configurationFile);
            return activityStatWeeklyConfigurationParser.parse();

        } else if (configurationFactory.hasSection(HeartbeatConfiguration.SECTION_NAME)) {

            Configuration heartbeatSection = configurationFactory.getConfiguration(HeartbeatConfiguration.SECTION_NAME);
            HeartbeatConfigurationParser heartbeatConfigurationParser
                    = new HeartbeatConfigurationParser(baseConfiguration, heartbeatSection, configurationFile);
            return heartbeatConfigurationParser.parse();

        } else if (configurationFactory.hasSection(WatchdogConfiguration.SECTION_NAME)) {

            Configuration watchdogSection = configurationFactory.getConfiguration(WatchdogConfiguration.SECTION_NAME);
            WatchdogConfigurationParser watchdogConfigurationParser
                    = new WatchdogConfigurationParser(baseConfiguration, watchdogSection, configurationFile);
            return watchdogConfigurationParser.parse();

        } else {
            throw new M7rSchedulerConfigurationException("No valid scheduler configuration: " +
                    "[" + configurationFile.toAbsolutePath() + "]. Section name not recognized.");
        }
    }

    private static ConfigurationFactory obtainConfigurationFactory(Path configurationFile) {
        ConfigurationFactory configurationFactory = new ConfigurationFactory();
        try {
            configurationFactory.addConfigurationFileFromFilesystem(configurationFile.toFile());
        } catch (ConfigurationFileNotFoundException | IOException e) {
            throw new M7rSchedulerException("Error binding scheduler configuration file ["
                    + configurationFile.toAbsolutePath() + "]: " + e.getMessage(), e);
        }
        return configurationFactory;
    }

    private static BaseConfiguration getBaseConfiguration(
            Path configurationFile,
            ConfigurationFactory configurationFactory) {

        Configuration configuration = configurationFactory.getConfiguration();
        return BaseConfigurationParser.parse(configurationFile, configuration);
    }

}
