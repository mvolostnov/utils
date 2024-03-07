package com.lseg.testframework.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtils {
    public static byte[] readAsBytes(final String filepath) {
        try {
            return Files.readAllBytes(Paths.get(filepath));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[] {};
    }

    public static String readAsString(final String filepath) {
        return new String(readAsBytes(filepath));
    }

    public static File createFile(final String filepath) {
        final File file = getFile(filepath);
        if (!isFileExist(file)) {
            try {
                if (file.createNewFile()) {
                    log.info("[FileUtils] New file created: [" + filepath + "].");
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            log.warn("[FileUtils] File [" + filepath + "] is NOT created: file already exists!");
        }
        return file;
    }
    public static File getFile(final String filepath) {
        Validate.notNullOrBlank(filepath, "[FileUtils] Specified file path is NULL or empty/blank!");
        return getPath(filepath).toFile();
    }

    public static boolean isFileExist(final File file) {
        return file.exists() && !file.isDirectory();
    }

    public static Path getPath(final String pathname) {
        Validate.notNullOrBlank(pathname, "[FileUtils] Specified path name is NULL or empty/blank!");
        return Paths.get(pathname);
    }
}
