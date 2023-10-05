package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // Repo안에 @Component가 있기 때문에 Component scan의 대상이 된다.
public class ItemRepository {

    // 원래는 HashMap을 쓰면 안된다??? 멀티 쓰레드 환경에선 ㄴㄴㄴ
    // ConcurrentHashMap써야한다.
    private static final Map<Long, Item> store = new HashMap<>();

    // automic long 이런거 써야한다.
    private static long sequence = 0L;  // static -> 객체가 하나만 생김

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }
    /**
     * store.values()를 바로 반환해서 Collections로 반환해도 된다.
     * 하지만 ArrayList로 감싸기 때문에, 값이 추가되더라도 store을 보존할 수 있다.
     * 바로 내놓을 경우, type을 처리해야하는 문제도 있다.
     */

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    /**
     * 정석대로 하려면 ItemUpdateDto를 만들어서 파라미터 3개만 딱 만들어서 넣는 것이 맞다.
     * 이유는, 분업상황에서 혼란을 방지하기 위해서 하는 것이다. id에 접근하는 것을 원천차단하기 위해서!
     * 중복 vs 명확성 : 명확성을 따르는 것이 좋다. 중복하더라도!
     */

    public void clearStore() {
        store.clear();
    }
}
