package de.teamteamteam.spacescooter.datastructure;

/**
 * The node for the link ConcurrentLinkedList.
 */
public class ConcurrentLinkedListNode<T> {

	/**
	 * The next node after this one.
	 */
	private ConcurrentLinkedListNode<T> next;

	/**
	 * This nodes value.
	 */
	private T content;

	
	/**
	 * Constructor to create a null node.
	 */
	protected ConcurrentLinkedListNode() {
		this.content = null;
		this.next = null;
	}

	/**
	 * Constructor to create a node with content.
	 */
	protected ConcurrentLinkedListNode(T content) {
		this.content = content;
		this.next = null;
	}
	

	/**
	 * Set the value for this list node.
	 */
	public void setValue(T content) {
		this.content = content;
	}

	/**
	 * Get the nodes value.
	 */
	public T getValue() {
		return this.content;
	}

	/**
	 * Set the next node.
	 */
	public void setNext(ConcurrentLinkedListNode<T> next) {
		this.next = next;
	}

	/**
	 * Set the next node.
	 */
	public ConcurrentLinkedListNode<T> next() {
		return this.next;
	}
	
	/**
	 * Tells whether the current node has a next node.
	 */
	public boolean hasNext() {
		return (this.next != null);
	}
}
