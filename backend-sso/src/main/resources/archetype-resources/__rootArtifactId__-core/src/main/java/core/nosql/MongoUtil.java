#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.nosql;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by summer on 2018/2/27.
 */
public class MongoUtil
{

    private MongoTemplate mongoTemplate;

    public MongoUtil(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 插入数据
     * @param collection
     * @param value
     */
    public void save(String collection, Object value)
    {
        mongoTemplate.save(value, collection);
    }

    /**
     *  查询结果数量
     * @param collcetion
     * @param clz
     * @param condQuery
     * @return
     */
    public long count(String collcetion, Class clz, Query condQuery)
    {
       return mongoTemplate.count(condQuery, clz, collcetion);
    }

    /**
     *  分页查询
     * @param collcetion
     * @param clz
     * @param condQuery
     * @param curPage
     * @param pageSize
     * @param <T>
     * @return
     */
    public <T> List<T> pageQuery(String collcetion, Class<T> clz, Query condQuery, int curPage, int pageSize)
    {
        int skip = (curPage - 1) * pageSize;
        condQuery.skip(skip);// 从那条记录开始
        condQuery.limit(pageSize);// 取多少条记录
        return mongoTemplate.find(condQuery, clz, collcetion);
    }

    /**
     *  查询数据
     * @param collection
     * @param clz
     * @param condQuery
     * @param <T>
     * @return
     */
    public <T> List<T> find(String collection, Class<T> clz, Query condQuery)
    {
        return mongoTemplate.find(condQuery, clz, collection);
    }

    
    public <T> T  findOne(String collectionName, Class<T> clz, Query condQuery)
    {
        return mongoTemplate.findOne(condQuery, clz, collectionName);
    }
    
    /**
     *  更新数据
     * @param collection
     * @param condQuery
     * @param clz
     * @param valUpdate
     */
    public UpdateResult update(String collection, Query condQuery, Class clz, Update valUpdate)
    {
        UpdateResult result = mongoTemplate.updateFirst(condQuery, valUpdate, clz, collection);
        return result;
    }

    /**
     *  删除数据
     * @param collection
     * @param condQuery
     * @param clz
     * @return
     */
    public DeleteResult remove(String collection, Query condQuery, Class clz)
    {
        DeleteResult result = mongoTemplate.remove(condQuery, clz, collection);
        return result;
    }

    /**
     *  删除数据
     * @param collection
     * @param condQuery
     * @return
     */
    public DeleteResult remove(String collection, Query condQuery)
    {
        DeleteResult result = mongoTemplate.remove(condQuery, collection);
        return result;
    }

    /**
     * 查询条件构造器
     * @param condAndVal
     * @return
     */
    public static Query queryBuilder(Map<String, Object> condAndVal)
    {
        Query query = new Query();
        if(null != condAndVal && condAndVal.size() > 0)
        {
            Set keys =condAndVal.keySet();
            Iterator<String> it = keys.iterator();
            Criteria criteria = null;
            String key = null;
            while (it.hasNext())
            {
                key = it.next();
                criteria = Criteria.where(key).is(condAndVal.get(key));
                query.addCriteria(criteria);
            }
        }
        return query;
    }

}
