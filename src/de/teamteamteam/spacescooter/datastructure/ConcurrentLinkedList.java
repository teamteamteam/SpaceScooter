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
	}

	
	/**
	 * Add an element to the list.
	 */
	public void add(T element) {
		ConcurrentLinkedListNode<T> newNode = new ConcurrentLinkedListNode<T>(element);
		ConcurrentLinkedListNode<T> currentNode = this.head;
		while(currentNode.hasNext()) currentNode = currentNode.next();
		currentNode.setNext(newNode);
	}

	/**
	 * Remove an element from the list.
	 */
	public void remove(T element) {
		ConcurrentIterator<T> iter = this.iterator();
		while (iter.hasNext()) {
			T e = iter.next();
			if (e.equals(element)) {
				iter.remove();
				return;
			}
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
