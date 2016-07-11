package br.gov.mg.fazenda.projetar.entity;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

public interface IKeyableAuditableEntity<T,K> extends IKeyableEntity<K> , IAuditableEntity<T> {

}
