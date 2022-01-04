package com.ampcarsoft.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ampcarsoft.entity.Coche;
import com.ampcarsoft.repository.CocheRepository;

@Controller
public class CochesController {

	@Autowired
	private CocheRepository dao;

	@RequestMapping("/wcoches")
	public ModelAndView mostrarPagina(@ModelAttribute(value = "coche") Coche coche) {
		return new ModelAndView("coches/cochesListado");
	}

	@RequestMapping("/wcochesFormulario")
	public ModelAndView mostrarPagina2(@ModelAttribute(value = "coche") Coche coche) {
		return new ModelAndView("coches/cochesFormulario");
	}

	@RequestMapping("/insertarCoche")
	public String insertar(@ModelAttribute(value = "coche") Coche coche) {
		dao.save(coche);
		return "redirect:/wcoches";
	}

	@RequestMapping("/modificarCoche")
	public String modificar(@ModelAttribute(value = "coche") Coche coche) {
		dao.save(coche);
		return "redirect:/wcoches";
	}

	@RequestMapping("/borrarCoche")
	public String borrar(@ModelAttribute(value = "coche") Coche coche,
			@RequestParam(value = "matricula") String matricula, HttpServletRequest request) {

		dao.deleteById(matricula);
		return "redirect:/wcoches";
	}

	@RequestMapping("/seleccionarCoche")
	public ModelAndView seleccionar(@ModelAttribute(value = "coche") Coche coche,
			@RequestParam(value = "matricula") String matricula, HttpServletRequest request) {

		String idCocheStr = request.getParameter("matricula");

		Optional<Coche> c = dao.findById(matricula);
		coche.setMatricula(c.get().getMatricula());
		coche.setMarca(c.get().getMarca());
		coche.setModelo(c.get().getModelo());
		coche.setNumPuertas(c.get().getNumPuertas());

		return new ModelAndView("coches/cochesFormulario");
	}

	@ModelAttribute(value = "listadoCoches")
	public List<Coche> getListadoCoches() {
		return (List<Coche>) dao.findAll();
	}

}
