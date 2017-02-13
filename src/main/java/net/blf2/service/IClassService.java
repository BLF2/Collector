package net.blf2.service;

import net.blf2.entity.ClassInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by blf2 on 17-2-11.
 */
public interface IClassService {
    boolean registerClassInfo(ClassInfo classInfo);
    ClassInfo findClassInfoByClassId(String classId);
    List<ClassInfo> findClassInfoAll();
    boolean checkMonitorHasCreatedClass(String monitorId) throws Exception;
    boolean checkMajorNameGradeNumHasExist(String majorName,String classGrade,String classNum) throws Exception;
    ClassInfo findClassInfoByMonitorId(String monitorId);
    List<String> findMajorNameGradeNumsAll();
}
