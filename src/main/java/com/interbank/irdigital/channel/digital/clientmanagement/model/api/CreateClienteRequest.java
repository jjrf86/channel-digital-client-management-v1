package com.interbank.irdigital.channel.digital.clientmanagement.model.api;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@Schema(description = "Informaci&oacute;n del cliente.")
public class CreateClienteRequest implements Serializable {

	private static final long serialVersionUID = -5576334121753401101L;
	
	@NotBlank
	@Schema(description = "Nombres completos del cliente.",
			example = "Jesse James", type = "String", required = true)
	@Size(min = 2, max = 256)
	private String nombre;
	
	@NotBlank
	@Schema(description = "Apellido Paterno del cliente.",
			example = "Rios", type = "String", required = true)
	@Size(min = 2, max = 256)
	private String apellidoPaterno;
	
	@NotBlank
	@Schema(description = "Apellido Materno del cliente.",
			example = "Franco", type = "String", required = true)
	@Size(min = 2, max = 256)
	private String apellidoMaterno;
	
	@NotBlank
	@Schema(description = "Edad del cliente.",
			example = "35", type = "Integer", required = true)
	@Size(min = 2, max = 3)
	private Integer edad;
	
	@NotBlank
	@Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")
	@Schema(description = "Fecha de nacimiento del cliente.",
			example = "06/02/1986", type = "String", required = true)
	@Size(min = 10, max = 10)
	private String fechaNacimiento;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}