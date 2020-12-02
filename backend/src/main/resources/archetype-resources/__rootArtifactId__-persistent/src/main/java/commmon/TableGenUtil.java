#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.commmon;

import ${groupId}.bury.util.POGenerator;

/**
 * Created by summer on 2019/7/26.
 */
public class TableGenUtil
{
    public static void main(String[] args)
    {
        try
        {
            POGenerator poGenerator = new POGenerator();
            poGenerator.genForPack("POConf.xml");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
