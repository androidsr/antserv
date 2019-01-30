package cn.zft.antserv.mapper;

import cn.zft.antserv.entity.SysParams;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysParamsMapper extends Mapper<SysParams, Integer> {
    List<Map<String, String>> findByGroupId(String groupId);
}
