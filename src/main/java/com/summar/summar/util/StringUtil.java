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

}
