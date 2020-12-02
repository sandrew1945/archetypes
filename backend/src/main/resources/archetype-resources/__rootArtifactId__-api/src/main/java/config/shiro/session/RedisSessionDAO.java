#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * <pre>
 * FILE : MySqlSessionDAO.java
 * CLASS : MySqlSessionDAO
 *
 * AUTHOR : Administrator
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2017年5月8日| Administrator| Created |
 * DESCRIPTION:
 * </pre>
 * <p/>
 * ${symbol_dollar}Id: MySqlSessionDAO.java,v 0.1 2017年5月8日 上午10:35:36 Administrator Exp ${symbol_dollar}
 */
/**
 * ${symbol_dollar}Id: MySqlSessionDAO.java,v 0.1 2017年5月8日 上午10:35:36 Administrator Exp ${symbol_dollar}
 */

package ${package}.config.shiro.session;

import ${package}.core.nosql.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;


/**
 * Function    : 
 * @author     : Administrator
 * CreateDate  : 2017年5月8日
 * @version    :
 */
@Log4j2
public class RedisSessionDAO extends CachingSessionDAO
{

    private static final Long EXPIRE_TIME = 30 * 60l;


    @Resource
    private RedisUtil redisUtil;

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.CachingSessionDAO${symbol_pound}doUpdate(org.apache.shiro.session.Session)
     */
    @Override
    protected void doUpdate(Session session)
    {
        //如果会话过期/停止 没必要再更新了
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid())
        {
            return;
        }
        redisUtil.set(String.valueOf(session.getId()), session, EXPIRE_TIME);
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.CachingSessionDAO${symbol_pound}doDelete(org.apache.shiro.session.Session)
     */
    @Override
    protected void doDelete(Session session)
    {
        redisUtil.remove(String.valueOf(session.getId()));
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO${symbol_pound}doCreate(org.apache.shiro.session.Session)
     */
    @Override
    protected Serializable doCreate(Session session)
    {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        redisUtil.set(String.valueOf(sessionId), session);
        return session.getId();
    }

    /* (non-Javadoc)
     * @see org.apache.shiro.session.mgt.eis.AbstractSessionDAO${symbol_pound}doReadSession(java.io.Serializable)
     */
    @Override
    protected Session doReadSession(Serializable sessionId)
    {
        Session session = (Session) redisUtil.get(String.valueOf(sessionId));
        return session;
    }

}
