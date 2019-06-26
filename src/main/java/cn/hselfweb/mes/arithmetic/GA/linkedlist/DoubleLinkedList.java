package cn.hselfweb.mes.arithmetic.GA.linkedlist;

import cn.hselfweb.mes.enity.CraftExtend;



public class DoubleLinkedList
{
  private Node head = new Node(null); // 头节点
  private int size = 1; // 链表大小
  // 以下是接口方法

  public void setHead(Node n){
	  head = n;
  }
  public boolean addFirst(CraftExtend o)
  {
    addAfter(new Node(o), head);
    return true;
  }

  public boolean addLast(CraftExtend o)
  {
    addBefore(new Node(o), head);
    return true;
  }

  public boolean add(CraftExtend o)
  {
    return addLast(o);
  }

  public boolean add(int index, CraftExtend o)
  {
    addBefore(new Node(o), getNode(index));
    return true;
  }

  public boolean remove(int index)
  {
    removeNode(getNode(index));
    return true;
  }

  public boolean removeFirst()
  {
    removeNode(head.next);
    return true;
  }

  public boolean removeLast()
  {
    removeNode(head.prev);
    return true;
  }

  public CraftExtend get(int index)
  {
    return getNode(index).value;
  }

  public int size()
  {
    return size;
  }

  public String toString()
  {
    StringBuffer s = new StringBuffer("[");
    Node node = head;
    for (int i = 0; i < size; i++)
    {
      node = node.next;
      if (i > 0)
        s.append(", ");
      s.append(node.value);
    }
    s.append("]");
    return s.toString();
  }
  //以下是实现方法

  private Node getNode(int index)
  {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException();
//    Node node = head.next;
    Node node = head;
    for (int i = 0; i < index; i++)
      node = node.next;
    return node;
  }

  private void addBefore(Node newNode, Node node)
  {
    newNode.next = node;
    newNode.prev = node.prev;
    newNode.next.prev = newNode;
    newNode.prev.next = newNode;
    size++;
  }

  private void addAfter(Node newNode, Node node)
  {
    newNode.prev = node;
    newNode.next = node.next;
    newNode.next.prev = newNode;
    newNode.prev.next = newNode;
    size++;
  }

  private void removeNode(Node node)
  {
    node.prev.next = node.next;
    node.next.prev = node.prev;
    node.prev = null;
    node.next = null;
    size--;
  }
  
  //获取该双向链表中所有工序信息中的工序名，便于在计算适应值中遍历使用
  public String[] ListGXName(){
	return null;
	  
  }
}