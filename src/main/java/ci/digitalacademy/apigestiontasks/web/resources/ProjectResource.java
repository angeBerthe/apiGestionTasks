package ci.digitalacademy.apigestiontasks.web.resources;

import ci.digitalacademy.apigestiontasks.services.ProjectService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/projects")
@Slf4j
@RequiredArgsConstructor
public class ProjectResource {

    private final ProjectService projectService;

    @ApiResponse(responseCode = "201", description = "REST, request to save a project")

    @PostMapping
    private ResponseEntity<ProjectDTO> saveProject(@RequestBody ProjectDTO projectDTO){
        log.debug("REST request to save : {}", projectDTO);
        ProjectDTO project = projectService.saveProject(projectDTO);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Request to get project"),
            @ApiResponse(responseCode = "404", description = "Project not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<?> getProjectById(@Parameter(required = true, description = "ID of project to be founded") @PathVariable Long id) {
        log.info("REST Request to get project : {}", id);
        Optional<ProjectDTO> projectDTO = projectService.findOne(id);
        if (projectDTO.isPresent()) {
            return new ResponseEntity<>(projectDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("project not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long id){
        log.debug("REST, Request to update project {} {}", projectDTO, id);
        return new ResponseEntity<>(projectService.update(projectDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        log.info("REST Request to delete project : {}", id);
        projectService.delete(id);
    }

    @GetMapping
    public List<ProjectDTO> getAllProject(){
        log.info("REST Request to get all projects");
        return projectService.findAll();
    }

    @PatchMapping("/{id}")
    private ProjectDTO partialUpdate(@RequestBody ProjectDTO projectDTO, @PathVariable Long id){
        log.debug("REST request to partial update {} {}", projectDTO, id);
        return projectService.partialupdate(projectDTO, id);
    }

    @GetMapping("/slug/{slug}")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Request to get project by slug"),
            @ApiResponse(responseCode = "404", description = "project not found", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @Operation(summary = "get one by slug", description = "this endpoint allow to get one slug")
    public ResponseEntity<?> getOneBySlug(@PathVariable String slug){
        log.debug("REST Request to get by slug project : {}", slug);
        Optional<ProjectDTO> projectDTO = projectService.findOneBySlug(slug);
        if (projectDTO.isPresent()){
            return new ResponseEntity<>(projectDTO.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Slug project not found",HttpStatus.NOT_FOUND);
        }
    }

}
