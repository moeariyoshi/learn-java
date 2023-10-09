package warmup2;

public abstract class AbstractSet<E> {
	/// Returns the number of elements in the set.
	public abstract int size();
	
	/// Adds an element e to the set.
	public abstract void add(E e);
	
	/// Remove an element e from the set, if it exists.
	public abstract void remove(E e);
	
	/// Returns true if e is contained in the set.
	public abstract boolean contains(E e);
	
	/// Returns the elements of the set in an array.
	public abstract Object[] toArray();
	
	/// Returns a string representation of the set.
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		Object[] data = this.toArray();
		
		for (int i = 0; i < data.length; i++) {
			if (i > 0)
				sb.append(", ");
			sb.append(data[i].toString());
		}
		sb.append("}");
		return sb.toString();
	}
}