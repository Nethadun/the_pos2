package com.slit.pointofsales.service.impl;

import com.slit.pointofsales.dto.request.ItemRequest;
import com.slit.pointofsales.dto.response.ResponseDTO;
import com.slit.pointofsales.model.Item;
import com.slit.pointofsales.repository.ItemRepository;
import com.slit.pointofsales.service.ItemService;
import com.slit.pointofsales.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Override
    public ResponseDTO saveItem(ItemRequest itemRequest) {
        log.info("ItemServiceImpl.saveItem method accessed");
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Optional<Item> byId = itemRepository.findById(itemRequest.getItemId());
            if (byId.isPresent()){
                Item item = byId.get();
                item.setItemDescription(itemRequest.getItemDescription());
                item.setItemName(itemRequest.getItemName());
                item.setItemQuantity(itemRequest.getItemQuantity());
                item.setItemPrice(itemRequest.getItemPrice());
                item.setItemImages(ImageUtil.compressImage(itemRequest.getItemImages().getBytes()));
                Item save = itemRepository.save(item);
                if (save.getItemId()!= null){
                    responseDTO.setHttpStatus(HttpStatus.OK.value());
                    responseDTO.setMessage("Record updated");
                    responseDTO.setPayload(save);
                }else {
                    responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
                    responseDTO.setMessage("Record not update");
                    responseDTO.setPayload(save);
                }
            }
            Item item = new Item();
            item.setItemDescription(itemRequest.getItemDescription());
            item.setItemName(itemRequest.getItemName());
            item.setItemQuantity(itemRequest.getItemQuantity());
            item.setItemPrice(itemRequest.getItemPrice());
            item.setItemImages(ImageUtil.compressImage(itemRequest.getItemImages().getBytes()));
            Item save = itemRepository.save(item);
            if (save.getItemId()!= null){
                responseDTO.setHttpStatus(HttpStatus.CREATED.value());
                responseDTO.setMessage("Record saved");
                responseDTO.setPayload(save);
            }else {
                responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage("Record not save");
                responseDTO.setPayload(save);
            }
        } catch (IOException e) {
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage("Failed to upload profile pic");
            responseDTO.setPayload("");
            log.error("Error in ItemServiceImpl.saveItem method : {}",e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO findAllItems() {
        ResponseDTO responseDTO = new ResponseDTO();
        log.info("ItemServiceImpl.findAllItems method accessed");
        try {
            List<Item> all = itemRepository.findAll();
            if (!all.isEmpty()){
                responseDTO.setHttpStatus(HttpStatus.OK.value());
                responseDTO.setMessage("Record found");
                responseDTO.setPayload(all);
            }else {
                responseDTO.setHttpStatus(HttpStatus.OK.value());
                responseDTO.setMessage("Record not found");
                responseDTO.setPayload(all);
            }
        }catch (Exception e){
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage("Bad request");
            responseDTO.setPayload("");
            log.error("Error in ItemServiceImpl.findAllItems method : {}",e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteItems(Integer itemId) {
        ResponseDTO responseDTO = new ResponseDTO();
        log.info("ItemServiceImpl.findAllItems method accessed");
        try {
            Optional<Item> byId = itemRepository.findById(itemId);
            if (byId.isPresent()){
                Item item = byId.get();
                itemRepository.delete(item);
                responseDTO.setHttpStatus(HttpStatus.OK.value());
                responseDTO.setMessage("Record deleted");
                responseDTO.setPayload("");
            }else {
                responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
                responseDTO.setMessage("Record not delete");
                responseDTO.setPayload("");
            }
        }catch (Exception e){
            responseDTO.setHttpStatus(HttpStatus.BAD_REQUEST.value());
            responseDTO.setMessage("Bad request");
            responseDTO.setPayload("");
            log.error("Error in ItemServiceImpl.deleteItems method : {}",e.getMessage());
        }
        return responseDTO;
    }
}
