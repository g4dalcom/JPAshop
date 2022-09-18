package com.jpabook.jpashop.domain;

import com.jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED) // protected 기본 생성자와 같음
@Getter @Setter
@Entity
@Table(name = "order_item")
public class OrderItem {


    @GeneratedValue
    @Column(name = "order_item_id")
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id") // orderItem 입장에서 Item
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") //하나의 order는 여러개의 order_item을 가짐
    private Order order;

    private int orderPrice;  // 주문 가격
    private int count;   // 주문 수량


    /* 생성 메소드 */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    /* 비즈니스 로직 */
    public void cancel() {
        /* 재고수량을 원상복구 */
        getItem().addStock(count);
    }

    /* 조회 로직 */
    /* 주문상품 전체 조회 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
