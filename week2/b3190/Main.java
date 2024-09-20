package week2.b3190;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    private int x;
    private int y;
    
    public Node(int x, int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}

public class Main {

    public static int n;
    public static int k;
    public static int l;
    public static boolean end=false;
    public static int sec;
    public static int dir=1; //초기방향은 'r'
    public static char[] dirInfo={'u','r','d','l'}; //상하좌우
    public static int tailX=1;
    public static int tailY=1;

    public static int headX=1;
    public static int headY=1;
    public static int change=0;
    public static Queue<Node> queue = new LinkedList<>();
    
    public static void main(String[] args) {
        
        
        Scanner sc=new Scanner(System.in);

        //보드 크기
        n=sc.nextInt();
        int[][] board=new int[n+2][n+2];
        sc.nextLine();

        //사과 개수 
        k=sc.nextInt();
        sc.nextLine();
        int[][] appleInfo=new int[k][2];
        // 사과 위치 정보
        //2차원 리스트 형태의 사과 위치 정보 입력 받기
        for (int i=0; i<k; i++){
            for (int j=0; j<2; j++){
                appleInfo[i][j]=sc.nextInt();
            }
            sc.nextLine();
        }

        //방향 전환 횟수
        l=sc.nextInt();
        String[][] changeInfo=new String[l][2];
        //방향 전환 정보 저장
        for (int i=0; i<l; i++){
            for (int j=0; j<2; j++){
                changeInfo[i][j]=sc.next();
            }
            sc.nextLine();
        }
        
        sc.close();

        //board 상하좌우 벽 설정
        for (int i=0;i<n+2;i++){
            board[0][i]=1;
            board[n+1][i]=1;
            board[i][0]=1;
            board[i][n+1]=1;
        }

        //board 사과 설정
        for (int i=0;i<k;i++){
            int x=appleInfo[i][0];
            int y=appleInfo[i][1];
            board[x][y]=2;
        }

        //시작 점을 queue에 추가
        Node startNode=new Node(headX, headY);
        queue.offer(startNode);
        
        //매 초마다 board 업데이트
        while(!end){
            //방향 전환 여부 확인
            if(change<l&&changeInfo[change][0].equals(Integer.toString(sec))){
                if(changeInfo[change][1].equals("D")){
                    dir=(dir + 1) % 4;
                } 
                else{
                    dir = (dir + 3) % 4;
                }
                if(change==l-1) change=l-1;
                else change+=1;
            }

            //머리 내밀기
            switch (dir) {
                case 0:
                    //위쪽 칸을 1로 설정
                    headX-=1;
                    break;
                case 2:
                    //아래 칸을 1로 설정
                    headX+=1;
                    break;
                case 3:
                    //좌측 칸을 1로 설정
                    headY-=1;
                    break;
                case 1:
                    //우측 칸을 1로 설정
                    headY+=1;
                    break;   
                default:
                    break;
            }
            if (headX < 0 || headX >= n+2 || headY < 0 || headY >= n+2) {
                end = true;
                break;
            }
            //머리가 새롭게 닿은 곳을 큐에 추가
            Node newNode=new Node(headX, headY);
            queue.offer(newNode);
            //머리가 닿은 곳의 값 확인
            int currentValue=board[headX][headY];

            switch(currentValue){
                case 0:
                    //꼬리가 있던 곳을 0으로 바꿈
                    Node tail=queue.poll();
                    tailX=tail.getX();
                    tailY=tail.getY();
                    board[tailX][tailY]=0;
                    break;
                case 1:
                    //1을 만나면 끝남
                    end=true;
                    break;
                case 2:
                    //꼬리가 있는 곳을 그대로 둠. 
                    break;
                default:
                    break;
            }

            board[headX][headY]=1;
            sec+=1;
        }
        
        System.err.println(sec);
        
    }
}
