package com.interbank.irdigital.channel.digital.clientmanagement.model.api.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Entity(name = "CLIENTE")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 6368531526997590892L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",nullable = false)
	private Integer id;
	
	@Column(name = "nombre",nullable = false, length = 250)
	private String nombre;
	
	@Column(name = "apellido_paterno",nullable = false, length = 250)
	private String apellidoPaterno;
	
	@Column(name = "apellido_materno",nullable = false, length = 250)
	private String apellidoMaterno;
	
	@Column(name = "edad",nullable = false,length = 3)
	private Integer edad;
	
	@Column(name = "fecha_nacimiento",nullable = false, length = 10)
	@Size(min = 10, max = 10)
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}