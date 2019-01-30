package cn.zft.antserv.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysRoles implements java.io.Serializable {
    private Integer id;
    private String roleName;
    private String menuIds;
}