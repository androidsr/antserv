package cn.zft.antserv.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysMenus implements java.io.Serializable {
    private Integer id;
    private String title;
    private String menuKey;
    private String menuLevel;
    private String icon;
    private String superId;
    private String btnId;
    private String menuOrder;
}