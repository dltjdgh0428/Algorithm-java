class Solution {
private static int[][] DIR = {{-1,0},{0,-1},{-1,-1},{1,1},{1,0},{0,1},{1,-1},{-1,1},{0,0}};
    private static int N;
    public static int solution(int[][] board) {
        N = board.length;
        return bfs(board);
    }

    private static int bfs(int[][] board){
        int count =0;
        boolean[][] visited = new boolean[board.length][board.length];
        for(int i=0;i<board.length;i++){
            for(int j =0; j<board.length;j++){
                if(board[i][j]==1){
                    for(int k=0;k<DIR.length;k++){
                        int xx = i+DIR[k][0];
                        int yy = j+DIR[k][1];

                        if(canMove(xx,yy)&&!visited[xx][yy]){
                            visited[xx][yy] = true;
                            count++;
                        }
                    }
                }
            }
        }

        return (int)Math.pow(board.length,2)-count;
    }
    private static boolean canMove(int x,int y){
        return x>=0 && y>=0 &&x<N && y<N;
    }
}