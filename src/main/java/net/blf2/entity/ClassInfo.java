package net.blf2.entity;

/**
 * Created by blf2 on 17-1-8.
 * 班级信息
 */
public class ClassInfo {
    private String classId;//班级ID
    private String majorName;//专业
    private String classGrade;//年级
    private String classNum;//班级
    private UserInfo monitorInfo;//班长信息
    private String classNote;//班级备注

    public String getClassNote() {
        return classNote;
    }

    public void setClassNote(String classNote) {
        this.classNote = classNote;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public UserInfo getMonitorInfo() {
        return monitorInfo;
    }

    public void setMonitorInfo(UserInfo monitorInfo) {
        this.monitorInfo = monitorInfo;
    }
}
