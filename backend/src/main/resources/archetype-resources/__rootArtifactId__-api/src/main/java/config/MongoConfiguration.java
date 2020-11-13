#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.net.UnknownHostException;

/**
 * Created by summer on 2018/1/25.
 */
//@Configuration
public class MongoConfiguration
{
    @Value("${symbol_dollar}{spring.data.mongodb.primary.uri}")
    private String MONGO_URI_P;
    @Value("${symbol_dollar}{spring.data.mongodb.secondary.uri}")
    private String MONGO_URI_S;

    @Bean
    public MongoMappingContext mongoMappingContext()
    {
        MongoMappingContext mappingContext = new MongoMappingContext();
        return mappingContext;
    }

    // ==================== 连接到 primary mongodb 服务器 ======================================

    @Bean //使用自定义的typeMapper去除写入mongodb时的“_class”字段
    public MappingMongoConverter mappingMongoConverter() throws Exception
    {
        DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactoryPri());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Bean
    @Primary
    public MongoDbFactory dbFactoryPri() throws UnknownHostException
    {
        return new SimpleMongoDbFactory(new MongoClientURI(MONGO_URI_P));
    }

    @Bean
    @Primary
    public MongoTemplate mongoTemplatePri() throws Exception
    {
        return new MongoTemplate(this.dbFactoryPri(), this.mappingMongoConverter());
    }


    @Bean
    public MongoDbFactory dbFactorySec() throws UnknownHostException
    {
        return new SimpleMongoDbFactory(new MongoClientURI(MONGO_URI_S));
    }

    @Bean
    public MongoTemplate mongoTemplateSec() throws Exception
    {
        return new MongoTemplate(dbFactorySec(), this.mappingMongoConverter());
    }
}