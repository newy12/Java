package com.summar.summar.util;


public class StringUtil {
    /**
     * 회원가입 & 로그인
     */
    public static final String login = "\\n\" +\n" +
            "                    \"    \\\"major2\\\": \\\"전공2\\\",\\n\" +\n" +
            "                    \"    \\\"major1\\\": \\\"전공1\\\",\\n\" +\n" +
            "                    \"    \\\"follower\\\": 0,\\n\" +\n" +
            "                    \"    \\\"following\\\": 0,\\n\" +\n" +
            "                    \"    \\\"userNickname\\\": \\\"4124\\\",\\n\" +\n" +
            "                    \"    \\\"accessToken\\\": \\\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd5MTMiLCJleHAiOjE2NzExMTc4MDEsImlhdCI6MTY3MTExNzUwMX0.SUBYajqxbX7JA4v2iBvMUUpGhWCnM4PY3pSMBPv-7jI\\\",\\n\" +\n" +
            "                    \"    \\\"loginStatus\\\": \\\"로그인\\\",\\n\" +\n" +
            "                    \"    \\\"refreshToken\\\": \\\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXd5MTMiLCJleHAiOjE2NzExNTM1MDEsImlhdCI6MTY3MTExNzUwMX0.fOZj98iGiMr1KY4q2MszAddhcX1u8Ko3ds7K6Fl1rcU\\\"\\n\" +\n" +
            "                    \"}";

    public static final String giveAccessToken = "{\\n\" +\n" +
            "                    \"    \\\"accessToken\\\": \\\"djwdbGciOiJIUzI1NiJ9.eyJzdWIiOiJkZGQiLCJleHAiOjE2NzE2OTkyNjUsImlhdCI6MTY3MTY5ODk2NX0.7exMAI-zaTqu5ouZI6AMfuEmE7bTlvAp9wMlCHvVzoA\\\"\\n\" +\n" +
            "                    \"}";
    public static final String giveBothToken = "{\\n\" +\n" +
            "                    \"    \\\"results\\\": {\\n\" +\n" +
            "                    \"        \\\"accessToken\\\": \\\"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOdasasdjeHAiOjE2NzE2OTg2NDcsImlhdCI6MTY3MTY5ODM0N30.ONblIUyQSNyQFB_aQep9mrRGZunP14rxaV8glafC1d8\\\",\\n\" +\n" +
            "                    \"        \\\"refreshTokenSeq\\\": \\\"102c533a-6e5a-436d-86e7-f144d4ssaa41\\\"\\n\" +\n" +
            "                    \"    }\\n\" +\n" +
            "                    \"}";
    public static final String findUserInfo = "{\\n\" +\n" +
            "                    \"    \\\"result\\\": {\\n\" +\n" +
            "                    \"        \\\"userNickname\\\": \\\"dd\\\",\\n\" +\n" +
            "                    \"        \\\"major1\\\": \\\"전공1\\\",\\n\" +\n" +
            "                    \"        \\\"major2\\\": \\\"전공2\\\",\\n\" +\n" +
            "                    \"        \\\"follower\\\": 0,\\n\" +\n" +
            "                    \"        \\\"following\\\": 0\\n\" +\n" +
            "                    \"    }\\n\" +\n" +
            "                    \"}";
    public static final String changeUserInfo = "{\\n\" +\n" +
            "                    \"    \\\"status\\\": \\\"SUCCESS\\\",\\n\" +\n" +
            "                    \"    \\\"message\\\": \\\"정상처리\\\",\\n\" +\n" +
            "                    \"    \\\"errorMessage\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"errorCode\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"result\\\": null\\n\" +\n" +
            "                    \"}";
    public static final String addIntroduce = "{\\n\" +\n" +
            "                    \"    \\\"status\\\": \\\"SUCCESS\\\",\\n\" +\n" +
            "                    \"    \\\"message\\\": \\\"정상처리\\\",\\n\" +\n" +
            "                    \"    \\\"errorMessage\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"errorCode\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"result\\\": null\\n\" +\n" +
            "                    \"}";
    public static final String checkNicknameDuplication = "{\\n\" +\n" +
            "                    \"  \\\"status\\\": \\\"SUCCESS\\\",\\n\" +\n" +
            "                    \"  \\\"message\\\": \\\"정상처리\\\",\\n\" +\n" +
            "                    \"  \\\"errorMessage\\\": null,\\n\" +\n" +
            "                    \"  \\\"errorCode\\\": null,\\n\" +\n" +
            "                    \"  \\\"result\\\": {\\n\" +\n" +
            "                    \"    \\\"result\\\": true\\n\" +\n" +
            "                    \"  }\\n\" +\n" +
            "                    \"}";
    public static final String searchUserInfo = "{\\n\" +\n" +
            "                    \"  \\\"results\\\": {\\n\" +\n" +
            "                    \"    \\\"userNickname\\\": \\\"신승욱\\\",\\n\" +\n" +
            "                    \"    \\\"follower\\\": 0,\\n\" +\n" +
            "                    \"    \\\"following\\\": 0,\\n\" +\n" +
            "                    \"    \\\"userEmail\\\": \\\"112468669451266982874\\\",\\n\" +\n" +
            "                    \"    \\\"major1\\\": \\\"자연계열\\\",\\n\" +\n" +
            "                    \"    \\\"major2\\\": \\\"수학ㆍ물리ㆍ천문ㆍ지리\\\"\\n\" +\n" +
            "                    \"  }\\n\" +\n" +
            "                    \"}";
    public static final String searchUserInitialList = "{\\n\" +\n" +
            "                    \"    \\\"firstPage\\\": true,\\n\" +\n" +
            "                    \"    \\\"lastPage\\\": true,\\n\" +\n" +
            "                    \"    \\\"totalPageCount\\\": 1,\\n\" +\n" +
            "                    \"    \\\"recordsPerPage\\\": 30,\\n\" +\n" +
            "                    \"    \\\"content\\\": [\\n\" +\n" +
            "                    \"        {\\n\" +\n" +
            "                    \"            \\\"userNickname\\\": \\\"승욱\\\",\\n\" +\n" +
            "                    \"            \\\"major1\\\": \\\"공학계열\\\",\\n\" +\n" +
            "                    \"            \\\"major2\\\": \\\"건축\\\",\\n\" +\n" +
            "                    \"            \\\"follower\\\": 50,\\n\" +\n" +
            "                    \"            \\\"following\\\": 30,\\n\" +\n" +
            "                    \"            \\\"introduce\\\": null,\\n\" +\n" +
            "                    \"            \\\"profileImageUrl\\\": null\\n\" +\n" +
            "                    \"        },\\n\" +\n" +
            "                    \"        {\\n\" +\n" +
            "                    \"            \\\"userNickname\\\": \\\"신승욱\\\",\\n\" +\n" +
            "                    \"            \\\"major1\\\": \\\"자연계열\\\",\\n\" +\n" +
            "                    \"            \\\"major2\\\": \\\"수학ㆍ물리ㆍ천문ㆍ지리\\\",\\n\" +\n" +
            "                    \"            \\\"follower\\\": 0,\\n\" +\n" +
            "                    \"            \\\"following\\\": 0,\\n\" +\n" +
            "                    \"            \\\"introduce\\\": null,\\n\" +\n" +
            "                    \"            \\\"profileImageUrl\\\": null\\n\" +\n" +
            "                    \"        }\\n\" +\n" +
            "                    \"    ],\\n\" +\n" +
            "                    \"    \\\"totalRecordCount\\\": 2,\\n\" +\n" +
            "                    \"    \\\"currentPageNo\\\": 1\\n\" +\n" +
            "                    \"}";
    public static final String nulls = "\"\\\"result\\\":null\"";

