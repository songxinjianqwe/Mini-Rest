package com.sinjinsong.minirest.sample.main;

import java.util.Objects;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class Person {
    private String name;
    private String age;
    private Car car;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("I am " + name + ",hello world");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(age, person.age) &&
                Objects.equals(car, person.car);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, car);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
