package Order.miniproject.repository;

import Order.miniproject.domin.Address;
import Order.miniproject.domin.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class MemberRepositoryTest {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberRepositoryTest(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Test
  @Transactional
  void save() {
    //given
    Member member = new Member();
    member.setName("test");
    Address address = new Address("seoul", "doksan", "11111");
    member.setAddress(address);
    //when
    memberRepository.save(member);
    //then
    Member findMember = memberRepository.findById(member.getId());
    assertThat(member).isEqualTo(findMember);
  }

  @Test
  void findById() {
  }

  @Test
  void findAll() {
  }
}