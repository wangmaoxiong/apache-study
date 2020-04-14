package com.wmx.apachestudy.beanUtils;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/13 14:31
 */
public class PropertyUtilsTest {
    public static void main(String[] args) {
        new PropertyUtilsTest().testIndexedProperty();
    }

    /**
     * copyProperties(final Object dest, final Object orig)：将 orig 对象的属性赋值给 dest 对象的属性
     * 1）只有两个对象的属性名称一致时，才会进行赋值操作
     * 2）如果两个对象的属性名称一致，但是数据类型不一致，则类型转换异常报错.
     * 3）对于 DO、DTO、BO、AO、VO、POJO 各种领域模型对象的转换非常方便，不需要自己一个一个属性转换，也不需要再自己封装工具类.
     */
    public void testCopyProperties() {
        try {
            Map<String, Object> personMap = new HashMap<>(8);
            personMap.put("id", 9527);
            personMap.put("name", "华安");
            personMap.put("birthday", LocalDateTime.now());
            personMap.put("address", "长沙市");

            Person person = new Person();
            //将 personMap 对象的属性赋值给 person 对象中的同名属性
            PropertyUtils.copyProperties(person, personMap);
            //输出：Person{id=9527, name='华安', birthday=2020-04-13T15:37:03.423, isMarry=false, price=null}
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Map<String, Object> describe(final Object bean): 将 bean 对象的属性提取到 map 中
     * 1）bean 对象中只有提供了 getXxx 方法的属性才会提取，否则不提取
     */
    public void testDescribe() {
        try {
            Person person = new Person(9528, "华福", LocalDateTime.now(), false, null);
            Map<String, Object> describe = PropertyUtils.describe(person);
            //输出：{birthday=2020-04-13T15:36:45.757, price=null, marry=false, name=华福, id=9528}
            System.out.println(describe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PropertyDescriptor getPropertyDescriptor(final Object bean,final String name)：
     * 1）获取 bean 对象的 name 属性的 属性描述符 PropertyDescriptor。
     * 2）PropertyDescriptor：属性描述符，从中可以获取属性的名称、其 getter、setter 方法等信息.
     */
    public void testGetDescriptor() {
        try {
            Person person = new Person(9528, "华福", LocalDateTime.now(), false, null);
            PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(person, "name");
            //获取属性的 getter、setter 方法对象，没有提供时返回 null
            Method readMethod = pd.getReadMethod();
            Method writeMethod = pd.getWriteMethod();
            //调用 getter 方法取值，输出：华福
            System.out.println(readMethod.invoke(person, null));
            //调用 setter 方法赋值
            writeMethod.invoke(person, "孙悟空");
            //输出：孙悟空
            System.out.println(readMethod.invoke(person, null));
            //输出：name, class java.lang.String
            System.out.println(pd.getDisplayName() + ", " + pd.getPropertyType());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PropertyDescriptor[] getPropertyDescriptors(final Object bean) ：获取 bean 对象的全部属性描述符
     */
    public void testGetPropertyDescriptors() {
        try {
            Person person = new Person(9528, "华福", LocalDateTime.now(), false, null);
            PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(person);
            String[] propertyNames = new String[pds.length];
            for (int i = 0; i < pds.length; i++) {
                propertyNames[i] = pds[i].getName();
            }
            //输出：[birthday, price, marry, name, id]
            System.out.println(Arrays.asList(propertyNames));
            PropertyDescriptor indexedProperty = (PropertyDescriptor) PropertyUtils.getIndexedProperty(pds, null, 1);
            System.out.println(indexedProperty);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * copyProperties(final Object dest, final Object orig)：可以实现克隆的功能，将一个对象克隆一个一模一样的出来.
     * 1）即使 orig 对象的属性关联了其它对象，同样可以一并复制.
     */
    public void testClone() {
        try {
            Person person = new Person(9528, "华福", LocalDateTime.now(), false, null);
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            Department department = new Department(1000, "开发部", personList);
            Department cloneDept = new Department();
            PropertyUtils.copyProperties(cloneDept, department);
            System.out.println(cloneDept);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * java bean 基本属性的取值、赋值。
     * setSimpleProperty(final Object bean,final String name, final Object value)
     * Object getProperty(final Object bean, final String name)
     * <p>
     * 1、属性必须有相应的 getter、setter 方法，否则报错.
     * 2、除了 8 种基本数据类型、String、List、Map 等对象数据类型也都可以获取.
     */
    public void testSimpleProperty() {
        try {
            Person person = new Person();
            PropertyUtils.setSimpleProperty(person, "id", 9527);
            PropertyUtils.setSimpleProperty(person, "birthday", LocalDateTime.now());

            Integer id = (Integer) PropertyUtils.getSimpleProperty(person, "id");
            LocalDateTime birthday = (LocalDateTime) PropertyUtils.getSimpleProperty(person, "birthday");

            //输出：9527,2020-04-14T08:42:06.445
            System.out.println(id + "," + birthday);

            List<Person> personList = new ArrayList<>();
            personList.add(person);
            Department department = new Department();
            PropertyUtils.setSimpleProperty(department, "personList", personList);
            //输出：Department{id=null, name='null', personList=[Person{id=9527, name='null', birthday=2020-04-14T08:44:28.594, isMarry=null, price=null}]}
            System.out.println(department);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * java bean 索引属性取值赋值. 比如 数组、List 等类型的属性
     * Object getIndexedProperty(final Object bean, final String name)
     * Object getIndexedProperty(final Object bean,final String name, final int index)
     * setIndexedProperty(final Object bean, final String name,final Object value)
     * setIndexedProperty(final Object bean, final String name,final int index, final Object value)
     * 1）bean ：待处理的 java bean 对象.
     * 2）name ：索引属性名称，可以直接使用[]指定索引，如 name=personList[0] ，此时不需要再设置 index 索引参数
     * 3）index ：索引属性的索引，如 name=personList,index=0，此时 name 属性不需要再设置索引.
     * 4）value ：为索引属性指定的索引设置值，注意 set 索引属性时，指定的索引必须存在，否则空指针异常，即只能修改，不能添加.
     */
    @SuppressWarnings("all")
    public void testIndexedProperty() {
        try {
            Person person1 = new Person(9523, "华福", LocalDateTime.now(), false, null);
            List<Person> personList = new ArrayList<>();
            personList.add(person1);
            Department department = new Department(1000, "开发部", personList);

            Person person_result1 = (Person) PropertyUtils.getIndexedProperty(department, "personList[0]");
            //输出：Person{id=9523, name='华福', birthday=2020-04-14T09:33:12.118, isMarry=false, price=null}
            System.out.println(person_result1);

            Person person2 = new Person(9524, "华安", LocalDateTime.now(), true, null);
            PropertyUtils.setIndexedProperty(department, "personList", 0, person2);

            Person person_result2 = (Person) PropertyUtils.getIndexedProperty(department, "personList", 0);
            //输出：Person{id=9524, name='华安', birthday=2020-04-14T09:34:52.331, isMarry=true, price=null}
            System.out.println(person_result2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
