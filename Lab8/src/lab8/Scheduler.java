package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;



public class Scheduler {

    public static void main(String[] args) {

        Scanner input = null;

        if (args.length < 2) {
            System.out.println("Program usage: java Scheduler filename sort");
            System.out.println("Sorts: priority, deadline, length, name");
        }

        MyPriorityQueue<Task> tasks = null;
        tasks = new MyPriorityQueue<Task>(new AvailableComparator());
        Comparator<Task> cmp = null;

        String sort = args[1];

        if (sort.equals("priority")) {
            cmp = new PriorityComparator();
            System.out.println("Scheduling jobs by Priority");
        } else if (sort.equals("available")) {
            cmp = new AvailableComparator();
            System.out.println("Scheduling jobs by availability");
        } else if (sort.equals("deadline")) {
            cmp = new DeadlineComparator();
            System.out.println("Scheduling jobs by Deadline");
        } else if (sort.equals("length")) {
            cmp = new LengthComparator();
            System.out.println("Scheduling jobs by Length");
        } else if (sort.equals("name")) {
            cmp = new NameComparator();
            System.out.println("Scheduling jobs by Name");
        } else if (sort.equals("mine")) {
            cmp = new MyComparator();
            System.out.println("Scheduling jobs by custom comapratpor");
        } else {
            System.out.println("You have entered an invalid option for sort.  Please enter one of the following:");
            System.out
                    .println("1 -- Priority, 2 -- Earliest Deadline First, 3 -- Shortest Job First, 4 -- Alphabetical");
        }

        MyPriorityQueue<Task> runOrder = new MyPriorityQueue<Task>(cmp);

        try {
            input = new Scanner(new File(args[0]));
            while (input.hasNextLine()) {
                if (input.hasNext()) {
                    String name = input.next();
                    int priority = input.nextInt();
                    int availableTime = input.nextInt();
                    int length = input.nextInt();
                    int deadline = input.nextInt();
                    Task t1 = new Task(name, priority, availableTime, length, deadline);

                    tasks.offer(t1);
                } else {
                    break;
                }
            }

            int time = 0;
            boolean currentlyRunning = false;
            Task currentTask = null;
            int startTime = 0;
            int missedDeadlines = 0;
            int missedMillis = 0;
            int[] priorities = new int[10];
            int priorityInversions = 0;

            while (tasks.size() + runOrder.size() > 0) {

                while (tasks.size() > 0) {
                    Task t2 = tasks.peek();
                    if (t2.availableTime <= time) {
                        runOrder.offer(tasks.poll());
                        priorities[t2.priority] = priorities[t2.priority] + 1;
                    } else {
                        break;
                    }
                }

                if (currentlyRunning) {
                    if (time >= startTime + currentTask.length) {
                        currentlyRunning = false;
                        if (startTime + currentTask.length > currentTask.deadline) {
                            missedDeadlines = missedDeadlines + 1;
                            missedMillis = missedMillis + (startTime + currentTask.length - currentTask.deadline);
                        }
                        priorities[currentTask.priority] = priorities[currentTask.priority] - 1;
                    }
                }

                if (!currentlyRunning) {
                    if (runOrder.size() > 0) {
                        currentTask = runOrder.poll();
                        currentlyRunning = true;
                        startTime = time;

                        for (int i = currentTask.priority + 1; i < priorities.length; i++) {
                            if (priorities[i] > 0) {
                                priorityInversions = priorityInversions + 1;
                                break;
                            }
                        }

                        System.out.println("" + time + ": running " + currentTask.name + " priority "
                                + currentTask.priority + " availability " + currentTask.availableTime + " length "
                                + currentTask.length + " deadline " + currentTask.deadline + " " + runOrder.size()
                                + " other jobs in queue.");
                    }
                }

                time = time + 10;
            }

            System.out.println("All jobs have been run. " + missedDeadlines + " deadlines were missed, by a total of "
                    + missedMillis + " milliseconds.  There were " + priorityInversions + " priority inversions.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

}
