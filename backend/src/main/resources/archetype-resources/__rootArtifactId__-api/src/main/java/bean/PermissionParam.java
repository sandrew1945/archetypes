#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.bean;

import ${package}.service.util.MenuNode;
import lombok.Data;

import java.util.List;

/**
 * Created by summer on 2020/2/1.
 */
@Data
public class PermissionParam
{
    public Integer roleId;

    public List<MenuNode> node;
}
