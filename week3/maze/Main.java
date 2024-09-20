import java.util.*;

class Node{
    private int x;
    private int y;

    public Node(int x,int y){
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
    public static int n,m;
    public static int[][] graph=new int[201][201];

    //이동할 네 가지 방향 정의
    public static int dx[]={-1,1,0,0};
    public static int dy[]={0,0,-1,1};

    //상하좌우를 모두 방문하는 bfs. 
    public static int bfs(int x, int y){
        //큐(Queue) 구현을 위해 queue 라이브러리 사용
        Queue<Node> q=new LinkedList<>();

        //시작 지점인 0,0을 추가
        q.offer(new Node(x,y));
        //큐가 빌 때까지 반복하기
        while(!q.isEmpty()){
            //지금 큐에 있는 것 중 가장 먼저 들어온 노드를 반환
            Node node=q.poll();
            //반환된 노드의 x값
            x=node.getX();
            //반환된 노드의 y값
            y=node.getY();
            
            //현재 위치에서 4가지 방향으로의 위치 확인
            for (int i=0; i<4;i++){
                //4가지 값중 하나를 더함
                int nx=x+dx[i];
                //4가지 값중 하나를 더함
                int ny=y+dy[i];
                //미로 찾기 공간을 벗어난 경우 무시 
                if(nx<0||nx>=n||ny<0||ny>=m) continue;
                //괴물인 경우 무시 
                if(graph[nx][ny]==0) continue;
                //해당 노드를 처음 방문하는 경우에만 최단 거리 기록
                if(graph[nx][ny]==1){
                    //그래프에 있던 1,0들은 bfs탐색을 진행하면서 시작점으로부터 거리로 바뀜
                    graph[nx][ny]=graph[x][y]+1;
                    //이 노드에 대한 상하좌우 탐색을 예약하기 위해 큐에 삽입
                    q.offer(new Node(nx,ny));
                }
            }
        }
        //끝점에는 결국 시작점으로부터 끝점까지의 거리가 저장될 것. 그 값이 구하는 값
        return graph[n-1][m-1];
    }

    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);

        //N,M을 공백을 기준으로 구분하여 입력 받기
        n=sc.nextInt();
        m=sc.nextInt();
        sc.nextLine();//버퍼 지우기

        //2차원 리스트의 맵 정보 입력 받기
        for (int i=0; i<n; i++){
            String str = sc.nextLine();
            for (int j=0; j<m; j++){
                graph[i][j]=str.charAt(j)-'0';
            }
        }

        //BFS를 수행한 결과 출력, 시작점은 0,0
        System.out.println(bfs(0,0));
        sc.close();

    }
}
