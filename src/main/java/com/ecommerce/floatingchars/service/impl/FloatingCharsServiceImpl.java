package com.ecommerce.floatingchars.service.impl;

import com.ecommerce.floatingchars.dto.ItemFloatingCharsCatDto;
import com.ecommerce.floatingchars.dto.ItemFloatingCharsDto;
import com.ecommerce.floatingchars.dto.ItemFloatingCharsRelDto;
import com.ecommerce.floatingchars.entity.ItemFloatingCharsCatEntity;
import com.ecommerce.floatingchars.entity.ItemFloatingCharsEntity;
import com.ecommerce.floatingchars.entity.ItemFloatingCharsRelEntity;
import com.ecommerce.floatingchars.mapper.ItemFloatingCharsCatMapper;
import com.ecommerce.floatingchars.mapper.ItemFloatingCharsItemRelMapper;
import com.ecommerce.floatingchars.mapper.ItemFloatingCharsMapper;
import com.ecommerce.floatingchars.repository.ItemFloatingCharsCatRepository;
import com.ecommerce.floatingchars.repository.ItemFloatingCharsRelRepository;
import com.ecommerce.floatingchars.repository.ItemFloatingCharsRepository;
import com.ecommerce.floatingchars.service.FloatingCharsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FloatingCharsServiceImpl implements FloatingCharsService
{

    @Autowired
    private ItemFloatingCharsRepository itemFloatingCharsRepository;

    @Autowired
    private ItemFloatingCharsCatRepository itemFloatingCharsCatRepository;

    @Autowired
    private ItemFloatingCharsRelRepository itemFloatingCharsRelRepository;

    @Autowired
    ItemFloatingCharsMapper charsMapper;

    @Autowired
    ItemFloatingCharsCatMapper charsCatMapper;

    @Autowired
    ItemFloatingCharsItemRelMapper charsItemRelMapper;

    public ItemFloatingCharsDto saveFloatingCharsDtos(ItemFloatingCharsDto itemFloatingCharRequest) {

        // Map to Emntity
        ItemFloatingCharsEntity itemFloatingCharMapped = charsMapper.toItemFloatingCharsEntity(itemFloatingCharRequest);

        //Persist
        ItemFloatingCharsEntity itemFloatingCharsEntity = itemFloatingCharsRepository.save(itemFloatingCharMapped);

        // Map to Dto
        ItemFloatingCharsDto itemFloatingCharsDto = charsMapper.toItemFloatingCharsDto(itemFloatingCharsEntity);

        return itemFloatingCharsDto;
    }

    @Cacheable("itemFloatingChars")
    public List<ItemFloatingCharsDto> getItemFloatingCharsDtos() {

        // Retrieve from DB
        Iterable<ItemFloatingCharsEntity> floatingCharsEntities = itemFloatingCharsRepository.findAll();

        // Map to Dto
        List<ItemFloatingCharsDto> itemFloatingCharsDto = charsMapper.toItemFloatingCharsDtoList(floatingCharsEntities);

        itemFloatingCharsDto.stream().map(itemFloatingChar -> {
            ItemFloatingCharsEntity floatingCharsEntity = new ItemFloatingCharsEntity(itemFloatingChar.getFloatingCharId());
            List<ItemFloatingCharsCatEntity> floatingCharsCatEntities = itemFloatingCharsCatRepository.findByItemFloatingChar(floatingCharsEntity);
            List<ItemFloatingCharsCatDto> floatingCharsCatDtos = charsCatMapper.toItemFloatingCharsCatDtoList(floatingCharsCatEntities);

            return itemFloatingChar.setCatalogList(floatingCharsCatDtos);

        }).collect(Collectors.toList());

        return itemFloatingCharsDto;
    }

    public void deleteFloatingChar(Integer itemFloatingCharId) {

        //Persist
        itemFloatingCharsRepository.deleteById(itemFloatingCharId);
    }

    public ItemFloatingCharsCatDto saveItemFloatingCharsCat(Integer floatingCharsCat, ItemFloatingCharsCatDto itemFloatingCharsCatRequest) throws Exception {

        Optional<ItemFloatingCharsEntity> floatingCharsEntities = itemFloatingCharsRepository.findById(floatingCharsCat);

        if(!floatingCharsEntities.isPresent()) {
            throw new Exception();
        }

        // Map to Entities
        ItemFloatingCharsCatEntity itemFloatingCharsCatEntity = charsCatMapper.toItemFloatingCharsCatEntity(itemFloatingCharsCatRequest);
        itemFloatingCharsCatEntity.setItemFloatingChar(floatingCharsEntities.get());

        // Persist Entity
        itemFloatingCharsCatEntity = itemFloatingCharsCatRepository.save(itemFloatingCharsCatEntity);

        // Map to DTO
        ItemFloatingCharsCatDto charsCatDto = charsCatMapper.toItemFloatingCharsCatDto(itemFloatingCharsCatEntity);

        return charsCatDto;
    }

    public List<ItemFloatingCharsCatDto> getItemFloatingCharsCat(Integer itemFloatingCharsCatId) {

        Optional<ItemFloatingCharsEntity> floatingCharsEntities = itemFloatingCharsRepository.findById(itemFloatingCharsCatId);

        if(!floatingCharsEntities.isPresent()) {
            return new ArrayList<>();
        }

        // Retrive from DB
        List<ItemFloatingCharsCatEntity> floatingCharsCatEntities = itemFloatingCharsCatRepository.findByItemFloatingChar(floatingCharsEntities.get());

        // Map to DTO
        List<ItemFloatingCharsCatDto> floatingCharsCatDtos = charsCatMapper.toItemFloatingCharsCatDtoList(floatingCharsCatEntities);

        return floatingCharsCatDtos;
    }

    public void deleteFloatingCharCat(Integer itemFloatingCharCatId) {

        //Persist
        itemFloatingCharsCatRepository.deleteById(itemFloatingCharCatId);
    }

    /**
     * Item Floating Char Item Rel
     */

    public ItemFloatingCharsRelDto saveItemFloatingCharsItemRel(Integer floatingCharsCat, Integer itemId, ItemFloatingCharsRelDto itemFloatingCharsRelDto) throws Exception {

        Optional<ItemFloatingCharsEntity> floatingCharsEntities = itemFloatingCharsRepository.findById(floatingCharsCat);
        if(!floatingCharsEntities.isPresent()) {
            throw new Exception();
        }

        // Map to Entity
        ItemFloatingCharsRelEntity itemFloatingCharsCatEntity = charsItemRelMapper.toItemFloatingCharsRelEntity(itemFloatingCharsRelDto);

        // Persist Entity
        ItemFloatingCharsRelEntity persisted = itemFloatingCharsRelRepository.save(itemFloatingCharsCatEntity);

        // Map to DTO
        ItemFloatingCharsRelDto charsCatDto = charsItemRelMapper.toItemFloatingCharsRelDto(persisted);

        return charsCatDto;
    }

    public void deleteItemFloatingCharsItemRel(Integer itemFloatingCharsRelId) {

        //Persist
        itemFloatingCharsRelRepository.deleteById(itemFloatingCharsRelId);
    }
}
