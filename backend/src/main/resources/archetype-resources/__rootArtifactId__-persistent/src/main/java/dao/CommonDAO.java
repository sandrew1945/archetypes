#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import ${package}.core.exception.DAOException;
import ${groupId}.bury.Session;
import ${groupId}.bury.SqlSessionFactory;
import ${groupId}.bury.bean.PO;
import ${groupId}.bury.exception.POException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 *  通用的CRUD操作DAO
 * Created by summer on 2019/7/26.
 */
@Slf4j
@Repository
public class CommonDAO
{
    @Resource
    SqlSessionFactory sessionFactory;

    /**
     *  新增
     * @param po
     * @return
     * @throws DAOException
     */
    public <T extends PO> int insert(T po) throws DAOException
    {
        try
        {
            Session session = sessionFactory.openSession();
            return session.insert(po);
        }
        catch (POException e)
        {
            log.error("insert " + po.getClass() + " error!");
            throw new DAOException();
        }
    }

    /**
     *  批量新增
     * @param list
     * @return
     * @throws DAOException
     */
    public <T extends PO> int[] insert(List<T> list) throws DAOException
    {
        try
        {
            Session session = sessionFactory.openSession();
            return session.insert(list);
        }
        catch (POException e)
        {
            log.error("batch insert " + list == null ? list.get(0).getClass().getName() : "null list" + " error!");
            throw new DAOException();
        }
    }

    /**
     *  删除
     * @param po
     * @return
     * @throws DAOException
     */
    public <T extends PO> int delete(T po) throws DAOException
    {
        try
        {
            Session session = sessionFactory.openSession();
            return session.delete(po);
        }
        catch (POException e)
        {
            log.error("delete " + po.getClass() + " error!");
            throw new DAOException();
        }
    }

    /**
     *  更新
     * @param cond
     * @param value
     * @return
     */
    public <T extends PO> int update(T cond, T value) throws DAOException
    {
        try
        {
            Session session = sessionFactory.openSession();
            return session.update(cond, value);
        }
        catch (POException e)
        {
            log.error("update " + cond.getClass() + " error!");
            throw new DAOException();
        }
    }

    /**
     *  查询
     * @param cond
     * @return
     */
    public <T extends PO> List<T> select(T cond) throws DAOException
    {
        try
        {
            Session session = sessionFactory.openSession();
            return session.select(cond);
        }
        catch (POException e)
        {
            log.error("query " + cond.getClass() + " error!");
            throw new DAOException();
        }
    }

    /**
     *  根据ID查询
     * @param cond
     * @return
     */
    public <T extends PO> T selectById(T cond) throws DAOException
    {
        try
        {
            Session session = sessionFactory.openSession();
            return session.selectById(cond);
        }
        catch (POException e)
        {
            log.error("query " + cond.getClass() + " error!");
            throw new DAOException();
        }
    }
}
