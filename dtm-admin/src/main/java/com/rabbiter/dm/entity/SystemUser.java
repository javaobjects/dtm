package com.rabbiter.dm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author xianxian
 * @date 2021-12-08
 */
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1545931180051212983L;

    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    private String realName;

    @NotNull
    @Size(min = 5, max = 20)
    private String loginName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    transient private String password;

    private String icon;

    @Pattern(regexp = "([1][3,4,5,7,8,9][0-9]{9})|^$")
    private String cellphone;

    @Pattern(regexp = "(\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*)|^$")
    private String email;

    private List<SystemRole> userRole;

    private List<Long> userRoleId;

    private Set<String> permissions;

    private Long leaderId;

    private SystemUser leader;

    @NotNull
    private Long buildingId;

    private Building building;

    private Boolean isOnLine;

    private List<SystemFunction> functions;

    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SystemRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(List<SystemRole> userRole) {
        this.userRole = userRole;
    }

    public List<Long> getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(List<Long> userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public SystemUser getLeader() {
        return leader;
    }

    public void setLeader(SystemUser leader) {
        this.leader = leader;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Boolean getIsOnLine() {
        return isOnLine;
    }

    public void setIsOnLine(Boolean onLine) {
        isOnLine = onLine;
    }

    public List<SystemFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<SystemFunction> functions) {
        this.functions = functions;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", icon='" + icon + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", userRoleId=" + userRoleId +
                ", permissions=" + permissions +
                ", leaderId=" + leaderId +
                ", leader=" + leader +
                ", buildingId=" + buildingId +
                ", building=" + building +
                ", isOnLine=" + isOnLine +
                ", functions=" + functions +
                ", createTime=" + createTime +
                '}';
    }
}
