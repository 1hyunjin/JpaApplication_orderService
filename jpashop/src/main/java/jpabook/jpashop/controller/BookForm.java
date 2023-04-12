package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {
    //edit -> column selection mode (alt + shift + insert)
    private String name;
    private Long id;
    private int stockQuantity;
    private int price;
    private String author;
    private String isbn;
}
