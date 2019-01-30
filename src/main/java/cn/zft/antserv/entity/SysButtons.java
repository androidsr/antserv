package cn.zft.antserv.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysButtons implements java.io.Serializable {
    private Integer id;
    private String title;
    private String click;
    private String icon;
    private String state;
}