package com.ecommerce.floatingchars.repository;

import com.ecommerce.floatingchars.entity.ItemFloatingCharsCatEntity;
import com.ecommerce.floatingchars.entity.ItemFloatingCharsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemFloatingCharsCatRepository extends CrudRepository<ItemFloatingCharsCatEntity, Integer> {

    List<ItemFloatingCharsCatEntity> findByItemFloatingChar(ItemFloatingCharsEntity itemFloatingChar);

}
