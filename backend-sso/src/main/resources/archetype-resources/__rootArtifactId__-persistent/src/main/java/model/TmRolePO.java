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

@TableName("tm_role")
public class TmRolePO extends PO
{

    public TmRolePO()
    {
    }


                                            
    public TmRolePO(Integer roleId)
    {
        if (null == this.roleId)
        {
            this.roleId = new EqualPack<Integer>();
        }
        this.roleId.setValue(roleId);
    }

    @ColumnName(value = "role_id", isPK = true, autoIncrement = true)
    private Pack<Integer> roleId;

    @ColumnName(value = "role_code", isPK = false, autoIncrement = false)
    private Pack<String> roleCode;

    @ColumnName(value = "role_name", isPK = false, autoIncrement = false)
    private Pack<String> roleName;

    @ColumnName(value = "role_type", isPK = false, autoIncrement = false)
    private Pack<Integer> roleType;

    @ColumnName(value = "role_status", isPK = false, autoIncrement = false)
    private Pack<Integer> roleStatus;

    @ColumnName(value = "is_delete", isPK = false, autoIncrement = false)
    private Pack<Integer> isDelete;

    @ColumnName(value = "create_by", isPK = false, autoIncrement = false)
    private Pack<Integer> createBy;

    @ColumnName(value = "create_date", isPK = false, autoIncrement = false)
    private Pack<Date> createDate;

    @ColumnName(value = "update_by", isPK = false, autoIncrement = false)
    private Pack<Integer> updateBy;

    @ColumnName(value = "update_date", isPK = false, autoIncrement = false)
    private Pack<Date> updateDate;


    public void setRoleId(Integer roleId)
    {
        if (null == this.roleId)
        {
            this.roleId = new EqualPack<Integer>();
        }
        this.roleId.setValue(roleId);
    }

    public void setRoleId(Pack<Integer> roleId)
    {
        this.roleId = roleId;
    }
        
    public Integer getRoleId()
    {
        return this.roleId == null ? null : this.roleId.getValue();
    }

    public void setRoleCode(String roleCode)
    {
        if (null == this.roleCode)
        {
            this.roleCode = new EqualPack<String>();
        }
        this.roleCode.setValue(roleCode);
    }

    public void setRoleCode(Pack<String> roleCode)
    {
        this.roleCode = roleCode;
    }
        
    public String getRoleCode()
    {
        return this.roleCode == null ? null : this.roleCode.getValue();
    }

    public void setRoleName(String roleName)
    {
        if (null == this.roleName)
        {
            this.roleName = new EqualPack<String>();
        }
        this.roleName.setValue(roleName);
    }

    public void setRoleName(Pack<String> roleName)
    {
        this.roleName = roleName;
    }
        
    public String getRoleName()
    {
        return this.roleName == null ? null : this.roleName.getValue();
    }

    public void setRoleType(Integer roleType)
    {
        if (null == this.roleType)
        {
            this.roleType = new EqualPack<Integer>();
        }
        this.roleType.setValue(roleType);
    }

    public void setRoleType(Pack<Integer> roleType)
    {
        this.roleType = roleType;
    }
        
    public Integer getRoleType()
    {
        return this.roleType == null ? null : this.roleType.getValue();
    }

    public void setRoleStatus(Integer roleStatus)
    {
        if (null == this.roleStatus)
        {
            this.roleStatus = new EqualPack<Integer>();
        }
        this.roleStatus.setValue(roleStatus);
    }

    public void setRoleStatus(Pack<Integer> roleStatus)
    {
        this.roleStatus = roleStatus;
    }
        
    public Integer getRoleStatus()
    {
        return this.roleStatus == null ? null : this.roleStatus.getValue();
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

}