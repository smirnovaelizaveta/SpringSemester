package ru.otus.taskChecker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.taskChecker.model.SolutionCheck;

import java.io.File;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SolutionProcessorFacade implements SolutionProcessor {
    private final DockerService dockerService;
    private final LogChecker logChecker;
    private final MavenService mavenService;
    private final ZipExtractor zipExtractor;

    @Override
    public SolutionCheck check(byte[] solutionZip) {
        File projectRoot = zipExtractor.extract(solutionZip);
//        mavenService.build(projectRoot);
        String log = dockerService.runAndGetLog(projectRoot);
        projectRoot.delete();
        return logChecker.checkLog(log);
    }
}
