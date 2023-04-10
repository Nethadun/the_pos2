package com.slit.pointofsales.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "item_quantity")
    private BigDecimal itemQuantity;
    @Column(name = "item_price")
    private BigDecimal itemPrice;
    @Lob
    @Column(name = "item_images")
    private  byte[] itemImages;
    @Column(name = "item_description")
    private String itemDescription;
}
