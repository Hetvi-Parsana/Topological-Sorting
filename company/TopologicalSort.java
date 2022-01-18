package com.company;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TopologicalSort {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);

        String filename = "src/com/company/input.txt";
        sc.close();

        File file = new File(filename);
        sc = new Scanner(file);

        int n = sc.nextInt();
        
        int adj[][] = new int[n][n];
        int v0, v1;
        // Declaring a square, 2D array to act as an adjacency matrix
        while(sc.hasNext()) {
            // finding the first index
            v0 = sc.next().charAt(0)-65;
            // finding the second vertex
            v1 = sc.next().charAt(0)-65;
            adj[v0][v1] = 1;
        }

        int[] pred = new int[n];

        for(int i=0; i<n; i++) {
            pred[i] = 0;
            for(int j=0; j<n; j++) {
                pred[i] += adj[j][i];
            }
        }
        // Initializing an empty queue.
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<n; i++) {
            if(pred[i] == 0) {
                queue.add(i);
            }
        }

        int[] sorted = new int[n];
        int topnum = 0;
        int node;

        while (!queue.isEmpty()) {

            node = queue.poll();
            sorted[topnum] = node;
            topnum++;

            for(int j=0; j<n; j++) {
                if(adj[node][j] == 1) {
                    pred[j]--;
                    if(pred[j] == 0) {
                        queue.add(j);
                    }
                }
            }
        }
        // All the vertices with be assigned with topnum in the topological sorting order.
        for(int i=0; i<n; i++) {
            System.out.print(i+1 + " ");
        }

        System.out.println();
        
        for(int i=0; i<n; i++) {
            System.out.print((char)(65 + sorted[i]) + " ");
        }

        sc.close();
    }
}