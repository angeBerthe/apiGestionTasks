package ci.digitalacademy.apigestiontasks.services.impl;

import ci.digitalacademy.apigestiontasks.models.Tasks;
import ci.digitalacademy.apigestiontasks.repositories.TasksRepository;
import ci.digitalacademy.apigestiontasks.services.TasksService;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.TasksMapper;
import ci.digitalacademy.apigestiontasks.services.mapping.ProjectMapping;
import ci.digitalacademy.apigestiontasks.services.mapping.TaskMapping;
import ci.digitalacademy.apigestiontasks.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;

    @Override
    public TasksDTO save(TasksDTO tasksDTO) {
        log.debug("Request to save Tasks : {}", tasksDTO);
        Tasks tasks = tasksMapper.toEntity(tasksDTO);
        tasks = tasksRepository.save(tasks);
        return tasksMapper.fromEntity(tasks);
    }

    @Override
    public Optional<TasksDTO> findOne(Long id) {
        log.debug("Request to get Tasks by id : {}", id);
        return tasksRepository.findById(id).map(tasks -> {
            return tasksMapper.fromEntity(tasks);
        });
    }

    @Override
    public TasksDTO update(TasksDTO tasksDTO) {
        log.debug("Request to update Tasks : {}", tasksDTO);
        return findOne(tasksDTO.getId()).map(tasks -> {
            tasks.setWording(tasksDTO.getWording());
            tasks.setStatus(tasksDTO.getStatus());
            tasks.setStartDate(tasksDTO.getStartDate());
            tasks.setEndDate(tasksDTO.getEndDate());
            return save(tasks);
        }).orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    @Override
    public TasksDTO update(TasksDTO tasks, Long id) {
        log.debug("Request to update Tasks {} with id {}", tasks, id);
        tasks.setId(id);
        return update(tasks);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tasks : {}", id);
        tasksRepository.deleteById(id);
    }

    @Override
    public List<TasksDTO> findAll() {
        return tasksRepository.findAll().stream().map(tasks -> {
            log.debug("Request to get Tasks : {}", tasks);
            return tasksMapper.fromEntity(tasks);
        }).toList();
    }

    @Override
    public TasksDTO saveTasks(TasksDTO tasksDTO) {
        log.debug("Request to save Tasks {} with slug", tasksDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(tasksDTO.getWording()));
        tasksDTO.setSlug(slug);
        return save(tasksDTO);
    }

    @Override
    public Optional<TasksDTO> findBySlug(String slug) {
        log.debug("Request to get Tasks by slug {}", slug);
        return tasksRepository.findBySlug(slug).map(tasks -> {
            return tasksMapper.fromEntity(tasks);
        });
    }

    @Override
    public TasksDTO partialUpdate(TasksDTO tasksDTO, Long id) {
        return tasksRepository.findById(id).map(tasks -> {
            TaskMapping.partialUpdate(tasks, tasksDTO);
            return tasks;
        }).map(tasksRepository::save).map(tasksMapper::fromEntity).orElse(null);
    }
}
