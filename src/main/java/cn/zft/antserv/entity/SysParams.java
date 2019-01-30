package cn.zft.antserv.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysParams implements java.io.Serializable {
    private Integer id;
    private String paramName;
    private String paramValue;
    private String groupName;
    private String groupId;
    private String otherValue;
}