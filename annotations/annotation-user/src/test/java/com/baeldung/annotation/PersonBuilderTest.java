package com.nklkarthi.annotation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonBuilderTest {

    @Test
    public void whenBuildPersonWithBuilder_thenObjectHasPropertyValues() {

        Person person = new PersonBuilder().setAge(25).setName("John").build();

        assertEquals(25, person.getAge());
        assertEquals("John", person.getName());

    }

}
