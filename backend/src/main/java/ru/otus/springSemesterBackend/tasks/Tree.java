package ru.otus.springSemesterBackend.tasks;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
     Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>(rootData);
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public static class Node<T> {
         T data;
        List<Node<T>> children;

        public Node(T data) {
            this.data = data;
            this.children = new ArrayList<Node<T>>();
        }

        public void addChild(Node child) {
            children.add(child);
        }
    }

    public Node<T> getRoot() {
        return root;
    }
}
