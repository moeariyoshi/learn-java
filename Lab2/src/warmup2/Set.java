package warmup2;

public class Set<E> extends AbstractSet<E> {
    private Object[] data;
    private int size;
    
    public Set(int max_size) {
	this.data = new Object[max_size];
	this.size = 0;
    }
    
    @Override
    public int size() {
	return this.size;
    }
    
    @Override
    public void add(E e) {
	int i;
	for (i = 0; i < this.size; i++) {
	    if (e.equals(this.data[i])) {
		return;
	    } 
	}
	
	if (i < this.data.length) {
	    this.data[i] = e;
	    this.size++;
	} else {
	    throw new UnsupportedOperationException("Out of space");
	} 
    } 

    @Override
    public void remove(E e) {
	
	int j;
	for (j = 0 ; j < this.size; j++) {
	    if (e.equals(this.data[j])) {
		for (int k = j; k < this.data.length - 1; k++) {
			this.data[k] = this.data[k+1];
		}
		this.data[this.data.length-1] = 0;
		this.size--;
	    } else {
		continue;
	    }
	}
    }

    @Override
    public boolean contains(E e) {
	int l;
	for (l=0; l<this.size; l++) {
	    if (e.equals(this.data[l])) {
		return true;
	    } 
	}
	return false;

    }

    @Override
    public Object[] toArray() {
	Object[] newArray = new Object[this.size];
	for (int m = 0; m < this.size; m++) {
	    newArray[m] = this.data[m];
	}
	return newArray;
    }

}
