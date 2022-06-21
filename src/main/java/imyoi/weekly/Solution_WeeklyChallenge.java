package imyoi.weekly;

import java.util.HashSet;
import java.util.Set;

public class Solution_WeeklyChallenge {

    private static int answer = 0;

    /**
     * #87946 피로도
     * @param k : 유저의 현재 피로도
     * @param dungeons : 던전별 ["최소 필요 피로도", "소모 피로도"] 2차원 배열
     * @return 유저가 탐험할수 있는 최대 던전 수
     */
    public int solution01(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length]; //탐색처리
        dfs(dungeons, visited, k, 0);
        return answer;
    }

    public static void dfs(int[][] arr, boolean[] visited, int k, int cnt) {
        for(int i=0; i<arr.length; i++) {
            if(!visited[i] && k >= arr[i][0]) { //현재 피로도 >= 최소 필요 피로도
                visited[i] = true; //탐색시작
                dfs(arr, visited, k-arr[i][1], cnt+1); //던전 통과 시 경우의 수 더하기
                visited[i] = false; //탐색종료
            }
        }
        answer = Math.max(answer, cnt);
    }

    /**
     * #87377 교점에 별 만들기
     * ✓ [교점공식] x = BF-ED / AD-BC
     * ✓ [교점공식] y = EC-AF / AD-BC
     * ✓ AD-BC = 0인 경우 두 직선은 평행 또는 일치합니다.
     * ✓ A = 0이면서 B = 0인 경우는 주어지지 않습니다.
     * @param line : 직선 A, B, C에 대한 정보가 담긴 배열
     * @return 모든 별을 포함하는 최소 사각형을 return
     * */
    public String[] solution02(int[][] line) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        Set<String> point = new HashSet<>();
        for (int i=0; i<line.length-1; i++) {
            for (int j=i+1; j<line.length; j++) {
                long adbc = (long)line[i][0] * (long)line[j][1] - (long)line[i][1] * (long)line[j][0]; //ad-bc
                long bfed = (long)line[i][1] * (long)line[j][2] - (long)line[i][2] * (long)line[j][1]; //bf-ed
                long ecaf = (long)line[i][2] * (long)line[j][0] - (long)line[i][0] * (long)line[j][2]; //ec-af

                if(adbc == 0) continue; //두 직선은 평행 또는 일치
                if(bfed%adbc != 0 || ecaf%adbc != 0) continue; //정수 아님

                int x = (int)bfed/(int)adbc;
                int y = (int)ecaf/(int)adbc;
                point.add(x + "," + y); //좌표찍기

                //최소, 최대값 갱신
                minX = Math.min(minX, (int)x);
                minY = Math.min(minY, (int)y);
                maxX = Math.max(maxX, (int)x);
                maxY = Math.max(maxY, (int)y);
            }
        }
        if(minX == Integer.MAX_VALUE) { //교점 없음
            String[] answer = new String[1];
            answer[0] = "*";
        }

        int idx = 0;
        String[] answer = new String[maxY - minY + 1];
        for (int i = maxY; i >= minY; i--) { //제일 상단(y축이 양수)부터 ~
            StringBuilder sb = new StringBuilder();
            for (int j = minX; j <= maxX; j++) {
                sb.append(point.contains(j + "," + i) ? "*" : "."); //정수좌표 존재시 "*"를 그림
            }
            answer[idx++] = sb.toString();
        }
        return answer;
    }
}
