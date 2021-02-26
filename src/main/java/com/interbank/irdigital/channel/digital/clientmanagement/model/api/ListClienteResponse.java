package com.interbank.irdigital.channel.digital.clientmanagement.model.api;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Lista de Clientes.")
public class ListClienteResponse implements Serializable {

	private static final long serialVersionUID = -3461859522631099815L;
	
	@Schema(description = "Nombres completos del cliente.",
			example = "Jesse James", type = "String", required = true)
	private String nombre;
	
	@NotBlank
	@Schema(description = "Apellido Paterno del cliente.",
			example = "Rios", type = "String", required = true)
	private String apellidoPaterno;
	
	@NotBlank
	@Schema(description = "Apellido Materno del cliente.",
			example = "Franco", type = "String", required = true)
	private String apellidoMaterno;
	
	@NotBlank
	@Schema(description = "Edad del cliente.",
			example = "35", type = "Integer", required = true)
	private Integer edad;
	
	@NotBlank
	@Schema(description = "Fecha de nacimiento del cliente.", format = "dd/MM/yyyy",
			example = "06/02/1986", type = "Date", required = true)
	private Date fechaNacimiento;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}