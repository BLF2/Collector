package net.blf2.dao;

import net.blf2.entity.ClassInfo;

import java.util.List;

/**
 * Created by blf2 on 17-1-8.
 */
public interface IClassDao {
    ClassInfo queryClassInfoByClassId(String classId);
    void insertClassInfo(ClassInfo classInfo);
    void updateClassInfo(ClassInfo classInfo);
    void deleteClassInfoByClassId(String classId);
    List<ClassInfo> queryClassInfoAll();
}
