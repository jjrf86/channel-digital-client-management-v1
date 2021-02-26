package com.interbank.irdigital.channel.digital.clientmanagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.interbank.irdigital.channel.digital.clientmanagement.model.api.domain.Cliente;

/**
 * Inferfaz ClientManagementDao, abstrae la capa de acceso a datos usando el
 * patron de dise&ntilde;o dao.</br>
 * <b>Interface</b>: ClientManagementDao</br>
 * @author Jesse James Rios Franco </br>
 * <u>Desarrollado por</u>: </br>
 * <ul>
 * <li>Jesse James Rios Franco<li>
 * </ul>
 * @version 1.0
 */
@Repository
public interface ClientManagementDao extends CrudRepository<Cliente, Integer>{

}