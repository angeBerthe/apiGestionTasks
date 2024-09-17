package ci.digitalacademy.apigestiontasks.services.mapping;

import ci.digitalacademy.apigestiontasks.models.Project;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;

public class ProjectMapping {

    private ProjectMapping(){
    }

    public static void partialUpdate(Project project, ProjectDTO projectDTO){
        if (projectDTO.getNameProject()!=null){
            project.setNameProject(projectDTO.getNameProject());
        }
        if (projectDTO.getDescription()!=null){
            project.setDescription(projectDTO.getDescription());
        }
        if (projectDTO.getStatus()!=null){
            project.setStatus(projectDTO.getStatus());
        }
        if (projectDTO.getEndDate()!=null){
            project.setEndDate(projectDTO.getEndDate());
        }
        if (projectDTO.getStartDate()!=null){
            project.setStartDate(projectDTO.getStartDate());
        }
    }
}
