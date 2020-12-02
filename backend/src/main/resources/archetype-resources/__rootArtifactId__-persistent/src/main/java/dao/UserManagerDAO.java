#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import ${package}.core.bean.AclUserBean;
import ${package}.core.common.Constants;
import ${package}.core.exception.DAOException;
import ${package}.model.TmRolePO;
import ${groupId}.bury.Session;
import ${groupId}.bury.SqlSessionFactory;
import ${groupId}.bury.bean.PageResult;
import ${groupId}.bury.callback.DAOCallback;
import ${groupId}.bury.callback.POCallBack;
import ${groupId}.bury.util.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class UserManagerDAO
{
    @Resource
    SqlSessionFactory sessionFactory;

    /**
     *  分页查询用户列表
     * @param userCode
     * @param userName
     * @param userStatus
     * @param limit
     * @param curPage
     * @return
     * @throws DAOException
     */
    public PageResult<AclUserBean> userManagerPageQuery(String userCode, String userName, Integer userStatus, int limit, int curPage) throws DAOException
    {
        Parameters params = new Parameters();
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(Constants.STATUS_ENABLE);
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT${symbol_escape}n");
        sql.append("tu.user_id,${symbol_escape}n");
        sql.append("        tu.user_code,${symbol_escape}n");
        sql.append("        tu.user_name,${symbol_escape}n");
        sql.append("        tu.sex,${symbol_escape}n");
        sql.append("        tu.user_status,${symbol_escape}n");
        sql.append("        tu.phone,${symbol_escape}n");
        sql.append("        tu.mobile,${symbol_escape}n");
        sql.append("        tu.email,${symbol_escape}n");
        sql.append("        tr.role_code,${symbol_escape}n");
        sql.append("        group_concat(tr.role_name) AS role_name${symbol_escape}n");
        sql.append("FROM${symbol_escape}n");
        sql.append("tm_user tu${symbol_escape}n");
        sql.append("LEFT JOIN tr_user_role tur ON tu.user_id = tur.user_id${symbol_escape}n");
        sql.append("LEFT JOIN (${symbol_escape}n");
        sql.append("    SELECT${symbol_escape}n");
        sql.append("tm_role.role_id,${symbol_escape}n");
        sql.append("        tm_role.role_code,${symbol_escape}n");
        sql.append("        tm_role.role_name${symbol_escape}n");
        sql.append("FROM${symbol_escape}n");
        sql.append("        tm_role${symbol_escape}n");
        sql.append("WHERE tm_role.is_delete = ?${symbol_escape}n");
        sql.append("AND   tm_role.role_status = ?${symbol_escape}n");
		sql.append(") tr ON tr.role_id = tur.role_id${symbol_escape}n");
        sql.append("WHERE 1 = 1${symbol_escape}n");
        if (StringUtils.isNotEmpty(userCode))
        {
            sql.append("AND user_code LIKE ?${symbol_escape}n");
            params.addLikeParam(userCode);
        }
        if (StringUtils.isNotEmpty(userName))
        {
            sql.append("AND user_name LIKE ?${symbol_escape}n");
            params.addLikeParam(userName);
        }
        if (null != userStatus)
        {
            sql.append("AND user_status = ?${symbol_escape}n");
            params.addParam(userStatus);
        }
        sql.append("AND tu.is_delete = ?${symbol_escape}n");
        sql.append("GROUP BY user_code,is_delete");
        params.addParam(Constants.IF_TYPE_NO);
        return session.pageQuery(sql.toString(), params.getParams(), new DAOCallback<AclUserBean>()
        {
            @Override
            public AclUserBean wrapper(ResultSet rs, int index) throws SQLException
            {

                AclUserBean userBean = new AclUserBean();
                userBean.setUserId(rs.getInt("user_id"));
                userBean.setUserCode(rs.getString("user_code"));
                userBean.setUserName(rs.getString("user_name"));
                userBean.setSex(rs.getInt("sex"));
                userBean.setPhone(rs.getString("phone"));
                userBean.setMobile(rs.getString("mobile"));
                userBean.setEmail(rs.getString("email"));
                userBean.setRoleName(rs.getString("role_name"));
                userBean.setUserStatus(rs.getString("user_status"));
                return userBean;
            }
        }, limit, curPage);
    }

    /**
     *  查询用户的角色
     * @param userId
     * @return
     * @throws DAOException
     */
    public List<TmRolePO> queryRelationRole(Integer userId) throws DAOException
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select tr.* from tm_role tr JOIN tr_user_role tur ON tr.role_id = tur.role_id WHERE tur.user_id = ?");

        Parameters params = new Parameters(userId);
        return session.select(sql.toString(), params.getParams(), new POCallBack<>(TmRolePO.class));
    }

    /**
     *  查询用户未关联的角色
     * @param userId
     * @param roleName
     * @return
     * @throws DAOException
     */
    public List<TmRolePO> getRoleExistOwn(Integer userId, String roleName) throws DAOException
    {
        Session session = sessionFactory.openSession();
        Parameters params = new Parameters(Constants.STATUS_ENABLE);
        params.addParam(userId);
        StringBuilder sql = new StringBuilder();
        sql.append("select${symbol_escape}n");
        sql.append("tr.role_id,${symbol_escape}n");
        sql.append("        tr.role_code,${symbol_escape}n");
        sql.append("        tr.role_name,${symbol_escape}n");
        sql.append("        tr.role_type,${symbol_escape}n");
        sql.append("        tr.role_status${symbol_escape}n");
        sql.append("from${symbol_escape}n");
        sql.append("tm_role tr${symbol_escape}n");
        sql.append("where${symbol_escape}n");
        sql.append("tr.role_status = ?${symbol_escape}n");
        sql.append("and not exists (${symbol_escape}n");
        sql.append("    select${symbol_escape}n");
        sql.append("    1${symbol_escape}n");
        sql.append("    from${symbol_escape}n");
        sql.append("    tr_user_role tur${symbol_escape}n");
        sql.append("    where${symbol_escape}n");
        sql.append("    tr.role_id = tur.role_id${symbol_escape}n");
        sql.append("    and tur.user_id = ?)${symbol_escape}n");
        if (StringUtils.isNotEmpty(roleName))
        {
            sql.append("    and tr.role_name like ?");
            params.addLikeParam(roleName);
        }
        return session.select(sql.toString(), params.getParams(), new POCallBack<>(TmRolePO.class));
    }
}
