#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.platform;

import ${package}.core.exception.ServiceException;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by summer on 2018/1/25.
 */
@Configuration
public class PlatformTransactionConfiguration
{
    @Value("${symbol_dollar}{aop.pointcut.expression}")
    private String aopPointcutExpression;

    /**********************************/
    /**      事务配置信息  START      **/
    /**********************************/

    /*事务拦截类型*/
    @Bean("txSource")
    public TransactionAttributeSource transactionAttributeSource()
    {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
             /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
        readOnlyTx.setReadOnly(true);
        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
            /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, Collections.singletonList(new RollbackRuleAttribute(ServiceException.class)));
        requiredTx.setTimeout(5);
        Map<String, TransactionAttribute> txMap = new HashMap<String, TransactionAttribute>();
        txMap.put("add*", requiredTx);
        txMap.put("save*", requiredTx);
        txMap.put("create*", requiredTx);
        txMap.put("insert*", requiredTx);
        txMap.put("update*", requiredTx);
        txMap.put("delete*", requiredTx);
        txMap.put("get*", readOnlyTx);
        txMap.put("query*", readOnlyTx);
        txMap.put("*", readOnlyTx);
        source.setNameMap(txMap);

        return source;
    }

    /**
     * 切面拦截规则 参数会自动从容器中注入
     */
    @Bean
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(TransactionInterceptor txInterceptor)
    {
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setAdvice(txInterceptor);
        pointcutAdvisor.setExpression(aopPointcutExpression);
        return pointcutAdvisor;
    }

    /*事务拦截器*/
    @Bean("txInterceptor")
    TransactionInterceptor getTransactionInterceptor(PlatformTransactionManager platformTransactionManager)
    {
        return new TransactionInterceptor(platformTransactionManager, transactionAttributeSource());
    }
    /**********************************/
    /**        事务配置信息  END       **/
    /**********************************/
}