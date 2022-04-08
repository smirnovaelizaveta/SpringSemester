package ru.otus.taskChecker.taskChecker;

import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TaskChecker {

    private static final String TOPIC = "file";

    private final List<String> CONSUMED_MESSAGES = new ArrayList<>();

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(byte[] file) {
//        log.info("Message consumed: {}", m);
        System.out.println("kek"+file);

        InvocationRequest request = new DefaultInvocationRequest();


        File outputFile = null;
        try {
            outputFile = Files.createFile(Path.of("file.zip")).toFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(file);

                        request.setBaseDirectory(outputFile);

        }
        catch (IOException e) {


        }

//
//        try {
//            request.setBaseDirectory(Files.writeString(Paths.get("file.zip"), file).toFile());
//        } catch (IOException e) {
//
//
//        }
        request.setBaseDirectory(new File("C:\\Users\\smirn\\OneDrive\\Рабочий стол\\springtask\\ComponentAnnotation"));
        request.setPomFile( new File( "C:\\Users\\smirn\\OneDrive\\Рабочий стол\\springtask\\ComponentAnnotation\\pom.xml" ) );
        request.setGoals( Arrays.asList( "clean", "install", "test" ) );

        Invoker invoker = new DefaultInvoker();
        try {
            invoker.execute( request );
        } catch (MavenInvocationException e) {
            throw new RuntimeException(e);
        }
//        CONSUMED_MESSAGES.add();
    }
}
