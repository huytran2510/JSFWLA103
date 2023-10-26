package fa.training.service.templates;

import fa.training.dto.MemberForRegister;
import fa.training.dto.MemberForUpdate;
import fa.training.entities.Member;

import java.util.List;

public interface MemberService {
    void save(MemberForRegister memberForRegister);

    Member update(MemberForUpdate memberForUpdate, String username);

    Member findByUsername(String username);


}
