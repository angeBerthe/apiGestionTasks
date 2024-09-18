package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.NotificationService;
import ci.digitalacademy.apigestiontasks.services.ProjectService;
import ci.digitalacademy.apigestiontasks.services.TasksService;

import ci.digitalacademy.apigestiontasks.services.TeamService;
import ci.digitalacademy.apigestiontasks.services.dto.NotificationDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping
    @ApiResponse(responseCode = "201", description = "REST, request to save a task")
    @Operation(summary = "Save a new task", description = "This endpoint allows saving tasks and sends a notification")
    public ResponseEntity<?> save(@RequestBody TasksDTO tasks) {
        log.debug("Rest request to save Tasks : {}", tasks);
        TasksDTO savedTasks = tasksService.saveWithProjectAndTeam(tasks);

        return new ResponseEntity<>(savedTasks, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TasksDTO> update(@PathVariable Long id, @RequestBody TasksDTO tasks) {
        log.debug("Rest request to update Tasks : {}", tasks);
        TasksDTO updatedTask = tasksService.update(tasks, id);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        log.debug("Rest request to delete Tasks : {}", id);
        tasksService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get tasks by id"),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getTasksById(@PathVariable Long id) {
        log.debug("Rest request to get Tasks by id : {}", id);
        Optional<TasksDTO> tasks = tasksService.findOne(id);
        if (tasks.isPresent()) {
            return new ResponseEntity<>(tasks.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Id of task not found", HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>("Slug not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @Operation(summary = "get all tasks", description = "this endpoint allow to get all tasks")
    public List<TasksDTO> getAllTasks() {
        log.debug("Rest request to get all tasks");
        return tasksService.findAll();
    }


}
