package com.quartz.mongo.intro.quartzintro.constants;

public class Node {

  static String name;
  static Node node;
  private Node(String name) {
    this.name = name;
  }

  public static synchronized Node createNode(String name) {
    if (node==null) {
      node = new Node(name);
    };
    return node;
  }


  public static String getName() {
    return name;
  }
}
