package cn.zft.antserv.service.impl;

import cn.zft.antserv.entity.SysButtons;
import cn.zft.antserv.entity.SysMenus;
import cn.zft.antserv.mapper.SysButtonsMapper;
import cn.zft.antserv.mapper.SysMenuMapper;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import cn.zft.antserv.service.SysMenusService;
import cn.zft.antserv.utils.StaticFunc;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysMenusServiceImpl implements SysMenusService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysButtonsMapper sysButtonsMapper;

    @Override
    public void add(SysMenus user) {
        sysMenuMapper.insert(user);
    }

    @Override
    public void update(SysMenus user) {
        sysMenuMapper.update(user);
    }

    @Override
    public void delete(Integer id) {
        sysMenuMapper.delete(id);
    }

    @Override
    public SysMenus get(Integer id) {
        return sysMenuMapper.get(id);
    }

    @Override
    public DataTableVM findList(Map<String, Object> params) {
        StaticFunc.page(params);
        List<SysMenus> data = sysMenuMapper.findList(params);
        Integer count = sysMenuMapper.count(params);
        return new DataTableVM(data, count);
    }

    @Override
    public SelectVM.Response select(SelectVM.Request request) {
        request.configPage();
        List<SelectVM.Select> data = sysMenuMapper.select(request);
        SelectVM.Response res = new SelectVM.Response();
        res.setData(data);
        return res;
    }

    @Override
    public List<Map<String, Object>> getRoleMenus() {
        List<SysMenus> data = sysMenuMapper.getMenus(null);
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1;
        Map<String, Object> map2;
        Map<String, Object> map3;
        List<Map<String, Object>> children;
        List<Map<String, Object>> children2;
        for (SysMenus m : data) {
            if (!"1".equals(m.getMenuLevel())) {
                continue;
            }
            Integer id = m.getId();
            map1 = new HashMap<>();
            map1.put("id", m.getId() + "");
            map1.put("title", m.getTitle());
            children = new ArrayList<>();
            for (SysMenus m2 : data) {
                if (!"2".equals(m2.getMenuLevel()) || !(id + "").equals(m2.getSuperId())) {
                    continue;
                }
                map2 = new HashMap<>();
                map2.put("id", m2.getId() + "");
                map2.put("title", m2.getTitle());
                String btnIds = m2.getBtnId();
                if (!StringUtils.isEmpty(btnIds)) {
                    String[] array;
                    if (btnIds.contains(",")) {
                        array = btnIds.split(",");
                    } else {
                        array = new String[]{btnIds};
                    }
                    List<SysButtons> buttons = sysButtonsMapper.array(array);
                    children2 = new ArrayList<>();
                    for (SysButtons btn : buttons) {
                        map3 = new HashMap<>();
                        map3.put("id", m2.getId() + "-" + btn.getId());
                        map3.put("title", btn.getTitle());
                        children2.add(map3);
                    }
                    map2.put("children", children2);
                }
                children.add(map2);
            }
            map1.put("children", children);
            list.add(map1);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getMenus(Claims claims) {

        List<SysMenus> data = sysMenuMapper.getMenus(claims.get("role",String.class));
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map1;
        Map<String, String> map2;
        List<Map<String, String>> children;
        for (SysMenus m : data) {
            if (!"1".equals(m.getMenuLevel())) {
                continue;
            }
            Integer id = m.getId();
            map1 = new HashMap<>();
            map1.put("title", m.getTitle());
            map1.put("key", m.getMenuKey());
            map1.put("icon", m.getIcon());
            children = new ArrayList<>();
            for (SysMenus m2 : data) {
                if (!"2".equals(m2.getMenuLevel()) || !(id + "").equals(m2.getSuperId())) {
                    continue;
                }
                map2 = new HashMap<>();
                map2.put("title", m2.getTitle());
                map2.put("key", m2.getMenuKey());
                map2.put("icon", m2.getIcon());
                children.add(map2);
            }
            map1.put("children", children);
            list.add(map1);
        }
        return list;
    }
}
