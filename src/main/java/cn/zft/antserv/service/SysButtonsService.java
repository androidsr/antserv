package cn.zft.antserv.service;

import cn.zft.antserv.entity.SysButtons;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.util.List;
import java.util.Map;

public interface SysButtonsService {

    SysButtons get(Integer id);

    DataTableVM findList(Map<String, Object> params);

    void add(SysButtons user);

    void update(SysButtons entity);

    void delete(Integer id);

    SelectVM.Response select(SelectVM.Request req);

    List<SysButtons> array(String menuKey, Claims claims);
}
