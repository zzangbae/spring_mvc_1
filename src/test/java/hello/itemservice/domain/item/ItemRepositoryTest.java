package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    // 아이템을 만들고, 저장하고,
    @Test
    void save() {
        // given
        Item item = new Item("Item A", 10000, 10);

        // when(수행 - 테스트할 로직 메서드가 들어감)
        Item saveItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("Item A", 10000, 10);
        Item item2 = new Item("Item B", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("Item A", 10000, 10);
        Item saveItem = itemRepository.save(item);
        Long itemId = saveItem.getId();

        // when(수행)
        Item fixedItem = new Item("Item B", 20000, 30);
        itemRepository.update(itemId, fixedItem);

        // then
        Item findItem = itemRepository.findById(itemId);
        assertThat(findItem.getItemName()).isEqualTo("Item B");
        assertThat(findItem.getPrice()).isEqualTo(20000);
        assertThat(findItem.getQuantity()).isEqualTo(30);
    }
}
