package com.wmx.apachestudy.beanUtils;

import com.wmx.apachestudy.propertyUtils.Department;
import com.wmx.apachestudy.propertyUtils.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/4/13 17:16
 */
public class BeanUtilsTest {
    public static void main(String[] args) {
        new BeanUtilsTest().testCloneBean();
    }

    /**
     * Object cloneBean(final Object bean)：基于属性的 getter和 setter 克隆 bean，即使 bean类本身没有实现 Cloneable。
     * 查看 {@link BeanUtilsBean#cloneBean} 源码就可以看出，底层通过反射重新 newInstance 一个新对象，
     * 然后 {@link PropertyUtilsBean#copyProperties} 赋值就对象的属性到新对象中.
     * 即使 bean 对象的属性关联了其它对象，也会被一并克隆.
     */
    public void testCloneBean() {
        try {
            Person person = new Person(9523, "华福", LocalDateTime.now(), false, null);
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            Department department = new Department(1000, "开发部", personList);

            Department cloneBean = (Department) BeanUtils.cloneBean(department);
            cloneBean.setName("华安");
            //输出：Person{id=9523, name='华福', birthday=2020-04-14T08:34:05.296, isMarry=false, price=null}
            System.out.println(person);
            //输出：Department{id=1000, name='华安', personList=[Person{id=9523, name='华福', birthday=2020-04-14T08:34:05.296, isMarry=false, price=null}]}
            System.out.println(cloneBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
