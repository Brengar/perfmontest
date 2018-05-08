package steps;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.stdwin.TreeViewNode;
import src.com.company.ApplicationModel;
import src.com.company.ApplicationModel.mainwindow;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PerfMonSteps {

    private static ApplicationModel appModel;

    /**
     * Данный метод запускает perfmon.exe, оздает новую ApplicationModel
     * @throws GeneralLeanFtException
     */
    public static void start() throws GeneralLeanFtException {
        Desktop.launchAut("perfmon.exe");
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
                saveImage(Integer.toString(i));
                toolbar.getButtons().get(0).press();
            }
        }
    }

    /**
     * Данный метод закрывает окно perfmon.exe
     * @throws GeneralLeanFtException
     */
    public static void end() throws GeneralLeanFtException {
        appModel.mainwindow().close();
    }

    /**
     * Данный метод сохраняет изображение из perfmon.exe в папку results
     * @param name имя файла в который сохраняется изображение
     * @throws GeneralLeanFtException
     */
    private static void saveImage(String name) throws GeneralLeanFtException {
        try {
            BufferedImage bi = (BufferedImage)appModel.mainwindow().getSnapshot();
            File outputfile = new File("results/"+name+".png");
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
        }
    }
}
