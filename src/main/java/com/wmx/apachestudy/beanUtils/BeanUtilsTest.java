package com.wmx.apachestudy.beanUtils;

import com.wmx.apachestudy.propertyUtils.Department;
import com.wmx.apachestudy.propertyUtils.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/13 17:16
 */
public class BeanUtilsTest {

    /**
     * Object cloneBean(final Object bean)：基于属性的 getter和 setter 克隆 bean，即使 bean类本身没有实现 Cloneable。
     * 查看 {@link BeanUtilsBean#cloneBean} 源码就可以看出，底层通过反射重新 newInstance 一个新对象，
     * 然后 {@link PropertyUtilsBean#copyProperties} 赋值就对象的属性到新对象中.
     * 即使 bean 对象的属性关联了其它对象，也会被一并克隆.
     */
    public void testCloneBean() {
        try {
            Person person = new Person(9281, "华福", LocalDateTime.now(), false, null);
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            Department department = new Department(1000, "开发部", personList);

            Department cloneBean = (Department) BeanUtils.cloneBean(department);
            cloneBean.setName("华安");
            //输出：Person{id=9281, name='华福', birthday=2020-04-14T08:34:05.296, isMarry=false, price=null}
            System.out.println(person);
            //输出：Department{id=1000, name='华安', personList=[Person{id=9281, name='华福', birthday=2020-04-14T08:34:05.296, isMarry=false, price=null}], dataMap=null}
            System.out.println(cloneBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * copyProperties(final Object dest, final Object orig)
     * 对于属性名称相同的所有情况，将属性值从源 orig 复制到 dest.
     * 常用于做对象克隆，或者将 Map 对象的属性值赋值给 java bean.
     */
    public void testCopyProperties() {
        try {
            Person person = new Person(9528, "华福", LocalDateTime.now(), false, null);
            Person person_clone = new Person();
            BeanUtils.copyProperties(person_clone, person);
            //输出：Person{id=9528, name='华福', birthday=2020-04-14T20:20:44.402, marry=false, price=null}
            System.out.println(person_clone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * copyProperty(final Object bean, final String name, final Object value)
     * 将 value 值赋值给 bean 的 name 属性，自动进行类型转换，意思是即使 name 是 flout 类型，传入 "667.99" 也能自动转换.
     */
    public void testCopyProperty() {
        try {
            Person person = new Person(1000, "华福", LocalDateTime.now(), false, null);
            BeanUtils.copyProperty(person, "price", "8899.89");
            //输出：Person{id=1000, name='华福', birthday=2020-04-14T20:25:06.181, marry=false, price=8899.89}
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setProperty(final Object bean, final String name, final Object value)：设置对象的属性值.
     * String getProperty(final Object bean, final String name)：获取对象的属性值.
     */
    public void testSetProperty() {
        try {
            Department department = new Department(2001, "大数据研发中心");
            Map<String, Object> dataMap = new HashMap<>(8);
            dataMap.put("code", 200);
            dataMap.put("message", "success");

            BeanUtils.setProperty(department, "dataMap", dataMap);
            //输出：Department{id=2001, name='大数据研发中心', personList=null, dataMap={code=200, message=success}}
            System.out.println(department);
            //输出：大数据研发中心
            System.out.println(BeanUtils.getProperty(department, "name"));
            //map 嵌套类型，可以使用 属性名(key) 获取 map 属性中指定 key 的值， 输出：200
            //如果 list、数组等类型，则可以使用 属性名[索引] 的方式获取
            System.out.println(BeanUtils.getProperty(department, "dataMap(code)"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Map<String, String> describe(final Object bean)
     * 将 bean 对象的属性提取到 map 中。bean 对象中只有提供了 getXxx 方法的属性才会提取，否则不提取。
     */
    public void testDescribe() {
        try {
            Department department = new Department(1002, "大数据组");
            Map<String, Object> dataMap = new HashMap<>(8);
            dataMap.put("code", 201);
            dataMap.put("message", "success");
            department.setDataMap(dataMap);
            Map<String, String> describe = BeanUtils.describe(department);
            //输出：{personList=null, dataMap={code=201, message=success}, name=大数据组, id=1002}
            System.out.println(describe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * populate(final Object bean, final Map<String, ? extends Object> properties)
     * 将 properties map 的属性值赋给 bean 中同名的属性
     */
    public void testPopulate() {
        try {
            Map<String, Object> deptMap = new HashMap<>(8);
            deptMap.put("id", 1003);
            deptMap.put("name", "Java开发部");
            Map<String, Object> dataMap = new HashMap<>(8);
            dataMap.put("code", 202);
            dataMap.put("message", "success");
            deptMap.put("dataMap", dataMap);

            Department department = new Department();
            BeanUtils.populate(department, deptMap);
            //输出：Department{id=1003, name='Java开发部', personList=null, dataMap={code=202, message=success}}
            System.out.println(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BeanUtilsTest().testSetProperty();
    }

}
