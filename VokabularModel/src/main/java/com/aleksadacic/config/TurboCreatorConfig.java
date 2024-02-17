package com.aleksadacic.config;

import com.aleksadacic.engine.config.Config;
import com.aleksadacic.engine.framework.SpringContextAware;

@SuppressWarnings("unused")
public class TurboCreatorConfig {
    private TurboCreatorConfig() {
    }

    public static String getBusinessPackage() {
        return (String) getProperty("pkg.business");
    }

    public static String getWriterPackage() {
        return (String) getProperty("pkg.writer");
    }


    public static String getSourcePackage() {
        return (String) getProperty("pkg.source");
    }

    public static String getExportConfigPath() {
        return (String) getProperty("resource.model.export.config");
    }

    public static String getExportFilePath() {
        return (String) getProperty("resource.model.export");
    }

    public static Object getProperty(String name) {
        return SpringContextAware.getBean(Config.class).getProperty(name);
    }
}
