package com.company;

import com.hp.lft.verifications.Verify;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.insight.InsightDescription;
import com.hp.lft.sdk.insight.InsightObject;
import com.hp.lft.sdk.stdwin.*;
import unittesting.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws GeneralLeanFtException, IOException, InterruptedException {

        Desktop.launchAut("perfmon.exe");

        Thread.sleep(4000);

        Window window = Desktop.describe(Window.class, new WindowDescription.Builder()
                .childWindow(false)
                .ownedWindow(false)
                .windowClassRegExp("MMCMainFrame")
                .windowTitleRegExp("Системный монитор").build());
        TreeView sysTreeView32TreeView = Desktop.describe(Window.class, new WindowDescription.Builder()
                .childWindow(false)
                .ownedWindow(false)
                .windowClassRegExp("MMCMainFrame")
                .windowTitleRegExp("Системный монитор").build())
                .describe(Window.class, new WindowDescription.Builder()
                        .childWindow(true)
                        .ownedWindow(false)
                        .windowClassRegExp("MMCChildFrm")
                        .build())
                .describe(TreeView.class, new TreeViewDescription.Builder()
                        .nativeClass("SysTreeView32").build());

        for (int i=2;i<15;i++){
            sysTreeView32TreeView.activateNode(i);
            saveImage(window,Integer.toString(i-1));
        }

        window.close();
    }

    private void saveImage(Window window,String name) throws GeneralLeanFtException {
        try {
            BufferedImage bi = (BufferedImage)window.getSnapshot();
            File outputfile = new File("results/"+name+".png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
        }
    }

}