package it.partec.webappspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.partec.webappspringsecurity.model.Class;

public interface ClassRepository extends JpaRepository<Class, Long>{
	
	public Class findByName(String name);
}
