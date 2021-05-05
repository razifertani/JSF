package tp.front.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tp.front.entity.*;

public interface EmployeRepository extends CrudRepository<Employe, Long> {
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO t_employe_departement (employes_id,departement_id) VALUES (:en,:dp)", nativeQuery = true)
	void AffectEmplDep(@Param("en") Integer en, @Param("dp") Integer dp);

	@Transactional
	@Modifying
	@Query(value = "Update  contrat c set c.employe_contrat_id = :em where c.reference = :con", nativeQuery = true)
	void AffectContEmp(@Param("em") Integer em, @Param("con") Integer con);

	@Query("SELECT count(e) FROM Employe e")
	long countAllEmp();
	
	@Query("SELECT nom FROM Employe")
	List<String> getEmplNames();
	
	@Query("SELECT e FROM Employe e WHERE e.email=:email and e.password=:password")

	public Employe getEmployeByEmailAndPassword(@Param("email")String login,
	@Param("password")String password);
	
}
