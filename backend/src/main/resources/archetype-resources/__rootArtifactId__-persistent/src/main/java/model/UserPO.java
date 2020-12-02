#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *   自动生成的PO,不要手动修改
 *
 */
package ${package}.model;

import ${groupId}.bury.annotations.ColumnName;
import ${groupId}.bury.annotations.TableName;
import ${groupId}.bury.bean.PO;

import java.util.Date;

@TableName("tm_user")
public class UserPO extends PO
{

    public UserPO()
    {
    }

                                                                        
    public UserPO(Integer userId)
    {
        this.userId = userId;
    }

    @ColumnName(value = "user_id", isPK = true, autoIncrement = true)
    private Integer userId;

    @ColumnName(value = "user_code", isPK = false, autoIncrement = false)
    private String userCode;

    @ColumnName(value = "user_name", isPK = false, autoIncrement = false)
    private String userName;

    @ColumnName(value = "password", isPK = false, autoIncrement = false)
    private String password;

    @ColumnName(value = "sex", isPK = false, autoIncrement = false)
    private Integer sex;

    @ColumnName(value = "phone", isPK = false, autoIncrement = false)
    private String phone;

    @ColumnName(value = "mobile", isPK = false, autoIncrement = false)
    private String mobile;

    @ColumnName(value = "email", isPK = false, autoIncrement = false)
    private String email;

    @ColumnName(value = "birthday", isPK = false, autoIncrement = false)
    private Date birthday;

    @ColumnName(value = "avatar", isPK = false, autoIncrement = false)
    private String avatar;

    @ColumnName(value = "user_type", isPK = false, autoIncrement = false)
    private Integer userType;

    @ColumnName(value = "user_status", isPK = false, autoIncrement = false)
    private Integer userStatus;

    @ColumnName(value = "is_delete", isPK = false, autoIncrement = false)
    private Integer isDelete;

    @ColumnName(value = "create_date", isPK = false, autoIncrement = false)
    private Date createDate;

    @ColumnName(value = "create_by", isPK = false, autoIncrement = false)
    private Integer createBy;

    @ColumnName(value = "update_date", isPK = false, autoIncrement = false)
    private Date updateDate;

    @ColumnName(value = "update_by", isPK = false, autoIncrement = false)
    private Integer updateBy;


    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
        
    public Integer getUserId()
    {
        return this.userId;
    }
    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }
        
    public String getUserCode()
    {
        return this.userCode;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
        
    public String getUserName()
    {
        return this.userName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
        
    public String getPassword()
    {
        return this.password;
    }
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }
        
    public Integer getSex()
    {
        return this.sex;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
        
    public String getPhone()
    {
        return this.phone;
    }
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
        
    public String getMobile()
    {
        return this.mobile;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
        
    public String getEmail()
    {
        return this.email;
    }
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }
        
    public Date getBirthday()
    {
        return this.birthday;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
        
    public String getAvatar()
    {
        return this.avatar;
    }
    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }
        
    public Integer getUserType()
    {
        return this.userType;
    }
    public void setUserStatus(Integer userStatus)
    {
        this.userStatus = userStatus;
    }
        
    public Integer getUserStatus()
    {
        return this.userStatus;
    }
    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }
        
    public Integer getIsDelete()
    {
        return this.isDelete;
    }
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
        
    public Date getCreateDate()
    {
        return this.createDate;
    }
    public void setCreateBy(Integer createBy)
    {
        this.createBy = createBy;
    }
        
    public Integer getCreateBy()
    {
        return this.createBy;
    }
    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }
        
    public Date getUpdateDate()
    {
        return this.updateDate;
    }
    public void setUpdateBy(Integer updateBy)
    {
        this.updateBy = updateBy;
    }
        
    public Integer getUpdateBy()
    {
        return this.updateBy;
    }
}