package com.rabbiter.dm.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author XiongXinxin
 * @date 2022-03-13
 */
public class Repair {
    private Long id;

    @NotNull
    private Long roomId;

    @NotBlank
    private String describe;

    private List<String> picture;

    private Boolean status;

    private LocalDate createDate;

    private LocalDate finishDate;

    private Room room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", describe='" + describe + '\'' +
                ", picture=" + picture +
                ", status=" + status +
                ", createDate=" + createDate +
                ", finishDate=" + finishDate +
                ", room=" + room +
                '}';
    }
}
