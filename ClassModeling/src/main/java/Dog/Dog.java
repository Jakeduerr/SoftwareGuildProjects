/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog;

/**
 *
 * @author jakeduerr
 */
public class Dog {
    private int weight;
    private int height;
    private int age;
    private String breed;
    private String name;
    private int legs;
    
    
    public Dog(String name) {
        this.name = name;
    }
    
    public void bark() {
        System.out.println(name + " says woof!");
    }

    @Override
    public String toString() {
        return name + " is a good dog";
        
        
    }
    
    

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLegs() {
        return legs;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }
    
    
    
}
