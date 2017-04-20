package org.nklkarthi.web;

import org.nklkarthi.persistence.query.JPASpecificationLiveTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
// @formatter:off
    JPASpecificationLiveTest.class
    ,FooDiscoverabilityLiveTest.class
    ,FooLiveTest.class
    ,MyUserLiveTest.class
}) //
public class LiveTestSuite {

}
