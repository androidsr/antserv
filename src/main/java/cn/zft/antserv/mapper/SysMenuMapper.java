package cn.zft.antserv.mapper;

import cn.zft.antserv.entity.SysMenus;
import cn.zft.antserv.model.SelectVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends Mapper<SysMenus, Integer> {
    List<SysMenus> getMenus(@Param("role") String role);

    List<SelectVM.Select> select(SelectVM.Request request);
}
