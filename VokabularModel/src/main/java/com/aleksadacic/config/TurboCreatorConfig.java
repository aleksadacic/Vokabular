package com.aleksadacic.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TurboCreatorConfig {
    //    TODO
    private static final Properties props = new Properties();

    private TurboCreatorConfig() {
    }

    static {
        try {
            InputStream is = TurboCreatorConfig.class.getResourceAsStream("/generator.properties");
            props.load(is);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static String getBusinessPackage() {
        return (String) getProperty("pkg.business");
    }

    @SuppressWarnings("unused")
    public static String getWriterPackage() {
        return (String) getProperty("pkg.writer");
    }


    public static String getSourcePackage() {
        return (String) getProperty("pkg.source");
    }

    @SuppressWarnings("unused")
    public static String getExportConfigPath() {
        return (String) getProperty("resource.model.export.config");
    }

    public static String getExportFilePath() {
        return (String) getProperty("resource.model.export");
    }

    public static String getDataPackage() {
        return (String) getProperty("pkg.data");
    }

    public static String getServicePackage() {
        return (String) getProperty("pkg.service");
    }


    public static String getImporterPackage() {
        return (String) getProperty("pkg.importer");
    }

    public static Object getProperty(String name) {
        return props.getProperty(name);
    }
}
