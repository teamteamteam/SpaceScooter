package de.teamteamteam.spacescooter.datastructure;

/**
 * The Iterator belonging to the link ConcurrentLinkedList.
 */
public class ConcurrentIterator<T> {

	/**
	 * List the iterator belongs to.
	 */
	private ConcurrentLinkedList<T> list;

	/**
	 * Current node the iterator is on.
	 * Is null when the end of the list has been reached.
	 */
	private ConcurrentLinkedListNode<T> currentNode;

	
	/**
	 * Constructor initializing the iterator.
	 */
	public ConcurrentIterator(ConcurrentLinkedList<T> list) {
		this.list = list;
		this.reset();
	}

	
	/**
	 * Reset the Iterator to repeat the iteration.
	 */
	public void reset() {
		this.currentNode = this.list.head;
	}

	/**
	 * Tells whether there is one more element to iterate over.
	 * Always use this for iteration purposes before calling next()!
	 */
	public boolean hasNext() {
		if(this.currentNode == null) {
			return false;
		}
		this.currentNode = this.currentNode.next();
		while(this.currentNode != null && this.currentNode.getValue() == null) {
			this.currentNode = this.currentNode.next();
		}
		return (this.currentNode != null && this.currentNode.getValue() != null);
	}

	/**
	 * Get the next value.
	 */
	public T next() {
		if(this.currentNode == null) {
			throw new RuntimeException("End of concurrent list has no next element!");
		}
		T value = this.currentNode.getValue();
		return value;
	}

	/**
	 * Add this element to the list.
	 */
	public void add(T element) {
		this.list.add(element);
	}

	/**
	 * Remove the current element from the list.
	 */
	public void remove() {
		this.currentNode.setValue(null);
	}

}
