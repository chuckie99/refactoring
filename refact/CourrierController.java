package com.gestioncourrier.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestioncourrier.models.Courrier;
import com.gestioncourrier.models.Courrier.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;
import com.gestioncourrier.models.Borderaux;
import com.gestioncourrier.models.Correspondant;
import com.gestioncourrier.services.CourrierService;

@Controller
public class CourrierController {
	
	@Autowired
	private CourrierService courrierservice;


	@GetMapping("/courrier")
	public String init1 (HttpServletRequest req ) {
		req.setAttribute("courrierview", courrierservice.findAllCourrier());
		req.setAttribute("mode", "COURRIER_VIEW");
		return "courrier";	
	}
	
	
	@GetMapping("/updatecourrier")
	public String init(@RequestParam int id, HttpServletRequest req) {
		req.setAttribute("courrierupdate", courrierservice.findOneCourrier(id));
		req.setAttribute("nature", courrierservice.findAllNatureCourrier());
		req.setAttribute("type", courrierservice.findAllTypeCourrier());
		req.setAttribute("categorie", courrierservice.findAllCategorieCourrier());
		req.setAttribute("borderaux", courrierservice.findAllBorderaux());
		req.setAttribute("correspondant", courrierservice.findAllCorrespondant());
		req.setAttribute("mode", "COURRIER_EDIT");
		return "courrier";
	}
	
	@GetMapping("/attribuerborderaux")
	public String bord(@RequestParam int id, HttpServletRequest req) {
		req.setAttribute("courrierupdate", courrierservice.findOneCourrier(id));
		req.setAttribute("nature", courrierservice.findAllNatureCourrier());
		req.setAttribute("type", courrierservice.findAllTypeCourrier());
		req.setAttribute("categorie", courrierservice.findAllCategorieCourrier());
		req.setAttribute("correspondant", courrierservice.findAllCorrespondant());
		req.setAttribute("mode", "BORDERAUX_EDIT");
		return "courrier";
	}
	

	@PostMapping("/savenewborderaux")
	public void saveborderaux(@ModelAttribute Borderaux borderaux,@ModelAttribute Courrier courrier, HttpServletRequest req, HttpServletResponse resp) {
		courrierservice.savecourrierborderaux(borderaux, courrier);
		System.out.println(borderaux);
		req.setAttribute("courrierview", courrierservice.findAllCourrier());
		req.setAttribute("mode", "COURRIER_VIEW");
		try {
			resp.sendRedirect("/courrier");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	binder.registerCustomEditor (Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), false));
	}
	
	@PostMapping("/savecourrierall")
	public void savecorrespondant(@ModelAttribute Courrier courrier,@ModelAttribute Correspondant cr, HttpServletRequest req, HttpServletResponse resp) {
		courrierservice.savecourrier(courrier,cr);
		req.setAttribute("courrierview", courrierservice.findAllCourrier());
		req.setAttribute("mode", "COURRIER_VIEW");
		try {
			resp.sendRedirect("/courrier");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/savecourrier")
	public void savecorrespondant(@ModelAttribute Courrier courrier, HttpServletRequest req, HttpServletResponse resp) {
		courrierservice.saveC(courrier);
		req.setAttribute("courrierview", courrierservice.findAllCourrier());
		req.setAttribute("mode", "COURRIER_VIEW");
		try {
			resp.sendRedirect("/courrier");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/newcourrier")
	public String newcourrier(HttpServletRequest req) {
		req.setAttribute("nature", courrierservice.findAllNatureCourrier());
		req.setAttribute("type", courrierservice.findAllTypeCourrier());
		req.setAttribute("categorie", courrierservice.findAllCategorieCourrier());
		req.setAttribute("borderaux", courrierservice.findAllBorderaux());
		req.setAttribute("correspondant", courrierservice.findAllCorrespondant());
		req.setAttribute("cort", courrierservice.findAllNatureCorr());
		req.setAttribute("corp", courrierservice.findAllTypeCorr());
		req.setAttribute("mode", "COURRIER_NEW");
		return "courrier";
	}
	
	@GetMapping("/deletecourrier")
	public void deletecorrespondant(@RequestParam int id, HttpServletRequest req, HttpServletResponse resp) {
		courrierservice.deletecourrier(id);
		req.setAttribute("courrierview", courrierservice.findAllCourrier());
		req.setAttribute("mode", "COURRIER_VIEW");
		try {
			resp.sendRedirect("/courrier");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}