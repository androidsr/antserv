package cn.zft.antserv.controller;

import cn.zft.antserv.entity.SysUsers;
import cn.zft.antserv.model.BaseVM;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import cn.zft.antserv.service.SysUsersService;
import cn.zft.antserv.utils.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class SysUsersController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUsersService sysUsersService;

    //详情
    @GetMapping("/{id}")
    public SysUsers get(@PathVariable("id") String id) {
        return sysUsersService.get(id);
    }

    //查询
    @GetMapping
    public DataTableVM get(@RequestParam Map<String, Object> params) {
        return sysUsersService.findList(params);
    }

    //创建
    @PostMapping
    public BaseVM.Response post(@RequestBody SysUsers user) {
        sysUsersService.add(user);
        return ResponseCode.getBaseVM("00200");
    }

    //修改
    @PutMapping("/{id}")
    public BaseVM.Response put(@PathVariable("id") String id, @RequestBody SysUsers entity) {
        entity.setId(id);
        sysUsersService.update(entity);
        return ResponseCode.getBaseVM("00200");
    }

    //删除
    @DeleteMapping("/{id}")
    public BaseVM.Response delete(@PathVariable("id") String id) {
        sysUsersService.delete(id);
        return ResponseCode.getBaseVM("00200");
    }

    //下拉框
    @GetMapping("/select")
    public SelectVM.Response select(SelectVM.Request req) {
        return sysUsersService.select(req);
    }

}
