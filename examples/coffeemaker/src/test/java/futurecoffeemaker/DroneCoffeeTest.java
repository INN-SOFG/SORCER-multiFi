package futurecoffeemaker;

import edu.pjatk.inn.futurecoffemaker.DroneCoffeeMaker;
import org.junit.Test;
import sorcer.service.Context;
import sorcer.service.MogramException;
import sorcer.service.Routine;
import sorcer.service.SignatureException;

import static org.junit.Assert.assertEquals;
import static sorcer.eo.operator.*;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

public class DroneCoffeeTest {
    @Test
    public void testCheckDrinkAvailability() throws MogramException, SignatureException {
        Routine cmt = task(sig("checkDrinkAvailability", DroneCoffeeMaker.class), "espresso");
        Context out = context(exert(cmt));
        assertEquals(value(out, "droneCoffeeService/drinkDelivered"), true);
    }
}
