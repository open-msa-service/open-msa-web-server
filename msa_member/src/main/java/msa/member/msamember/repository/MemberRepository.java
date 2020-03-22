package msa.member.msamember.repository;

import msa.member.msamember.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Member m SET m.email = :#{#mem.email}, m.phoneNumber = :#{#mem.phoneNumber}," +
            "m.statusMessage = :#{#mem.statusMessage}, m.introduceMessage = :#{#mem.introduceMessage}," +
            "m.profileHref = :#{#mem.profileHref}, m.updateTime = :#{#mem.updateTime} WHERE m.userId = :#{#mem.userId}")
    void updateMemberInfo(@Param("mem") Member member);

    Optional<List<Member>> findMemberByUsernameIgnoreCaseContaining(String username);

}
