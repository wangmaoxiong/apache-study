package com.wmx.apachestudy.propertyUtils;

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
     * java bean 索引属性取值赋值. 比如 数组、List 等类型的属性。目的是对索引元素能一步到位的进行操作
     * Object getIndexedProperty(final Object bean, final String name)
     * Object getIndexedProperty(final Object bean,final String name, final int index)
     * setIndexedProperty(final Object bean, final String name,final Object value)
     * setIndexedProperty(final Object bean, final String name,final int index, final Object value)
     * 1）bean ：待处理的 java bean 对象.
     * 2）name ：索引属性名称，可以直接使用[]指定索引，如 name=personList[0] ，此时不需要再设置 index 索引参数
     * 3）index ：索引属性的索引，如 name=personList,index=0，此时 name 属性不需要再设置索引.
     * 4）value ：为索引属性指定的索引设置值，注意 set 索引属性时，指定的索引必须存在，否则空指针异常，即只能修改，不能添加.
     */
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

    /**
     * java bean key-value 键值属性取值赋值。目的是对map元素能一步到位的进行操作，而不需要先获取 map ，然后再操作 key-value
     * setMappedProperty(final Object bean, final String name,final Object value)
     * setMappedProperty(final Object bean, final String name,final String key, final Object value)
     * getMappedProperty(final Object bean, final String name)
     * getMappedProperty(final Object bean, final String name, final String key)
     * 1) bean ：待操作的 对象
     * 2）name ：map 类型的属性名称，格式：propertyName 或者 propertyName(key)，前者需要再指定 key 参数，后者则不需要再指定 key 参数.
     * 3）key：map 对象的 key 值，如果指定了，则 name 参数不需要再使用 (key) 指定.
     * 4）value：map 对象的 value 值.
     * 5）注意 setMappedProperty 赋值时，name 属性的 map 对象可以为空，但是 map 对象必须存在，否则设值不会成功，但是也不报错.
     */
    public void testMappedProperty() {
        try {
            Department department = new Department(1000, "开发部");
            //setMappedProperty 赋值之前，map 对象必须存在.
            department.setDataMap(new HashMap<>(2));
            PropertyUtils.setMappedProperty(department, "dataMap", "address", "长沙市");
            PropertyUtils.setMappedProperty(department, "dataMap(info)", "2019年度10强团队");
            //输出：Department{id=1000, name='开发部', personList=null, dataMap={address=长沙市, info=2019年度10强团队}}
            System.out.println(department);

            //输出：长沙市 \n 2019年度10强团队
            System.out.println(PropertyUtils.getMappedProperty(department, "dataMap(address)"));
            System.out.println(PropertyUtils.getMappedProperty(department, "dataMap", "info"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 操作嵌套类型的 Java Bean 属性。即对象内的属性嵌套了其它对象.
     * Object getNestedProperty(final Object bean, final String name)
     * setNestedProperty(final Object bean,final String name, final Object value)
     * 1、bean ：待操作对象
     * 2、name ：属性名称，如果是索引类型，则使用格式：propertyName[i].嵌套对象属性名；如果是 map 类型，则使用 propertyName(key).嵌套对象属性名
     */
    public void testNestedProperty() {
        try {
            Person person1 = new Person(9524, "华强", LocalDateTime.now(), false, null);
            List<Person> personList = new ArrayList<>();
            personList.add(person1);
            Department department = new Department(1002, "开发部", personList);
            Integer personName = (Integer) PropertyUtils.getNestedProperty(department, "personList[0].id");
            //输出：9524
            System.out.println(personName);
            //赋值时注意 personList[i] 对象必须存在，否则报错.
            PropertyUtils.setNestedProperty(department, "personList[0].price", 8878.99F);
            System.out.println(personList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * java bean 属性赋值、取值。综合了其它 setXxxProperty、getXxxProperty 方法的所有功能.
     * setProperty(final Object bean, final String name, final Object value)
     * Object getProperty(final Object bean, final String name)
     * 1、name：属性名称，如果是索引类型、map 类型、嵌套类型，想要级联操作属性时，则也是 propertyName[i]、propertyName(key) 的写法.
     */
    public void testProperty1() {
        try {
            Person person1 = new Person(9524, "华强", LocalDateTime.now(), false, null);
            PropertyUtils.setProperty(person1, "id", 1000);
            PropertyUtils.setProperty(person1, "marry", true);
            //输出：Person{id=1000, name='华强', birthday=2020-04-14T19:38:11.496, marry=true, price=null}
            System.out.println(person1);
            Object id = PropertyUtils.getProperty(person1, "id");
            Object marry = PropertyUtils.getProperty(person1, "marry");
            //输出：1000,true
            System.out.println(id + "," + marry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testProperty2() {
        try {
            Person person1 = new Person(2000, "华胜", LocalDateTime.now(), true, 9988.98F);
            List<Person> personList = new ArrayList<>();
            personList.add(person1);
            Department department = new Department(1002, "开发部", personList);
            PropertyUtils.setProperty(department, "personList[0].name", "华风");
            String name = (String) PropertyUtils.getProperty(department, "personList[0].name");
            //输出：华风
            System.out.println(name);
            //输出：[Person{id=2000, name='华风', birthday=2020-04-14T19:43:22.984, marry=true, price=9988.98}]
            System.out.println(department.getPersonList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Class<?> getPropertyType(final Object bean, final String name)
     * 1、获取 bean 的属性的类型.
     */
    public void testPropertyType() {
        try {
            Department department = new Department();
            Class<?> id = PropertyUtils.getPropertyType(department, "id");
            Class<?> list = PropertyUtils.getPropertyType(department, "personList");
            //输出：class java.lang.Integer
            System.out.println(id);
            //输出：interface java.util.List
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PropertyUtilsTest().testPropertyType();
    }
}
