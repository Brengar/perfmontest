package steps;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Keyboard;
import com.hp.lft.sdk.stdwin.TreeViewNode;
import com.hp.lft.sdk.stdwin.Window;
import src.com.company.ApplicationModel;
import src.com.company.ApplicationModel.mainwindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PerfMonSteps {

    private static ApplicationModel appModel;
    private static byte i;

    public static void start() throws GeneralLeanFtException, InterruptedException {
        Desktop.launchAut("perfmon.exe");
        Thread.sleep(4000);
        i=1;
        appModel = new ApplicationModel();
        appModel.mainwindow().maximize();
    }

    public static void onlyStepThatWillBeInThisTest() throws GeneralLeanFtException {
        mainwindow.window.sysTreeView32TreeView tree=appModel.mainwindow().window().sysTreeView32TreeView();
        for (int i=2;i<15;i++){
            tree.activateNode(i);
            saveImage();
        }
    }

    public static void end() throws GeneralLeanFtException {
        appModel.mainwindow().close();
    }

    private static void saveImage() throws GeneralLeanFtException {
        try {
            BufferedImage bi = (BufferedImage)appModel.mainwindow().getSnapshot();
            File outputfile = new File("results/"+i+".png");
            ImageIO.write(bi, "png", outputfile);
            i++;
        } catch (IOException e) {
        }
    }
}
