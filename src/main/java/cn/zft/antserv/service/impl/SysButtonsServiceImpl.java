package cn.zft.antserv.service.impl;

import cn.zft.antserv.entity.SysButtons;
import cn.zft.antserv.mapper.SysButtonsMapper;
import cn.zft.antserv.mapper.SysMenuMapper;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import cn.zft.antserv.service.SysButtonsService;
import cn.zft.antserv.utils.StaticFunc;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysButtonsServiceImpl implements SysButtonsService {

    @Autowired
    SysButtonsMapper sysButtonsMapper;

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public SysButtons get(Integer id) {
        return sysButtonsMapper.get(id);
    }

    @Override
    public DataTableVM findList(Map<String, Object> params) {
        StaticFunc.page(params);
        List<SysButtons> data = sysButtonsMapper.findList(params);
        Integer count = sysButtonsMapper.count(params);
        return new DataTableVM(data, count);
    }

    @Override
    public void add(SysButtons user) {
        sysButtonsMapper.add(user);
    }

    @Override
    public void update(SysButtons entity) {
        sysButtonsMapper.update(entity);
    }

    @Override
    public void delete(Integer id) {
        sysButtonsMapper.delete(id);
    }

    @Override
    public SelectVM.Response select(SelectVM.Request req) {
        List<SelectVM.Select> data = sysButtonsMapper.select(req);
        SelectVM.Response res = new SelectVM.Response();
        res.setData(data);
        return res;
    }

    @Override
    public List<SysButtons> array(String menuKey, Claims claims) {
        String role = (String) claims.get("role");
        List<SysButtons> menu = sysButtonsMapper.getMenuButton(menuKey, role);
        return menu;
    }

}
