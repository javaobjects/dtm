package com.rabbiter.dm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author xianxian
 * @date 2022-01-19
 */
public class Student {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long roomId;

    private Long facultyId;

    @NotBlank
    private String number;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;

    private String phone;

    private String photo;

    private Boolean isLeave;

    private Room room;

    private Faculty faculty;

    private LocalDate startDate;

    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(Boolean leave) {
        isLeave = leave;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomId=" + roomId +
                ", facultyId=" + facultyId +
                ", number='" + number + '\'' +
                ", registrationDate=" + registrationDate +
                ", phone='" + phone + '\'' +
                ", photo='" + photo + '\'' +
                ", isLeave=" + isLeave +
                ", room=" + room +
                ", faculty=" + faculty +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
