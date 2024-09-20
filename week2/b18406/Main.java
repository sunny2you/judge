package week2.b18406;
import java.util.Scanner;

public class Main{
    public static int n;
    public static int length;
    public static int leftSum=0;
    public static int rightSum=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        n=sc.nextInt();
        String str = Integer.toString(n);
        length=str.length();
        int[] score=new int[length];

        //str의 각 문자를 정수로 바꾸어 배열의 원소로 삽입
        for (int i=0; i<length; i++){
            score[i]=str.charAt(i)-'0';
        }

        for (int i=0; i<length/2;i++){
            leftSum+=score[i];
            rightSum+=score[i+length/2];
        }

        if(leftSum==rightSum){
            System.out.println("LUCKY");
        }
        else{
            System.out.println("READY");
        }
        sc.close();
    }
}