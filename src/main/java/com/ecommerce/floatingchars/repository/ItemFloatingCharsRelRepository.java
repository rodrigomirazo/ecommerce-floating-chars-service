package com.ecommerce.floatingchars.repository;

import com.ecommerce.floatingchars.entity.ItemFloatingCharsRelEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemFloatingCharsRelRepository  extends CrudRepository<ItemFloatingCharsRelEntity, Integer> {

    List<ItemFloatingCharsRelEntity> findByItemId(Integer itemId);
}
