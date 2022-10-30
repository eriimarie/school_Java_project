/**********************************************************************
 *
 * 
 * 
 * The class ArrayQueue implements the Queue ADT using an array to
 * 
 * hold the queue items.
 *
 * 
 * 
 * It uses the circular implementation so that the time complexities of
 * 
 * both enqueue and dequeue are O(1)
 *
 * 
 * 
 * @author J.W. Benham
 * 
 *************************************************************************/
package erii_algo;
class ArrayQueue<T> implements Queue<T>

{

	private T[] queueItems;
	private int frontIndex, backIndex, queueSize;

	public ArrayQueue(int capacity)

	{

		@SuppressWarnings("unchecked")

		T[] temp = (T[]) new Object[capacity];
		queueItems = temp;
		frontIndex = 0;
		backIndex = -1;
		queueSize = 0;

	}

	// Adds item to back

	public void enqueue(T item)

	{

		backIndex++;
		if (backIndex == queueItems.length)
			backIndex = 0;
		queueItems[backIndex] = item;
		queueSize++;

	}

	// Removes item from front

	public T dequeue()

	{

		T returnValue = queueItems[frontIndex];
		frontIndex++;
		if (frontIndex == queueItems.length)
			frontIndex = 0;
		queueSize--;
		return returnValue;

	}

	// Returns item at front

	public T getFront()

	{

		return queueItems[frontIndex];

	}

	// Returns true iff the queue is empty

	public boolean isEmpty()

	{

		return queueSize == 0;

	}

	// Removes all items from queue

	public void clear() {

		for (int i = 0; i < queueItems.length; i++)
			queueItems[i] = null;
		frontIndex = 0;
		backIndex = -1;
		queueSize = 0;

	}

}
