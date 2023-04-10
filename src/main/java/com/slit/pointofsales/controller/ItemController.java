package com.slit.pointofsales.controller;

import com.slit.pointofsales.dto.request.ItemRequest;
import com.slit.pointofsales.dto.response.ResponseDTO;
import com.slit.pointofsales.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item/")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("save")
    public ResponseDTO saveItem(@ModelAttribute ItemRequest itemRequest){
        log.info("ItemController.saveItem method accessed");
        return itemService.saveItem(itemRequest);
    }

    @GetMapping("findAll")
    public ResponseDTO findAll(){
        log.info("ItemController.findAll method accessed");
        return itemService.findAllItems();
    }

    @DeleteMapping("delete/{itemId}")
    public ResponseDTO deleteItem(@PathVariable Integer itemId){
        log.info("ItemController.deleteItem method accessed");
        return itemService.deleteItems(itemId);
    }
}
