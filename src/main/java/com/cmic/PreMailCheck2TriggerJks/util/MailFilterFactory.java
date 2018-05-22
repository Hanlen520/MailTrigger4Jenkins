package com.cmic.PreMailCheck2TriggerJks.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import com.cmic.PreMailCheck2TriggerJks.Tips;

public class MailFilterFactory {

	@Tips(description = "由于sentDateTerm在实际使用中出现了过滤失败的问题，目前使用此方法处理")
	public static Date mDateRange;

	public static class Builder {
		private String subject;// 主题
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

		/**
		 * 当前到目标时间间隔这段时间段
		 * 
		 * @param timeAgo
		 * @return
		 */
		public Builder setTimeRange2Now(Calendar timeAgo) {
			mDateRange = timeAgo.getTime();
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
			if (isNoEmpty(bodyContainString)) {
				tempList.add(new BodyTerm(bodyContainString));
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
