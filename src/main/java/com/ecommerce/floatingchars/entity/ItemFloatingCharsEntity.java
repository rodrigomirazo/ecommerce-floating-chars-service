package com.ecommerce.floatingchars.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_floating_chars")
public class ItemFloatingCharsEntity {

    @Id
    @Column(name = "floating_char_id")
    private int floatingCharId;

    @Column(name = "char_name")
    private String charName;

    public ItemFloatingCharsEntity() {
    }

    public ItemFloatingCharsEntity(int floatingCharId, String charName) {
        this.floatingCharId = floatingCharId;
        this.charName = charName;
    }

    public ItemFloatingCharsEntity(int floatingCharId) {
        this.floatingCharId = floatingCharId;
    }

    public int getFloatingCharId() {
        return floatingCharId;
    }

    public ItemFloatingCharsEntity setFloatingCharId(int floatingCharId) {
        this.floatingCharId = floatingCharId;
        return this;
    }

    public String getCharName() {
        return charName;
    }

    public ItemFloatingCharsEntity setCharName(String charName) {
        this.charName = charName;
        return this;
    }
}
