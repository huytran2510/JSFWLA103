package fa.training.service;

import fa.training.dto.MemberForRegister;
import fa.training.dto.MemberForUpdate;
import fa.training.entities.Member;
import fa.training.entities.enums.ERole;
import fa.training.repository.MemberRepository;
import fa.training.repository.RoleRepository;
import fa.training.service.templates.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(MemberForRegister memberForRegister) {
        Member member = new Member(memberForRegister);
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        LocalDateTime currentTime = LocalDateTime.now();
        member.setCreateDate(currentTime);
        member.setRoles(new HashSet<>(roleRepository.findByName(ERole.USER)));
        memberRepository.save(member);
    }

    @Override
    public Member update(MemberForUpdate memberForUpdate, String username) {
        Member member = memberRepository.findMemberByUsername(username);
        if (member != null) {
            member.setFirstname(memberForUpdate.getFirstname());
            member.setLastname(memberForUpdate.getLastname());
            member.setPhone(memberForUpdate.getPhone());
            member.setDescription(memberForUpdate.getDescription());
            member.setUpdateTime(LocalDateTime.now());
            return memberRepository.save(member);
        } else {
            return null;
        }
    }
    @Override
    public Member findByUsername(String username) {
        return memberRepository.findMemberByUsername(username);
    }


}
