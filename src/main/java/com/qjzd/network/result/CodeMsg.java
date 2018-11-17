package com.qjzd.network.result;

public class CodeMsg {
	
	private int code;
	private String msg;
	
	//通用的错误码
	public static CodeMsg SUCCESS = new CodeMsg(1, "success");
	public static CodeMsg NODATA = new CodeMsg(-1, "无数据");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
	//登录模块 5002XX
	public static CodeMsg USERNAME_EMPTY = new CodeMsg(500209, "用户名不能为空");
	public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
	public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
	public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
	public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
	public static CodeMsg USERNAME_NOT_EXIST = new CodeMsg(500214, "用户名不存在");
	public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
	public static CodeMsg OLDPASS_ERROR = new CodeMsg(500215, "旧密码错误");
	public static CodeMsg REPASS_ERROR = new CodeMsg(500215, "两次密码输入不正确");
	public static CodeMsg TOWPASS_ERROR = new CodeMsg(500215, "新密码与旧密码相同");

	//基本信息模块
	public static CodeMsg TYPE_ERROR = new CodeMsg(500216, "类型不能为空或不正确");
	public static CodeMsg CONTEXT_ERROR = new CodeMsg(500216, "内容不能为空");
	public static CodeMsg TITLE_ERROR = new CodeMsg(500216, "名称不能为空");
	public static CodeMsg CONTACTS_ERROR = new CodeMsg(500216, "联系人不能为空");
	public static CodeMsg PHONE_ERROR = new CodeMsg(500216, "手机号不能为空");
	public static CodeMsg ADDRESS_ERROR = new CodeMsg(500216, "地址不能为空");
	
	//产品模块 5003XX
	public static CodeMsg TYPENAME_ALREADY_EXISTED = new CodeMsg(500310, "名称已存在");
	public static CodeMsg PRODUCT_EXISTED_TYPE = new CodeMsg(500310, "存在此类型的产品，无法删除!");
	//订单模块 5004XX
	
	//秒杀模块 5005XX
	
	private CodeMsg( ) {
	}
			
	private CodeMsg( int code,String msg ) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public CodeMsg fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMsg(code, message);
	}

	@Override
	public String toString() {
		return "CodeMsg [code=" + code + ", msg=" + msg + "]";
	}
	
	
}
