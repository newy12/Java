package com.ilgusi.favorite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ilgusi.favorite.model.service.FavoriteService;

@Controller
public class FavoriteController {
	@Autowired
	private FavoriteService service;
}
