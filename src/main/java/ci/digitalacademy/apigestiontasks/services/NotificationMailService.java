package ci.digitalacademy.apigestiontasks.services;

import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TasksDTO;

public interface NotificationMailService {


    void sendNoficationMailAddMemberByTeam(MemberDTO memberDTO);

    void sendNoficationMailAttributeTaskByMember(TasksDTO tasksDTO);
}
