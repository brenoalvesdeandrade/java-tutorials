package com.nklkarthi.powermockito.introduction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.nklkarthi.powermockito.introduction.*")
public class PowerMockitoTest {

    @Test
    public void givenFinalMethods_whenUsingPowerMockito_thenCorrect() throws Exception {
        CollaboratorWithFinalMethods mock = mock(CollaboratorWithFinalMethods.class);
        whenNew(CollaboratorWithFinalMethods.class).withNoArguments().thenReturn(mock);

        CollaboratorWithFinalMethods collaborator = new CollaboratorWithFinalMethods();
        verifyNew(CollaboratorWithFinalMethods.class).withNoArguments();

        when(collaborator.helloMethod()).thenReturn("Hello nklkarthi!");
        String welcome = collaborator.helloMethod();
        Mockito.verify(collaborator).helloMethod();
        assertEquals("Hello nklkarthi!", welcome);
    }

    @Test(expected = RuntimeException.class)
    public void givenStaticMethods_whenUsingPowerMockito_thenCorrect() {
        mockStatic(CollaboratorWithStaticMethods.class);

        when(CollaboratorWithStaticMethods.firstMethod(Mockito.anyString())).thenReturn("Hello nklkarthi!");
        when(CollaboratorWithStaticMethods.secondMethod()).thenReturn("Nothing special");
        doThrow(new RuntimeException()).when(CollaboratorWithStaticMethods.class);
        CollaboratorWithStaticMethods.thirdMethod();

        String firstWelcome = CollaboratorWithStaticMethods.firstMethod("Whoever");
        String secondWelcome = CollaboratorWithStaticMethods.firstMethod("Whatever");

        assertEquals("Hello nklkarthi!", firstWelcome);
        assertEquals("Hello nklkarthi!", secondWelcome);

        verifyStatic(Mockito.times(2));
        CollaboratorWithStaticMethods.firstMethod(Mockito.anyString());

        verifyStatic(Mockito.never());
        CollaboratorWithStaticMethods.secondMethod();

        CollaboratorWithStaticMethods.thirdMethod();
    }

    @Test
    public void givenPartialMocking_whenUsingPowerMockito_thenCorrect() throws Exception {
        String returnValue;

        spy(CollaboratorForPartialMocking.class);
        when(CollaboratorForPartialMocking.staticMethod()).thenReturn("I am a static mock method.");
        returnValue = CollaboratorForPartialMocking.staticMethod();
        verifyStatic();
        CollaboratorForPartialMocking.staticMethod();
        assertEquals("I am a static mock method.", returnValue);

        CollaboratorForPartialMocking collaborator = new CollaboratorForPartialMocking();
        CollaboratorForPartialMocking mock = spy(collaborator);

        when(mock.finalMethod()).thenReturn("I am a final mock method.");
        returnValue = mock.finalMethod();
        Mockito.verify(mock).finalMethod();
        assertEquals("I am a final mock method.", returnValue);

        when(mock, "privateMethod").thenReturn("I am a private mock method.");
        returnValue = mock.privateMethodCaller();
        verifyPrivate(mock).invoke("privateMethod");
        assertEquals("I am a private mock method. Welcome to the Java world.", returnValue);
    }
}