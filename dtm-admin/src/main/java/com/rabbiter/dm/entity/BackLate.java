package com.rabbiter.dm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author xianxian
 * @date 2022-02-16
 */
public class BackLate {
    private Long id;

    @NotNull
    private Long studentId;

    private Student student;


    private String reason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime backDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getBackDate() {
        return backDate;
    }

    public void setBackDate(LocalDateTime backDate) {
        this.backDate = backDate;
    }

    @Override
    public String toString() {
        return "BackLate{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", student=" + student +
                ", reason='" + reason + '\'' +
                ", backDate=" + backDate +
                '}';
    }
}
