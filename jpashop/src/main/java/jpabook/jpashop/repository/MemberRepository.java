package jpabook.jpashop.repository;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;   //entity 매니저 만들어서 주입

    public void save(Member member) {
        em.persist(member); //em.persist() : db가 commit되는 시점에 insert 된다.
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);   // 단건 조회, (entity class, pk)
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();  //entity 객체를 대상으로 조회 (jpql),  Member.class : 조회 타입
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)  //이름에 의해서 조회
                .setParameter("name", name)
                .getResultList();
    }

}
