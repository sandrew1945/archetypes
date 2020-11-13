#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.common;

/**
 * Created by summer on 2018/7/3.
 */
public enum Fixcode
{
    STATUS_ENABLE(1001, 10011001, "有效"),
    STATUS_DISABLE(1001, 10011002, "无效"),
    SEX_MALE(1002, 10021001, "男"),
    SEX_FEMALE(1002, 10021002, "女"),
    SEX_UNKNOW(1002, 10021003, "未知"),
    IF_TYPE_YES(1003, 10031001, "是"),
    IF_TYPE_NO(1003, 10031002, "否"),
    ACCESS_TYPE_LOGIN(1004, 10041001, "登录"),
    ACCESS_TYPE_LOGOUT(1004, 10041002, "登出"),

    MARITAL_STATUS_UNMARRIED(2001, 20011001, "未婚"),
    MARITAL_STATUS_MARRIED(2001, 20011002, "已婚"),
    MARITAL_STATUS_DIVORCED(2001, 20011003, "离异"),
    MARITAL_STATUS_BEREFT(2001, 20011004, "丧偶"),
    MARITAL_STATUS_REMARRIED(2001, 20011005, "再婚"),

    NATION_HANZ(2002, 20021001, "汉族"),
    NATION_MANZ(2002, 20021002, "满族"),
    NATION_HUIZ(2002, 20021003, "回族"),
    NATION_CHAOXZ(2002, 20021004, "朝鲜族"),
    NATION_MENGGZ(2002, 20021005, "蒙古族"),
    NATION_MIAOZ(2002, 20021006, "苗族"),
    NATION_ZHUANGZ(2002, 20021007, "壮族"),
    NATION_ZANGZ(2002, 20021008, "藏族"),
    NATION_YAOZ(2002, 20021009, "瑶族"),
    NATION_GAOSHANZ(2002, 20021010, "高山族"),
    NATION_XIBZ(2002, 20021011, "锡伯族"),
    NATION_TUJZ(2002, 20021012, "土家族"),
    NATION_DAHEZ(2002, 20021013, "达翰尔族"),
    NATION_OTHER(2002, 20021014, "其他"),

    BLOOD_TYPE_A(2004, 20041001, "A型"),
    BLOOD_TYPE_B(2004, 20041002, "B型"),
    BLOOD_TYPE_AB(2004, 20041003, "AB型"),
    BLOOD_TYPE_O(2004, 20041004, "O型"),
    BLOOD_TYPE_RHYI(2004, 20041005, "Rh阴性"),
    BLOOD_TYPE_RHYA(2004, 20041006, "Rh阳性"),

    EDUCATION_DOCTOR(2007, 20071001, "博士"),
    EDUCATION_MASTER(2007, 20071002, "硕士"),
    EDUCATION_BACHELOR(2007, 20071003, "本科"),
    EDUCATION_JUNIOR(2007, 20071004, "大科"),
    EDUCATION_PRO_HIGH(2007, 20071005, "职高"),
    EDUCATION_TECHNICAL(2007, 20071006, "技校"),
    EDUCATION_SENIOR_HIGH(2007, 20071007, "高中"),
    EDUCATION_JUNIOR_HIGH(2007, 20071008, "初中"),
    EDUCATION_PRIMARY(2007, 20071009, "小学"),
    EDUCATION_OTHER(2007, 20071010, "其他"),

    CERT_TYPE_IDENTITY(2008, 20081001, "身份证"),
    CERT_TYPE_OFFICER(2008, 20081002, "军官证"),
    CERT_TYPE_PASSPORT(2008, 20081003, "护照"),
    CERT_TYPE_OTHER(2008, 20081004, "其他");


    private Integer type;       // 字典类别
    private Integer fixcode;     // 字典码
    private String desc;        // 描述
    private Fixcode(Integer type, Integer fixcode, String desc)
    {
        this.type = type;
        this.fixcode = fixcode;
        this.desc = desc;
    }

    public Integer getType()
    {
        return type;
    }

    public Integer getFixcode()
    {
        return fixcode;
    }

    public String getDesc()
    {
        return desc;
    }
}