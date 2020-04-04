import java.util.Scanner;

public class deadlock {
    public static void main(String[] args){
        int number_of_process;

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Number of process: ");
        number_of_process = myObj.nextInt();

        int number_of_resources;
        System.out.println("Number of resources: ");
        number_of_resources = myObj.nextInt();

        int[] available = new int[number_of_resources];
        System.out.println("List resources available: ");
        for(int i=0; i<number_of_resources; i++){
            available[i] = myObj.nextInt();
        }

        System.out.println("List resources allocation of process: ");
        int[][] allocation = new int[number_of_process][number_of_resources];
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                allocation[i][j] = myObj.nextInt();
            }
        }
        System.out.println("List resources request of process: ");
        int[][] request = new int[number_of_process][number_of_resources];
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                request[i][j] = myObj.nextInt();
            }
        }

        boolean[] finish = new boolean[number_of_process];
        for(int i=0; i<number_of_process; i++){
            boolean check = false;
            for(int j=0; j<number_of_resources; j++){
                if(allocation[i][j] != 0){
                    check = true;
                    break;
                }
            }
            finish[i]= !check;
        }
        System.out.println("Allocation");
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                System.out.print(allocation[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("request");
        for(int i=0; i<number_of_process; i++){
            for(int j=0; j<number_of_resources; j++){
                System.out.print(request[i][j] + " ");
            }
            System.out.println();
        }
        while(!done(finish)){
            boolean check_all = true;
            for(int i=0; i<number_of_process; i++){
                if(!finish[i] && checkToAccess(request[i], available)){
                    for(int j=0; j<available.length; j++){
                        available[j] = available[j] + allocation[i][j];
                    }
                    finish[i]=true;
                    check_all=false;
                }
            }
            if(check_all){
                System.out.println("Be tac");
                System.exit(0);
            }
        }
        System.out.println("K be tac");
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
