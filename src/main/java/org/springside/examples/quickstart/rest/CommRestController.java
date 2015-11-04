/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.examples.quickstart.entity.InfoAction;
import org.springside.examples.quickstart.entity.InfoType;
import org.springside.examples.quickstart.entity.InfoTypeOne;
import org.springside.examples.quickstart.entity.InfoTypeTwo;
import org.springside.examples.quickstart.entity.Task;
import org.springside.examples.quickstart.service.info.InfoActionService;
import org.springside.examples.quickstart.service.info.InfoTypeOneService;
import org.springside.examples.quickstart.service.info.InfoTypeService;
import org.springside.examples.quickstart.service.info.InfoTypeTwoService;
import org.springside.examples.quickstart.service.task.TaskService;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

/**
 * Common的Restful API的Controller.
 * 
 * @author calvin
 */
@RestController
@RequestMapping(value = "/api/v1")
public class CommRestController {

	private static Logger logger = LoggerFactory.getLogger(CommRestController.class);

	@Autowired
	private TaskService taskService;
	@Autowired
	private InfoActionService infoActionService;
	@Autowired
	private InfoTypeService infoTypeService;
	@Autowired
	private InfoTypeOneService infoTypeOneService;
	@Autowired
	private InfoTypeTwoService infoTypeTwoService;

	@Autowired
	private Validator validator;

	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<Task> list() {
		return taskService.getAllTask();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Task get(@PathVariable("id") Long id) {
		Task task = taskService.getTask(id);
		if (task == null) {
			String message = "任务不存在(id:" + id + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return task;
	}
	
	@RequestMapping(value = "infoActionList", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<InfoAction> finaAllInfoAction() {
		return infoActionService.findAll();
	}
	
	@RequestMapping(value = "infoTypeList", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<InfoType> finaAllInfoType() {
		return infoTypeService.findAll();
	}
	
	@RequestMapping(value = "infoTypeOneList", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<InfoTypeOne> finaAllInfoTypeOne() {
		return infoTypeOneService.findAll();
	}
	
	@RequestMapping(value = "infoTypeTwoList", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<InfoTypeTwo> finaAllInfoTypeTwo() {
		return infoTypeTwoService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
	public ResponseEntity<?> create(@RequestBody Task task, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, task);

		// 保存任务
		taskService.saveTask(task);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		Long id = task.getId();
		URI uri = uriBuilder.path("/api/v1/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
	// 按Restful风格约定，返回204状态码, 无内容. 也可以返回200状态码.
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Task task) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, task);

		// 保存任务
		taskService.saveTask(task);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		taskService.deleteTask(id);
	}
}
