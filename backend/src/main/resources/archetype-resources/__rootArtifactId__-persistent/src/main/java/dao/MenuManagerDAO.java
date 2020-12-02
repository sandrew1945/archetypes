#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import ${package}.core.common.Constants;
import ${package}.core.exception.DAOException;
import ${package}.model.TmFunctionPO;
import ${groupId}.bury.Session;
import ${groupId}.bury.SqlSessionFactory;
import ${groupId}.bury.callback.POCallBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by summer on 2019/7/26.
 */
@Slf4j
@Repository
public class MenuManagerDAO
{
    @Resource
    SqlSessionFactory sessionFactory;

    /**
     *  查询系统菜单
     * @return
     * @throws DAOException
     */
    public List<TmFunctionPO> getMenuList() throws DAOException
    {
        Session session = sessionFactory.openSession();
        TmFunctionPO cond = new TmFunctionPO();
        cond.setIsDelete(Constants.IF_TYPE_NO);
        List<TmFunctionPO> list = session.selectForOrder(cond, new POCallBack(TmFunctionPO.class), "asc","func_order");
        return list;
    }
}
