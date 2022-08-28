package benchmarck_jmh;

import org.openjdk.jmh.annotations.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;


@State(Scope.Benchmark)
public class SpringbootStringRedisTemplateBenchmark {

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    private StringRedisTemplate redisTemplate;


    @Setup(Level.Trial)
    public void setUp() {
        redisTemplate = SpringApplication.run(SpringBootApplicationClass.class).getBean(StringRedisTemplate.class);
    }


    @Benchmark
    public void testGet() {
        redisTemplate.opsForValue().get("testkey");
    }

}

@SpringBootApplication
class SpringBootApplicationClass {

//    in application.properties


//    lettuce.pool.maxTotal=50
//
//    lettuce.pool.maxIdle=10
//
//    lettuce.pool.minIdle=0
//    lettuce.sentinel.master=mymaster
//
//    lettuce.sentinel.nodes=10.xx.xx.xx:26379,10.xx.xx.xx:26379
//
//    lettuce.password=xxxxxx

}



