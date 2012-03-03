// Richard The
// CS241, section 01
// Project 3: a comparison of different sorting algorithms
// 28 February 2012

import java.util.*;

public class sorting {
   
   static int swap = 0, compare = 0;
   
   private static void print_and_reset(){
      System.out.println("# of swaps : " + swap + "\n# of comparisons : " + compare);
      swap = 0; compare = 0;
   }
   
   private static void insert_sort(int[] n){
      int current, j;
      for(int i = 1; i < n.length; i++){
         current = n[i]; j = i-1;
         while ((j>=0) && (n[j]>current)){
            n[j+1]=n[j--]; compare++;
         }
         n[j+1] = current;
      }
      System.out.println("- Insertion Sort -\n# of items : " + n.length);
      print_and_reset();
   }
	
   private static void selection_sort(int[] n){
      int min, temp;
      for(int i = 0; i < n.length; i++){
         min = i;
         for(int j = i+1; j < n.length; j++){
            if(n[j] < n[min]) min = j;
            compare++;
         }
         if(i != min){
            temp = n[i]; n[i] = n[min];
            n[min] = temp; swap++;
         }
      }
      System.out.println("- Selection Sort -\n# of items : " + n.length);
      print_and_reset();
   }

   private static void merge_sort(int[] n){
      merge_sort(n, 0, n.length);
      System.out.println("- Merge Sort -\n# of items : " + n.length);
      print_and_reset();
   }
   
   private static void merge_sort(int[] n, int low, int high){
      int n1, n2;
      if(high > 1){
         n1 = high / 2; n2 = high - n1;
         merge_sort(n, low, n1);
         merge_sort(n, low + n1, n2);
         merge(n, low, n1, n2);
      }
   }
   
   private static void merge(int[] n, int low, int n1, int n2){
      int[] temp = new int[n1+n2]; // Allocate the temporary array
      int copy  = 0, copy1 = 0, copy2 = 0, i;

      // Merge elements, copying from two halves of data to the temporary array.
      while ((copy1 < n1) && (copy2 < n2)) {
         if (n[low + copy1] < n[low + n1 + copy2])
            temp[copy++] = n[low + (copy1++)];
         else temp[copy++] = n[low + n1 + (copy2++)];
         compare++;
      }

      // Copy any remaining entries in the left and right subarrays.
      while (copy1 < n1) temp[copy++] = n[low + (copy1++)];
      while (copy2 < n2) temp[copy++] = n[low + n1 + (copy2++)];

      // Copy from temp back to the data array.
      for (i = 0; i < n1+n2; i++) n[low + i] = temp[i];
   }
	
   private static void quick_sort(int[] n){
      quick_sort(n, 0, n.length);
      System.out.println("- Quick Sort -\n# of items : " + n.length);
      print_and_reset();
   }
   
   private static void quick_sort(int[] n, int p, int q){
      int pivot, n1, n2;
      if(q > 1){
         pivot = partition(n, p, q); // Partition the array, and set the pivot index
         n1 = pivot - p; n2 = q - n1 - 1; // Compute the sizes of the two pieces
         quick_sort(n, p, n1);
         quick_sort(n, pivot + 1, n2);
      }
   }
   
   private static int partition(int[] n, int p, int q){
      int pivot = n[p], big = p + 1, small = p + q - 1, temp;
      
      while(big <= small){
         while(big < n.length && n[big] <= pivot) { big++; compare++; }
         while(n[small] > pivot) { small--; compare++; }
         if(big < small){
            temp = n[big]; n[big] = n[small];
            n[small] = temp; swap++;
         }
      }
      
      n[p] = n[small]; n[small] = pivot;
      swap++; return small;
   }
   
   private static void heap_sort(int[] n){
      int temp;
      
      for(int i = n.length/2-1; i >= 0; i--) max_heapify(n, i, n.length);
      
      System.out.print("Heapified : ");
      for(int i = 0; i < n.length; i++) System.out.print(n[i] + " ");
      
      for(int i = n.length-1; i > 0; i--){
         temp = n[i]; n[i] = n[0];
         n[0] = temp; swap++;
         max_heapify(n, 0, i);
      }
      
      System.out.println("\n- Heap Sort -\n# of items : " + n.length);
      print_and_reset();
   }
   
   private static void max_heapify(int[] n, int p, int q){
      int left = 2*p+1, right = 2*p+2, largest = p, temp;
      
      if (left < q && n[left] > n[p]){
         largest = left;
         compare++;
      }
      
      if (right < q && n[right] > n[largest]){
         largest = right;
         compare++;
      }
      
      if (largest != p) {
         temp = n[largest]; n[largest] = n[p];
         n[p] = temp; swap++;
         max_heapify(n, largest, q);
      }
   }
   
