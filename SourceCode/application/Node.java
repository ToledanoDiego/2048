package application;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a Node that can be used in a tree.
 *
 * @author Diego Toledano
 * @version 1.1
 * @since 19/04/2020
 */
public class Node<T> {
  public List<Node<T>> children = new ArrayList<Node<T>>();
  private Node<T> parent = null;
  int[][] data = null;
  double data2 = 0;

  /**
   * Constructor.
   * 
   * @param data data of the node.
   * @param parent parent of the node.
   */
  public Node(int[][] data, Node<T> parent) {
    this.data = data;
    this.parent = parent;
  }


  /**
   * Constructor.
   * 
   * @param data data of the node.
   */
  public Node(int[][] data) {
    this.data = data;
  }

  /**
   * get the current children.
   * 
   * @return current children.
   */
  public List<Node<T>> getChildren() {
    return children;
  }

  /**
   * set the current parent.
   * 
   * @param parent parent to set.
   */
  public void setParent(Node<T> parent) {
    parent.addChild(this);
    this.parent = parent;
  }

  /**
   * add a child to the current node.
   * 
   * @param data data of the child.
   */
  public void addChild(int[][] data) {
    Node<T> child = new Node<T>(data);
    child.setParent(this);
    this.children.add(child);
  }

  /**
   * add a child to the current node.
   * 
   * @param child node we add as a child.
   */
  public void addChild(Node<T> child) {
    // System.out.println("hello");
    child.parent = this.parent;
    this.children.add(child);
  }

  /**
   * get the current data.
   * 
   * @return current data.
   */
  public Object getData() {
    return this.data;
  }

  /**
   * set the current data.
   * 
   * @param data data to set.
   */
  public void setData(int[][] data) {
    this.data = data;
  }

  /**
   * check if the node is the root node of the tree.
   * 
   * @return true if this node is the root node of the tree.
   */
  public boolean isRoot() {
    return (this.parent == null);
  }

  /**
   * check if the node is a leaf node of the tree.
   * 
   * @return true if this node is a leaf node of the tree.
   */
  public boolean isLeaf() {
    return this.children.size() == 0;
  }

  /**
   * remove the parent of this node.
   */
  public void removeParent() {
    this.parent = null;
  }

  /**
   * set the current second data.
   * 
   * @param data2 data2 to set.
   */
  public void setdata2(double saticEval) {
    this.data2 = saticEval;
  }

  /**
   * get the current second data.
   * 
   * @return current second data.
   */
  public double getData2() {
    return data2;
  }
}
