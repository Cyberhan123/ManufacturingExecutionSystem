package cn.hselfweb.mes.arithmetic.GA.linkedlist;

import cn.hselfweb.mes.enity.CraftExtend;

//import com.hrbust.arithmetic.GA.GongXu;



public class Node {
	
	    CraftExtend value;
	    Node prev = this;
	    Node next = this;
  
	    public CraftExtend getValue() {
			return value;
		}

		public void setValue(CraftExtend value) {
			this.value = value;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node(CraftExtend v)
	    {
	      value = v;
	    }

	    public String toString()
	    {
	      return value.toString();
	    }
	  
}
