#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 *   自动生成的PO,不要手动修改
 *
 */
package ${package}.model;

import ${groupId}.bury.annotations.TableName;
import ${groupId}.bury.annotations.ColumnName;
import ${groupId}.bury.bean.PO;
import ${groupId}.bury.bean.Pack;
import ${groupId}.bury.bean.EqualPack;

import java.util.Date;

@TableName("tm_function")
public class TmFunctionPO extends PO
{

    public TmFunctionPO()
    {
    }


                                                
    public TmFunctionPO(Integer functionId)
    {
        if (null == this.functionId)
        {
            this.functionId = new EqualPack<Integer>();
        }
        this.functionId.setValue(functionId);
    }

    @ColumnName(value = "function_id", isPK = true, autoIncrement = true)
    private Pack<Integer> functionId;

    @ColumnName(value = "path", isPK = false, autoIncrement = false)
    private Pack<String> path;

    @ColumnName(value = "title", isPK = false, autoIncrement = false)
    private Pack<String> title;

    @ColumnName(value = "icon", isPK = false, autoIncrement = false)
    private Pack<String> icon;

    @ColumnName(value = "func_order", isPK = false, autoIncrement = false)
    private Pack<Integer> funcOrder;

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


    public void setFunctionId(Integer functionId)
    {
        if (null == this.functionId)
        {
            this.functionId = new EqualPack<Integer>();
        }
        this.functionId.setValue(functionId);
    }

    public void setFunctionId(Pack<Integer> functionId)
    {
        this.functionId = functionId;
    }
        
    public Integer getFunctionId()
    {
        return this.functionId == null ? null : this.functionId.getValue();
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

    public void setFuncOrder(Integer funcOrder)
    {
        if (null == this.funcOrder)
        {
            this.funcOrder = new EqualPack<Integer>();
        }
        this.funcOrder.setValue(funcOrder);
    }

    public void setFuncOrder(Pack<Integer> funcOrder)
    {
        this.funcOrder = funcOrder;
    }
        
    public Integer getFuncOrder()
    {
        return this.funcOrder == null ? null : this.funcOrder.getValue();
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