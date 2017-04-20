package il.ac.technion.cs.sd.app;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;


public class SdHw0Test {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

}
