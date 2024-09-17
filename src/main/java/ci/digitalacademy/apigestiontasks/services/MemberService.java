package ci.digitalacademy.apigestiontasks.services;


import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    MemberDTO save(MemberDTO memberDTO);

    MemberDTO update(MemberDTO memberDTO);

    Optional<MemberDTO> findOne(Long id);

    Optional<MemberDTO> findBySlug(String slug);

    List<MemberDTO> findAll();

    void delete(Long id);


    MemberDTO update(MemberDTO memberDTO, Long id);
    MemberDTO partialUpdate(MemberDTO memberDTO, Long id);
}
