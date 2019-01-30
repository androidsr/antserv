package cn.zft.antserv.service;

import cn.zft.antserv.entity.SysParams;
import cn.zft.antserv.model.DataTableVM;

import java.util.List;
import java.util.Map;

public interface SysParamsService {

    List<Map<String, String>> findByGroupId(String groupId);

    SysParams get(Integer id);

    DataTableVM findList(Map<String, Object> params);

    void add(SysParams user);

    void update(SysParams entity);

    void delete(Integer id);
}
