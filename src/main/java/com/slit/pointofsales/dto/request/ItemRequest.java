package com.slit.pointofsales.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemRequest implements Serializable {
    private Integer itemId;
    private String itemName;
    private BigDecimal itemQuantity;
    private BigDecimal itemPrice;
    private transient MultipartFile itemImages;
    private String itemDescription;
}
