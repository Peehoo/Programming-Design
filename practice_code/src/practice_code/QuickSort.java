package practice_code;

import java.util.Random;

public class QuickSort {

	public static void main(String args[]){
		int[] arr = {3,8,5,4,1,7,2};
		quicksort(arr, 0, arr.length);
	}
	
	public static void quicksort(int[] arr, int low, int high){
		Random r = new Random();
		int pivot = r.nextInt(high);
		int i = low;
		int j = high-1;
		
		while(i<pivot && j>pivot){
			while(arr[i] < arr[pivot]){
				i++;
			}
			while(arr[j] > arr[pivot]){
				j--;
			}
			if(i<=j){
				arr = exchange(arr,i,j);
			}
		}
		quicksort(arr, low, pivot-1);
		quicksort(arr, pivot+1, high);
	}
	
	private static int[] exchange(int[] arr, int i, int j){
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
}
