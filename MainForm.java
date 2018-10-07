import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainForm {
    private JTree srcFileTree;
    private JTree dstFileTree;
    private JButton btnSrcPath;
    private JScrollPane jspDst;
    private JScrollPane jspSrc;
    private JPanel pnlMain;
    private JButton btnDestPath;

    private Logger lg;
    private ImgFileList fileList;

    public MainForm(Logger lg) {
        this.lg = lg;

        btnSrcPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // choose new source directory
                JFileChooser chr = new JFileChooser();
                chr.setCurrentDirectory(new File("."));
                chr.setDialogTitle("Wybierz katalog do skopiowania");
                chr.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chr.setAcceptAllFileFilterUsed(false);

                if (chr.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    try {
                        fileList.setSrcPath(chr.getSelectedFile().getAbsolutePath());
                        setSrcFileList();
                    } catch (IOException exc) {
                        lg.log(Level.SEVERE, "Error in setting source dir: " + exc.getMessage());
                    }
            }
        });
        btnDestPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // choose new destination directory
                JFileChooser chr = new JFileChooser();
                chr.setCurrentDirectory(new File("."));
                chr.setDialogTitle("Wybierz katalog docelowy");
                chr.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chr.setAcceptAllFileFilterUsed(false);

                if (chr.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                    try {
                        fileList.setDestPath(chr.getSelectedFile().getAbsolutePath());
                        setDestFileList();
                    } catch (IOException exc) {
                        lg.log(Level.SEVERE, "Error in setting dest dir: " + exc.getMessage());
                    }
            }
        });
    }

    public static void main(String[] args) {
    }

    public void setSrcFileList(ImgFileList ifl) {
        fileList = ifl;

        setSrcFileList();
    }

    private void setSrcFileList() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileList.getSrcPath());
        Iterator<ImgFileEntry> it = fileList.getIterator();

        while (it.hasNext()) {
            ImgFileEntry ife = it.next();            // Kolejny plik do skopiowania.

            DefaultMutableTreeNode tn = new DefaultMutableTreeNode(ife);
            root.add(tn);
        }

        DefaultTreeModel mdl = new DefaultTreeModel(root);
        srcFileTree.setModel(mdl);
    }

    public void setDestFileList(ImgFileList ifl) {
        fileList = ifl;

        setDestFileList();
    }

    private void setDestFileList() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(fileList.getDestPath());
        Iterator<ImgFileEntry> it = fileList.getIterator();

        Hashtable<String, DefaultMutableTreeNode> nodes = new Hashtable<String, DefaultMutableTreeNode>();

        while (it.hasNext()) {
            ImgFileEntry ife = it.next();            // Kolejny plik do skopiowania.

            // Wytnij część wspólną ze ścieżki do pliku
            Path rp = ife.getDestFilePath().relativize(Paths.get(fileList.getDestPath()));

            // Przejdź pozostałą część ścieżki katalog po katalogu odpowiednio uzupełniając drzewo katalogów w modelu.
            Iterator<Path> ip = rp.iterator();
            int elemCnt = rp.getNameCount(); // ilość elementów w tej ścieżce.
            DefaultMutableTreeNode prevNode = root;

            while (ip.hasNext() && elemCnt > 0) {
                Path pth = ip.next();                       // kolejny element ścieżki
                String name = pth.getName(0).toString();    // nazwa elementu
                DefaultMutableTreeNode nd = new DefaultMutableTreeNode(name);

                if (elemCnt == 1) {       // ostatni element czyli nazwa pliku
                    prevNode.add(nd);
                } else if (!nodes.containsKey(name)) {      // kolejny katalog
                    nodes.put(name, nd);                    // nie ma w słowniku, napotkany pierwszy raz
                    prevNode.add(nd);                       // dodaj do drzewa katalogów
                }
                prevNode = nd;
                --elemCnt;
            }
        }

        DefaultTreeModel mdl = new DefaultTreeModel(root);
        dstFileTree.setModel(mdl);
    }

    public void show() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("MainForm");

        frame.setContentPane(pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        pnlMain = new JPanel();
        pnlMain.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        pnlMain.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        btnSrcPath = new JButton();
        btnSrcPath.setText("Kopiuj z ...");
        pnlMain.add(btnSrcPath, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jspDst = new JScrollPane();
        pnlMain.add(jspDst, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        dstFileTree = new JTree();
        jspDst.setViewportView(dstFileTree);
        jspSrc = new JScrollPane();
        jspSrc.setAutoscrolls(true);
        pnlMain.add(jspSrc, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        srcFileTree = new JTree();
        jspSrc.setViewportView(srcFileTree);
        btnDestPath = new JButton();
        btnDestPath.setSelected(true);
        btnDestPath.setText("Kopiuj do ...");
        pnlMain.add(btnDestPath, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}


