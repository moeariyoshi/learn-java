package trees;

/**
 * The main frame for our tree GUI.
 *
 * @author John Donaldson
 * @author Benjamin Kuperman (Spring 2007)
 * @author Stephen Checkoway (Summer 2021)
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class TreeAppFrame extends JFrame {
    private static final long serialVersionUID = 4975630320269247694L;
    
    private TreeDisplay treeDisplayArea;
    private JTextArea outputArea;
    private FileDialog fd;
    private BinaryTree<String> tree;

    // Construct the frame
    public TreeAppFrame() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Component initialization
    private void init() throws Exception {
        Font monospace = new Font("Monospaced", Font.PLAIN, 14);
        this.treeDisplayArea = new TreeDisplay(monospace);
        this.outputArea = new JTextArea();
        this.fd = new FileDialog(this);
        this.tree = new EmptyTree<>();

        // Sub-panel
        JPanel buttonPanel = new JPanel();

        // Basic properties for the window
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(800, 600));
        this.setTitle("Tree App");

        // Set up the overall window space
        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout(6, 6));
        contentPane.add(new JScrollPane(treeDisplayArea), BorderLayout.CENTER);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputScrollPane.setPreferredSize(new Dimension(260, 200));
        contentPane.add(outputScrollPane, BorderLayout.EAST);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        this.outputArea.setEditable(false);
        this.outputArea.setLineWrap(true);
        this.outputArea.setWrapStyleWord(true);
        this.outputArea.setFont(monospace);

        // Set up the button bar
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(800, 80));

        // Load File
        JButton button = new JButton("Load File");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            fd.setVisible(true);
            String filename = fd.getFile();
            String dirname = fd.getDirectory();
            if (filename != null && dirname != null) {
                try {
                    tree = TreeLoader.loadTreeFromFile(dirname + filename);
                    outputArea.setText("");
                } catch (Exception ex1) {
                    outputArea.setText(ex1.toString());
                    tree = new EmptyTree<String>();
                }
                treeDisplayArea.setTree(tree);
                this.validate();
            }
        });

        // Mirror
        button = new JButton("Mirror");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            tree = tree.mirrorImage();
            if (tree == null) {
                tree = new EmptyTree<String>();
            }
            treeDisplayArea.setTree(tree);
            this.validate();
        });
        
        // Pare
        button = new JButton("Pare");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            tree = tree.pare();
            if (tree == null) {
                tree = new EmptyTree<String>();
            }
            treeDisplayArea.setTree(tree);
            this.validate();
        });
        
        // Cut Leaves
        button = new JButton("Cut Leaves");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            tree = tree.cutLeaves();
            if (tree == null) {
                tree = new EmptyTree<String>();
            }
            treeDisplayArea.setTree(tree);
            this.validate();
        });

        // Count Nodes
        button = new JButton("Count Nodes");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            outputArea.setText("Node Count: " + tree.nodeCount());
        });

        // Height
        button = new JButton("Height");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            outputArea.setText("Height: " + tree.height());
        });
        
        // Diameter
        button = new JButton("Diameter");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            outputArea.setText("Diameter: " + tree.diameter());
        });

        // Count Leaves
        button = new JButton("Count Leaves");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            int leafct = tree.leafCount();
            if (leafct == 1)
                outputArea.setText("The tree has 1 leaf");
            else
                outputArea.setText("The tree has " + leafct + " leaves");
        });

        // Level Count
        button = new JButton("Level Count");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            int level = 0;
            String output = "";
            int lcount = tree.levelCount(level);
            if (lcount == 0)
                output += "The tree is empty";
            else
                while (lcount > 0 && level <= 1000) {
                    if (lcount == 1)
                        output += ("There is 1 node at level " + level + "\n");
                    else
                        output += ("There are " + lcount + " nodes at level " + level + "\n");
                    ++level;
                    lcount = tree.levelCount(level);
                }
            outputArea.setText(output);
        });

        // Weight Balance Factor
        button = new JButton("Weight Balance Factor");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            outputArea.setText("Weight balance factor: " + tree.weightBalanceFactor());
        });

        // Pre-order
        button = new JButton("Pre-order");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> list = new ArrayList<>();
            tree.preOrderElements(list);
            for (String data : list) {
                if (sb.length() != 0)
                    sb.append('\n');
                sb.append(data);
            }
            outputArea.setText("" + sb.toString());
        });

        // In-order
        button = new JButton("In-order");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> list = new ArrayList<>();
            tree.inOrderElements(list);
            for (String data : list) {
                if (sb.length() != 0)
                    sb.append('\n');
                sb.append(data);
            }
            outputArea.setText("" + sb.toString());
        });

        // Post-order
        button = new JButton("Post-order");
        buttonPanel.add(button);
        wrapButtonAction(button, e -> {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> list = new ArrayList<>();
            tree.postOrderElements(list);
            for (String data : list) {
                if (sb.length() != 0)
                    sb.append('\n');
                sb.append(data);
            }
            outputArea.setText("" + sb.toString());
        });

        // Quit
//        button = new JButton("Quit");
//        buttonPanel.add(button);
//        button.addActionListener(e -> this.dispose());
//        button.setForeground(Color.BLUE);
    }
    
    private void wrapButtonAction(JButton button, ActionListener listener) {
        button.addActionListener(e -> {
            try {
                listener.actionPerformed(e);
            } catch (Exception ex) {
                ex.printStackTrace();
                outputArea.setText(ex.toString());
            }
        });
    }
}
