package com.aleksadacic.generator.utils;

import com.aleksadacic.creator.turbo.reader.ModelObject;
import com.aleksadacic.creator.turbo.writer.file.Writer;

public class WriterUtils {
    public static final String BUSINESS_PACKAGE = "com.aleksadacic.business";
    private static final String WRITER_PACKAGE = "com.aleksadacic.generator.writers";
    private static final String PATH_DELIMITER = "/";
    private static final String PACKAGE_DELIMITER = ".";

    private WriterUtils() {

    }

    public static Writer getWriter(ModelObject modelObject, String fullPackageName, Class<? extends Writer> clazz) throws Exception {
//        return (Writer) Class.forName(WRITER_PACKAGE + "." + clazz.getSimpleName() + "Writer")
        return clazz.getConstructor(ModelObject.class, String.class)
                .newInstance(modelObject, fullPackageName);
    }

    public static String getPackageFromPath(String path) {
        return path.replace("/", ".").replace("\\", ".").replace("src.main.java.", "");
    }

    public static String createPath(String... parts) {
        return String.join(PATH_DELIMITER, parts);
    }

    public static String createPackage(String... parts) {
        return String.join(PACKAGE_DELIMITER, parts);
    }

}
