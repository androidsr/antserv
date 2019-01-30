package cn.zft.antserv.mapper;

import cn.zft.antserv.entity.SysButtons;
import cn.zft.antserv.entity.SysRoles;
import cn.zft.antserv.model.SelectVM;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysButtonsMapper extends Mapper<SysButtons, Integer> {
    List<SelectVM.Select> select(SelectVM.Request req);

    List<SysButtons> array(String[] id);

    List<SysButtons> getMenuButton(String menuKey, String role);
}
