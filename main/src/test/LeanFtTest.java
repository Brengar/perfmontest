package test;

import com.hp.lft.sdk.GeneralLeanFtException;
import org.junit.*;
import steps.PerfMonSteps;

import java.io.IOException;

public class LeanFtTest extends UnitTestClassBase {

    public LeanFtTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
        PerfMonSteps.start();
    }

    @After
    public void tearDown() throws Exception {
        PerfMonSteps.end();
    }

    @Test
    public void test() throws GeneralLeanFtException {
        PerfMonSteps.onlyStepThatWillBeInThisTest();
    }

}