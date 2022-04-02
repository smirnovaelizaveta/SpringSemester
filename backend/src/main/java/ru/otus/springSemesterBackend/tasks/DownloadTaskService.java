package ru.otus.springSemesterBackend.tasks;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.otus.springSemesterBackend.tasks.taskRepository.TaskRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.TreeSet;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class DownloadTaskService {

    @Autowired
    private TaskRepository taskRepository;

//    @RequestMapping(path = "api/task/{taskId}/zip")
//    public ResponseEntity<StreamingResponseBody> downloadTaskCodeFile(HttpServletResponse response,
//                                                             @PathVariable(name = "taskId") String taskId) {
//
//
//
////        logger.info("download request for sampleId = {}", sampleId);
//
////        File taskCodeFile = new Task(taskId).getTaskCodeFile();
////
////        StreamingResponseBody stream = out -> {
////            String msg = "/srb" + " @ " + new Date();
////            out.write(Files.readAllBytes(taskCodeFile.toPath()));
////
////        };
////
////        response.setContentType("application/zip");
////        response.setHeader("Content-Disposition", "attachment; filename=" + taskCodeFile.getName());
////        response.addHeader("Pragma", "no-cache");
////        response.addHeader("Expires", "0");
//
////        return ResponseEntity.ok(stream);
//        return null;
//    }

    @RequestMapping(path = "api/task/{taskId}/zip")
    public ResponseEntity<StreamingResponseBody> downloadTaskCodeFile(HttpServletResponse response,
                                                                      @PathVariable(name = "taskId") Long taskId) {


        Task task = taskRepository.findById(taskId)
//                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Unable to find resource"));


        StreamingResponseBody stream = out -> {
            out.write(task.getTaskCode());

        };

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + task.getTaskInfo().getName()+".zip");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");

        return ResponseEntity.ok(stream);
    }

    @RequestMapping(path = "api/task/{taskId}/project-tree")
    public String getProjectTree(HttpServletResponse response,
                                                                      @PathVariable(name = "taskId") Long taskId) {
//
//
//        Task task = taskRepository.findById(taskId)
////                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
//                .orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Unable to find resource"));
//
//        Path write = null;
//        try {
//            write = Files.write(Path.of("C:\\Users\\smirn\\IdeaProjects\\SpringSemester\\backend\\src\\main\\resources\\tasks\\task"+taskId), task.getTaskCode());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            Files.walkFileTree(Path.of("C:\\Users\\smirn\\IdeaProjects\\SpringSemester\\backend\\src\\main\\resources\\tasks\\task"+taskId))
//    //                .filter(Files::isRegularFile)
//                    .map(Path::toFile)
//                    .forEach(System.out::println);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        //treesets to hold paths alphabetically
        Path rootDirPath = Path.of("C:\\Users\\smirn\\IdeaProjects\\SpringSemester\\backend\\src\\main\\resources\\tasks\\task" + taskId);
        Tree.Node node =
walk(rootDirPath.toString());
//        Gson gson = new Gson();
        //        TreeSet<Path> paths = new TreeSet<>();
//        try {
//            Files.walkFileTree(rootDirPath, new SimpleFileVisitor<Path>() {
//
//
//                @Override
//                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//                    paths.add(dir);
//                    return super.preVisitDirectory(rootDirPath, attrs);
//                }
//                @Override
//                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    paths.add(file);
//                    return super.visitFile(rootDirPath, attrs);
//                }
//                @Override
//                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
//                    return super.visitFileFailed(file, exc);
//                }
//                @Override
//                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//                    return super.postVisitDirectory(rootDirPath, exc);
//                }
//            });
//            //I'm printing the contents alphabetically,.. your impl might vary
//            paths.forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        StreamingResponseBody stream = out -> {
//            out.write(task.getTaskCode());
//
//        };
//
//        response.setContentType("application/zip");
//        response.setHeader("Content-Disposition", "attachment; filename=" + task.getTaskInfo().getName()+".zip");
//        response.addHeader("Pragma", "no-cache");
//        response.addHeader("Expires", "0");

        return null;
    }

    private Tree.Node walk( String path ) {


        File root = new File( path );
        Tree.Node node = new Tree.Node(root);
        File[] list = root.listFiles();

        if (list == null) return node;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                node.addChildren(node, new Tree.Node<>(f));
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                node.addChildren(node, new Tree.Node<>(f));
                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
        return node;
    }
}
