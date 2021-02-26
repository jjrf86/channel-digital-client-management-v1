package com.interbank.irdigital.channel.digital.clientmanagement.model.api;

import java.io.Serializable;

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
@Schema(description = "Kpi de los clientes.")
public class KpiClienteResponse implements Serializable{

	private static final long serialVersionUID = -2303655693594253257L;
	
	@Schema(description = "Promedio de edad de los clientes.",
			example = "40", type = "Integer", required = true)
	private Integer promedioEdad;
	
	@Schema(description = "Desviacion estandar del total de clientes.",
			example = "3.15", type = "Double", required = true)
	private Double desviacionEdadCliente;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
