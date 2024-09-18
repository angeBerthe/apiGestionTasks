package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.ProjectService;
import ci.digitalacademy.apigestiontasks.services.TasksService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@Slf4j
@RequiredArgsConstructor
public class TasksResource {

    private final TasksService tasksService;
    private final ProjectService projectService;

    @PostMapping
    @ApiResponse(responseCode = "201", description = "REST, request to save a task")
    public ResponseEntity<?> save(@RequestBody TasksDTO tasks) {
        log.debug("Rest request to save Tasks : {}", tasks);
        Optional<ProjectDTO> project = projectService.findOne(tasks.getProject().getId());
        if (project.isPresent()) {
            tasks.setProject(project.get());
            return new ResponseEntity<>(tasksService.saveTasks(tasks), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Id of project not found" ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTasksById(@PathVariable Long id) {
        log.debug("Rest request to get Tasks by id : {}", id);
        Optional<TasksDTO> tasks = tasksService.findOne(id);
        if (tasks.isPresent()) {
            return new ResponseEntity<>(tasks.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Id of task not found" ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/slug/{slug}")
    @Operation(summary = "get task by slug", description = "this endpoint allow to get task by slug")
    public ResponseEntity<?> getTasksBySlug(@PathVariable String slug) {
        log.debug("Rest request to get Tasks by slug : {}", slug);
        Optional<TasksDTO> tasks = tasksService.findBySlug(slug);
        if (tasks.isPresent()) {
            return new ResponseEntity<>(tasks.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Slug not found" ,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "get all tasks", description = "this endpoint allow to get all tasks")
    public List<TasksDTO> getAllTasks() {
        log.debug("Rest request to get all tasks");
        return tasksService.findAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update task", description = "this endpoint allow to update tasks")
    public ResponseEntity<TasksDTO> updateT(@PathVariable Long id, @RequestBody TasksDTO tasks) {
        log.debug("Rest request to update Tasks : {}", tasks);
        return new ResponseEntity<>(tasksService.update(tasks, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete task", description = "this endpoint allow to delete task")
    public void delete(@PathVariable Long id) {
        log.debug("Rest request to delete Tasks : {}", id);
        tasksService.delete(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "partial update task", description = "this endpoint allow to update task")
    public TasksDTO partialUpdate(@RequestBody TasksDTO tasksDTO, @PathVariable Long id){
        log.debug("REST Request to partial update {}", id);
        return tasksService.partialUpdate(tasksDTO, id);
    }
}
