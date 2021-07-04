package com.ecommerce.floatingchars.service;

import com.ecommerce.floatingchars.dto.ItemFloatingCharsCatDto;
import com.ecommerce.floatingchars.dto.ItemFloatingCharsDto;

import java.util.List;

public interface FloatingCharsService {

    ItemFloatingCharsDto saveFloatingCharsDtos(ItemFloatingCharsDto itemFloatingCharRequest);

    List<ItemFloatingCharsDto> getItemFloatingCharsDtos();

    void deleteFloatingChar(Integer itemFloatingCharId);

    ItemFloatingCharsCatDto saveItemFloatingCharsCat(Integer floatingCharsCat, ItemFloatingCharsCatDto itemFloatingCharsCatRequest) throws Exception;

    List<ItemFloatingCharsCatDto> getItemFloatingCharsCat(Integer itemFloatingCharsCatId);

    void deleteFloatingCharCat(Integer itemFloatingCharCatId);
}
