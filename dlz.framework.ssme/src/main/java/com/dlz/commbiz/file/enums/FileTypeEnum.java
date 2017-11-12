package com.dlz.commbiz.file.enums;


public enum FileTypeEnum {
	fwb("0"),//富文本编辑器
	logo("1"),//用户logo
	sfz("2"),//身份证正面
	xsz("3"),//学生证1
	yyzz("4"),//营业执照正面
	ht("5"),//合同
	dkpz("6"),//打款凭证	
	ewm("7"),//二维码
	jdjyj("8"),//甲定价依据
	htqd("9"),//项目-合同签订
	htsc("10"),//合同上传&图纸
	goodsLogo("11");//商品缩略图
	/*
	 * 类型ID
	 */
	public String typeCode;
	
	private FileTypeEnum(String typeCode) {
		this.typeCode = typeCode;
	}
	
	/**  
	 * 获取typeCode
	 * @return typeCode typeCode  
	 */
	public String getTypeCode() {
		return typeCode;
	}
}
