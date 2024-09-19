package ci.digitalacademy.apigestiontasks.services.impl;


import ci.digitalacademy.apigestiontasks.models.Member;
import ci.digitalacademy.apigestiontasks.repositories.MemberRepository;
import ci.digitalacademy.apigestiontasks.services.MemberService;
import ci.digitalacademy.apigestiontasks.services.NotificationMailService;
import ci.digitalacademy.apigestiontasks.services.TeamService;
import ci.digitalacademy.apigestiontasks.services.dto.MemberDTO;
import ci.digitalacademy.apigestiontasks.services.dto.TeamDTO;
import ci.digitalacademy.apigestiontasks.services.mapper.MemberMapper;
import ci.digitalacademy.apigestiontasks.utils.SlugifyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final TeamService teamService;
    private final NotificationMailService notificationMailService;


    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        log.debug("Request to save: {}", memberDTO);
        Optional<TeamDTO> teamDTO = teamService.findOne(memberDTO.getTeam().getId());
        if (teamDTO.isPresent()){
            memberDTO.setTeam(teamDTO.get());
        }
        Member member = memberMapper.toEntity(memberDTO);
        member = memberRepository.save(member);
        return memberMapper.fromEntity(member);
    }

    @Override
    public MemberDTO update(MemberDTO memberDTO) {
        log.debug("Request to update: {}", memberDTO);
        return findOne(memberDTO.getId()).map(existingMember ->{
            existingMember.setFirstName(memberDTO.getFirstName());
            existingMember.setLastName(memberDTO.getLastName());
            existingMember.setPhoneNumber(memberDTO.getPhoneNumber());
            return save(memberDTO);
        }).orElseThrow(() ->new IllegalArgumentException());

    }

    @Override
    public Optional<MemberDTO> findOne(Long id) {
        log.debug("Resquest to get by id: {}", id);
        return memberRepository.findById(id).map(member -> {
            return memberMapper.fromEntity(member);
        });
    }

    @Override
    public Optional<MemberDTO> findBySlug(String slug) {
        log.debug("Resquest to get by slug: {}", slug);
        return memberRepository.findBySlug(slug).map(memberMapper::fromEntity);
    }

    @Override
    public List<MemberDTO> findAll() {
        log.debug("Request to found all member");
        return memberRepository.findAll().stream().map(member -> {
            return memberMapper.fromEntity(member);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete {}", id);
        memberRepository.deleteById(id);
    }

    @Override
    public MemberDTO update(MemberDTO memberDTO, Long id) {
        log.debug("Resquest to update member with two parameters :{} {}",memberDTO, id);
        memberDTO.setId(id);
        return update(memberDTO);
    }

    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) {
        log.debug("Request to save Member {} with slug", memberDTO);
        final String slug = SlugifyUtils.generate(String.valueOf(memberDTO.getEmail()));
        memberDTO.setSlug(slug);
        MemberDTO memberDTO1 = save(memberDTO);
        notificationMailService.sendNoficationMailAddMemberByTeam(memberDTO);
        return memberDTO1;
    }

}
