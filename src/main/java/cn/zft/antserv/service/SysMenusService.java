package cn.zft.antserv.service;

import cn.zft.antserv.entity.SysMenus;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Map;

public interface SysMenusService {
    void add(SysMenus user);

    void update(SysMenus user);

    void delete(Integer id);

    SysMenus get(Integer id);

    DataTableVM findList(Map<String, Object> params);

    List<Map<String, Object>> getMenus(Claims claims);

    SelectVM.Response select(SelectVM.Request request);

    List<Map<String, Object>> getRoleMenus();
}
