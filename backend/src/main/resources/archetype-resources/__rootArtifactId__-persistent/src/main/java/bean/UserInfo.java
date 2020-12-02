#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.bean;

import ${groupId}.bury.bean.PO;
import lombok.Data;

@Data
public class UserInfo extends PO
{
    private Integer userId;

    private String userCode;

    private String userName;

    private Integer roleId;

    private String roleName;

    private String roleCode;

    private Integer sex;

    private String phone;

    private String mobile;

    private String email;

    private String avatarPath;

}
