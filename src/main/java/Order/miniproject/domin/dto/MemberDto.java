package Order.miniproject.domin.dto;

import Order.miniproject.domin.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {
  String name;
  Address address;
}
