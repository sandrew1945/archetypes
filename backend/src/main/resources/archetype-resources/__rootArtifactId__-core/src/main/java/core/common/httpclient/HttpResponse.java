#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.common.httpclient;

import lombok.Data;

/**
 * Created by summer on 2020/3/27.
 */
@Data
public class HttpResponse
{
    private int returnCode;

    private String returnContent;
}
