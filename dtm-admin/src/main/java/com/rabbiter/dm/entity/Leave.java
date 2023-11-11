package com.rabbiter.dm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author xianxian
 * @date 2022-03-03
 */
public class Leave {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    @NotBlank
    private String reason;

    private Boolean isBack;
    @NotNull
    private Long studentId;

    private Student student;

    @NotBlank
    private String target;

    private LocalDate backDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getIsBack() {
        return isBack;
    }

    public void setIsBack(Boolean back) {
        isBack = back;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public LocalDate getBackDate() {
        return backDate;
    }

    public void setBackDate(LocalDate backDate) {
        this.backDate = backDate;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                ", isBack=" + isBack +
                ", studentId=" + studentId +
                ", student=" + student +
                ", target='" + target + '\'' +
                ", backDate=" + backDate +
                '}';
    }
}
