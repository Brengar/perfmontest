package steps;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Keyboard;
import com.hp.lft.sdk.WaitUntilTestObjectState;
import com.hp.lft.sdk.stdwin.TreeViewNode;
import com.hp.lft.sdk.stdwin.Window;
import src.com.company.ApplicationModel;
import src.com.company.ApplicationModel.mainwindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.hp.lft.sdk.WaitUntilTestObjectState.waitUntil;
import static junit.framework.TestCase.assertTrue;

public class PerfMonSteps {

    private static ApplicationModel appModel;
    private static byte i;

    public static void start() throws GeneralLeanFtException, InterruptedException {
        Desktop.launchAut("perfmon.exe");
        i=1;
        appModel = new ApplicationModel();
        appModel.mainwindow().maximize();
    }

    public static void onlyStepThatWillBeInThisTest() throws GeneralLeanFtException {
        mainwindow.window.sysTreeView32TreeView tree=appModel.mainwindow().window().sysTreeView32TreeView();
        mainwindow.toolbarWindow32ToolBar toolbar = appModel.mainwindow().toolbarWindow32ToolBar();

        //Данный цикл разворачивает все ноды
        for (int i=0;;i++){
            try {
                tree.getVisibleNodes().get(i).expand();
            } catch (IndexOutOfBoundsException e){
                break;
            }
            toolbar.getButtons().get(0).press();
        }

        //Данный цикл проходится по всем нодам и если нода не развернута сохраняет скриншот
        //Если нода развернута, значит это папка и делать скрин не нужно
        List <TreeViewNode> nodeList =tree.getVisibleNodes();
        for (int i=0;i<nodeList.size();i++){
            if (!nodeList.get(i).isExpanded()) {
                tree.select(i);
                toolbar.getButtons().get(0).press();
                saveImage();
            }
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
