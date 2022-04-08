package ru.otus.taskChecker.service;

import net.lingala.zip4j.ZipFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class DefaultZipExtractor implements ZipExtractor {
    @Override
    public File extract(byte[] zip) {
        try {
            File solutionZip = File.createTempFile("solutionZip", "zip");
            Files.write(solutionZip.toPath(), zip);

            Path solution = Files.createTempDirectory("solution");
            try (ZipFile zipFile = new ZipFile(solutionZip)) {
                zipFile.extractAll(solution.toString());
            }
            return solution.toFile();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
