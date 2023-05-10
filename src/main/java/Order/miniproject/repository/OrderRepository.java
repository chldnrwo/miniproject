package Order.miniproject.repository;

import Order.miniproject.domin.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final EntityManager em;

  public void save(Order order){
    em.persist(order);
  }

  public Order findByID(Long id){
    return em.find(Order.class, id);
  }

  public List<Order> findAll(){ //동적쿼리로 수정 필요
    return em.createQuery("select o from Order o", Order.class)
        .getResultList();
  }

}
