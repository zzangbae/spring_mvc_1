package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

// @Data   // 위험하다. Getter나 Setter을 써라. in 핵심도메인 모델에서는 위험하다.
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;   // null을 가정하기 때문에 int가 아닌, Integer을 쓴다.

    // 기본 생성자
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
