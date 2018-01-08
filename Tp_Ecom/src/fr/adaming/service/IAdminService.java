package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Admin;
@Local
public interface IAdminService {

	public Admin isExist(Admin a) throws Exception;
}
