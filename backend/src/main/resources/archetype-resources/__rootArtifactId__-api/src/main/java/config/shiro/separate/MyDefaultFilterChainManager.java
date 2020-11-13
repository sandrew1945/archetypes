#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.shiro.separate;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;

/**
 * Created by summer on 2019/12/19.
 */
public class MyDefaultFilterChainManager extends DefaultFilterChainManager
{
    @Override
    protected void addDefaultFilters(boolean init)
    {
        for (MyDefaultFilter defaultFilter : MyDefaultFilter.values())
        {
            addFilter(defaultFilter.name(), defaultFilter.newInstance(), init, false);
        }
    }
}
