#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.common;


import lombok.Data;

/**
 * Created by summer on 2019/12/18.
 */
@Data
public class JsonResult
{
    private boolean result;

    private String msg;

    private Object data;

    public JsonResult requestSuccess(Object data)
    {
        this.result = true;
        this.data = data;
        return this;
    }

    public JsonResult requestSuccess()
    {
        this.result = true;
        this.msg = "";
        return this;
    }

    public JsonResult requestFailure(String errMsg)
    {
        this.result = false;
        this.msg = errMsg;
        return this;
    }
}
