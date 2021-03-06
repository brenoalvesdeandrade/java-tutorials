package com.nklkarthi.java8;

import com.nklkarthi.java8.entity.Human;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class Java8SortUnitTest {

    // tests -

    @Test
    public final void givenPreLambda_whenSortingEntitiesByName_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        Collections.sort(humans, new Comparator<Human>() {
            @Override
            public final int compare(final Human h1, final Human h2) {
                return h1.getName().compareTo(h2.getName());
            }
        });

        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public final void whenSortingEntitiesByName_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        humans.sort((final Human h1, final Human h2) -> h1.getName().compareTo(h2.getName()));

        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public final void givenLambdaShortForm_whenSortingEntitiesByName_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        humans.sort((h1, h2) -> h1.getName().compareTo(h2.getName()));

        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public final void whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 12), new Human("Sarah", 10), new Human("Zack", 12));
        humans.sort((lhs, rhs) -> {
            if (lhs.getName().equals(rhs.getName())) {
                return lhs.getAge() - rhs.getAge();
            } else {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public final void givenCompositionVerbose_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 12), new Human("Sarah", 10), new Human("Zack", 12));
        final Comparator<Human> byName = (h1, h2) -> h1.getName().compareTo(h2.getName());
        final Comparator<Human> byAge = (h1, h2) -> Ints.compare(h1.getAge(), h2.getAge());

        humans.sort(byName.thenComparing(byAge));
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public final void givenComposition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 12), new Human("Sarah", 10), new Human("Zack", 12));

        humans.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public final void whenSortingEntitiesByAge_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        humans.sort((h1, h2) -> Ints.compare(h1.getAge(), h2.getAge()));
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public final void whenSortingEntitiesByNameReversed_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));
        final Comparator<Human> comparator = (h1, h2) -> h1.getName().compareTo(h2.getName());

        humans.sort(comparator.reversed());
        Assert.assertThat(humans.get(0), equalTo(new Human("Sarah", 10)));
    }

    @Test
    public final void givenMethodDefinition_whenSortingEntitiesByNameThenAge_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        humans.sort(Human::compareByNameThenAge);
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

    @Test
    public final void givenInstanceMethod_whenSortingEntitiesByName_thenCorrectlySorted() {
        final List<Human> humans = Lists.newArrayList(new Human("Sarah", 10), new Human("Jack", 12));

        humans.sort(Comparator.comparing(Human::getName));
        Assert.assertThat(humans.get(0), equalTo(new Human("Jack", 12)));
    }

}
