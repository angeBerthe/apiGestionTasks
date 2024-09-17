package ci.digitalacademy.apigestiontasks.mapping;

import ci.digitalacademy.apigestiontasks.models.Member;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;

public final class MemberMapping {

    private MemberMapping(){}

    public static void partialUpdate(Member member, MemberDTO memberDTO){
        if(memberDTO.getFirstName() != null){
            member.setFirstName(memberDTO.getFirstName());
        }
        if (memberDTO.getLastName() != null){
            member.setLastName(memberDTO.getLastName());
        }
        if (memberDTO.getEmail() != null){
            member.setEmail(memberDTO.getEmail());
        }
        if (memberDTO.getPhoneNumber() != null){
            member.setPhoneNumber(memberDTO.getPhoneNumber());
        }
    }

}
