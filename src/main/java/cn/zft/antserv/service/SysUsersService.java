package cn.zft.antserv.service;

import cn.zft.antserv.entity.SysUsers;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;

import java.util.Map;

public interface SysUsersService {

    SysUsers get(String id);

    DataTableVM findList(Map<String, Object> params);

    void add(SysUsers user);

    void update(SysUsers entity);

    void delete(String id);

    SelectVM.Response select(SelectVM.Request req);
}
