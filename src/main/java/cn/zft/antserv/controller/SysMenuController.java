package cn.zft.antserv.controller;

import cn.zft.antserv.entity.SysMenus;
import cn.zft.antserv.model.BaseVM;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.model.SelectVM;
import cn.zft.antserv.service.SysMenusService;
import cn.zft.antserv.utils.ResponseCode;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/menu")
public class SysMenuController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysMenusService sysMenusService;

    //详情
    @GetMapping("/{id}")
    public SysMenus get(@PathVariable("id") Integer id) {
        return sysMenusService.get(id);
    }

    //查询
    @GetMapping
    public DataTableVM get(@RequestParam Map<String, Object> params) {
        return sysMenusService.findList(params);
    }

    //创建
    @PostMapping
    public BaseVM.Response post(@RequestBody SysMenus user) {
        sysMenusService.add(user);
        return ResponseCode.getBaseVM("00200");
    }

    //修改
    @PutMapping("/{id}")
    public BaseVM.Response put(@PathVariable("id") Integer id, @RequestBody SysMenus entity, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> logger.error(error.getDefaultMessage()));
        }
        entity.setId(id);
        sysMenusService.update(entity);
        return ResponseCode.getBaseVM("00200");
    }

    //删除
    @DeleteMapping("/{id}")
    public BaseVM.Response delete(@PathVariable("id") Integer id) {
        sysMenusService.delete(id);
        return ResponseCode.getBaseVM("00200");
    }

    @GetMapping("/home")
    public List<Map<String, Object>> home(@RequestAttribute Claims claims) {
        return sysMenusService.getMenus(claims);
    }

    @GetMapping("/role")
    public List<Map<String, Object>> role() {
        return sysMenusService.getRoleMenus();
    }

    //下拉框
    @GetMapping("/select")
    public SelectVM.Response select(SelectVM.Request request) {
        return sysMenusService.select(request);
    }
}
