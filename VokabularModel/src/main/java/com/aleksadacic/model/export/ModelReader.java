package com.aleksadacic.model.export;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModelReader {
    private ModelReader() {
    }

    public static List<Class<?>> getClassesInPackage(String packageName) throws IOException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = classLoader.getResources(path);

        List<Class<?>> classes = new ArrayList<>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource.getProtocol().equals("file")) {
                classes.addAll(getClassesFromFileSystem(resource, packageName));
            } else if (resource.getProtocol().equals("jar")) {
                classes.addAll(getClassesFromJarFile(resource, packageName));
            }
        }

        return classes;
    }

    private static List<Class<?>> getClassesFromFileSystem(URL resource, String packageName) throws
            ClassNotFoundException {
        String directory = new File(resource.getFile()).getAbsolutePath();
        List<Class<?>> classes = new ArrayList<>();
        File dir = new File(directory);
        if (dir.exists() && dir.isDirectory()) {
            String[] files = dir.list();
            if (files != null) {
                for (String file : files) {
                    if (file.endsWith(".class")) {
                        String className = packageName + '.' + file.substring(0, file.length() - 6);
                        classes.add(Class.forName(className));
                    }
                }
            }
        }
        return classes;
    }

    private static List<Class<?>> getClassesFromJarFile(URL resource, String packageName) throws
            IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        String jarPath = resource.getFile();
        jarPath = jarPath.substring(5, jarPath.indexOf('!')); // Strip "file:" and "!/" from the URL

        try (JarFile jarFile = new JarFile(jarPath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.startsWith(packageName) && entryName.endsWith(".class")) {
                    String className = entryName.replace("/", ".").substring(0, entryName.length() - 6);
                    classes.add(Class.forName(className));
                }
            }
        }
        return classes;
    }
}
