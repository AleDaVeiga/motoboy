package com.wgsistemas.motoboy.controller.admin;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import com.wgsistemas.motoboy.controller.dominio.ReportDeliveryByCustomerForm;
import com.wgsistemas.motoboy.service.DeliveryService;
import com.wgsistemas.motoboy.util.DateUtil;

@Controller
@RequestMapping(value = "/admin/report/deliveries")
public class AdminReportDeliveryByCustomerController {
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping(value = "/customers")
	public String report(Model model) {
		ReportDeliveryByCustomerForm reportDeliveryByCustomerForm = new ReportDeliveryByCustomerForm();
		ZonedDateTime today = DateUtil.newZonedDateTime();
		reportDeliveryByCustomerForm.setStartDeliveryAt(DateUtil.newDateFrom(today.withDayOfMonth(1)));
		reportDeliveryByCustomerForm.setEndDeliveryAt(DateUtil.newDateFrom(today.withDayOfMonth(1).plusMonths(1).minusDays(1)));
		model.addAttribute("reportDeliveryByCustomerForm", reportDeliveryByCustomerForm);
		return "admin/report/delivery/deliveriesByCustomer";
	}

	@PostMapping(value = "/customers")
	public ModelAndView filterReport(@ModelAttribute("reportDeliveryByCustomerForm") ReportDeliveryByCustomerForm reportDeliveryByCustomerForm, Model model) {
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:report/admin/deliveryByCustomer.jrxml");
		view.setApplicationContext(applicationContext);

		Map<String, Object> params = new HashMap<>();
		params.put("datasource", deliveryService.findByOwnerAndDeliveryAtOrderByCustomer_FullNameAsc(SecurityContextHolder.getContext().getAuthentication().getName(), reportDeliveryByCustomerForm));
		params.put("format", "pdf");

		return new ModelAndView(view, params);
	}
}