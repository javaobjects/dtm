package com.rabbiter.dm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xianxian
 * @date 2022-02-09
 */
public class DepartApplication {
    private Long id;

    private Long applyUserId;

    private SystemUser applyUser;

    private String reason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time;

    private Long studentId;

    private Student student;

    private Boolean isFinished;

    private Boolean isPass;

    private List<DepartApplicationUser> operateList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public SystemUser getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(SystemUser applyUser) {
        this.applyUser = applyUser;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean finished) {
        isFinished = finished;
    }

    public Boolean getIsPass() {
        return isPass;
    }

    public void setIsPass(Boolean pass) {
        isPass = pass;
    }

    public List<DepartApplicationUser> getOperateList() {
        return operateList;
    }

    public void setOperateList(List<DepartApplicationUser> operateList) {
        this.operateList = operateList;
    }

    @Override
    public String toString() {
        return "DepartApplication{" +
                "id=" + id +
                ", applyUserId=" + applyUserId +
                ", applyUser=" + applyUser +
                ", reason='" + reason + '\'' +
                ", time=" + time +
                ", studentId=" + studentId +
                ", student=" + student +
                ", isFinished=" + isFinished +
                ", isPass=" + isPass +
                ", operateList=" + operateList +
                '}';
    }
}
