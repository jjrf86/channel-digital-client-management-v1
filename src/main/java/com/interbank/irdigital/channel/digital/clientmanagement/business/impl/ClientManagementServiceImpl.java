package com.interbank.irdigital.channel.digital.clientmanagement.business.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.interbank.irdigital.channel.digital.clientmanagement.business.ClientManagementService;
import com.interbank.irdigital.channel.digital.clientmanagement.dao.ClientManagementDao;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.CreateClienteRequest;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.KpiClienteResponse;
import com.interbank.irdigital.channel.digital.clientmanagement.model.api.domain.Cliente;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase del servicio que contiene los metodos necesarios para la gestion de
 * data y logica de negocio que consumira la clase REST
 * ClientManagementController.</br>
 * <b>Class</b>: ClientManagementServiceImpl</br>
 * 
 * @author Jesse James Rios Franco </br>
 *         <u>Desarrollado por</u>: </br>
 *         <ul>
 *         <li>Jesse James Rios Franco
 *         <li>
 *         </ul>
 * @version 1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class ClientManagementServiceImpl implements ClientManagementService {

	private ClientManagementDao clientManagementDao;

	@Override
	public Completable createCliente(CreateClienteRequest request) {
		log.info("Inicio createCliente");
		return Single.fromCallable(() -> convertRequestToDomain(request))
				.map(cliente -> clientManagementDao.save(cliente)).ignoreElement().subscribeOn(Schedulers.io());
	}

	/**
	 * Metodo encargado de convertir lo datos del request al objeto dominio.
	 * @param request
	 * @return Cliente
	 * @throws Exception
	 */
	private Cliente convertRequestToDomain(CreateClienteRequest request) throws Exception {
		log.info("Inicio convertRequestToDomain");
		try {
			return Cliente.builder().nombre(request.getNombre()).apellidoPaterno(request.getApellidoPaterno())
					.apellidoMaterno(request.getApellidoMaterno()).edad(request.getEdad())
					.fechaNacimiento(new SimpleDateFormat("dd/MM/yyyy").parse(request.getFechaNacimiento())).build();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Exception("Error al parsear la fecha de nacimiento");
		}
	}

	@Override
	public Single<KpiClienteResponse> getKpiClientes() {
		log.info("Inicio getKpiClientes");

		return Single.fromCallable(() -> clientManagementDao.findAll())
				.map(iterableCliente -> calculateKpiCliente(iterableCliente));

	}

	/**
	 * Metodo encargado de hacer el calculo del promedio de edades como la desviacion estandar.
	 * @param spliteratorCliente
	 * @return KpiClienteResponse
	 */
	private KpiClienteResponse calculateKpiCliente(Iterable<Cliente> spliteratorCliente) {
		log.info("Inicio calculateAverageYears");
		
		
		float media = (int)StreamSupport.stream(spliteratorCliente.spliterator(), true)
				.mapToInt(cliente -> cliente.getEdad()).average().orElse(Double.NaN);
		
		double varianza = 0.0;
		
		double desviacion = 0.0;
		
		int valor [] = StreamSupport.stream(spliteratorCliente.spliterator(), true)
				.mapToInt(cliente -> cliente.getEdad()).toArray();

		for(int i = 0 ; i < valor.length; i++){
			double rango;
			rango = Math.pow(valor[i] - media, 2f);
			varianza = varianza + rango;
		}
		
		varianza = varianza / valor.length;
		
		desviacion = Math.sqrt(varianza);
		
		return KpiClienteResponse.builder()
				.promedioEdad((int)media)
				.desviacionEdadCliente(Double.valueOf(DecimalFormat.getInstance().format(desviacion)))
				.build();
	}

	@Override
	public Observable<Cliente> getListClientes() {
		return Observable.fromIterable(clientManagementDao.findAll());
	}
}