package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {   // merge보단 변경감지 기능을 사용해라!
        //findItem = 영속 상태 , transaction에 의해서 commit이 됨 - 변경감지기능
        Item findItem = itemRepository.findOne(itemId);
        // 이름, 가격, 수량만 update 해준다.
        //set을 막 깔면 안된다. findItem.change(price, name, stockQuantity) 이런식으로 써야한다.
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        //itemRepository.save(findItem); -> 할 필요가 없다.
    }
    // update할 data들이 많다? -> Service계층에 dto를 만드세요

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
