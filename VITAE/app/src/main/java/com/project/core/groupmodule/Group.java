package com.project.core.groupmodule;

import com.project.core.usermodule.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ahmet Kaymak on 26.12.2016.
 */

public class Group {

    public Group(){}
    public Group(int groupId, int subjectId, String groupName,String groupType, User admin, String description, boolean isPrivate) {
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.groupName = groupName;
        this.groupType=groupType;
        this.admin = admin;
        this.description = description;
        this.isPrivate = isPrivate;
    }

    private int groupId;
    private int subjectId;
    private String groupName;
    private String groupType;
    private Date createDate;
    private User admin;
    private String description;
    //Photo nesnesi lazım
    private boolean isPrivate;

    private ArrayList<User> groupHaveUser=new ArrayList<>();

    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupType() {
        return groupType;
    }
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public User getAdmin() {
        return admin;
    }
    public void setAdmin(User admin) {
        this.admin = admin;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isPrivate() {
        return isPrivate;
    }
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

}
