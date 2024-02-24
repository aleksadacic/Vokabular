package com.aleksadacic.generator.utils;

import com.aleksadacic.config.TurboCreatorConfig;
import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.writer.file.Writer;

public class WriterUtils {
    public static final String BUSINESS_PACKAGE = TurboCreatorConfig.getBusinessPackage();
    public static final String DATA_PACKAGE = TurboCreatorConfig.getDataPackage();
    public static final String SERVICE_PACKAGE = TurboCreatorConfig.getServicePackage();
    private static final String PATH_DELIMITER = "/";

    private static final String PACKAGE_DELIMITER = ".";

    private WriterUtils() {

    }

    public static Writer getWriter(ModelObject modelObject, String fullPackageName, Class<? extends Writer> clazz) throws Exception {
        return clazz.getConstructor(ModelObject.class, String.class)
                .newInstance(modelObject, fullPackageName);
    }

    public static String getPackageFromPath(String entity, String level) {
        if (level.equals(AppLevel.BUSINESS.getName())) {
            return BUSINESS_PACKAGE + "." + entity.toLowerCase();
        }
        if (level.equals(AppLevel.DATA.getName())) {
            return DATA_PACKAGE + "." + entity.toLowerCase();
        }
        if (level.equals(AppLevel.SERVICE.getName())) {
            return SERVICE_PACKAGE + "." + entity.toLowerCase();
        }
        return null;
    }

    public static String createPath(String... parts) {
        return String.join(PATH_DELIMITER, parts);
    }

    public static String createPackage(String... parts) {
        return String.join(PACKAGE_DELIMITER, parts);
    }

}
