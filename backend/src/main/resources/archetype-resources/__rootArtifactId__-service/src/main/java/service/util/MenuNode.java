#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * VUE菜单节点
 * Created by summer on 2020/1/8.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuNode implements Comparable
{

    private String path;

    private String name;

    private String component;

    private String redirect;

    private Map<String, String> meta;

    private List<MenuNode> children = new ArrayList<>();

    public MenuNode()
    {
    }

    public void addChildren(MenuNode child)
    {
        if (null != children)
        {
            children.add(child);
        }
    }

    @Override
    public int compareTo(Object other)
    {
        MenuNode otherNode = (MenuNode) other;
        if (this.path.equals(otherNode.path) && this.name.equals(otherNode.name))
        {
            return 0;
        }
        return -1;
    }
}
