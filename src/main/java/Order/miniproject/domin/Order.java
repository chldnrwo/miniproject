package Order.miniproject.domin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems = new ArrayList<OrderItem>();

  @Column(name="order_date")
  private LocalDateTime orderDate;

  @Column(name="order_status")
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  public void addOrderItem(OrderItem orderItem) {
    orderItems.add(orderItem);
    orderItem.setOrder(this);
    //주문 수량 => 재고에(-) 반영
  }

  public static Order createOrder(Member member, OrderItem... orderItems) {
    Order order = new Order();
    order.setMember(member);
    for(OrderItem orderItem : orderItems){
      order.addOrderItem(orderItem);
    }
    order.setOrderDate(LocalDateTime.now());
    order.setOrderStatus(OrderStatus.ORDER);
    return order;
  }

  public void cancelOrder() { //전체 주문 취소
    this.setOrderStatus(orderStatus.CANCEL);
    for(OrderItem orderItem : orderItems){
      orderItem.cancel();
    }
  }
}
