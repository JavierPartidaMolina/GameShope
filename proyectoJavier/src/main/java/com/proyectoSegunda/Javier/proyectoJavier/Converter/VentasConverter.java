package com.proyectoSegunda.Javier.proyectoJavier.Converter;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.proyectoSegunda.Javier.proyectoJavier.Entity.Ventas;
import com.proyectoSegunda.Javier.proyectoJavier.Model.ventasModel;

@Component("VentasConverter")
public class VentasConverter {
	
	DozerBeanMapper mapper = new DozerBeanMapper();
	
	public ventasModel entity2model(Ventas venta) {
		return mapper.map(venta,ventasModel.class);
	}
	public Ventas model2entity(ventasModel ventamodel) {
		return mapper.map(ventamodel, Ventas.class);
	}
}
