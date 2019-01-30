package cn.zft.antserv.service.impl;

import cn.zft.antserv.entity.SysMenus;
import cn.zft.antserv.entity.SysParams;
import cn.zft.antserv.mapper.SysParamsMapper;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.service.SysParamsService;
import cn.zft.antserv.utils.StaticFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysParamsServiceImpl implements SysParamsService {

    @Autowired
    SysParamsMapper sysParamsMapper;

    @Override
    public List<Map<String, String>> findByGroupId(String groupId) {
        return sysParamsMapper.findByGroupId(groupId);
    }

    @Override
    public SysParams get(Integer id) {
        return sysParamsMapper.get(id);
    }

    @Override
    public DataTableVM findList(Map<String, Object> params) {
        StaticFunc.page(params);
        List<SysParams> data = sysParamsMapper.findList(params);
        Integer count = sysParamsMapper.count(params);
        return new DataTableVM(data, count);
    }

    @Override
    public void add(SysParams user) {
        sysParamsMapper.insert(user);
    }

    @Override
    public void update(SysParams entity) {
        sysParamsMapper.update(entity);
    }

    @Override
    public void delete(Integer id) {
        sysParamsMapper.delete(id);
    }

}
