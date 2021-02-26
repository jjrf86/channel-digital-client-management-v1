package com.interbank.irdigital.channel.digital.clientmanagement.util.swagger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.interbank.irdigital.channel.digital.exception.ApiException;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Dry para endpoints de consulta swagger en el API.</br>
 * <b>Class</b>: SwaggerGetOperation</br>
 * @author Jesse James Rios Franco </br>
 * <u>Desarrollado por</u>: </br>
 * <ul>
 * <li>Jesse James Rios Franco<li>
 * </ul>
 * @version 1.0
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
	@ApiResponse(responseCode = "400", description = "Los datos proporcionados por el cliente no son validos.",
			content = @Content(schema = @Schema(implementation = ApiException.class))),
	@ApiResponse(responseCode = "404", description = "El cliente no esta registrado en la base de datos.",
			content = @Content(schema = @Schema(implementation = ApiException.class))),
	@ApiResponse(responseCode = "500", description = "Error al obtener el recurso.",
			content = @Content(schema = @Schema(implementation = ApiException.class)))
})
public @interface SwaggerGetOperation {

}
