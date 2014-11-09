package de.teamteamteam.spacescooter.datastructure;

/**
 * A very simple and non-List conform type of List whose aim is to deal with
 * most concurrent modifications.
 */
public class ConcurrentLinkedList<T> {

	/**
	 * Head of the list.
	 */
	protected ConcurrentLinkedListNode<T> head;

	
	/**
	 * Constructor to initialize the empty list.
	 */
	public ConcurrentLinkedList() {
		this.head = new ConcurrentLinkedListNode<T>();
		this.head.setNext(new ConcurrentLinkedListNode<T>());
	}

	
	/**
	 * Add an element to the list.
	 */
	public void add(T element) {
		ConcurrentLinkedListNode<T> currentNode = this.head.next();
		//jump to the next node until we reached the last OR we got one that has a null value.
		while(currentNode.hasNext() && currentNode.getValue() != null) currentNode = currentNode.next();
		//if it has a null value, add the element here
		if(currentNode.getValue() == null) {
			currentNode.setValue(element);
		} else {
			//since getValue must be != null, we surely reached the _last_ node.
			//create a new node and append it to the list.
			ConcurrentLinkedListNode<T> newNode = new ConcurrentLinkedListNode<T>(element);
			currentNode.setNext(newNode);
		}
	}

	/**
	 * Remove an element from the list.
	 */
	public void remove(T element) {
		ConcurrentLinkedListNode<T> currentNode = this.head;
		while(element.equals(currentNode.getValue()) == false && currentNode.hasNext()) {
			currentNode = currentNode.next();
		}
		if(currentNode.getValue().equals(element)) {
			currentNode.setValue(null);
			return;
		}
	}

	/**
	 * Get the Lists iterator.
	 */
	public ConcurrentIterator<T> iterator() {
		return new ConcurrentIterator<T>(this);
	}

	/**
	 * Simple way to print the whole list content for debugging purposes.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		ConcurrentLinkedListNode<T> currentNode = this.head;
		s.append(this.head + ": " + this.head.getValue() + "\n");
		while((currentNode = currentNode.next()) != null) {
			s.append(currentNode + ": " + currentNode.getValue() + "\n");
		}
		return s.toString();
	}
}
