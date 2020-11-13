#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *   自动生成的PO,不要手动修改
 *
 */
package ${package}.model;

import com.sandrew.bury.annotations.TableName;
import com.sandrew.bury.annotations.ColumnName;
import com.sandrew.bury.bean.PO;
import com.sandrew.bury.bean.Pack;
import com.sandrew.bury.bean.EqualPack;

import java.util.Date;

@TableName("tm_user")
public class TmUserPO extends PO
{

    public TmUserPO()
    {
    }


                                                                        
    public TmUserPO(Integer userId)
    {
        if (null == this.userId)
        {
            this.userId = new EqualPack<Integer>();
        }
        this.userId.setValue(userId);
    }

    @ColumnName(value = "user_id", isPK = true, autoIncrement = true)
    private Pack<Integer> userId;

    @ColumnName(value = "user_code", isPK = false, autoIncrement = false)
    private Pack<String> userCode;

    @ColumnName(value = "user_name", isPK = false, autoIncrement = false)
    private Pack<String> userName;

    @ColumnName(value = "password", isPK = false, autoIncrement = false)
    private Pack<String> password;

    @ColumnName(value = "sex", isPK = false, autoIncrement = false)
    private Pack<Integer> sex;

    @ColumnName(value = "phone", isPK = false, autoIncrement = false)
    private Pack<String> phone;

    @ColumnName(value = "mobile", isPK = false, autoIncrement = false)
    private Pack<String> mobile;

    @ColumnName(value = "email", isPK = false, autoIncrement = false)
    private Pack<String> email;

    @ColumnName(value = "birthday", isPK = false, autoIncrement = false)
    private Pack<Date> birthday;

    @ColumnName(value = "avatar", isPK = false, autoIncrement = false)
    private Pack<String> avatar;

    @ColumnName(value = "user_type", isPK = false, autoIncrement = false)
    private Pack<Integer> userType;

    @ColumnName(value = "user_status", isPK = false, autoIncrement = false)
    private Pack<Integer> userStatus;

    @ColumnName(value = "is_delete", isPK = false, autoIncrement = false)
    private Pack<Integer> isDelete;

    @ColumnName(value = "create_date", isPK = false, autoIncrement = false)
    private Pack<Date> createDate;

    @ColumnName(value = "create_by", isPK = false, autoIncrement = false)
    private Pack<Integer> createBy;

    @ColumnName(value = "update_date", isPK = false, autoIncrement = false)
    private Pack<Date> updateDate;

    @ColumnName(value = "update_by", isPK = false, autoIncrement = false)
    private Pack<Integer> updateBy;


    public void setUserId(Integer userId)
    {
        if (null == this.userId)
        {
            this.userId = new EqualPack<Integer>();
        }
        this.userId.setValue(userId);
    }

    public void setUserId(Pack<Integer> userId)
    {
        this.userId = userId;
    }
        
    public Integer getUserId()
    {
        return this.userId == null ? null : this.userId.getValue();
    }

    public void setUserCode(String userCode)
    {
        if (null == this.userCode)
        {
            this.userCode = new EqualPack<String>();
        }
        this.userCode.setValue(userCode);
    }

    public void setUserCode(Pack<String> userCode)
    {
        this.userCode = userCode;
    }
        
    public String getUserCode()
    {
        return this.userCode == null ? null : this.userCode.getValue();
    }

    public void setUserName(String userName)
    {
        if (null == this.userName)
        {
            this.userName = new EqualPack<String>();
        }
        this.userName.setValue(userName);
    }

    public void setUserName(Pack<String> userName)
    {
        this.userName = userName;
    }
        
    public String getUserName()
    {
        return this.userName == null ? null : this.userName.getValue();
    }

    public void setPassword(String password)
    {
        if (null == this.password)
        {
            this.password = new EqualPack<String>();
        }
        this.password.setValue(password);
    }

    public void setPassword(Pack<String> password)
    {
        this.password = password;
    }
        
    public String getPassword()
    {
        return this.password == null ? null : this.password.getValue();
    }

    public void setSex(Integer sex)
    {
        if (null == this.sex)
        {
            this.sex = new EqualPack<Integer>();
        }
        this.sex.setValue(sex);
    }

    public void setSex(Pack<Integer> sex)
    {
        this.sex = sex;
    }
        
    public Integer getSex()
    {
        return this.sex == null ? null : this.sex.getValue();
    }

    public void setPhone(String phone)
    {
        if (null == this.phone)
        {
            this.phone = new EqualPack<String>();
        }
        this.phone.setValue(phone);
    }

