package tp.front.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tp.front.repository.*;
import tp.front.entity.*;


@Service
public class EmployeService implements IEmployeService {

    //private static final Logger L=LogManager.getLogger(EntrepriseService.class);


    @Autowired
    EmployeRepository em;


    @Override
    public int addOrUpdateEmploye(Employe employe) {
        em.save(employe);
        return employe.getId().intValue();
    }

    @Override
    public int ajouterEmploye(Employe employe) {
        em.save(employe);
        return employe.getId().intValue();
    }


    @Override
    public String getEmployePrenomById(int employeId) {
        Employe emn = em.findById((long) employeId).get();
        return emn.getPrenom();
    }

    @Override
    public long getNombreEmployeJPQL() {
        return em.count();
    }

    @Override
    public List<String> getAllEmployeNamesJPQL() {
        return em.getEmplNames();
    }

    @Override
    public List<Employe> getAllemploye() {
        return (List<Employe>) em.findAll();
    }

    @Override
    public Employe authenticate(String login, String password) {
        return em.getEmployeByEmailAndPassword(login, password);
    }

    @Override
    public List<Employe> getAllEmployes() {
        return (List<Employe>) em.findAll();
    }

    public void deleteEmployeById(int employeId) {
        em.deleteById((long) employeId);
    }


}
