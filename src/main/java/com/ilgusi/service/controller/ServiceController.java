package com.ilgusi.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ilgusi.service.model.service.ServiceService;

@Controller
public class ServiceController {
	@Autowired
	private ServiceService service;
}
