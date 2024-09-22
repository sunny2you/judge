package week2.p60062;

import java.util.*;

public class Solution {
    
    public int solution(int n, int[] weak, int[] dist) {
        // 취약 지점을 계산하기 쉽게 선형으로 바꿈
        int len = weak.length;
        // 선형 취약지점//출발지점으로부터 거리 계산이 쉬워짐. 사람 수
        int[] extendedWeak = new int[len * 2];
        for (int i = 0; i < len; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + len] = weak[i] + n;
        }

        // 친구 순열을 계산
        Arrays.sort(dist); // 친구들의 이동 거리를 오름차순으로 정렬

        int answer = dist.length + 1; // 친구들을 모두 투입해도 안 되는 경우 대비 (최댓값)

        
        for (int start = 0; start < len; start++) {
            //1등 친구를 어느 지점부터 투입 시킬지에 따라, 최소 인원수가 달라질 수 있다. 
            do {
                answer = Math.min(answer, findMinimumFriends(start, len, extendedWeak, dist));
            } while (nextPermutation(dist)); // 친구들의 순열을 모두 탐색
        }

        // 모든 취약 지점을 덮을 수 없으면 -1 반환
        return answer > dist.length ? -1 : answer;
    }

    // 주어진 친구 순서에서 최소 몇 명의 친구가 필요한지 계산
    private int findMinimumFriends(int start, int len, int[] extendedWeak, int[] dist) {
        //start: 어떤 취약 지점을 점검하는 것으로 시작할 것인지, 취약 지점 총 개수 
        //len: 취약 지점이 몇 개인지
        int count = 1; // 첫번째 친구
        int position = extendedWeak[start] + dist[dist.length - count]; // 첫 번째 친구가 덮을 수 있는 최대 거리

        //남은 취약 지점의 개수를 다 점검할 때 까지 반복
        for (int i = start + 1; i < start + len; i++) {
            //start+1(시작 다음지점)부터 시작해서 start+len까지 취약 지점들을 점검
            //len
            if (position < extendedWeak[i]) { //시작 다음지점을 지금 친구가 못 덮을 경우
                count++; //두번째 친구한테로.
                if (count > dist.length) { //모든 친구를 다 쓴 경우.
                    return dist.length + 1; //이렇게 해서 나중에 -1 반환하도록.
                }
                position = extendedWeak[i] + dist[dist.length - count]; //두번째 친구가 덮을 수 있는 최대 거리 
                //5 ---- 9 - 10 ----- 13(1)
            }
        }

        return count;
    }

    // 다음 순열을 생성하는 메소드 (사전순으로 다음 순열을 생성)
    private boolean nextPermutation(int[] dist) {
        int i = dist.length - 1;
        while (i > 0 && dist[i - 1] >= dist[i]) {
            i--;
        }

        if (i == 0) {
            return false; // 더 이상 다음 순열이 없음
        }

        int j = dist.length - 1;
        while (dist[i - 1] >= dist[j]) {
            j--;
        }

        swap(dist, i - 1, j);

        int k = dist.length - 1;
        while (i < k) {
            swap(dist, i++, k--);
        }

        return true;
    }

    private void swap(int[] dist, int i, int j) {
        int temp = dist[i];
        dist[i] = dist[j];
        dist[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        
        System.out.println(solution.solution(n, weak, dist)); // 예시 출력: 2
    }
}

