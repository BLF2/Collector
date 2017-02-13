package net.blf2.service.impl;

import net.blf2.dao.IClassDao;
import net.blf2.entity.ClassInfo;
import net.blf2.service.IClassService;
import net.blf2.util.Consts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by blf2 on 17-2-13.
 */
@Service("ClassService")
public class ClassService implements IClassService {

    @Resource
    private IClassDao classDao;

    public IClassDao getClassDao() {
        return classDao;
    }

    public void setClassDao(IClassDao classDao) {
        this.classDao = classDao;
    }

    public boolean registerClassInfo(ClassInfo classInfo) {
        try {
            classDao.insertClassInfo(classInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public ClassInfo findClassInfoByClassId(String classId) {
        ClassInfo classInfo = null;
        try {
            classInfo = classDao.queryClassInfoByClassId(classId);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return  classInfo;
    }

    public List<ClassInfo> findClassInfoAll() {
        List<ClassInfo> classInfoAll = null;
        try {
            classInfoAll = classDao.queryClassInfoAll();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return classInfoAll == null && classInfoAll.size() == 0 ? null : classInfoAll;
    }

    public boolean checkMonitorHasCreatedClass(String monitorId) throws Exception{
        String classId = null;
        try {
            classId = classDao.queryClassIdByMonitorId(monitorId);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Consts.DATABASE_RETURN_ERROR);
        }
        return classId != null;
    }

    public boolean checkMajorNameGradeNumHasExist(String majorName, String classGrade, String classNum) throws Exception{
        String classId = null;
        try {
            classId = classDao.queryClassIdByClassMajorNameGradeNum(majorName, classGrade, classNum);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Consts.DATABASE_RETURN_ERROR);
        }
        return classId != null;
    }

    public ClassInfo findClassInfoByMonitorId(String monitorId) {
        ClassInfo classInfo = null;
        try {
            classInfo = classDao.queryClassInfoByMonitorId(monitorId);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return classInfo;
    }

    public List<String> findMajorNameGradeNumsAll() {
        List<String> majorNameGradeNumsAll = null;
        try {
            majorNameGradeNumsAll = classDao.queryMajorNameGradeNums();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return majorNameGradeNumsAll == null || majorNameGradeNumsAll.size() == 0 ? null : majorNameGradeNumsAll;
    }
}