   private static void comparison_sort(int[] n){
      int[] count = new int[n.length], S = new int[n.length];

      for(int i = 0; i < n.length; i++) count[i] = 0;

      for(int i = 0; i < n.length-1; i++)
         for(int j = i+1; j < n.length; j++){
            if(n[i] < n[j]) count[j] = count[j] + 1;
            else count[i] = count[i] + 1;
         }

      for(int i = 0; i < n.length; i++) S[count[i]] = n[i];

      for(int i = 0; i < n.length; i++) n[i] = S[i];

      System.out.println("- Comparison Counting Sort -");
      System.out.print("Count elements : ");
      for(int i = 0; i < count.length; i++) System.out.print(count[i] + " ");
      System.out.println("\n# of items : " + n.length);
      print_and_reset();
   }

   private static void print(int[] items){
      System.out.print("Current order of elements : ");
      for(int i = 0; i < items.length; i++) System.out.print(items[i] + " ");
      System.out.println();
   }

   public static void main(String[] args) {
		
      boolean option = true; // variable for prompting the user to enter different options
		
      while(true){
         int item, before = 0, after = 0, input_type = 0;
         int[] items = {};
         
         while(option){ // choose either random or manual input
            System.out.println("Enter the corresponding number :\n(1) Random list\n(2) Manual input");
            input_type = (new Scanner(System.in)).nextInt();
            if (input_type != 1 && input_type != 2) System.out.println("Wrong input!");
            else option = false;
         }
         option = true;
         
         while(option) { // # of items to be sorted
            System.out.println("Enter the # of items to be sorted: ");
            item = (new Scanner(System.in)).nextInt();
            if (item < 0) System.out.println("The # of items can't be negative!");
            else {
               items = new int[item];
               if (input_type == 1){ // generate random list of integers
                  Random rand = new Random();
                  for(int i = 0; i < items.length; i++) items[i] = rand.nextInt(100);
               }
               else { // the user enters elements manually
                  System.out.println("Enter the elements : ");
                  for(int i = 0; i < items.length; i++)
                     items[i] = (new Scanner(System.in)).nextInt();
               }
               option = false;
            }
         }
         option = true;
         
         while(option) { // print before sort
            System.out.println("Print before sorting?\n(1) Yes\n(2) No");
            before = (new Scanner(System.in)).nextInt();
            if (before != 1 && before != 2) System.out.println("Wrong input!");
            else option = false;
         }
         option = true;
			
         while(option) { // print after sort
            System.out.println("Print after sorting?\n(1) Yes\n(2) No");
            after = (new Scanner(System.in)).nextInt();
            if (after != 1 && after != 2) System.out.println("Wrong input!");
            else option = false;
         }
         option = true;
			
         while(option) { // choose the sorting algorithm
            System.out.println("Choose the sorting algorithm :\n(1) Insertion sort\n(2) Selection sort\n(3) Merge sort\n(4) Quick sort\n(5) Heap sort\n(6) Comparison Counting sort\n(0) QUIT program");
            int sort_input = (new Scanner(System.in)).nextInt();
            if (sort_input == 0) return; // quit the program when input = 0
				
            switch(sort_input) {
            case 1: {
               if (before == 1) print(items);
               insert_sort(items);
               if (after == 1) print(items);
               System.out.println("--------------------\n");
               option = false; break;
            }
            case 2: {
               if (before == 1) print(items);
               selection_sort(items);
               if (after == 1) print(items);
               System.out.println("--------------------\n");
               option = false; break;
            }
            case 3:{
               if (before == 1) print(items);
               merge_sort(items);
               if (after == 1) print(items);
               System.out.println("--------------------\n");
               option = false; break;
            }
            case 4: {
               if (before == 1) print(items);
               quick_sort(items);
               if (after == 1) print(items);
               System.out.println("--------------------\n");
               option = false; break;
            }
            case 5: {
               if (before == 1) print(items);
               heap_sort(items);
               if (after == 1) print(items);
               System.out.println("--------------------\n");
               option = false; break;
            }
            case 6: {
               if (before == 1) print(items);
               comparison_sort(items);
               if (after == 1) print(items);
               System.out.println("--------------------\n");
               option = false; break;
            }
            default: System.out.println("Wrong input!");
            }
         }
         option = true;
      }
   }
}