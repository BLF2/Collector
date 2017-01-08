package net.blf2.dao;

import java.util.List;

/**
 * Created by blf2 on 17-1-8.
 * 基本接口
 */
public interface IBaseDao<T> {
    public T getInfoById(String id);//根据id查询一条数据
    public List<T> getInfoAll();//查询所有数据
    public Boolean updateInfoById(String id);//更新一条数据
    public Boolean deleteInfoById(String id);//删除一条数据
    public T addInfo(T tInfo);//插入一条数据
}
