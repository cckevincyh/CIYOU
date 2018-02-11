package com.ciyou.edu.entity

/**
 * @Author C.
 * @Date 2018-02-11 11:27
 */
class TreeNode implements Serializable{
    private static final long serialVersionUID = 1L
    private int id
    private String text
    private List<TreeNode> nodes


    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", nodes=" + nodes +
                '}';
    }
    TreeNode() {

    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    String getText() {
        return text
    }

    void setText(String text) {
        this.text = text
    }

    List<TreeNode> getNodes() {
        return nodes
    }

    void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes
    }

    TreeNode(int id, String text, List<TreeNode> nodes) {
        this.id = id
        this.text = text
        this.nodes = nodes
    }
}
