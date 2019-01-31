package cn.zft.antserv.service.impl;

import cn.zft.antserv.entity.SysRoles;
import cn.zft.antserv.mapper.SysRolesMapper;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import cn.zft.antserv.service.SysRolesService;
import cn.zft.antserv.utils.StaticFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRolesServiceImpl implements SysRolesService {

    @Autowired
    SysRolesMapper sysRolesMapper;

    @Override
    public SysRoles get(Integer id) {
        return sysRolesMapper.get(id);
    }

    @Override
    public DataTableVM findList(Map<String, Object> params) {
        StaticFunc.page(params);
        List<SysRoles> data = sysRolesMapper.findList(params);
        Integer count = sysRolesMapper.count(params);
        return new DataTableVM(data, count);
    }

    @Override
    public void add(SysRoles user) {
        sysRolesMapper.add(user);
    }

    @Override
    public void update(SysRoles entity) {
        sysRolesMapper.update(entity);
    }

    @Override
    public void delete(Integer id) {
        sysRolesMapper.delete(id);
    }

    @Override
    public SelectVM.Response select(SelectVM.Request req) {
        req.configPage();
        List<SelectVM.Select> data = sysRolesMapper.select(req);
        SelectVM.Response res = new SelectVM.Response();
        res.setData(data);
        return res;
    }

}
