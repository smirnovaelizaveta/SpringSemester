package ru.otus.springSemesterBackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTreeDto {
    List<ProjectFile> files;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProjectFile {
        String name;
        String content;
        List<ProjectFile> children;
        Boolean readonly;

    }

    public static class Builder {
        private static final Pattern PARENT_REGEX = Pattern.compile("(.*\\/)[^\\/]+\\/?$");

        private ProjectTreeDto projectTreeDto;
        Map<String, ProjectFile> filesMap;

        public Builder() {
            projectTreeDto = new ProjectTreeDto(new ArrayList<>());
            filesMap = new HashMap<>();
        }

        public void add(ProjectFile projectFile) {
            Matcher matcher = PARENT_REGEX.matcher(projectFile.name);
            if (matcher.find()) {
                String parentName = matcher.group(1);
                ProjectFile parent = filesMap.get(parentName);
                if (parent == null) {
                    parent = new ProjectFile(parentName, "", new ArrayList<>(), true);
                    add(parent);
                }
                parent.children.add(projectFile);
            } else {
                projectTreeDto.files.add(projectFile);
            }
            filesMap.put(projectFile.name, projectFile);
        }

        public ProjectTreeDto build() {
            return projectTreeDto;
        }
    }
}
