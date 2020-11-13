#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import ${package}.bean.MenuBean;
import ${package}.bean.RoleTreeNode;
import ${package}.bean.UserInfo;
import ${package}.core.common.Constants;
import ${package}.core.exception.DAOException;
import ${package}.model.TmUserPO;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.util.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by summer on 2019/7/26.
 */
@Slf4j
@Repository
public class LoginDAO
{
    @Resource
    SqlSessionFactory sessionFactory;

    public List<TmUserPO> selectByUserCode(String userCode, Integer userStatus) throws DAOException
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from tm_user${symbol_escape}n");
        sql.append("where user_code = ?${symbol_escape}n");
        sql.append("and user_status = ?${symbol_escape}n");
        sql.append("and is_delele = ?");
        Parameters params = new Parameters();
        params.addParam(userCode);
        params.addParam(userStatus);
        params.addParam(Constants.IF_TYPE_NO);
        return session.select(sql.toString(), params.getParams(), new POCallBack<>(TmUserPO.class));
    }

    public List<UserInfo> selectRoleByUserCode(String userCode) throws DAOException
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select tr.role_id,tr.role_code,tr.role_name from tm_user tu${symbol_escape}n");
        sql.append("left join tr_user_role tur on tu.user_id = tur.user_id${symbol_escape}n");
        sql.append("left join tm_role tr on tur.role_id = tr.role_id${symbol_escape}n");
        sql.append("where tu.user_status = ?${symbol_escape}n");
        sql.append("and tr.role_status = ?${symbol_escape}n");
        sql.append("and tu.is_delete = ?${symbol_escape}n");
        sql.append("and tr.is_delete = ?${symbol_escape}n");
        sql.append("and tu.user_code = ?");
        Parameters params = new Parameters();
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(userCode);
        return session.select(sql.toString(), params.getParams(), new POCallBack<>(UserInfo.class));
    }

    public List<RoleTreeNode> selectRoleForChoice(Integer userId) throws DAOException
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select tr.role_id as id,tr.role_name as name, 0 as pid, 'false' as isparent${symbol_escape}n");
        sql.append("from tm_user tu${symbol_escape}n");
        sql.append("left join tr_user_role tur on tu.user_id = tur.user_id${symbol_escape}n");
        sql.append("left join tm_role tr on tur.role_id = tr.role_id${symbol_escape}n");
        sql.append("where tu.user_status = ?${symbol_escape}n");
        sql.append("and tu.is_delete = ?${symbol_escape}n");
        sql.append("and tr.is_delete = ?${symbol_escape}n");
        sql.append("and tr.role_status = ?${symbol_escape}n");
        sql.append("and tu.user_id = ?");
        Parameters params = new Parameters();
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(userId);
        return session.select(sql.toString(), params.getParams(), new DAOCallback<RoleTreeNode>()
        {
            @Override
            public RoleTreeNode wrapper(ResultSet rs, int index) throws SQLException
            {
                RoleTreeNode roleTreeNode= new RoleTreeNode();
                roleTreeNode.setId(rs.getString("id"));
                roleTreeNode.setpId(rs.getString("pid"));
                roleTreeNode.setName(rs.getString("name"));
                roleTreeNode.setIsParent(rs.getString("isparent"));
                return roleTreeNode;
            }
        });
    }

    public List<MenuBean> getMenuInfo(Integer roleId)
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct tf.function_id,${symbol_escape}n");
        sql.append("tf.function_code,${symbol_escape}n");
        sql.append("tf.function_name,${symbol_escape}n");
        sql.append("tf.father_func,${symbol_escape}n");
        sql.append("tf.access_url,${symbol_escape}n");
        sql.append("tf.icon${symbol_escape}n");
        sql.append("from tm_role tr, tr_role_func trf, tm_function tf${symbol_escape}n");
        sql.append("where tr.role_id = trf.role_id${symbol_escape}n");
        sql.append("and trf.function_id = tf.function_id${symbol_escape}n");
        sql.append("and tr.role_status = ?${symbol_escape}n");
        sql.append("and tf.function_status = ?${symbol_escape}n");
        sql.append("and tr.role_id = ?${symbol_escape}n");
        sql.append("order by function_order");
        Parameters params = new Parameters();
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(roleId);
        return session.select(sql.toString(), params.getParams(), new DAOCallback<MenuBean>()
        {
            @Override
            public MenuBean wrapper(ResultSet rs, int i) throws SQLException
            {
                MenuBean menuBean = new MenuBean();
                menuBean.setImgUrl(rs.getString("icon"));
                menuBean.setFuncId(rs.getString("function_id"));
                menuBean.setFuncCode(rs.getString("function_code"));
                menuBean.setFuncName(rs.getString("function_name"));
                menuBean.setParentId(rs.getString("father_func"));
                menuBean.setFunctionUrl(rs.getString("access_url"));
                return menuBean;
            }
        });
    }
}
