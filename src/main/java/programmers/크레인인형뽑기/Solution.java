package programmers.크레인인형뽑기;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String[] solution(String[] record) {
        List<String> list = new ArrayList<>();
        Map<String, String> idMap = new HashMap<>();
        List<String> answerList = new ArrayList<>();
        for (String event : record) {
            String[] info = event.split(" ");
            String action = info[0];
            String userId = info[1];
            if (action.equals("Enter") || action.equals("Change")) {
                String nickName = info[2];
                idMap.put(userId, nickName);
            }
            list.add(new StringBuilder()
                    .append(action)
                    .append(" ")
                    .append(userId)
                    .toString());
        }

        for (String actionInfo : list) {
            String[] actionInfoArr = actionInfo.split(" ");
            String action = actionInfoArr[0];
            String userId = actionInfoArr[1];

            if (action.equals("Enter")) {
                answerList.add(new StringBuilder()
                        .append(idMap.get(userId))
                        .append("님이 들어왔습니다.")
                        .toString());
            } else if (action.equals("Leave")) {
                answerList.add(new StringBuilder()
                        .append(idMap.get(userId))
                        .append("님이 나갔습니다.")
                        .toString());
            }
        }

        return answerList.toArray(new String[0]);
    }

}