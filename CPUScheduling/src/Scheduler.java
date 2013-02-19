/** Richard The (2796)
 *  CS431 Project (Dr. Gilbert Young)
 *  Date: 3/5/13
 *  Filename: Scheduler.java 
 */

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.lang.StringBuilder;

public class Scheduler
{
    private static String printHeader(String alg) {
        StringBuilder sb = new StringBuilder();
        if (alg.equalsIgnoreCase("FCFS"))
            sb.append("** FIRST-COME-FIRST-SERVE **");
        else if (alg.equalsIgnoreCase("SJF"))
            sb.append("** SHORTEST JOB FIRST **");
        else if (alg.equalsIgnoreCase("RR3"))
            sb.append("** ROUND-ROBIN (Time Slice = 3) **");
        else if (alg.equalsIgnoreCase("RR5"))
            sb.append("** ROUND-ROBIN (Time Slice = 5) **");
        else
            sb.append("Invalid algorithm");
        sb.append("\n\nJob Name\tStart\tEnd");
        sb.append("\n--------------------------------------------------------");
        return sb.toString();
    }

    private static void FCFS(List<Job> joblist) {
        int sum = 0;  // start time
        int[] turnaround = new int[joblist.size()];

        for (int i = 0; i < joblist.size(); i++) {
            int started = sum;
            sum += joblist.get(i).getCost();
            turnaround[i] = sum;
            System.out.println(joblist.get(i).getName() + "\t\t" + started + "\t" + sum + "\t Completed " + joblist.get(i).getName() + " @ " + sum);
        }

        printAverageTurnaroundTime(calculateAverage(turnaround, joblist));
    }

    private static void SJF(List<Job> joblist) {
        ArrayList<Job> sortedJobList = new ArrayList<Job>(joblist);
        Collections.sort(sortedJobList);
        FCFS(sortedJobList);
    }

    private static void RR(List<Job> joblist, int quantum) {
        //System.out.println(joblist);
        int completed = 0;  // number of jobs that have been completed
        int sum = 0;
        int[] turnaround = new int[joblist.size()];
        Job currentJob;

        do {
            for (int i = 0; i < joblist.size(); i++) {
                currentJob = joblist.get(i);
                boolean jobCompleted = false;
                if (currentJob.getCost() > 0) {
                    System.out.print(currentJob.getName() + "\t\t" + sum);
                    int afterDec = currentJob.decrement(quantum);
                    sum += quantum;
                    if (afterDec <= 0) {
                        completed++;
                        sum += afterDec;
                        //System.out.println("Completed " + currentJob.name + " at " + sum);
                        turnaround[i] = sum;
                        jobCompleted = true;
                    }
                    System.out.print("\t" + sum + (jobCompleted ? "\tCompleted " + currentJob.getName() + " at " + sum : "") + "\n");
                }
            }
        // loop through every job until all the jobs are completed
        } while(completed < joblist.size());

        printAverageTurnaroundTime(calculateAverage(turnaround, joblist));
    }

    private static ArrayList<Job> parsefile(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        ArrayList<Job> jobs = new ArrayList<Job>();
        String name;
        int cost;
        while (scanner.hasNextLine()) {
            name = scanner.nextLine();
            cost = Integer.parseInt(scanner.nextLine());
            jobs.add(new Job(name, cost));
        }
        return jobs;
    }

    private static void printAverageTurnaroundTime(double time) {
        System.out.println("Average Turnaround Time: " + new DecimalFormat("#.##").format(time) + "\n\n");
    }

    private static double calculateAverage(int[] turnaround, List<Job> joblist) {
        if (turnaround.length == 0) return 0;
        double sum = 0;
        System.out.println();
        for (int i = 0; i < turnaround.length; i++) {
            sum += turnaround[i];
            System.out.println("Job " + joblist.get(i).getName() + " completed at " + turnaround[i]);
        }
        System.out.println();
        return sum / turnaround.length;
    }
	
	public static void main(String[] args)
	{
        ArrayList<Job> joblist1, joblist2, joblist3, joblist4, joblist5, joblist6;
        String file1 = "testdata1.txt", file2 = "testdata2.txt", file3 = "testdata3.txt";
        try {
            joblist1 = parsefile(file1);
            joblist2 = parsefile(file2);
            joblist3 = parsefile(file3);
            // need to create a new list because RR changes the cost of each job
            // used for rr-5
            joblist4 = parsefile(file1);
            joblist5 = parsefile(file2);
            joblist6 = parsefile(file3);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }
        
        // file 1
        System.out.println("FILE: " + file1);
        System.out.println(printHeader("FCFS"));
        FCFS(joblist1);
        System.out.println(printHeader("SJF"));
        SJF(joblist1);
        System.out.println(printHeader("RR3"));
        RR(joblist1, 3);
        System.out.println(printHeader("RR5"));
        RR(joblist4, 5);
        
        // file 2
        System.out.println("FILE: " + file2);
        System.out.println(printHeader("FCFS"));
        FCFS(joblist2);
        System.out.println(printHeader("SJF"));
        SJF(joblist2);
        System.out.println(printHeader("RR3"));
        RR(joblist2, 3);
        System.out.println(printHeader("RR5"));
        RR(joblist5, 5);

        // file 3
        System.out.println("FILE: " + file3);
        System.out.println(printHeader("FCFS"));
        FCFS(joblist3);
        System.out.println(printHeader("SJF"));
        SJF(joblist3);
        System.out.println(printHeader("RR3"));
        RR(joblist3, 3);
        System.out.println(printHeader("RR5"));
        RR(joblist6, 5);
    }

}