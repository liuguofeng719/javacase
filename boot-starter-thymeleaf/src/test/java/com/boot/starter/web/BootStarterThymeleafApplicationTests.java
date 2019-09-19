package com.boot.starter.web;

import com.alibaba.fastjson.parser.ParserConfig;
import com.boot.starter.utils.FastJsonRedisSerializer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootStarterThymeleafApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    RedisTemplate redisTemplate;

    ValueOperations operations;
    HashOperations hashOperations;
    ListOperations listOperations;
    SetOperations setOperations;
    ZSetOperations zSetOperations;

    @Before
    public void init() {

        /**
         * 文档 http://redisdoc.com/ 中文文档
         */

        /**
         * 设置key和value 的序列化策略
         * autoType is not support 已经setAutoTypeSupport=ture
         */
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);

        /**
         * 字符串操作
         */
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer);
//        redisTemplate.setValueSerializer(stringSerializer);
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        redisTemplate.setHashValueSerializer(stringSerializer);

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);

        //字符串操作
        operations = redisTemplate.opsForValue();
        //hash 操作
        hashOperations = redisTemplate.opsForHash();
        //list 操作
        listOperations = redisTemplate.opsForList();
        //set 操作
        setOperations = redisTemplate.opsForSet();
        //有序set 操作
        zSetOperations = redisTemplate.opsForZSet();
    }

    /**
     * 字符串操作
     */
    @Test
    public void stringOperations() throws Exception {
//        setOrget();
//        setex();
//        setoffset();
//        setnx();
//        msetAndmget();
//        multiSetIfAbsent();
//        getAndSet();
//        incrementLong();
//        incrementDouble();
//        append();
    }

    /**
     * list 操作
     */
    @Test
    public void listOperations() throws Exception {
//        leftPush();
        leftPushAll();
//        leftPushIfPresent();
//        rightPush();
//        rightPushALL();
//        rightPushIfPresent();
//        set();
//        remove();
//        index();
        leftPop();
        System.out.println("exists = " + hashKey());
//        rightPopAndLeftPush();
//        range();
//        trim();
    }

    /**
     * hash 键值对操作
     */
    @Test
    public void hashOperations() throws Exception {

    }

    /**
     * zset 有序,不重复操作
     */
    @Test
    public void zSetOperations() throws Exception {
//        add();
//        addCollection();
        rank();
    }

    /**
     *
     */
    private void rank() {
        final String zadd = "zadd";
        final Long aLong = zSetOperations.rank(zadd, "zset-2");
        println(zadd+" 排名为",aLong);
    }

    private void addCollection() {
        final String zadd = "zadd";
        ZSetOperations.TypedTuple<Object> z1 = new DefaultTypedTuple<>("zset-1",1.0);
        ZSetOperations.TypedTuple<Object> z2 = new DefaultTypedTuple<>("zset-2",2.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = Sets.newHashSet();
        tuples.add(z1);
        tuples.add(z2);
        final Long aLong = zSetOperations.add(zadd, tuples);//zAdd
        println(zadd, aLong);
    }

    /**
     *
     */
    private void add() {
        final String zadd = "zadd";
        final Boolean aBoolean = zSetOperations.add(zadd, "zadd", 1.0);//zadd
        println(zadd, aBoolean);
    }


    /**
     * set 无序,不重复
     */
    @Test
    public void setOperations() throws Exception {
//        setAdd();
//        setRemove();
//        setpop();
//        setmove();
//        isMember();
//        intersect();//集合求交集
//        intersectAndStore();//集合求交集，并且存入新的key
//        union();//集合求并集
//        unionAndStore();//集合求并集，并且存入新的集合key中
//        difference();//集合求差集
//        differenceAndStore();//求差集并且存入新的集合key中
//        members();//获取集合key的所有成员
//        randomMember();//随机获取集合中的元素
//        distinctRandomMembers();//随机获取集合元素，并且去重
//        scan();//遍历集合
    }

    private void scan() {

        final String userKey = "userKey";
        final List<User> users = initData();
        List<String> idNos = Lists.newArrayList();
        for (final User user : users) {
           idNos.add(user.getIdNo());
        }
        setOperations.add(userKey, idNos.toArray(new String[0]));
        final Cursor<String> cursor = setOperations.scan(userKey, ScanOptions.NONE);//scan
        while (cursor.hasNext()) {
            final String next = cursor.next();
            println(userKey,next);
        }
    }

    private List<User> initData() {
        List<User> userList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            final int age = RandomUtils.nextInt(0, 4);
            user.setAge(age);
            user.setIdNo(UUID.randomUUID().toString());
            user.setName(String.valueOf((char) i));
            user.setAddress("smzc_" + UUID.randomUUID());
            userList.add(user);
        }
        return userList;
    }

    /**
     * 随机获取集合元素，并且去重
     */
    private void distinctRandomMembers() {
        final String destKey = "destKey";
        final Set randomMembers = setOperations.distinctRandomMembers(destKey, 2);//srandmember
        println(destKey,randomMembers);

    }

    private void randomMember() {
        final String destKey = "destKey";
        final Object member = setOperations.randomMember(destKey);//srandmember
        println(destKey,member);
        final List randomMembers = setOperations.randomMembers(destKey, 2);//随机获取多个
        println(destKey,randomMembers);
    }

    /**
     * 获取所有的集合的所有成员
     */
    private void members() {
        final String destKey = "destKey";
        final Set members = setOperations.members(destKey);
        println(destKey,members);
    }

    /**
     * 求差集，并且存入新的集合key中
     */
    private void differenceAndStore() {
        final String key = "setadd";
        final String otherKey = "newUnion";
        final String destKey = "destKey";
        final Long aLong = setOperations.differenceAndStore(otherKey,key, destKey);//sdiffstore
        println("differenceAndStore",aLong);
    }

    /**
     * 集合求差集
     * setadd
     *      1，2，3
     * newUnion
     *      2，3，4
     *      setadd 集合的差集为 1，这里的difference意思就是第一个参数和第二个参数做比较，第二个参数以第一个参数为参照物
     * difference(K key, Collection<K> otherKeys) 与多个集合求差集
     */
    private void difference() {
        final String key = "setadd";
        final String otherKey = "newUnion";
        final Set difference = setOperations.difference(otherKey,key);//sdiff
        println("difference集合集===",difference);
    }

    /**
     * 集合求并集，并且存入新的集合key中,返回值为目标的条数
     * 如果目标key 是本身，值会被新的覆盖掉，如果值相同不会重复加入
     */
    private void unionAndStore() {
        final String setadd = "setadd";
        final String setadd1 = "setadd1";
        final String newUnion = "newUnion";
        final Long unionAndStore = setOperations.unionAndStore(setadd, setadd1, newUnion);//sunionstore
        println("并集，并且将结果存入新的key=" + newUnion, unionAndStore);
    }

    /**
     * 集合求并集,不存在的key被视为空集。
     */
    private void union() {
        final String setadd = "setadd";
        final String setadd1 = "setadd1";
        final Set union = setOperations.union(setadd, setadd1);//sunion
        println("并集集合", union);
    }

    /**
     * 求交集，并且存把交集的值存到新的key中
     * intersectAndStore(K key, Collection<K> otherKeys, K destKey) 过个key求交集
     */
    private void intersectAndStore() {
        final String setadd = "setadd";
        final Set members1 = setOperations.members(setadd);
        println("setadd=====", members1);
        final String setadd1 = "setadd1";
        final Set members2 = setOperations.members(setadd1);
        println("setadd1=====", members2);
        final String setTest = "setTest";
        final Long aLong = setOperations.intersectAndStore(setadd, setadd1, setTest);//sinterstore
        println(setTest, aLong);
    }

    /**
     * 集合求交集
     */
    private void intersect() {
        final Set members1 = setOperations.members("setadd");
        println("setadd=====", members1);
        final Set members2 = setOperations.members("setadd1");
        println("setadd1=====", members2);
//        List<String> keys = Lists.newArrayList();
//        keys.add("key1");
//        keys.add("key2");
//        keys.add("key3");
//        setOperations.intersect(members1,keys);//与多一个集合求交集

        final Set intersect = setOperations.intersect("setadd", "setadd1");//sinter

        println("交集结果=====", intersect);
    }

    /**
     * 判断元素是不是在集合key的成员
     */
    private void isMember() {
        final String key = "setadd";
        final String value = "x1";
        final Boolean aBoolean = setOperations.isMember(key, value);
        println(key, aBoolean);
    }

    /**
     * 随机移除一个，并且返回移除的值
     */
    private void setpop() {
        final String setadd = "setadd";
//        final Object pop = setOperations.pop(setadd);
//        println(setadd, pop);

        final Object pop1 = setOperations.pop(setadd, 2);//spop
        println(setadd, pop1);
        final Set members = setOperations.members("setadd");//smembers
        println(setadd, members);
    }

    /**
     * SMOVE 操作
     */
    private void setmove() {
        final String setadd = "setadd";
        final String setNewAdd = "setNewAdd";
        final String value = "";
        setOperations.move(setadd, value, setNewAdd);//smove
    }

    /**
     * 移除一个或多个元素
     */
    private void setRemove() {
        final String setadd = "setadd";
        String[] strs = {"x1", "x2"};
        final Long remove = setOperations.remove(setadd, strs);
        println(setadd, remove);
    }

    /**
     * 返回添加的个数
     * 添加元素,
     */
    private void setAdd() {
        final String setadd = "setadd1";
        String[] strs = {"x1", "x2", "x3", "v4", "v5", "x6", "x7"};
//        List<String> list = Lists.newArrayList();
//        list.add("v1");
//        list.add("v2");
//        list.add("v3");
        final Long count = setOperations.add(setadd, strs);
        println(setadd, count);
    }


    /**
     * 截断list
     * start 是从0开始，-1 表示到结束
     */
    private void trim() {
        final String rightPushIfPresent = "rightPushIfPresent";
        listOperations.trim(rightPushIfPresent, 1, -1);//把第一个元素删除掉
        final List range = listOperations.range(rightPushIfPresent, 0, -1);
        println(rightPushIfPresent, range);
    }

    /**
     * 获取list的值
     * 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 stop 指定。
     * 下标(index)参数 start 和 stop 都以 0 为底，也就是说，以 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     */
    public void range() {
        final String rightPushIfPresent = "rightPushIfPresent";
        final List range = listOperations.range(rightPushIfPresent, 0, -1);
        println(rightPushIfPresent, range);
    }

    /**
     * 移除最右边的值，并且把该值添加到新的key里面
     */
    private void rightPopAndLeftPush() {
        final String rightPushIfPresent = "rightPushIfPresent";
        final String leftPushIfPresent = "leftPushIfPresent";
        listOperations.rightPopAndLeftPush(rightPushIfPresent, leftPushIfPresent);
    }

    /**
     * 返回最左边的值，并且删除该值
     * V leftPop(K key, long timeout, TimeUnit unit);
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     */
    private void leftPop() {
        final String rightPushIfPresent = "leftPushAll";
        final Object leftPop = listOperations.leftPop(rightPushIfPresent);
        println(rightPushIfPresent, leftPop);
    }

    private boolean hashKey(){
        return redisTemplate.hasKey("leftPushAll");
    }
    /**
     * 获取index的值,索引值从0开始
     */
    private void index() {
        final String rightPushIfPresent = "rightPushIfPresent";
        final Object index = listOperations.index(rightPushIfPresent, 2);
        println(rightPushIfPresent, index);
    }


    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * 计数参数以下列方式影响操作：
     * count > 0 : 从表头开始向表尾搜索，移除与 value 相等的元素，数量为 count 。
     * count < 0 : 从表尾开始向表头搜索，移除与 value 相等的元素，数量为 count 的绝对值。
     * count = 0 : 移除表中所有与 value 相等的值。
     * 返回值为删除的个数
     */
    private void remove() {
        final String rightPushIfPresent = "rightPushIfPresent";
        final Long aLong = listOperations.remove(rightPushIfPresent, 2, "v1");
        println(rightPushIfPresent, aLong);
    }

    /**
     * 在列表中index的位置设置value值,索引值是从0开始
     * 如果操过总数的索引，会出现，ERR index out of range
     * 如果设置的值不同，更新，相同更新
     */
    private void set() {
        final String rightPushIfPresent = "rightPushIfPresent";
        listOperations.set(rightPushIfPresent, 2, "x1");
    }


    private void leftPush() {
        final String leftPushKey = "leftPush";
        final Long leftPushValue = listOperations.leftPush(leftPushKey, "leftpush");
        println(leftPushKey, leftPushValue);
    }

    /**
     * 插入的顺序是 v3,v2,v1
     */
    private void leftPushAll() {

        final String leftPushKey = "leftPushAll";
        List<User> list = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            User u = new User();
            u.setAddress(i + " address");
            u.setAge(i * 10);
            u.setIdNo("513901-" + i);
            u.setName("name-" + i);
            list.add(u);
        }

        final Long leftPushValue = listOperations.leftPushAll(leftPushKey, list);
        println(leftPushKey, leftPushValue);
    }

    /**
     * 只有key存在才能添加成功
     * 返回值为集合的条数
     */
    private void leftPushIfPresent() {
        final String leftPushIfPresent = "leftPushIfPresent";
        final String value = "strValue";
        final Long leftPushValue = listOperations.leftPushIfPresent(leftPushIfPresent, value);
        println(leftPushIfPresent, leftPushValue);//0
        listOperations.leftPush(leftPushIfPresent, value);//0
        final Long leftPushValue1 = listOperations.leftPushIfPresent(leftPushIfPresent, value);
        println(leftPushIfPresent, leftPushValue1);
    }

    private void rightPush() {
        final String rightPushKey = "rightPush";
        final String value = "strValue";
        final Long rightPush = listOperations.rightPush(rightPushKey, value);
        println(rightPushKey, rightPush);
    }

    /**
     * 插入的顺序为 v1 ,v2 ,v3
     */
    private void rightPushALL() {
        final String rightPushALL = "rightPushALL";
        List<String> list = Lists.newArrayList();
        list.add("v1");
        list.add("v2");
        list.add("v3");
        final Long rightPushAllValue = listOperations.rightPushAll(rightPushALL, list);
        println(rightPushALL, rightPushAllValue);
        final List range = listOperations.range(rightPushALL, 0, -1);
        println(rightPushALL, range);

    }

    /**
     * 只有key存在的时候，才能操作成功，返回值为总得条数
     * key 存在的时候，只是在尾部增加一条数据，如果插入的是集合，结构如下：
     * eg：数据结构
     * 1 v1
     * 2 v2
     * 3 v3
     * 4 ["v1","v2","v3"]
     */
    private void rightPushIfPresent() {
        final String rightPushIfPresent = "rightPushIfPresent";
        List<String> list = Lists.newArrayList();
        list.add("v1");
        list.add("v1");
        list.add("v1");
        final Long pushIfPresent1 = listOperations.rightPushIfPresent(rightPushIfPresent, list);
        println(rightPushIfPresent, pushIfPresent1);//0
        final Long aLong = listOperations.rightPushAll(rightPushIfPresent, list);
        println(rightPushIfPresent, aLong);//3
        final Long pushIfPresent = listOperations.rightPushIfPresent(rightPushIfPresent, list);
        println(rightPushIfPresent, pushIfPresent);//4
    }

    /**
     * 如果字符串存在，不存在，则新增，存在则追加字符串
     */
    private void append() {
        final String append = "append";
        operations.append(append, "hello");
        operations.append(append, "world");
        println(append, operations.get(append));//key = append value = helloworld
    }

    /**
     * delta 变量增量,每次增加1.1
     */
    private void incrementDouble() {
        final String incrementDouble = "incrementDouble";
        final Double aDouble = operations.increment(incrementDouble, 1.1);
        println(incrementDouble, aDouble);
    }

    /**
     * 变量增量,每次增加1
     */
    private void incrementLong() {
        final String incrementLong = "incrementLong";
        final Long aLong = operations.increment(incrementLong, 1);
        println(incrementLong, aLong);
    }

    /**
     * 获取旧值，设置新的值
     */
    private void getAndSet() {
        final String key1 = "key";
        operations.set(key1, "123");
        final Object key = operations.getAndSet(key1, "456");
        println(key1, key);//key==123

        println(key1, operations.get(key1));//key==456
    }

    private void setex() throws InterruptedException {
        //set void set(K key, V value, long timeout, TimeUnit unit);
        //将值 value 关联到 key ，并将 key 的生存时间设为 seconds (以秒为单位)。底层实现setex
        //SETEX 是一个原子性(atomic)操作，关联值和设置生存时间两个动作会在同一时间内完成，该命令在 Redis 用作缓存时，非常实用。
        final String redisKeyTimeout = "redis:test:setex:timeout";
        operations.set(redisKeyTimeout, "历史", 10, TimeUnit.SECONDS);
        println(redisKeyTimeout, operations.get(redisKeyTimeout));
        Thread.sleep(10000);
        println(redisKeyTimeout, operations.get(redisKeyTimeout));
    }

    private void setOrget() {
        //设置字符串set voi set(K key, V value)
        final String redis_str = "redis_str";
        operations.set(redis_str, "张三");
        //获取字符串
        final Object redisStr = operations.get("redis_str");
        logger.error("redis==={}", redisStr);
        System.err.print(redisStr);
    }

    private void setoffset() {
        //set void set(K key, V value, long offset);
        //第一次通过调用set(key,value)，第二次设置相同的key，调用set(K key, V value, long offset)，
        //会在第一次值的offset值开始（索引是重1开始）替换，替换的长度就是第一个值的长度
        final String redisKeyOffset = "redis:test:setoffset";
        operations.set(redisKeyOffset, "hello world66");
        operations.set(redisKeyOffset, "redis1", 6);
        println(redisKeyOffset, operations.get(redisKeyOffset));//替换的结果为，redis:test:setoffset==hello redis16
    }

    private void setnx() {
        //setnx Boolean setnx(K key, V value);
        //检查key 是否存在，存在失败，不存在成功  具体实现为setnx
        final String redisKeysetIfAbsent = "redis:test:setnx";
        println(redisKeysetIfAbsent, operations.setIfAbsent(redisKeysetIfAbsent, "setnx"));//true
        println(redisKeysetIfAbsent, operations.setIfAbsent(redisKeysetIfAbsent, "setnx"));//false
    }

    private void msetAndmget() {
        //批量插入不同的key 具体实现mset是一个原子性(atomic)操作，所有给定 key 都会在同一时间内被设置
        Map<String, String> stringMap = Maps.newHashMap();
        stringMap.put("key1", "value1");
        stringMap.put("key2", "value2");
        stringMap.put("key3", "value3");
        operations.multiSet(stringMap);
        final List multiGet = operations.multiGet(Arrays.asList("key1", "key2", "key3"));
        println(stringMap.keySet(), multiGet);
    }

    /**
     * 同时设置多个键，先检查键是否存在
     */
    private void multiSetIfAbsent() {
        Map<String, String> map = Maps.newHashMap();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");
        Map<String, String> map1 = Maps.newHashMap();
        map1.put("k1", "v1");
        map1.put("k2", "v2");
        map1.put("k3", "v3");

        println(map, operations.multiSetIfAbsent(map));//true
        println(map1, operations.multiSetIfAbsent(map1));//false
    }

    private void println(Object key, Object value) {
        System.out.println("key = " + key + " value = " + value);
    }
}