    public static final String leaveUser = "{\\n\" +\n" +
            "                    \"    \\\"status\\\": \\\"SUCCESS\\\",\\n\" +\n" +
            "                    \"    \\\"message\\\": \\\"정상처리\\\",\\n\" +\n" +
            "                    \"    \\\"errorMessage\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"errorCode\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"result\\\": null\\n\" +\n" +
            "                    \"}";
    public static final String setting = "{\\n\" +\n" +
            "                    \"  \\\"status\\\": \\\"SUCCESS\\\",\\n\" +\n" +
            "                    \"  \\\"message\\\": \\\"정상처리\\\",\\n\" +\n" +
            "                    \"  \\\"errorMessage\\\": null,\\n\" +\n" +
            "                    \"  \\\"errorCode\\\": null,\\n\" +\n" +
            "                    \"  \\\"result\\\": {\\n\" +\n" +
            "                    \"    \\\"results\\\": [\\n\" +\n" +
            "                    \"      {\\n\" +\n" +
            "                    \"        \\\"settingId\\\": 1,\\n\" +\n" +
            "                    \"        \\\"settingType\\\": \\\"NOTICE\\\",\\n\" +\n" +
            "                    \"        \\\"title\\\": \\\"써머 출시 1.0.0 업데이트 안내\\\",\\n\" +\n" +
            "                    \"        \\\"content\\\": \\\"업데이트 안내입니다. 업데이트 안내입니다.업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다. 업데이트 안내입니다.\\\",\\n\" +\n" +
            "                    \"        \\\"regDatetime\\\": \\\"2022.01.13\\\"\\n\" +\n" +
            "                    \"      },\\n\" +\n" +
            "                    \"      {\\n\" +\n" +
            "                    \"        \\\"settingId\\\": 2,\\n\" +\n" +
            "                    \"        \\\"settingType\\\": \\\"NOTICE\\\",\\n\" +\n" +
            "                    \"        \\\"title\\\": \\\"써머 장애 안내\\\",\\n\" +\n" +
            "                    \"        \\\"content\\\": \\\"업데이트 안내입니다.\\\",\\n\" +\n" +
            "                    \"        \\\"regDatetime\\\": \\\"2022.01.13\\\"\\n\" +\n" +
            "                    \"      }\\n\" +\n" +
            "                    \"    ]\\n\" +\n" +
            "                    \"  }\\n\" +\n" +
            "                    \"}" ;

