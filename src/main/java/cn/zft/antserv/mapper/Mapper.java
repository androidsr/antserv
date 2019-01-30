package cn.zft.antserv.mapper;

import cn.zft.antserv.model.SelectVM;

import java.util.List;
import java.util.Map;

public interface Mapper<T, PK> {
    T get(PK id);

    List<T> findList(Map<String, Object> params);

    void insert(T entity);

    void delete(PK id);

    void update(T entity);

    Integer count(Map<String, Object> params);

    List<SelectVM.Select> select(SelectVM.Request req);
}