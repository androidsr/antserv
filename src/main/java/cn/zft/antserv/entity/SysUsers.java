package cn.zft.antserv.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysUsers implements java.io.Serializable {
	private String id;
	private String name;
	private String phone;
	private String email;
	private String sex;
	private String age;
	private String passwd;
	private String roleId;
}