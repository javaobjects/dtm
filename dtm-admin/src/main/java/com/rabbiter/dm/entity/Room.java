package com.rabbiter.dm.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author xianxian
 * @date 2022-01-19
 */
public class Room {
    private Long id;

    @NotBlank
    private String number;

    @NotNull
    private Long buildingId;

    @NotNull
    private Integer maxCapacity;

    private Integer currentNum;

    private Building building;

    private Boolean isFull;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Boolean getFull() {
        return isFull;
    }

    public void setFull(Boolean full) {
        isFull = full;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", buildingId=" + buildingId +
                ", maxCapacity=" + maxCapacity +
                ", currentNum=" + currentNum +
                ", building=" + building +
                ", isFull=" + isFull +
                '}';
    }
}
