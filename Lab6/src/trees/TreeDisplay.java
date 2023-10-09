package trees;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.JPanel;

public class TreeDisplay extends JPanel {
    private static final long serialVersionUID = 247226457561260229L;

    private static final double PADDING = 4;
    private static final double VERTICAL_SEP = 20;
    private static final double HORIZONTAL_SEP = 20;

    private BinaryTree<?> tree;
    private Font font;

    public TreeDisplay(Font font) {
        this.tree = null;
        this.font = font;
    }

    public void setTree(BinaryTree<?> tree) {
        this.tree = tree;

        if (tree == null || tree.isEmpty()) {
            this.setPreferredSize(null);
        } else {
            // Compute the size.
            Rectangle2D bbox = drawTree(null, tree)[1];

            int width = (int) Math.ceil(bbox.getWidth() + HORIZONTAL_SEP);
            int height = (int) Math.ceil(bbox.getHeight() + VERTICAL_SEP);
            this.setPreferredSize(new Dimension(width, height));
        }
        this.invalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);

        super.paintComponent(g);
        if (this.tree == null || this.tree.isEmpty()) {
            return;
        }

        g2.setFont(this.font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.translate(HORIZONTAL_SEP / 2, VERTICAL_SEP / 2);
        drawTree(g2, this.tree);
    }

    // Returns { root, boundingBox }.
    private Rectangle2D[] drawTree(Graphics2D g, BinaryTree<?> subTree) {
        String data = subTree.data().toString();
        Rectangle2D node = this.nodeShape(data);
        double halfWidth = node.getWidth() / 2.;
        double levelSep = node.getHeight() + VERTICAL_SEP;

        BinaryTree<?> left = subTree.leftChild();
        BinaryTree<?> right = subTree.rightChild();

        if (left.isEmpty()) {
            if (right.isEmpty()) {
                // Leaf.
                drawNode(g, node, data);
                return new Rectangle2D[] { node, node };
            }

            // Only a right child.
            double deltaX = HORIZONTAL_SEP + halfWidth;
            if (g != null)
                g.translate(deltaX, levelSep);
            Rectangle2D[] rootBBox = drawTree(g, right);
            if (g != null)
                g.translate(-deltaX, -levelSep);

            Rectangle2D rightRoot = translateRect(rootBBox[0], deltaX, levelSep);
            Rectangle2D boundingBox = translateRect(rootBBox[1], deltaX, levelSep);

            drawNode(g, node, data);
            drawEdge(g, node, rightRoot);

            boundingBox.add(node);
            return new Rectangle2D[] { node, boundingBox };
        }

        if (right.isEmpty()) {
            // Only a left child.
            if (g != null)
                g.translate(0., levelSep);
            Rectangle2D[] rootBBox = drawTree(g, left);
            if (g != null)
                g.translate(0., -levelSep);

            Rectangle2D leftRoot = translateRect(rootBBox[0], 0., levelSep);
            Rectangle2D boundingBox = translateRect(rootBBox[1], 0., levelSep);

            double deltaX = boundingBox.getWidth() + HORIZONTAL_SEP - halfWidth;
            node = translateRect(node, deltaX, 0.);
            drawNode(g, node, data);
            drawEdge(g, node, leftRoot);

            boundingBox.add(node);
            return new Rectangle2D[] { node, boundingBox };
        }

        // Two children.
        if (g != null)
            g.translate(0., levelSep);
        Rectangle2D[] rootBBox = drawTree(g, left);
        Rectangle2D leftRoot = translateRect(rootBBox[0], 0., levelSep);
        Rectangle2D boundingBox = translateRect(rootBBox[1], 0., levelSep);

        double deltaX = boundingBox.getWidth() + 2 * HORIZONTAL_SEP;
        if (g != null)
            g.translate(deltaX, 0.);
        rootBBox = drawTree(g, right);
        if (g != null)
            g.translate(-deltaX, -levelSep);

        Rectangle2D rightRoot = translateRect(rootBBox[0], deltaX, levelSep);
        boundingBox.add(translateRect(rootBBox[1], deltaX, levelSep));

        node = translateRect(node, deltaX - HORIZONTAL_SEP - node.getWidth() / 2., 0);
        drawNode(g, node, data);
        drawEdge(g, node, leftRoot);
        drawEdge(g, node, rightRoot);

        boundingBox.add(node);
        return new Rectangle2D[] { node, boundingBox };
    }

    private Rectangle2D nodeShape(String data) {
        FontMetrics metrics = this.getFontMetrics(this.font);
        double width = metrics.stringWidth(data) + 2 * PADDING;
        double height = metrics.getAscent() + metrics.getDescent() + 2 * PADDING;

        return new Rectangle2D.Double(0, 0, width, height);
    }

    private Rectangle2D translateRect(Rectangle2D rect, double x, double y) {
        return new Rectangle2D.Double(rect.getX() + x, rect.getY() + y, rect.getWidth(), rect.getHeight());
    }

    private void drawNode(Graphics2D g, RectangularShape shape, String data) {
        if (g == null)
            return;
        RoundRectangle2D node = new RoundRectangle2D.Double(shape.getX(), shape.getY(),
                                                            shape.getWidth(), shape.getHeight(),
                                                            10., 10.);
        g.draw(node);
        double x = shape.getX() + PADDING;
        double y = shape.getMaxY() - PADDING - g.getFontMetrics().getDescent();
        g.drawString(data, (float) x, (float) y);
    }

    private void drawEdge(Graphics2D g, RectangularShape from, RectangularShape to) {
        if (g == null)
            return;
        Shape line = new Line2D.Double(from.getCenterX(), from.getMaxY(), to.getCenterX(), to.getMinY());
        g.draw(line);
    }
}
