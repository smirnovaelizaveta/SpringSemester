package ru.otus.taskChecker.service;

import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class MavenInvokeService implements MavenService {
    @Override
    public void build(File projectRoot) {
//        log.info("Message consumed: {}", m);
//        System.out.println("kek"+file);
//
        InvocationRequest request = new DefaultInvocationRequest();
//
//
//        File outputFile = null;
//        try {
//            outputFile = Files.createFile(Path.of("file.zip")).toFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
//            outputStream.write(file);
//
//            request.setBaseDirectory(outputFile);
//
//        }
//        catch (IOException e) {
//
//
//        }

//
//        try {
//            request.setBaseDirectory(Files.writeString(Paths.get("file.zip"), file).toFile());
//        } catch (IOException e) {
//
//
//        }
        request.setBaseDirectory(projectRoot);
//        request.setPomFile( new File( "C:\\Users\\smirn\\OneDrive\\Рабочий стол\\springtask\\ComponentAnnotation\\pom.xml" ) );
        request.setGoals( Arrays.asList( "clean", "install", "test" ) );

        Invoker invoker = new DefaultInvoker();
        System.out.println("MAVEN_HOME IS " + System.getenv("MAVEN_HOME"));
        invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
        try {
            invoker.execute( request );
        } catch (MavenInvocationException e) {
            throw new RuntimeException(e);
        }
//        CONSUMED_MESSAGES.add();
    }
}
