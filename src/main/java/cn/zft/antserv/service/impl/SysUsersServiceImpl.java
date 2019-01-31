package cn.zft.antserv.service.impl;

import cn.zft.antserv.entity.SysUsers;
import cn.zft.antserv.mapper.SysUsersMapper;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import cn.zft.antserv.service.SysUsersService;
import cn.zft.antserv.utils.StaticFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysUsersServiceImpl implements SysUsersService {

    @Autowired
    SysUsersMapper sysUsersMapper;

    @Override
    public SysUsers get(String id) {
        return sysUsersMapper.get(id);
    }

    @Override
    public DataTableVM findList(Map<String, Object> params) {
        StaticFunc.page(params);
        List<SysUsers> data = sysUsersMapper.findList(params);
        Integer count = sysUsersMapper.count(params);
        return new DataTableVM(data, count);
    }

    @Override
    public void add(SysUsers user) {
        sysUsersMapper.add(user);
    }

    @Override
    public void update(SysUsers entity) {
        sysUsersMapper.update(entity);
    }

    @Override
    public void delete(String id) {
        sysUsersMapper.delete(id);
    }

    @Override
    public SelectVM.Response select(SelectVM.Request req) {
        req.configPage();
        return null;
    }

}
