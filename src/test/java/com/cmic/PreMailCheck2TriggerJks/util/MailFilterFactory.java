package com.cmic.PreMailCheck2TriggerJks.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SentDateTerm;
import javax.mail.search.SubjectTerm;

public class MailFilterFactory {

	public static class Builder {
		private String subject;// 主题
		private Calendar timeAgo;// 日期之前
		private String sender;// 发送者
		private String bodyContainString;// 内容

		public Builder setSubject(String subject) {
			this.subject = subject;
			return this;
		}

		public Builder setSender(String sender) {
			this.sender = sender;
			return this;
		}

		public Builder setTargetStringInBody(String bodyContainString) {
			this.bodyContainString = bodyContainString;
			return this;
		}

		public Builder setTimeRange(Calendar timeAgo) {
			this.timeAgo = timeAgo;
			return this;
		}

		public SearchTerm build() {
			List<SearchTerm> tempList = new ArrayList<SearchTerm>();
			if (isNoEmpty(subject)) {
				tempList.add(new SubjectTerm(subject));
			}
			if (isNoEmpty(sender)) {
				tempList.add(new FromStringTerm(sender));
			}
			if (timeAgo != null) {
				Date mondayDate = timeAgo.getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("hh时mm分ss秒");
				System.out.println(sdf.format(mondayDate));
				SearchTerm comparisonTermGe = new SentDateTerm(ComparisonTerm.GE, mondayDate);
				//SearchTerm comparisonTermLe = new SentDateTerm(ComparisonTerm., new Date());
				//SearchTerm comparisonAndTerm = new AndTerm(comparisonTermGe, comparisonTermLe);
				tempList.add(comparisonTermGe);
			}
			if (isNoEmpty(bodyContainString)) {
				tempList.add(new SubjectTerm(subject));
			}
			SearchTerm[] temp = new SearchTerm[tempList.size()];
			tempList.toArray(temp);
			return new AndTerm(temp);
		}

		public boolean isNoEmpty(String target) {
			return target != null && !target.isEmpty();
		}
	}
}
