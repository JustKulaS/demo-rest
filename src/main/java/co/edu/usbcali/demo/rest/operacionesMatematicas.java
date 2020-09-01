package co.edu.usbcali.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operaciones/")
public class operacionesMatematicas {
	@GetMapping("sumar/{n1}/{n2}")
	public resultado sumar(@PathVariable("n1") Integer numeroUno, @PathVariable("n2") Integer numeroDos) {
		return new resultado(numeroUno + numeroDos);
	}
	@GetMapping("restar/{n1}/{n2}")
	public resultado restar(@PathVariable("n1") Integer numeroUno, @PathVariable("n2") Integer numeroDos) {
		return new resultado(numeroUno - numeroDos);
	}
}
