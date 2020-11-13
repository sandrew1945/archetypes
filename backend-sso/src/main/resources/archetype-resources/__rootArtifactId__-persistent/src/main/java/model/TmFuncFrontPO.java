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

@TableName("tm_func_front")
public class TmFuncFrontPO extends PO
{

    public TmFuncFrontPO()
    {
    }


                                                            
    public TmFuncFrontPO(Integer funcId)
    {
        if (null == this.funcId)
        {
            this.funcId = new EqualPack<Integer>();
        }
        this.funcId.setValue(funcId);
    }

    @ColumnName(value = "func_id", isPK = true, autoIncrement = true)
    private Pack<Integer> funcId;

    @ColumnName(value = "role_id", isPK = false, autoIncrement = false)
    private Pack<Integer> roleId;

    @ColumnName(value = "path", isPK = false, autoIncrement = false)
    private Pack<String> path;

    @ColumnName(value = "name", isPK = false, autoIncrement = false)
    private Pack<String> name;

    @ColumnName(value = "title", isPK = false, autoIncrement = false)
    private Pack<String> title;

    @ColumnName(value = "file", isPK = false, autoIncrement = false)
    private Pack<String> file;

    @ColumnName(value = "icon", isPK = false, autoIncrement = false)
    private Pack<String> icon;

    @ColumnName(value = "redirect", isPK = false, autoIncrement = false)
    private Pack<String> redirect;

    @ColumnName(value = "father_id", isPK = false, autoIncrement = false)
    private Pack<Integer> fatherId;

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


    public void setFuncId(Integer funcId)
    {
        if (null == this.funcId)
        {
            this.funcId = new EqualPack<Integer>();
        }
        this.funcId.setValue(funcId);
    }

    public void setFuncId(Pack<Integer> funcId)
    {
        this.funcId = funcId;
    }
        
    public Integer getFuncId()
    {
        return this.funcId == null ? null : this.funcId.getValue();
    }

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

    public void setPath(String path)
    {
        if (null == this.path)
        {
            this.path = new EqualPack<String>();
        }
        this.path.setValue(path);
    }

    public void setPath(Pack<String> path)
    {
        this.path = path;
    }
        
    public String getPath()
    {
        return this.path == null ? null : this.path.getValue();
    }

    public void setName(String name)
    {
        if (null == this.name)
        {
            this.name = new EqualPack<String>();
        }
        this.name.setValue(name);
    }

    public void setName(Pack<String> name)
    {
        this.name = name;
    }
        
    public String getName()
    {
        return this.name == null ? null : this.name.getValue();
    }

    public void setTitle(String title)
    {
        if (null == this.title)
        {
            this.title = new EqualPack<String>();
        }
        this.title.setValue(title);
    }

    public void setTitle(Pack<String> title)
    {
        this.title = title;
    }
        
    public String getTitle()
    {
        return this.title == null ? null : this.title.getValue();
    }

    public void setFile(String file)
    {
        if (null == this.file)
        {
            this.file = new EqualPack<String>();
        }
        this.file.setValue(file);
    }

    public void setFile(Pack<String> file)
    {
        this.file = file;
    }
        
    public String getFile()
    {
        return this.file == null ? null : this.file.getValue();
    }

    public void setIcon(String icon)
    {
        if (null == this.icon)
        {
            this.icon = new EqualPack<String>();
        }
        this.icon.setValue(icon);
    }

    public void setIcon(Pack<String> icon)
    {
        this.icon = icon;
    }
        
    public String getIcon()
    {
        return this.icon == null ? null : this.icon.getValue();
    }

    public void setRedirect(String redirect)
    {
        if (null == this.redirect)
        {
            this.redirect = new EqualPack<String>();
        }
        this.redirect.setValue(redirect);
    }

    public void setRedirect(Pack<String> redirect)
    {
        this.redirect = redirect;
    }
        
    public String getRedirect()
    {
        return this.redirect == null ? null : this.redirect.getValue();
    }

    public void setFatherId(Integer fatherId)
    {
        if (null == this.fatherId)
        {
            this.fatherId = new EqualPack<Integer>();
        }
        this.fatherId.setValue(fatherId);
    }

    public void setFatherId(Pack<Integer> fatherId)
    {
        this.fatherId = fatherId;
    }
        
    public Integer getFatherId()
    {
        return this.fatherId == null ? null : this.fatherId.getValue();
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