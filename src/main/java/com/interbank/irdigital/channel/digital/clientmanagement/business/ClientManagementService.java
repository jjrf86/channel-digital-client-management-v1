package com.interbank.irdigital.channel.digital.clientmanagement.business;

import com.interbank.irdigital.channel.digital.clientmanagement.model.api.CreateClienteRequest;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.KpiClienteResponse;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.domain.Cliente;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Inferfaz del servicio para la logica de negocio que consumira la clase REST
 * ClientManagementController.</br>
 * <b>Interface</b>: ClientManagementService</br>
 * @author Jesse James Rios Franco </br>
 * <u>Desarrollado por</u>: </br>
 * <ul>
 * <li>Jesse James Rios Franco<li>
 * </ul>
 * @version 1.0
 */
public interface ClientManagementService {

	public Completable createCliente(CreateClienteRequest request);

	public Single<KpiClienteResponse> getKpiClientes();

	public Observable<Cliente> getListClientes();

}