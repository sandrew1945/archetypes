#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.common;

public interface Constants
{
	public static String LOGIN_USER = "loginUser"; // 登录用户
	public static String APP_KEY = "authc.appKey"; // appKey


	public static Integer STATUS_ENABLE = new Integer("10011001"); //有效
	public static Integer STATUS_DISABLE = new Integer("10011002"); //无效
	public static Integer SEX_MALE = new Integer("10021001"); //男
	public static Integer SEX_FEMALE = new Integer("10021002"); //女
	public static Integer SEX_UNKNOW = new Integer("10021003"); //未知
	public static Integer IF_TYPE_YES = new Integer("10031001"); //是
	public static Integer IF_TYPE_NO = new Integer("10031002"); //否

	public static Integer ITEM_BUSINESS_TYPE_I = new Integer("20011001"); //一级指标
	public static Integer ITEM_BUSINESS_TYPE_II = new Integer("20011002"); //二级指标
	public static Integer ITEM_BUSINESS_TYPE_III = new Integer("20011003"); //三级指标
	public static Integer ITEM_CLASSIFY_RC = new Integer("20021001"); //风控
	public static Integer ITEM_CLASSIFY_OP = new Integer("20021002"); //运营
	public static Integer ITEM_CLASSIFY_ECO = new Integer("20021003"); //经济
	public static Integer ITEM_LEVEL_SENIOR = new Integer("20031001"); //高级
	public static Integer ITEM_LEVEL_MIDDLE = new Integer("20031002"); //中级
	public static Integer ITEM_LEVEL_PRIMARY = new Integer("20031003"); //低级
	public static Integer SYSTEM_TYPE_OA = new Integer("20041001"); //OA
	public static Integer SYSTEM_TYPE_HS = new Integer("20041002"); //恒生交易柜台
	public static Integer SYSTEM_TYPE_JS = new Integer("20041003"); //极速交易柜台
	public static Integer SYSTEM_TYPE_MOT = new Integer("20041004"); //MOT
	public static Integer SYSTEM_TYPE_CRM = new Integer("20041005"); //CRM
	public static Integer SYSTEM_TYPE_DC = new Integer("20041006"); //数据中心
	public static Integer SYSTEM_TYPE_ZG = new Integer("20041007"); //资管系统
	public static Integer SYSTEM_TYPE_GZ = new Integer("20041008"); //估值系统
	public static Integer SYSTEM_TYPE_CW = new Integer("20041009"); //财务系统
	public static Integer ITEM_PERM_TYPE_DEPT = new Integer("20051001"); //部门权限
	public static Integer ITEM_PERM_TYPE_STAFF = new Integer("20051002"); //员工权限
	public static Integer API_CHANGE_TYPE_ADD = new Integer("20061001"); //新增
	public static Integer API_CHANGE_TYPE_EDIT = new Integer("20061002"); //编辑
	public static Integer API_CHANGE_TYPE_DEL = new Integer("20061003"); //删除
	public static Integer SYSTEM_APP_TPYE_ITEM = new Integer("20071001"); //指标
	public static Integer SYSTEM_APP_TPYE_APP = new Integer("20071002"); //应用
	public static Integer API_TYPE_API = new Integer("20081001"); //API
	public static Integer API_TYPE_ITEM = new Integer("20081002"); //指标
	public static Integer LINE_TPYE_JFGW = new Integer("20091001"); //经发管委
	public static Integer LINE_TPYE_JJYW = new Integer("20091002"); //经济业务管理条线
	public static Integer LINE_TPYE_LSKH = new Integer("20091003"); //零售客户条线
	public static Integer LINE_TPYE_JRKJ = new Integer("20091004"); //金融科技条线
	public static Integer LINE_TPYE_JRCP = new Integer("20091005"); //金融产品条线
	public static Integer LINE_TPYE_JYFK = new Integer("20091006"); //交易风控条线
	public static Integer LINE_TPYE_CFJG = new Integer("20091007"); //财富与机构条线
	public static Integer ITEM_TYPE_BASE = new Integer("20101001"); //基础
	public static Integer ITEM_TYPE_DERIVE = new Integer("20101002"); //衍生
	public static Integer ITEM_STATUS_ADD = new Integer("20111001"); //新增
	public static Integer ITEM_STATUS_USING = new Integer("20111002"); //在用
	public static Integer ITEM_STATUS_DISUSE = new Integer("20111003"); //停用
	public static Integer ITEM_STATUS_MODIFY = new Integer("20111004"); //修改
	public static Integer PROCESS_TYPE_PROCESS = new Integer("20121001"); //流程
	public static Integer PROCESS_TYPE_ITEM = new Integer("20121002"); //指标
	public static Integer COLLECT_TYPE_INTERFACE = new Integer("20131001"); //接口
	public static Integer COLLECT_TYPE_DATABASE = new Integer("20131002"); //数据库
	public static Integer COLLECT_TYPE_SCRIPT = new Integer("20131003"); //脚本任务
	public static Integer COLLECT_TYPE_OTHER = new Integer("20131004"); //其他
	public static Integer COLLECT_RES_SUCCESS = new Integer("20141001"); //成功
	public static Integer COLLECT_RES_FAILED = new Integer("20141002"); //失败
	public static Integer COLLECT_RES_PROCESS = new Integer("20141003"); //处理中


	public static Integer SEND_STATUS_NOT = new Integer("20151001"); //未发送
	public static Integer SEND_STATUS_ING = new Integer("20151002"); //发送中
	public static Integer SEND_STATUS_SUCCESS = new Integer("20151003"); //发送成功
	public static Integer SEND_STATUS_FAILED = new Integer("20151004"); //发送失败
}
