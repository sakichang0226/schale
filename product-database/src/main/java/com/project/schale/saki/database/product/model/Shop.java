package com.project.schale.saki.database.product.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Shop implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shops.shop_id
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    private Long shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shops.name
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shops.create_at
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    private LocalDateTime createAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shops.update_at
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    private LocalDateTime updateAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shops
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shops.shop_id
     *
     * @return the value of shops.shop_id
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shops.shop_id
     *
     * @param shopId the value for shops.shop_id
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shops.name
     *
     * @return the value of shops.name
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shops.name
     *
     * @param name the value for shops.name
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shops.create_at
     *
     * @return the value of shops.create_at
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shops.create_at
     *
     * @param createAt the value for shops.create_at
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shops.update_at
     *
     * @return the value of shops.update_at
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shops.update_at
     *
     * @param updateAt the value for shops.update_at
     *
     * @mbg.generated Mon Oct 21 22:15:08 JST 2024
     */
    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}