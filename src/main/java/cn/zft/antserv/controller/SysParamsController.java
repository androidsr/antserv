package cn.zft.antserv.controller;

import cn.zft.antserv.entity.SysParams;
import cn.zft.antserv.model.BaseVM;
import cn.zft.antserv.model.DataTableVM;
import cn.zft.antserv.service.SysParamsService;
import cn.zft.antserv.utils.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/params")
public class SysParamsController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysParamsService sysParamsService;

    //详情
    @GetMapping("/{id}")
    public SysParams get(@PathVariable("id") Integer id) {
        return sysParamsService.get(id);
    }

    //查询
    @GetMapping
    public DataTableVM get(@RequestParam Map<String, Object> params) {
        return sysParamsService.findList(params);
    }

    //创建
    @PostMapping
    public BaseVM.Response post(@RequestBody SysParams user) {
        sysParamsService.add(user);
        return ResponseCode.getBaseVM("00200");
    }

    //修改
    @PutMapping("/{id}")
    public BaseVM.Response put(@PathVariable("id") Integer id, @RequestBody SysParams entity) {
        entity.setId(id);
        sysParamsService.update(entity);
        return ResponseCode.getBaseVM("00200");
    }

    //删除
    @DeleteMapping("/{id}")
    public BaseVM.Response delete(@PathVariable("id") Integer id) {
        sysParamsService.delete(id);
        return ResponseCode.getBaseVM("00200");
    }

    //下拉框
    @GetMapping("/select")
    public List<Map<String, String>> findByGroupId(String groupId) {
        return sysParamsService.findByGroupId(groupId);
    }

}
