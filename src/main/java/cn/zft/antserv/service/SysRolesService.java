package cn.zft.antserv.service;

import cn.zft.antserv.entity.SysRoles;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;

import java.util.List;
import java.util.Map;

public interface SysRolesService {

    SysRoles get(Integer id);

    DataTableVM findList(Map<String, Object> params);

    void add(SysRoles user);

    void update(SysRoles entity);

    void delete(Integer id);

    SelectVM.Response select(SelectVM.Request req);
}
