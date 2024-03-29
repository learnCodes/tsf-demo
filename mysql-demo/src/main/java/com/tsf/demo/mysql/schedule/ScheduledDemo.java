package com.tsf.demo.mysql.schedule;

import com.tsf.demo.mysql.dao.TsfCountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.tsf.demo.mysql.dao.TsfUserDao;

@Service
public class ScheduledDemo {
	private static final Logger LOG = LoggerFactory.getLogger(ScheduledDemo.class);

	private static int index = 1;

	@Autowired
	private TsfUserDao userDao;

	@Autowired
	private TsfCountDao countDao;

	@Scheduled(fixedDelayString = "${consumer.auto.test.interval:10000}")
	public void doWork() throws InterruptedException, Exception {
		String username = "test-user-" + index;
		LOG.info("mysql-demo auto test, create user: [" + username + "]");

		LOG.info("mysql-demo auto test, save user into mysql: [" + username + "]");
		userDao.create(username);

		Thread.sleep(3000);

		String token = userDao.query(username);
		LOG.info("mysql-demo auto test, query user token from mysql: [" + token + "]");

		Thread.sleep(3000);

		countDao.insert();
		LOG.info("mysql-demo auto test, insert count into mysql");

		index++;
		if (index > 9) index = 1;
	}
}