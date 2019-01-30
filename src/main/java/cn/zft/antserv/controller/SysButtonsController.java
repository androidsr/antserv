package cn.zft.antserv.controller;

import cn.zft.antserv.entity.SysButtons;
import cn.zft.antserv.model.BaseVM;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import cn.zft.antserv.service.SysButtonsService;
import cn.zft.antserv.utils.ResponseCode;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/button")
public class SysButtonsController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysButtonsService sysButtonsService;

    //详情
    @GetMapping("/{id}")
    public SysButtons get(@PathVariable("id") Integer id) {
        return sysButtonsService.get(id);
    }

    //查询
    @GetMapping
    public DataTableVM get(@RequestParam Map<String, Object> params) {
        return sysButtonsService.findList(params);
    }

    //创建
    @PostMapping
    public BaseVM.Response post(@RequestBody SysButtons user) {
        sysButtonsService.add(user);
        return ResponseCode.getBaseVM("00200");
    }

    //修改
    @PutMapping("/{id}")
    public BaseVM.Response put(@PathVariable("id") Integer id, @RequestBody SysButtons entity) {
        entity.setId(id);
        sysButtonsService.update(entity);
        return ResponseCode.getBaseVM("00200");
    }

    //删除
    @DeleteMapping("/{id}")
    public BaseVM.Response delete(@PathVariable("id") Integer id) {
        sysButtonsService.delete(id);
        return ResponseCode.getBaseVM("00200");
    }

    //菜单按钮
    @GetMapping("/array")
    public List<SysButtons> get(String menuKey, @RequestAttribute Claims claims) {
        return sysButtonsService.array(menuKey, claims);
    }

    //下拉框
    @GetMapping("/select")
    public SelectVM.Response get(SelectVM.Request req) {
        return sysButtonsService.select(req);
    }

}
