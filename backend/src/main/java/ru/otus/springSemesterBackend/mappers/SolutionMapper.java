package ru.otus.springSemesterBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.springSemesterBackend.controllers.dto.SolutionDto;
import ru.otus.springSemesterBackend.controllers.dto.TaskDto;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.UserSolutionStatus;
import ru.otus.springSemesterBackend.model.task.Task;

@Mapper(componentModel="spring")
public interface SolutionMapper {
    @Mappings({
//            @Mapping(target="employeeId", source = "entity.id"),
//            @Mapping(target="employeeName", source = "entity.name"),
//            @Mapping(target="employeeStartDt", source = "entity.startDt", dateFormat = "dd-MM-yyyy HH:mm:ss")
            @Mapping(target="file", source = "solutionCode"),
            @Mapping(target="taskId", source = "task.id")


    })
    SolutionDto toDto(Solution solution);

    Solution fromDto(SolutionDto solutionDto);

}
