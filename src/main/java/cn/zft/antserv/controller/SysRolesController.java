package cn.zft.antserv.controller;

import cn.zft.antserv.entity.SysParams;
import cn.zft.antserv.entity.SysRoles;
import cn.zft.antserv.model.BaseVM;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import cn.zft.antserv.service.SysParamsService;
import cn.zft.antserv.service.SysRolesService;
import cn.zft.antserv.utils.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class SysRolesController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRolesService sysRolesService;

    //详情
    @GetMapping("/{id}")
    public SysRoles get(@PathVariable("id") Integer id) {
        return sysRolesService.get(id);
    }

    //查询
    @GetMapping
    public DataTableVM get(@RequestParam Map<String, Object> params) {
        return sysRolesService.findList(params);
    }

    //创建
    @PostMapping
    public BaseVM.Response post(@RequestBody SysRoles user) {
        sysRolesService.add(user);
        return ResponseCode.getBaseVM("00200");
    }

    //修改
    @PutMapping("/{id}")
    public BaseVM.Response put(@PathVariable("id") Integer id, @RequestBody SysRoles entity) {
        entity.setId(id);
        sysRolesService.update(entity);
        return ResponseCode.getBaseVM("00200");
    }

    //删除
    @DeleteMapping("/{id}")
    public BaseVM.Response delete(@PathVariable("id") Integer id) {
        sysRolesService.delete(id);
        return ResponseCode.getBaseVM("00200");
    }

    //下拉框
    @GetMapping("/select")
    public SelectVM.Response get(SelectVM.Request req) {
        return sysRolesService.select(req);
    }

}
