- [\java\com\mtl\pro\easy_work\common\RespRes.java](#fnref1)
- [\java\com\mtl\pro\easy_work\controller\TaskController.java](#fnref2)
- [\java\com\mtl\pro\easy_work\dao\TaskMapper.java](#fnref3)
- [\java\com\mtl\pro\easy_work\datasource\DruidSource.java](#fnref4)
- [\java\com\mtl\pro\easy_work\EasyWorkApplication.java](#fnref5)
- [\java\com\mtl\pro\easy_work\entity\Task.java](#fnref6)
- [\java\com\mtl\pro\easy_work\entity\TaskExample.java](#fnref7)
- [\java\com\mtl\pro\easy_work\service\impl\TaskServiceImpl.java](#fnref8)
- [\java\com\mtl\pro\easy_work\service\TaskService.java](#fnref9)
- [\resources\application.yml](#fnref10)
- [\resources\logback-spring.xml](#fnref11)
- [\resources\mapper\TaskMapper.xml](#fnref12)
#### \java\com\mtl\pro\easy_work\common\RespRes.java[^a1]
```java
package com.mtl.pro.easy_work.common;

import lombok.Data;

import java.util.List;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 8:31
 */
@Data
public class RespRes {
    private int code;
    private String msg;
    private List data;

    public RespRes(int code, String msg, List data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RespRes ok(){
        return new RespRes(1, "", null);
    }

    public static RespRes ok(String msg){
        return new RespRes(1, msg, null);
    }

    public static RespRes ok(List data){
        return new RespRes(1, "", data);
    }

    public static RespRes ok(String msg, List data){
        return new RespRes(1, msg, data);
    }

    public static RespRes error(){
        return new RespRes(0, "", null);
    }

    public static RespRes error(String msg){
        return new RespRes(1, msg, null);
    }

    public static RespRes error(List data){
        return new RespRes(1, "", data);
    }

    public static RespRes error(String msg, List data){
        return new RespRes(1, msg, data);
    }
}

```

#### \java\com\mtl\pro\easy_work\controller\TaskController.java[^a2]
```java
package com.mtl.pro.easy_work.controller;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.entity.Task;
import com.mtl.pro.easy_work.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 8:29
 */

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    RespRes get(){
        return taskService.getAllTask();
    }

    @PostMapping()
    RespRes save(@RequestBody Task task){
        return taskService.save(task);
    }

    @PutMapping()
    RespRes update(@RequestBody Task task){
        return taskService.update(task);
    }

    @DeleteMapping()
    RespRes delete(Integer id){
        return taskService.delete(id);
    }

}

```

#### \java\com\mtl\pro\easy_work\dao\TaskMapper.java[^a3]
```java
package com.mtl.pro.easy_work.dao;

import com.mtl.pro.easy_work.entity.Task;
import com.mtl.pro.easy_work.entity.TaskExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {
    long countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}
```

#### \java\com\mtl\pro\easy_work\datasource\DruidSource.java[^a4]
```java
package com.mtl.pro.easy_work.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 11:06
 */
@Configuration
public class DruidSource {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter : {}", e.getMessage());
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }
}

```

#### \java\com\mtl\pro\easy_work\EasyWorkApplication.java[^a5]
```java
package com.mtl.pro.easy_work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class EasyWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyWorkApplication.class, args);
    }

}

```

#### \java\com\mtl\pro\easy_work\entity\Task.java[^a6]
```java
package com.mtl.pro.easy_work.entity;

public class Task {
    private Integer id;

    private String name;

    private Integer done;

    private Integer allNum;

    private String note;

    private Integer user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
```

#### \java\com\mtl\pro\easy_work\entity\TaskExample.java[^a7]
```java
package com.mtl.pro.easy_work.entity;

import java.util.ArrayList;
import java.util.List;

public class TaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDoneIsNull() {
            addCriterion("done is null");
            return (Criteria) this;
        }

        public Criteria andDoneIsNotNull() {
            addCriterion("done is not null");
            return (Criteria) this;
        }

        public Criteria andDoneEqualTo(Integer value) {
            addCriterion("done =", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotEqualTo(Integer value) {
            addCriterion("done <>", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneGreaterThan(Integer value) {
            addCriterion("done >", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneGreaterThanOrEqualTo(Integer value) {
            addCriterion("done >=", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneLessThan(Integer value) {
            addCriterion("done <", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneLessThanOrEqualTo(Integer value) {
            addCriterion("done <=", value, "done");
            return (Criteria) this;
        }

        public Criteria andDoneIn(List<Integer> values) {
            addCriterion("done in", values, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotIn(List<Integer> values) {
            addCriterion("done not in", values, "done");
            return (Criteria) this;
        }

        public Criteria andDoneBetween(Integer value1, Integer value2) {
            addCriterion("done between", value1, value2, "done");
            return (Criteria) this;
        }

        public Criteria andDoneNotBetween(Integer value1, Integer value2) {
            addCriterion("done not between", value1, value2, "done");
            return (Criteria) this;
        }

        public Criteria andAllNumIsNull() {
            addCriterion("all_num is null");
            return (Criteria) this;
        }

        public Criteria andAllNumIsNotNull() {
            addCriterion("all_num is not null");
            return (Criteria) this;
        }

        public Criteria andAllNumEqualTo(Integer value) {
            addCriterion("all_num =", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotEqualTo(Integer value) {
            addCriterion("all_num <>", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumGreaterThan(Integer value) {
            addCriterion("all_num >", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("all_num >=", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumLessThan(Integer value) {
            addCriterion("all_num <", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumLessThanOrEqualTo(Integer value) {
            addCriterion("all_num <=", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumIn(List<Integer> values) {
            addCriterion("all_num in", values, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotIn(List<Integer> values) {
            addCriterion("all_num not in", values, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumBetween(Integer value1, Integer value2) {
            addCriterion("all_num between", value1, value2, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotBetween(Integer value1, Integer value2) {
            addCriterion("all_num not between", value1, value2, "allNum");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andUserIsNull() {
            addCriterion("user is null");
            return (Criteria) this;
        }

        public Criteria andUserIsNotNull() {
            addCriterion("user is not null");
            return (Criteria) this;
        }

        public Criteria andUserEqualTo(Integer value) {
            addCriterion("user =", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotEqualTo(Integer value) {
            addCriterion("user <>", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThan(Integer value) {
            addCriterion("user >", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("user >=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThan(Integer value) {
            addCriterion("user <", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserLessThanOrEqualTo(Integer value) {
            addCriterion("user <=", value, "user");
            return (Criteria) this;
        }

        public Criteria andUserIn(List<Integer> values) {
            addCriterion("user in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotIn(List<Integer> values) {
            addCriterion("user not in", values, "user");
            return (Criteria) this;
        }

        public Criteria andUserBetween(Integer value1, Integer value2) {
            addCriterion("user between", value1, value2, "user");
            return (Criteria) this;
        }

        public Criteria andUserNotBetween(Integer value1, Integer value2) {
            addCriterion("user not between", value1, value2, "user");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
```

#### \java\com\mtl\pro\easy_work\service\impl\TaskServiceImpl.java[^a8]
```java
package com.mtl.pro.easy_work.service.impl;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.dao.TaskMapper;
import com.mtl.pro.easy_work.entity.Task;
import com.mtl.pro.easy_work.entity.TaskExample;
import com.mtl.pro.easy_work.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 8:36
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Override
    public RespRes delete(Integer id) {
        int i = taskMapper.deleteByPrimaryKey(id);
        return i>0? RespRes.ok() : RespRes.error();
    }

    @Override
    public RespRes update(Task task) {
        TaskExample example = new TaskExample();
        TaskExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(task.getId());
        int i = taskMapper.updateByExample(task, example);
        return i>0? RespRes.ok() : RespRes.error();
    }

    @Override
    public RespRes save(Task task) {
        int insert = taskMapper.insert(task);
        return insert>0? RespRes.ok() : RespRes.error();
    }

    @Override
    public RespRes getAllTask() {
        return RespRes.ok(taskMapper.selectByExample(null));
    }
}

```

#### \java\com\mtl\pro\easy_work\service\TaskService.java[^a9]
```java
package com.mtl.pro.easy_work.service;

import com.mtl.pro.easy_work.common.RespRes;
import com.mtl.pro.easy_work.entity.Task;

/**
 * TODO ..
 *
 * @auther liegeheijie@gmail.com
 * @date 2019/5/15 8:35
 */
public interface TaskService {
    RespRes getAllTask();

    RespRes save(Task task);

    RespRes update(Task task);

    RespRes delete(Integer id);
}

```

#### \resources\application.yml[^a10]
```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/easywork?useUnicode=true&characterEncoding=UTF-8
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
    driverClassName: com.mysql.jdbc.Driver
    ###################以下为druid增加的配置###########################
    type: com.alibaba.druid.pool.DruidDataSource
    # 下面为连接池的补充设置，应用到上面所有数据源中
    # 初始化大小，最小，最大
    initialSize: 5
    minIdle: 5
    maxActive: 20
    # 配置获取连接等待超时的时间
    maxWait: 60000
    # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    timeBetweenEvictionRunsMillis: 60000
    # 配置一个连接在池中最小生存的时间，单位是毫秒
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    # 打开PSCache，并且指定每个连接上PSCache的大小
    poolPreparedStatements: true
    maxPoolPreparedStatementPerConnectionSize: 20
    # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙，此处是filter修改的地方
    filters: stat,wall,log4j
    # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000
    # 合并多个DruidDataSource的监控数据
    useGlobalDataSourceStat: true
server:
  port: 7070
mybatis:
  type-aliases-package:
  mapper-locations: classpath:mapper/*Mapper.xml

```

#### \resources\logback-spring.xml[^a11]
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

	<!--重要-->
	<property name="log.console" value="D:\\tmp" />
 	<contextName>logback</contextName>
	<appender name="console" class="ch.qos.logback.core.ConsoleAppender">
		<encoder>
			<pattern>
			%d{YY-MM-dd HH:mm:ss}[%level][%thread]%logger{36}-%msg%n
			</pattern>
		</encoder>
	</appender>
	<appender name="file" class="ch.qos.logback.core.rolling.RollingFileAppender">
		<File>${log.console}/tmp.log</File>
		<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<fileNamePattern>${log.console}/tmp.%d{yyyy-MM-dd}.%i.log.zip</fileNamePattern>
			<maxHistory>30</maxHistory>
			<totalSizeCap>1GB</totalSizeCap>
			
			 <timeBasedFileNamingAndTriggeringPolicy
                class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
       <!--         最大64MB 超过最大值，会重新建一个文件 -->
                <maxFileSize>64MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
            
		</rollingPolicy>
		<encoder>
			<pattern>%d{YY-MM-dd HH:mm:ss} %contextName [%thread] %-5level %logger{36} - %msg%n
			</pattern>
		</encoder>
	</appender>
	
	
    <root level="debug">
            <appender-ref ref="console" />  
            <!--<appender-ref ref="file" /> -->
    </root>  
   <!--  
   	<logger name="scai.aladdin.bpc.busi" level="info" additivity="false">
    	<appender-ref ref="busi"/>
    </logger> 
    <logger name="scai.aladdin.bpc.console" level="info" additivity="false">
    	<appender-ref ref="console-bpc"/>
    </logger> -->
</configuration>  
```

#### \resources\mapper\TaskMapper.xml[^a12]
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mtl.pro.easy_work.dao.TaskMapper">
  <resultMap id="BaseResultMap" type="com.mtl.pro.easy_work.entity.Task">
    <id column="id" jdbcType="INTEGER" property="id" />
    <result column="name" jdbcType="VARCHAR" property="name" />
    <result column="done" jdbcType="INTEGER" property="done" />
    <result column="all_num" jdbcType="INTEGER" property="allNum" />
    <result column="note" jdbcType="VARCHAR" property="note" />
    <result column="user" jdbcType="INTEGER" property="user" />
  </resultMap>
  <sql id="Example_Where_Clause">
    <where>
      <foreach collection="oredCriteria" item="criteria" separator="or">
        <if test="criteria.valid">
          <trim prefix="(" prefixOverrides="and" suffix=")">
            <foreach collection="criteria.criteria" item="criterion">
              <choose>
                <when test="criterion.noValue">
                  and ${criterion.condition}
                </when>
                <when test="criterion.singleValue">
                  and ${criterion.condition} #{criterion.value}
                </when>
                <when test="criterion.betweenValue">
                  and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                </when>
                <when test="criterion.listValue">
                  and ${criterion.condition}
                  <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                    #{listItem}
                  </foreach>
                </when>
              </choose>
            </foreach>
          </trim>
        </if>
      </foreach>
    </where>
  </sql>
  <sql id="Update_By_Example_Where_Clause">
    <where>
      <foreach collection="example.oredCriteria" item="criteria" separator="or">
        <if test="criteria.valid">
          <trim prefix="(" prefixOverrides="and" suffix=")">
            <foreach collection="criteria.criteria" item="criterion">
              <choose>
                <when test="criterion.noValue">
                  and ${criterion.condition}
                </when>
                <when test="criterion.singleValue">
                  and ${criterion.condition} #{criterion.value}
                </when>
                <when test="criterion.betweenValue">
                  and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}
                </when>
                <when test="criterion.listValue">
                  and ${criterion.condition}
                  <foreach close=")" collection="criterion.value" item="listItem" open="(" separator=",">
                    #{listItem}
                  </foreach>
                </when>
              </choose>
            </foreach>
          </trim>
        </if>
      </foreach>
    </where>
  </sql>
  <sql id="Base_Column_List">
    id, name, done, all_num, note, user
  </sql>
  <select id="selectByExample" parameterType="com.mtl.pro.easy_work.entity.TaskExample" resultMap="BaseResultMap">
    select
    <if test="distinct">
      distinct
    </if>
    <include refid="Base_Column_List" />
    from task
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
    <if test="orderByClause != null">
      order by ${orderByClause}
    </if>
  </select>
  <select id="selectByPrimaryKey" parameterType="java.lang.Integer" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from task
    where id = #{id,jdbcType=INTEGER}
  </select>
  <delete id="deleteByPrimaryKey" parameterType="java.lang.Integer">
    delete from task
    where id = #{id,jdbcType=INTEGER}
  </delete>
  <delete id="deleteByExample" parameterType="com.mtl.pro.easy_work.entity.TaskExample">
    delete from task
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
  </delete>
  <insert id="insert" parameterType="com.mtl.pro.easy_work.entity.Task">
    insert into task (id, name, done, 
      all_num, note, user)
    values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, #{done,jdbcType=INTEGER}, 
      #{allNum,jdbcType=INTEGER}, #{note,jdbcType=VARCHAR}, #{user,jdbcType=INTEGER})
  </insert>
  <insert id="insertSelective" parameterType="com.mtl.pro.easy_work.entity.Task">
    insert into task
    <trim prefix="(" suffix=")" suffixOverrides=",">
      <if test="id != null">
        id,
      </if>
      <if test="name != null">
        name,
      </if>
      <if test="done != null">
        done,
      </if>
      <if test="allNum != null">
        all_num,
      </if>
      <if test="note != null">
        note,
      </if>
      <if test="user != null">
        user,
      </if>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
      <if test="id != null">
        #{id,jdbcType=INTEGER},
      </if>
      <if test="name != null">
        #{name,jdbcType=VARCHAR},
      </if>
      <if test="done != null">
        #{done,jdbcType=INTEGER},
      </if>
      <if test="allNum != null">
        #{allNum,jdbcType=INTEGER},
      </if>
      <if test="note != null">
        #{note,jdbcType=VARCHAR},
      </if>
      <if test="user != null">
        #{user,jdbcType=INTEGER},
      </if>
    </trim>
  </insert>
  <select id="countByExample" parameterType="com.mtl.pro.easy_work.entity.TaskExample" resultType="java.lang.Long">
    select count(*) from task
    <if test="_parameter != null">
      <include refid="Example_Where_Clause" />
    </if>
  </select>
  <update id="updateByExampleSelective" parameterType="map">
    update task
    <set>
      <if test="record.id != null">
        id = #{record.id,jdbcType=INTEGER},
      </if>
      <if test="record.name != null">
        name = #{record.name,jdbcType=VARCHAR},
      </if>
      <if test="record.done != null">
        done = #{record.done,jdbcType=INTEGER},
      </if>
      <if test="record.allNum != null">
        all_num = #{record.allNum,jdbcType=INTEGER},
      </if>
      <if test="record.note != null">
        note = #{record.note,jdbcType=VARCHAR},
      </if>
      <if test="record.user != null">
        user = #{record.user,jdbcType=INTEGER},
      </if>
    </set>
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  <update id="updateByExample" parameterType="map">
    update task
    set id = #{record.id,jdbcType=INTEGER},
      name = #{record.name,jdbcType=VARCHAR},
      done = #{record.done,jdbcType=INTEGER},
      all_num = #{record.allNum,jdbcType=INTEGER},
      note = #{record.note,jdbcType=VARCHAR},
      user = #{record.user,jdbcType=INTEGER}
    <if test="_parameter != null">
      <include refid="Update_By_Example_Where_Clause" />
    </if>
  </update>
  <update id="updateByPrimaryKeySelective" parameterType="com.mtl.pro.easy_work.entity.Task">
    update task
    <set>
      <if test="name != null">
        name = #{name,jdbcType=VARCHAR},
      </if>
      <if test="done != null">
        done = #{done,jdbcType=INTEGER},
      </if>
      <if test="allNum != null">
        all_num = #{allNum,jdbcType=INTEGER},
      </if>
      <if test="note != null">
        note = #{note,jdbcType=VARCHAR},
      </if>
      <if test="user != null">
        user = #{user,jdbcType=INTEGER},
      </if>
    </set>
    where id = #{id,jdbcType=INTEGER}
  </update>
  <update id="updateByPrimaryKey" parameterType="com.mtl.pro.easy_work.entity.Task">
    update task
    set name = #{name,jdbcType=VARCHAR},
      done = #{done,jdbcType=INTEGER},
      all_num = #{allNum,jdbcType=INTEGER},
      note = #{note,jdbcType=VARCHAR},
      user = #{user,jdbcType=INTEGER}
    where id = #{id,jdbcType=INTEGER}
  </update>
</mapper>
```

[^a1]:\java\com\mtl\pro\easy_work\common\RespRes.java
[^a2]:\java\com\mtl\pro\easy_work\controller\TaskController.java
[^a3]:\java\com\mtl\pro\easy_work\dao\TaskMapper.java
[^a4]:\java\com\mtl\pro\easy_work\datasource\DruidSource.java
[^a5]:\java\com\mtl\pro\easy_work\EasyWorkApplication.java
[^a6]:\java\com\mtl\pro\easy_work\entity\Task.java
[^a7]:\java\com\mtl\pro\easy_work\entity\TaskExample.java
[^a8]:\java\com\mtl\pro\easy_work\service\impl\TaskServiceImpl.java
[^a9]:\java\com\mtl\pro\easy_work\service\TaskService.java
[^a10]:\resources\application.yml
[^a11]:\resources\logback-spring.xml
[^a12]:\resources\mapper\TaskMapper.xml
