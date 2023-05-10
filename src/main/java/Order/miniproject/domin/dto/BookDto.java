package Order.miniproject.domin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BookDto {
  private String name;
  private int price;
  private int stockQuantity;
  private String author;
  private String isbn;
}
