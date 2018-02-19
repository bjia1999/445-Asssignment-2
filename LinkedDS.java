public class LinkedDS<T> implements PrimQ<T>, Reorder{
	
	Node first;
	int numberOfEntries;
	
	public LinkedDS(){
		first = null;
		numberOfEntries = 0;
	}
	
	public LinkedDS(LinkedDS<T> oldList){
		Node temp = oldList.first;
		while(temp!=null){
			this.addItem(temp.data);
			temp=temp.next;
		}
	}
	
	public class Node{
		
		public T data;
		public Node next;
		
		public Node(T datum){
			this(datum,null);
		}
		
		public Node(T datum, Node nextNode){
			data = datum;
			next = nextNode;
		}
		

		
	}
	
	public Node getNodeAt(int givenPosition) {
        assert (first != null)
                && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = first;
		
        for (int counter = 1; counter < givenPosition; counter++) {
            currentNode = currentNode.next;
        }
        assert currentNode != null;
        return currentNode;
    }
	
	public boolean addItem(T newEntry){
		boolean result = false;
		
		Node newNode = new Node(newEntry);
        if (empty()) {
            first = newNode;
        } else
        {
            Node last = getNodeAt(numberOfEntries);
            last.next = newNode; 
        } 

        numberOfEntries++;
		return result;
		
	}
	
	public T removeItem(){		
		
		T temp = null;
		
		if(first!=null){
			temp = first.data;
			first = first.next;
			numberOfEntries--;
			return temp;
		}else{
			return temp;
		}
	}
	
	public boolean empty(){
		return first==null;
	}
	
	public int size(){
		return numberOfEntries;
	}
	
	public void clear(){
		while(!empty()){
			removeItem();
		}
	}
	
	public void reverse() {

		Node currentNode = first;
		Node nextNode = null;
		Node prevNode = null;
	
		while(currentNode!=null){
			nextNode = currentNode.next;
			currentNode.next = prevNode;
			prevNode = currentNode;
			currentNode = nextNode;
		}
	
		first = prevNode;
    }
	
	public void shiftRight(){
		
		Node last = first;
		Node secondToLast = null;
		
		while(last.next!=null){
			secondToLast=last;
			last=last.next;
		}
		
		secondToLast.next=null;
		last.next=first;
		first=last;
		
	}
	
	public void shiftLeft(){
		Node currentNode = first;
		Node oldFirst = first;
		first = first.next;
		
		while(currentNode.next!=null){
			currentNode=currentNode.next;
		}
		
		currentNode.next=oldFirst;
		
	}
	
	public void leftShift(int num){
		for(int i=0; i<num;i++){
			removeItem();
		}
	}
	
	public void rightShift(int num){
		Node currentNode = first;
		for(int i=0; i<size()-num-1;i++){
			currentNode=currentNode.next;
		}
		currentNode.next=null;
	}

	public void leftRotate(int num){
		/*
		if(num<0){
			rightRotate(num);
		}else{
    		for(int i=0;i<num;i++){
				shiftLeft();
			}
		}
		*/

		if(num==0) return;
		
		Node currentNode = first;
		int count = 1;
		while(count<num && currentNode!=null){
			currentNode=currentNode.next;
			count++;
		}
		if(currentNode==null) return;
		
		Node numNode = currentNode;
		while(currentNode.next!=null){
			currentNode=currentNode.next;
		}
		
		
		currentNode.next=first;
		first=numNode.next;
		numNode.next=null;
	}
	
	public void rightRotate(int num){
		
		if(num<0){
			leftRotate(num);
		}else{
    		for(int i=0;i<num;i++){
				shiftRight();
			}
		}
	}
	
	public String toString(){
		Node currentNode = first;
		String output = "";
		for(int i=0;i<size();i++){
			if (currentNode!=null){
				output+=currentNode.data + " ";
				currentNode=currentNode.next;
			}
		}
		return output;
	}
	
}

	
