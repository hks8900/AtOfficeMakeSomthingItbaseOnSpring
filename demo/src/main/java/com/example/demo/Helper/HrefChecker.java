package com.example.demo.Helper;

public class HrefChecker {
	public static String BoardSelector(String href) {
		if (href.contains("boardId=BBS_0000257")) {
			return "NoticeBoard";
		} else if (href.contains("boardId=BBS_0000505")) {
			return "EventSchedule";
		}  else {
//			System.out.println("베너 게시글 아님" + href);
			return null;
		}

	}

}
