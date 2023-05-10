package Order.miniproject.repository;

import Order.miniproject.domin.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final EntityManager em;

  public void save(Item item){
    em.persist(item);
  }

  public Item findById(Long id){
    return em.find(Item.class, id);
  }

  public List<Item> findById(String name){
    return em.createQuery("select m from Item m where m.name = :name", Item.class)
        .setParameter("name", name)
        .getResultList();
  }

  public List<Item> findAll(){
    return em.createQuery("select m from Item m", Item.class)
        .getResultList();
  }
  public void delete(Long id){
    Item item = em.find(Item.class, id);
    em.remove(item);
  }
  public void update(Item item) {
    em.merge(item);
  }
}
