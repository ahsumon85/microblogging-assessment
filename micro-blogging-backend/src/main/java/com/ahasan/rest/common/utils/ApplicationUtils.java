/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahasan.rest.common.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ahasan.rest.daoimpl.AuthUserDetail;
import com.ahasan.rest.entity.User;
import com.ahasan.rest.repo.UserRepository;

/**
 *
 * @author Ahasan Habib
 */
public class ApplicationUtils {

	public static Boolean isPasswordValid(String databasedPass, String givenPassword) {
		if (BCrypt.checkpw(databasedPass, givenPassword)) {
			return true;
		} else {
			return false;
		}
	}

	public static String convertDateToLocalDateTime(Date date) {
		try {
			/* output is 17-02-2019 05:33 PM */
			return new SimpleDateFormat("dd-MM-yyyy hh:mm aaa").format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String provideEncodePassword(String plainPassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
		return "{bcrypt}" + bCryptPasswordEncoder.encode(plainPassword);
	}

	public static String provideCurrentUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static String convertCamelcaseString(String text) {
		char ch[] = text.toCharArray();
		for (int i = 0; i < text.length(); i++) {

			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				if (ch[i] >= 'a' && ch[i] <= 'z') {

					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			} else if (ch[i] >= 'A' && ch[i] <= 'Z')

				ch[i] = (char) (ch[i] + 'a' - 'A');
		}

		String st = new String(ch);
		return st;
	}

}