    public void setPhone(Pack<String> phone)
    {
        this.phone = phone;
    }
        
    public String getPhone()
    {
        return this.phone == null ? null : this.phone.getValue();
    }

    public void setMobile(String mobile)
    {
        if (null == this.mobile)
        {
            this.mobile = new EqualPack<String>();
        }
        this.mobile.setValue(mobile);
    }

    public void setMobile(Pack<String> mobile)
    {
        this.mobile = mobile;
    }
        
    public String getMobile()
    {
        return this.mobile == null ? null : this.mobile.getValue();
    }

    public void setEmail(String email)
    {
        if (null == this.email)
        {
            this.email = new EqualPack<String>();
        }
        this.email.setValue(email);
    }

    public void setEmail(Pack<String> email)
    {
        this.email = email;
    }
        
    public String getEmail()
    {
        return this.email == null ? null : this.email.getValue();
    }

    public void setBirthday(Date birthday)
    {
        if (null == this.birthday)
        {
            this.birthday = new EqualPack<Date>();
        }
        this.birthday.setValue(birthday);
    }

    public void setBirthday(Pack<Date> birthday)
    {
        this.birthday = birthday;
    }
        
    public Date getBirthday()
    {
        return this.birthday == null ? null : this.birthday.getValue();
    }

    public void setAvatar(String avatar)
    {
        if (null == this.avatar)
        {
            this.avatar = new EqualPack<String>();
        }
        this.avatar.setValue(avatar);
    }

    public void setAvatar(Pack<String> avatar)
    {
        this.avatar = avatar;
    }
        
    public String getAvatar()
    {
        return this.avatar == null ? null : this.avatar.getValue();
    }

    public void setUserType(Integer userType)
    {
        if (null == this.userType)
        {
            this.userType = new EqualPack<Integer>();
        }
        this.userType.setValue(userType);
    }

    public void setUserType(Pack<Integer> userType)
    {
        this.userType = userType;
    }
        
    public Integer getUserType()
    {
        return this.userType == null ? null : this.userType.getValue();
    }

    public void setUserStatus(Integer userStatus)
    {
        if (null == this.userStatus)
        {
            this.userStatus = new EqualPack<Integer>();
        }
        this.userStatus.setValue(userStatus);
    }

    public void setUserStatus(Pack<Integer> userStatus)
    {
        this.userStatus = userStatus;
    }
        
    public Integer getUserStatus()
    {
        return this.userStatus == null ? null : this.userStatus.getValue();
    }

    public void setIsDelete(Integer isDelete)
    {
        if (null == this.isDelete)
        {
            this.isDelete = new EqualPack<Integer>();
        }
        this.isDelete.setValue(isDelete);
    }

    public void setIsDelete(Pack<Integer> isDelete)
    {
        this.isDelete = isDelete;
    }
        
    public Integer getIsDelete()
    {
        return this.isDelete == null ? null : this.isDelete.getValue();
    }

    public void setCreateDate(Date createDate)
    {
        if (null == this.createDate)
        {
            this.createDate = new EqualPack<Date>();
        }
        this.createDate.setValue(createDate);
    }

    public void setCreateDate(Pack<Date> createDate)
    {
        this.createDate = createDate;
    }
        
    public Date getCreateDate()
    {
        return this.createDate == null ? null : this.createDate.getValue();
    }

    public void setCreateBy(Integer createBy)
    {
        if (null == this.createBy)
        {
            this.createBy = new EqualPack<Integer>();
        }
        this.createBy.setValue(createBy);
    }

    public void setCreateBy(Pack<Integer> createBy)
    {
        this.createBy = createBy;
    }
        
    public Integer getCreateBy()
    {
        return this.createBy == null ? null : this.createBy.getValue();
    }

    public void setUpdateDate(Date updateDate)
    {
        if (null == this.updateDate)
        {
            this.updateDate = new EqualPack<Date>();
        }
        this.updateDate.setValue(updateDate);
    }

    public void setUpdateDate(Pack<Date> updateDate)
    {
        this.updateDate = updateDate;
    }
        
    public Date getUpdateDate()
    {
        return this.updateDate == null ? null : this.updateDate.getValue();
    }

    public void setUpdateBy(Integer updateBy)
    {
        if (null == this.updateBy)
        {
            this.updateBy = new EqualPack<Integer>();
        }
        this.updateBy.setValue(updateBy);
    }

    public void setUpdateBy(Pack<Integer> updateBy)
    {
        this.updateBy = updateBy;
    }
        
    public Integer getUpdateBy()
    {
        return this.updateBy == null ? null : this.updateBy.getValue();
    }

}