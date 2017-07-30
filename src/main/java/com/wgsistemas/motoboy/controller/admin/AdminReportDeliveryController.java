package com.wgsistemas.motoboy.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.wgsistemas.motoboy.service.DeliveryService;

@Controller
@RequestMapping(value = "/admin/report")
public class AdminReportDeliveryController {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private DeliveryService deliveryService;

	@GetMapping(value = "/deliveries")
	public ModelAndView create(Model model) {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:admin/report/deliveries.jrxml");
		view.setApplicationContext(applicationContext);

		Map<String, Object> params = new HashMap<>();
		params.put("datasource", deliveryService.findAll());
		params.put("format", "pdf");

		return new ModelAndView(view, params);
	}
}