package ci.digitalacademy.apigestiontasks.services.mapping;

import ci.digitalacademy.apigestiontasks.models.Project;
import ci.digitalacademy.apigestiontasks.models.Tasks;
import ci.digitalacademy.apigestiontasks.services.dto.ProjectDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;

public final class TaskMapping {

    private TaskMapping(){}


    public static void partialUpdate(Tasks tasks, TasksDTO tasksDTO){
        if (tasksDTO.getEndDate()!=null){
            tasks.setEndDate(tasksDTO.getEndDate());
        }
    }
}
