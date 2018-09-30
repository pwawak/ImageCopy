import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainFormTest {

    MainForm    frm;
    @Before
    public void setUp() throws Exception {
        frm = new MainForm();
    }

    @Test
    public void testExecParams() {
        frm.show();
        while ( true )  {}
    }

    @After
    public void tearDown() throws Exception {
    }
}