package cn.zft.antserv.mapper;

import cn.zft.antserv.entity.SysRoles;
import cn.zft.antserv.model.SelectVM;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRolesMapper extends Mapper<SysRoles, Integer> {
    List<SelectVM.Select> select(SelectVM.Request req);
}
