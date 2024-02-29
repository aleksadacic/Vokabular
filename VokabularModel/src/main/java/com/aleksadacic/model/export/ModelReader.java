package com.aleksadacic.model.export;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModelReader {
    private ModelReader() {
    }

    public static Map<String, List<Class<?>>> getClassesInPackage(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);

        List<Class<?>> classes = new ArrayList<>();
        List<Class<?>> enums = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource.getProtocol().equals("file")) {
                getClassesFromFileSystem(resource, packageName, classes, enums);
            } else if (resource.getProtocol().equals("jar")) {
                getClassesFromJarFile(resource, packageName, classes, enums);
            }
        }

        return Map.of("classes", classes, "enums", enums);
    }

    private static void getClassesFromFileSystem(URL resource, String packageName, List<Class<?>> classes, List<Class<?>> enums) throws
            ClassNotFoundException {
        String directory = new File(resource.getFile()).getAbsolutePath();
        File dir = new File(directory);
        if (dir.exists() && dir.isDirectory()) {
            String[] files = dir.list();
            if (files != null) {
                for (String file : files) {
                    if (file.endsWith(".class")) {
                        String className = packageName + '.' + file.substring(0, file.length() - 6);
                        Class<?> clazz = Class.forName(className);
                        if (clazz.isEnum()) {
                            enums.add(clazz);
                        } else {
                            classes.add(clazz);
                        }
                    }
                }
            }
        }
    }

    private static void getClassesFromJarFile(URL resource, String packageName, List<Class<?>> classes, List<Class<?>> enums) throws
            IOException, ClassNotFoundException {
        String jarPath = resource.getFile();
        jarPath = jarPath.substring(5, jarPath.indexOf('!')); // Strip "file:" and "!/" from the URL

        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.startsWith(packageName) && entryName.endsWith(".class")) {
                    String className = entryName.replace("/", ".").substring(0, entryName.length() - 6);
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isEnum()) {
                        enums.add(clazz);
                    } else {
                        classes.add(clazz);
                    }
                }
            }
        }
    }
}
