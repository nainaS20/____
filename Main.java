// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.*;


public class Main {
    void printSolution(int sol[][],int N,int M)
    {
        for (int i = 0; i < N; i++) {
            System.out.print("Here the matrix shows the path for drone"+i);
            for (int j = 0; j < M; j++)
                System.out.print(" " + sol[i][j] + " ");
            System.out.println();

        }
        System.out.println();

    }
    boolean isSafe(int arr[][], int x, int y,int xt,int yt,int N,int M)
    {
        // if (x, y outside maze) return false
        return (x >= 0 && x < N && y >= 0 && y < M && arr[x][y] == 0);
    }
    boolean solveMaze(int maze[][],int []d,int xt,int yt)
    {
        int N=maze.length;
        int M=maze[0].length;
        int i=d[0];
        int j=d[1];


        int sol[][]=new int [N][M];
        for(int a=0;a<N;a++)
        {
            for(int b=0;b<M;b++)
            {
                sol[a][b]=0;
            }
        }

        if (solveMazeUtil(maze,i,j, xt,yt, sol,N,M) == false) {
            System.out.print("Solution doesn't exist");
            return false;
        }

        printSolution(sol,N,M);
        return true;
    }
    boolean solveMazeUtil(int maze[][], int x, int y,int xt,int yt,
                          int sol[][],int N,int M)
    {
        // if (x, y is goal) return true
        if (x == xt && y == yt) {
            sol[x][y] = 1;
            return true;
        }

        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y,xt,yt,N,M) == true) {
            // mark x, y as part of solution path
            sol[x][y] = 1;

            /* Move forward in x direction */
            if (solveMazeUtil(maze, x + 1, y, xt,yt,sol,N,M))
                return true;

            /* If moving in x direction doesn't give
               solution then  Move down in y direction */
            if (solveMazeUtil(maze, x, y + 1,xt,yt,sol,N,M))
                return true;
            if (solveMazeUtil(maze, x+1, y+1 , xt,yt,sol,N,M))
                return true;
            if (solveMazeUtil(maze, x-1, y,xt,yt,sol,N,M))
                return true;
            if (solveMazeUtil(maze, x, y - 1,xt,yt,sol,N,M))
                return true;
            if (solveMazeUtil(maze, x-1, y + 1, xt,yt,sol,N,M))
                return true;
            if (solveMazeUtil(maze, x+1, y- 1, xt,yt,sol,N,M))
                return true;
            if (solveMazeUtil(maze, x-1, y - 1, xt,yt,sol,N,M))
                return true;

            /* If none of the above movements works then
               BACKTRACK: unmark x, y as part of solution
               path */
            sol[x][y] = 0;
            return false;
        }

        return false;
    }
    public static void main(String[] args) {

        Main m=new Main();
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("Enter number of rows- ");
        int row= sc.nextInt();
        System.out.print("Enter number of columns- ");
        int col= sc.nextInt();

        int [][]arr=new int[row][col];

        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = 0;
            }
        }


        for(int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++)
            {
                System.out.print(arr[i][j] +" ");
            }
            System.out.println();
        }
        int [][]drones=new int [4][4];
        for(int i=0;i<4;i++)
        {
            System.out.print("Enter Coordinates x&y: ");
            for(int j=0;j<2;j++)
            {

                drones[i][j]=sc.nextInt();

            }
        }
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<2;j++)
            {
                System.out.print(drones[i][j]+" ");
            }
            System.out.println();
        }

        for(int i=0;i<4;i++) {
            for (int j = 0; j < 2; j++) {
                int a=drones[i][0];
                int b=drones[i][1];
                arr[a][b]=1;
            }
        }
        for(int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++)
            {
                System.out.print(arr[i][j] +" ");
            }
            System.out.println();
        }




        System.out.print("Enter target values a & b: ");
        int xt=sc.nextInt();
        int yt=sc.nextInt();

//        int x1,y1,x2,y2,x3,y3,x4,y4;
//        int target_X,target_Y;
        for(int a=0;a<drones.length;a++)
        {
//                int i=drones[a][0];
//                int j=drones[a][1];
                int[] d=drones[a];
              List<int[]>finalpath=new ArrayList<>();
              m.solveMaze(arr,d,xt,yt);
//                System.out.println("From Drone" + (a+1) +"path to our target" + finalpath);
//
        }
    }

    private static boolean pathFinder(int[][]arr,int[] current,int xt,int yt,List<int[]>path){

        int i=current[0];
        int j=current[1];
        if(i<0 || i>=arr.length || j<0 || j>=arr[0].length || arr[i][j]==1 )
        {
            return false;
        }

        path.add(current);

        if(i==xt && j==yt)
        {
            return true;
        }

        arr[i][j]=1;

        if(pathFinder(arr,new int[]{i+1,j},xt,yt,path) || pathFinder(arr,new int[]{i,j+1},xt,yt,path)
            || pathFinder(arr,new int[]{i+1,j+1},xt,yt,path) || pathFinder(arr,new int[]{i-1,j},xt,yt,path) ||
                pathFinder(arr,new int[]{i,j-1},xt,yt,path) || pathFinder(arr,new int[]{i-1,j-1},xt,yt,path))
        {
            System.out.print(current);
            return true;

      }
       arr[i][j]=0;
//       path.remove(path.size()-1);
      return false;
}
}
