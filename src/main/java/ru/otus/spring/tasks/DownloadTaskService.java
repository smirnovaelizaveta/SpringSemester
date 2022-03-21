package ru.otus.spring.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@RestController
public class DownloadTaskService {

    @RequestMapping(path = "api/tasks/{taskId}/zip")
    public ResponseEntity<StreamingResponseBody> downloadTaskCodeFile(HttpServletResponse response,
                                                             @PathVariable(name = "taskId") String taskId) {

//        logger.info("download request for sampleId = {}", sampleId);

        File taskCodeFile = new Task(taskId).getTaskCodeFile();

        StreamingResponseBody stream = out -> {
            String msg = "/srb" + " @ " + new Date();
            out.write(Files.readAllBytes(taskCodeFile.toPath()));

        };

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + taskCodeFile.getName());
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");

        return ResponseEntity.ok(stream);
    }
}
