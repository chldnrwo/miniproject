package Order.miniproject.Service;

import Order.miniproject.domin.Album;
import Order.miniproject.domin.Item;
import Order.miniproject.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ItemServiceTest {
  @Autowired private ItemRepository itemRepository;

  @Test
  void saveItem(){
    //given
//    Book book = new Book();
//    book.setName("test");
//    book.setPrice(1000);
//    book.setStockQuantity(10);
//    book.setAuthor("author");
//    book.setIsbn("test");

    Album album = new Album();
    album.setName("test");
    album.setPrice(1000);
    album.setStockQuantity(10);
    album.setArtist("artist");
    album.setEtc("etc");

    //when
    itemRepository.save(album);
    //then
    Item findItem = itemRepository.findById(album.getId());
    assertThat(findItem).isEqualTo(album);
  }

}
