package com.slit.pointofsales.service;

import com.slit.pointofsales.dto.request.ItemRequest;
import com.slit.pointofsales.dto.response.ResponseDTO;

public interface ItemService {
    public ResponseDTO saveItem(ItemRequest itemRequest);

    public ResponseDTO findAllItems();

    public ResponseDTO deleteItems(Integer itemId);
}
