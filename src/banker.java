import java.util.Scanner;

public class banker {
    public static void main(String[] args){
        int number_of_process;

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Number of process: ");
        number_of_process = myObj.nextInt();

        int number_of_resources;
        System.out.print("Number of resources: ");
        number_of_resources = myObj.nextInt();

        int[] available = new int[number_of_resources];
        System.out.print("List resources available: ");
        for(int i=0; i<number_of_resources; i++){
            available[i] = myObj.nextInt();
        }

        System.out.print("List resources allocation of process: ");
        int[][] allocation = new int[number_of_process][number_of_resources];
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                allocation[i][j] = myObj.nextInt();
            }
        }

        System.out.print("List resources max of process: ");
        int[][] max = new int[number_of_process][number_of_resources];
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                max[i][j] = myObj.nextInt();
            }
        }
        int[][] need = new int[number_of_process][number_of_resources];
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        boolean[] finish = new boolean[number_of_process];
        for(int i=0; i<number_of_process; i++){
            finish[i] = false;
        }
        System.out.println("Allocation");
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                System.out.print(allocation[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("max");
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                System.out.print(max[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("need");
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                System.out.print(need[i][j] + " ");
            }
            System.out.println();
        }
        int[] history = new int[number_of_process];
        int index=0;
        while(!done(finish)){
            boolean check_all = true;
            for(int i=0; i<number_of_process; i++){
                if(!finish[i] && checkToAccess(need[i], available)){
                    history[index]=i;
                    index++;
                    System.out.println(i);
                    for(int j=0; j<available.length; j++){
                        available[j] = available[j] + allocation[i][j];
                    }
                    finish[i]=true;
                    check_all=false;
                }
            }
            if(check_all){
                System.out.println("Unsafe");
                System.exit(0);
            }
        }
        System.out.println("safe");
        for(int i=0; i<number_of_process; i++){
            System.out.println("P" + history[i]);
        }
    }

    public static boolean done(boolean[] arr){
        for(int i=0; i<arr.length; i++){
            if(!arr[i]){
                return false;
            }
        }
        return true;
    }

    public static boolean checkToAccess(int[] arr, int[] available){
        for(int i=0; i<available.length; i++){
            if(arr[i] > available[i]){
                return false;
            }
        }
        return true;
    }
}
