package Order.miniproject.Service;

import Order.miniproject.domin.Item;
import Order.miniproject.domin.Member;
import Order.miniproject.domin.Order;
import Order.miniproject.domin.OrderItem;
import Order.miniproject.repository.ItemRepository;
import Order.miniproject.repository.MemberRepository;
import Order.miniproject.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;

  @Transactional
  public Long saveOrder(Long memberId, Long itemId, int orderQuantity){
    Member member = memberRepository.findById(memberId);
    Item item = itemRepository.findById(itemId);
    // 오더 아이템 생성 (multiple ==> )
    // for
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderQuantity);

    Order order = Order.createOrder(member, orderItem);
    orderRepository.save(order);
    return order.getId();
  }
  public void cancelOrder(Long orderId){
    orderRepository.findByID(orderId).cancelOrder();
  }
  public List<Order> getOrderList(){ // 검색 조건 DTO
    return orderRepository.findAll();
  }
}
