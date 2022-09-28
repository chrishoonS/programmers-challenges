package chrishoonS.highScore;

import chrishoonS.kakao2019.Solution33;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Solution44Test {

    @Test
    void solution() {
        Solution44 slt44 = new Solution44();
        assertEquals(slt44.solution(new String[]{"119", "97674223", "1195524421"}), false);
        assertEquals(slt44.solution(new String[]{"123","456","789"}), true);
        assertEquals(slt44.solution(new String[]{"12","123","1235","567","88"}), false);
    }
}