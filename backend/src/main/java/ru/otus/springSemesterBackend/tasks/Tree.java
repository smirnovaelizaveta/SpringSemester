package ru.otus.springSemesterBackend.tasks;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>(rootData);
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;

        public Node(T data) {
            this.data = data;
//            this.parent = parent;
            this.children = new ArrayList<Node<T>>();
        }

        public void addChildren(Node current, Node child) {
            current.children.add(child);
        }
    }

    public Node<T> getRoot() {
        return root;
    }
}
