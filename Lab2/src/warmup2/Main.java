package warmup2;

public class Main {

    public static void main(String[] args) {
	AbstractSet<Integer> nums = new Set<Integer>(10);
	
	nums.add(10);
	nums.add(10);
	nums.add(15);
	nums.add(20);
	nums.add(15);
	System.out.println(nums.contains(10));
	nums.remove(10);
	System.out.println(nums.contains(10));
	nums.add(32);
	nums.add(16);
	System.out.println("There are " + nums.size() + " elements");
	System.out.println(nums);
    }

}
