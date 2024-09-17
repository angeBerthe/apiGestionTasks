package ci.digitalacademy.apigestiontasks.services.mapper.impl;

import ci.digitalacademy.apigestiontasks.models.Member;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberMapperImpl implements MemberMapper {

    private final ModelMapper modelMapper;
    @Override
    public MemberDTO fromEntity(Member entity) {
        log.debug("Mapping Member to MemberDTO");
        return modelMapper.map(entity, MemberDTO.class);
    }

    @Override
    public Member toEntity(MemberDTO dto) {
        log.debug("Mapping MemberDTO to Member");
        return modelMapper.map(dto, Member.class);
    }
}
