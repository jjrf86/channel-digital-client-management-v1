package com.interbank.irdigital.channel.digital.expose.web;

import javax.validation.Valid;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.interbank.irdigital.channel.digital.clientmanagement.business.ClientManagementService;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.CreateClienteRequest;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.KpiClienteResponse;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.ListClienteResponse;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.domain.Cliente;
import com.interbank.irdigital.channel.digital.clientmanagement.util.swagger.SwaggerCreateOperation;
import com.interbank.irdigital.channel.digital.clientmanagement.util.swagger.SwaggerGetOperation;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador principal que expone el servicio a trav&eacute;s de HTTP/Rest para
 * las operaciones del recurso.</br>
 * <b>Class</b>: ClientManagementController</br>
 * @author Jesse James Rios Franco </br>
 * <u>Desarrollado por</u>: </br>
 * <ul>
 * <li>Jesse James Rios Franco<li>
 * </ul>
 * @version 1.0
 */
@Slf4j
@Validated
@RefreshScope
@AllArgsConstructor
@Tag(name = "Client Management", description = "Client Management Controller")
@RestController
@RequestMapping("/channel/digital/v1/client-management")
public class ClientManagementController {
	
	private ClientManagementService clientManagementService;
	
	@Validated
	@ResponseStatus(code = HttpStatus.CREATED)
	@SwaggerCreateOperation
	@Operation(
			tags = "Registra un cliente",
			summary = "Registra un cliente",
			method = "POST", description = "classpath:/swagger/notes/save-client.md")
	@PostMapping(path = "/creacliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public Completable createClient(@Valid 
			@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(
					schema = @Schema(implementation = CreateClienteRequest.class),
					mediaType = MediaType.APPLICATION_JSON_VALUE))
			@RequestBody CreateClienteRequest request) {
		log.info("Inicio createClient");
		log.debug("CreateClienteRequest: "+request);
		return clientManagementService.createCliente(request);
	}
	
	@SwaggerGetOperation
	@Operation(
			tags = "Obtiene los KPIs de los cliente.",
			summary = "Obtiene los KPIs de los cliente.",
			method = "GET", description = "classpath:/swagger/notes/kpi-client.md")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Se obtuvieron los datos correctamente.",
				content = @Content(schema = @Schema(implementation = KpiClienteResponse.class)))
	})
	@GetMapping(value = "/kpideclientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<KpiClienteResponse> kpiCliente(){
		log.info("Inicio kpiCliente");
		return clientManagementService.getKpiClientes();
	}
	
	@SwaggerGetOperation
	@Operation(
			tags = "Obtiene la lista de los cliente.",
			summary = "Obtiene la lista de los cliente.",
			method = "GET", description = "classpath:/swagger/notes/list-client.md")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Se obtuvieron los datos correctamente.",
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = ListClienteResponse.class))))
	})
	@GetMapping(value = "/listclientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public Observable<ListClienteResponse> listClientes(){
		log.info("Inicio listClientes");
		return clientManagementService.getListClientes()
				.map(this::convertClienteToResponse);
	}
	
	private ListClienteResponse convertClienteToResponse(Cliente cliente) {
		return ListClienteResponse.builder()
				.nombre(cliente.getNombre())
				.apellidoPaterno(cliente.getApellidoPaterno())
				.apellidoMaterno(cliente.getApellidoMaterno())
				.edad(cliente.getEdad())
				.fechaNacimiento(cliente.getFechaNacimiento())
				.build();
	}
}