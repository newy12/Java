package com.ilgusi.favorite.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ilgusi.favorite.model.dao.FavoriteDao;

@Service
public class FavoriteService {
	@Autowired
	private FavoriteDao dao;
}