    public static final String findFollowers = "{\\n\" +\n" +
            "                    \"    \\\"firstPage\\\": true,\\n\" +\n" +
            "                    \"    \\\"lastPage\\\": true,\\n\" +\n" +
            "                    \"    \\\"totalPageCount\\\": 1,\\n\" +\n" +
            "                    \"    \\\"recordsPerPage\\\": 20,\\n\" +\n" +
            "                    \"    \\\"content\\\": [\\n\" +\n" +
            "                    \"        {\\n\" +\n" +
            "                    \"            \\\"userNickname\\\": \\\"영재2\\\",\\n\" +\n" +
            "                    \"            \\\"introduce\\\": null,\\n\" +\n" +
            "                    \"            \\\"major1\\\": \\\"공학계열\\\",\\n\" +\n" +
            "                    \"            \\\"major2\\\": \\\"컴퓨터정보공학과\\\",\\n\" +\n" +
            "                    \"            \\\"follower\\\": 1,\\n\" +\n" +
            "                    \"            \\\"following\\\": 1\\n\" +\n" +
            "                    \"        }\\n\" +\n" +
            "                    \"    ],\\n\" +\n" +
            "                    \"    \\\"totalRecordCount\\\": 1,\\n\" +\n" +
            "                    \"    \\\"currentPageNo\\\": 1\\n\" +\n" +
            "                    \"}";
    public static final String findFollowings = "{\\n\" +\n" +
            "                    \"    \\\"firstPage\\\": true,\\n\" +\n" +
            "                    \"    \\\"lastPage\\\": true,\\n\" +\n" +
            "                    \"    \\\"totalPageCount\\\": 1,\\n\" +\n" +
            "                    \"    \\\"recordsPerPage\\\": 20,\\n\" +\n" +
            "                    \"    \\\"content\\\": [\\n\" +\n" +
            "                    \"        {\\n\" +\n" +
            "                    \"            \\\"userNickname\\\": \\\"영재2\\\",\\n\" +\n" +
            "                    \"            \\\"introduce\\\": null,\\n\" +\n" +
            "                    \"            \\\"major1\\\": \\\"공학계열\\\",\\n\" +\n" +
            "                    \"            \\\"major2\\\": \\\"컴퓨터정보공학과\\\",\\n\" +\n" +
            "                    \"            \\\"follower\\\": 1,\\n\" +\n" +
            "                    \"            \\\"following\\\": 1\\n\" +\n" +
            "                    \"        }\\n\" +\n" +
            "                    \"    ],\\n\" +\n" +
            "                    \"    \\\"totalRecordCount\\\": 1,\\n\" +\n" +
            "                    \"    \\\"currentPageNo\\\": 1\\n\" +\n" +
            "                    \"}";
    public static final String addFollower = "{\\n\" +\n" +
            "                    \"    \\\"status\\\": \\\"SUCCESS\\\",\\n\" +\n" +
            "                    \"    \\\"message\\\": \\\"정상처리\\\",\\n\" +\n" +
            "                    \"    \\\"errorMessage\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"errorCode\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"result\\\": null\\n\" +\n" +
            "                    \"}";
    public static final String deleteFollower = "{\\n\" +\n" +
            "                    \"    \\\"status\\\": \\\"SUCCESS\\\",\\n\" +\n" +
            "                    \"    \\\"message\\\": \\\"정상처리\\\",\\n\" +\n" +
            "                    \"    \\\"errorMessage\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"errorCode\\\": \\\"\\\",\\n\" +\n" +
            "                    \"    \\\"result\\\": null\\n\" +\n" +
            "                    \"}";
    public static final String selectBanners = "{\\n\" +\n" +
            "                    \"    \\\"status\\\": \\\"SUCCESS\\\",\\n\" +\n" +
            "                    \"    \\\"message\\\": \\\"정상처리\\\",\\n\" +\n" +
            "                    \"    \\\"errorMessage\\\": null,\\n\" +\n" +
            "                    \"    \\\"errorCode\\\": null,\\n\" +\n" +
            "                    \"    \\\"result\\\": {\\n\" +\n" +
            "                    \"        \\\"results\\\": [\\n\" +\n" +
            "                    \"            {\\n\" +\n" +
            "                    \"                \\\"bannerSeq\\\": 1,\\n\" +\n" +
            "                    \"                \\\"imageUrl\\\": \\\"test.png\\\"\\n\" +\n" +
            "                    \"            }\\n\" +\n" +
            "                    \"        ]\\n\" +\n" +
            "                    \"    }\\n\" +\n" +
            "                    \"}";
    public static final String followCheck = "{\n" +
            "    \"status\": \"SUCCESS\",\n" +
            "    \"message\": \"정상처리\",\n" +
            "    \"errorMessage\": null,\n" +
            "    \"errorCode\": null,\n" +
            "    \"result\": {\n" +
            "        \"result\": false\n" +
            "    }\n" +
            "}";
    public static final String otherFollowers = "{\n" +
            "    \"firstPage\": true,\n" +
            "    \"lastPage\": true,\n" +
            "    \"totalPageCount\": 1,\n" +
            "    \"recordsPerPage\": 20,\n" +
            "    \"content\": [\n" +
            "        {\n" +
            "            \"userNickname\": \"신승욱카카오\",\n" +
            "            \"major1\": \"공학계열\",\n" +
            "            \"major2\": \"컴퓨터ㆍ통신\",\n" +
            "            \"follower\": 0,\n" +
            "            \"following\": 5,\n" +
            "            \"profileImageUrl\": \"http://test.jpg\",\n" +
            "            \"userSeq\": 6,\n" +
            "            \"followUp\": false,\n" +
            "            \"followStatus\": \"암것도아님\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"userNickname\": \"신승욱애플\",\n" +
            "            \"major1\": \"공학계열\",\n" +
            "            \"major2\": \"컴퓨터ㆍ통신\",\n" +
            "            \"follower\": 1,\n" +
            "            \"following\": 1,\n" +
            "            \"profileImageUrl\": null,\n" +
            "            \"userSeq\": 9,\n" +
            "            \"followUp\": false,\n" +
            "            \"followStatus\": \"암것도아님\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"totalRecordCount\": 2,\n" +
            "    \"currentPageNo\": 1\n" +
            "}";
    public static final String otherFollowings = "{\n" +
            "    \"firstPage\": true,\n" +
            "    \"lastPage\": true,\n" +
            "    \"totalPageCount\": 1,\n" +
            "    \"recordsPerPage\": 20,\n" +
            "    \"content\": [\n" +
            "        {\n" +
            "            \"userNickname\": \"신승욱네이버\",\n" +
            "            \"major1\": \"예체능계열\",\n" +
            "            \"major2\": \"무용ㆍ체육\",\n" +
            "            \"follower\": 2,\n" +
            "            \"following\": 0,\n" +
            "            \"profileImageUrl\": \"http://test.jpg\",\n" +
            "            \"userSeq\": 3,\n" +
            "            \"followUp\": false,\n" +
            "            \"followStatus\": \"한쪽팔로우\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"totalRecordCount\": 1,\n" +
            "    \"currentPageNo\": 1\n" +
            "}";
    public static final String notificationList = "{\n" +
            "    \"result\": [\n" +
            "        {\n" +
            "            \"content\": \"신승욱카카오님이 회원님을 팔로우했어요.\",\n" +
            "            \"userSeq\": 14,\n" +
            "            \"otherUserSeq\": 6,\n" +
            "            \"imageUrl\": null,\n" +
            "            \"notificationType\": \"팔로우\",\n" +
            "            \"createdDate\": \"2023-02-13 12:41:12\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"content\": \"맹씨님이 회원님을 팔로우했어요.\",\n" +
            "            \"userSeq\": 14,\n" +
            "            \"otherUserSeq\": 10,\n" +
            "            \"imageUrl\": \"https://www.naver.com\",\n" +
            "            \"notificationType\": \"팔로우\",\n" +
            "            \"createdDate\": \"2023-02-14 16:14:12\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    public static final String report = "{\n" +
            "    \"status\": \"SUCCESS\",\n" +
            "    \"message\": \"정상처리\",\n" +
            "    \"errorMessage\": null,\n" +
            "    \"errorCode\": null,\n" +
            "    \"result\": {\n" +
            "        \"result\": true\n" +
            "    }\n" +
            "}";
}
