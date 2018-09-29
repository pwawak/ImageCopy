import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainFormTest {

    @Before
    public void setUp() throws Exception {
        MainForm  frm = new MainForm();
        frm.show();
        while(true) {}
    }

    @Test
    public void testExecParams() {

    }

    @After
    public void tearDown() throws Exception {
    }
}