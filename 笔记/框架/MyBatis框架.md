# MyBatis框架

## 搭建MyBatis环境

1、下载需要的jar包

2、部署jar文件

3、创建MyBatis核心配置文件，为了在框架集成时更好地区分各个配置文件，取名为mybatis-config.xml

4、创建持久化类pojo和sql映射文件

5、在MyBatis核心配置文件中注册sql映射文件

6、创建测试类

需要注意的点：

MyBatis核心配置文件主要用于配置数据库连接和MyBatis运行时所需的各种特性

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- 通过这个配置文件完成mybatis与数据库的连接 -->
<configuration>
    <!-- 引入 jdbc.properties(数据库配置文件) 文件-->
    <properties resource="jdbc.properties"/>
    <!-- 配置mybatis的log实现为LOG4J -->
    <settings>
        <setting name="logImpl" value="LOG4J" />
    <!--禁止自动映射-->
    <!--     <setting name="autoMappingBehavior" value="NONE"/>-->
    </settings>
    
	<!-- 设置别名 -->
    <typeAliases>
        <package name="com.ydq.pojo"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <!--配置事务管理，采用JDBC的事务管理  -->
            <transactionManager type="JDBC"/>
            <!-- POOLED:mybatis自带的数据源，JNDI:基于tomcat的数据源 -->
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <!-- 将mapper映射文件加入到配置文件中 -->
    <mappers>
        <mapper resource="com/ydq/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```

创建SQL映射文件，完成对pojo实体类的映射

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ydq.dao.UserMapper">
    <resultMap id="userList" type="user">
        <id property="id" column="id"/>
        <result property="userName" column="userName"/>
        <result property="bankId" column="bankId"/>
        <result property="money" column="money"/>
        <association property="bank" resultMap="bankList"/>
        <collection property="card" resultMap="cardList"/>
    </resultMap>

    <!--公共的resultMap:银行bank类-->
    <resultMap id="bankList" type="Bank">
        <id property="id" column="id"/>
        <result property="bankname" column="bankname"/>
    </resultMap>
    <!--公共的resultMap:银行卡card类-->
    <resultMap id="cardList" type="Card">
        <id property="cid" column="cid"/>
        <result property="userid" column="userid"/>
        <result property="cType" column="cType"/>
    </resultMap>
    <!--根据用户ID查询指定用户的所有银行卡类型-->
    <select id="getCardByUserId" resultMap="userList">
        SELECT u.userName,c.cType FROM `user` u,card c WHERE u.uid = c.userid and u.uid = #{uid}
    </select>

    <!--根据用户ID查询用户信息-->
    <select id="selectUserById" resultType="user" parameterType="int">
        select * from user where uid = #{uid}
    </select>

    <!--查询所有用户信息-->
    <select id="getUserList" resultMap="userList">
		select u.*,b.bankname from `user` u,bank b where u.bankId = b.id
    </select>

    <!--添加用户-->
    <insert id="addUser" parameterType="user">
        insert into user(userName,bankId,money) values (#{userName},#{bankId},#{money})
    </insert>

    <!--删除用户-->
    <delete id="deleteUser" parameterType="int">
        delete from user where uid = #{uid}
    </delete>

    <!--修改用户信息-->
    <update id="upDateUser" parameterType="user">
        update user set userName = #{userName},bankId = #{bankId},money = #{money} where uid = 2
    </update>
</mapper>
```

## MyBatis的核心接口和类

### 1、SqlSessionFactoryBuilder（SqlSessionFactory的构造者）

它的最大特点是用过即丢，最佳的使用范围是方法体内，也就是局部变量

### 2、SqlSessionFactory（SqlSession的工厂）

用于创建SqlSession实例，SqlSessionFactory的最佳作用域是Application，随着应用的生命周期一同存在

3、SqlSession（用于数据持久化）

类似于JDBC中的Connection，最佳的作用范围是request作用域或者方法体作用域