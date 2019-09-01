package com.springboot2x.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 5:03 PM
 * @see jdk 1.7
 **/
@Component
public class PersonImpl implements Person {

    // 通过qualifier 修复多态，不知道确定那个子类注入
    @Autowired
    @Qualifier("dog")
    private Animal animal;

    @Autowired
    private List<Animal> animals;

    @Override
    public void service() {
        System.out.println("==before==" );
        animal.use();
        for (Animal animal : animals) {
            animal.use();
        }

    }

    @Override
    public void setAnimal(Animal animal) {

    }
}
