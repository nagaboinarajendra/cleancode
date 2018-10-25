package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] IMAGE_TYPES = {"jpg", "png"};
    private static final String[] DOCUMENT_TYPES = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    public File retrieveFile(String fileName) {
        validateFileType(fileName);
        final String dirPath = basePath + File.separator;
        return Paths.get(dirPath, fileName).toFile();
    }

    public List<String> images() {
        return files(basePath, IMAGE_TYPES);
    }

    public List<String> documents() {
        return files(basePath, DOCUMENT_TYPES);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String fileName) {
        FileExtensionPredicate imagePredicate = new FileExtensionPredicate(IMAGE_TYPES);
        return !imagePredicate.test(fileName);
    }

    private boolean isInvalidDocument(String fileName) {
        FileExtensionPredicate documentPredicate = new FileExtensionPredicate(DOCUMENT_TYPES);
        return !documentPredicate.test(fileName);
    }

    private List<String> files(String directoryPath, String[] allowedExtensions) {
        final FileExtensionPredicate predicate = new FileExtensionPredicate(allowedExtensions);
        return Arrays.asList(directory(directoryPath).list(getFilenameFilterByPredicate(predicate)));
    }

    private FilenameFilter getFilenameFilterByPredicate(final FileExtensionPredicate predicate) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File directoryPath, String fileName) {
                return predicate.test(fileName);
            }
        };
    }

    private File directory(String directoryPath) {
        File directory = new File(directoryPath);
        validateDirectory(directory);
        return directory;
    }

    private void validateDirectory(File directoryInstance) {
        if (isNotDirectory(directoryInstance)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directoryInstance.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File directory) {
        return !directory.isDirectory();
    }

}