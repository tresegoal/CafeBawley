package cm.rst.service;

import java.util.List;

/**
 * @author Fabrice
 * Service générique pemettant d'effectuer les opérations de CRUD
 * sur les différentes entités de l'application
 * @param <T> l'entité sur laquelle on souhaiterais effectuer le CRUD
 * @param <ID> L'identifiant de l'entité
 */
public interface ICrudService<T, ID> {

	/**
	 * @return tous les élements de l'entité
	 */
	List<T> getAll();
	
	/**
	 * @param entity prend en paramètre l'entité à créer
	 * @return Aucun retour n'est attendu
	 */
	T add(T entity);
	
	/**
	 * @param entity prend en paramètre l'entité à mettre à jour
	 * @return Aucun retour n'est attendu
	 */
	T update(T entity);
	
	/**
	 * @param id prend en paramètre l'identifiant de l'entité à supprimer
	 * @return Aucun retour n'est attendu
	 */
	void delete(ID id);
	
	/**
	 * @param id identifiant de l'entité
	 * @return retourne l'élement dont l'ID est passé en paramètre
	 */
	T findE(ID id);
}
