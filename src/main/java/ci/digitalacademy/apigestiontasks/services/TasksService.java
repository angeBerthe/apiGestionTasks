package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;

import java.util.List;
import java.util.Optional;

public interface TasksService {

    TasksDTO save(TasksDTO tasksDTO);

    Optional<TasksDTO> findOne(Long id);

    TasksDTO update(TasksDTO tasksDTO);

    TasksDTO update(TasksDTO tasksDTO, Long id);

    void delete(Long id);

    List<TasksDTO> findAll();

    TasksDTO saveTasks(TasksDTO tasksDTO);

    Optional<TasksDTO> findBySlug(String slug);

    TasksDTO partialUpdate(TasksDTO tasksDTO, Long id);


}
