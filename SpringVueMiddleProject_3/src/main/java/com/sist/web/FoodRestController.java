package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sist.service.FoodService;

@RestController
public class FoodRestController {
@Autowired
private FoodService service;
}
