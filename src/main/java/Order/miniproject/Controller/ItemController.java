package Order.miniproject.Controller;

import Order.miniproject.Service.ItemService;
import Order.miniproject.domin.Book;
import Order.miniproject.domin.Item;
import Order.miniproject.domin.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/addItem")
  public String addItem(){
    return "items/addItem";
  }
  @PostMapping("/addItem")
  public String addItemProcess(
      @ModelAttribute BookDto bookDto
      ){
    Book book = new Book();
    book.setName(bookDto.getName());
    book.setPrice(bookDto.getPrice());
    book.setStockQuantity(bookDto.getStockQuantity());
    book.setAuthor(bookDto.getAuthor());
    book.setIsbn(bookDto.getIsbn());
    itemService.saveItem(book);

    return "redirect:/items/itemList";
  }

  @GetMapping("/itemList")
  public void itemList(
      Model model
      ){
    List<Item> item = itemService.findAllItems();
    model.addAttribute("items", item);
  }

  @GetMapping("/{itemId}")
  public String item(@PathVariable Long itemId, Model model) {
    Item item = itemService.findItem(itemId);
    model.addAttribute("item", item);
    return "items/itemInfo";
  }

  @GetMapping("/{itemId}/edit")
  public String editForm(@PathVariable Long itemId, Model model) {
    Item item = itemService.findItem(itemId);
    model.addAttribute("item", item);
    return "items/updateItem";
  }
  @PostMapping("/{itemId}/edit")
  public String edit(@PathVariable Long itemId,
                     @ModelAttribute BookDto bookDto) {
    Book book = new Book();
    book.setId(itemId);
    book.setName(bookDto.getName());
    book.setPrice(bookDto.getPrice());
    book.setStockQuantity(bookDto.getStockQuantity());
    book.setAuthor(bookDto.getAuthor());
    book.setIsbn(bookDto.getIsbn());
    itemService.updateItem(book);

    return "redirect:/items/{itemId}";
  }
}
