package tp.front.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tp.front.entity.Employe;
import tp.front.entity.Role;
import tp.front.service.EmployeService;

@Scope(value = "session")
@Controller(value = "EmployeController")
@ELBeanName(value = "EmployeController")
@Join(path = "/", to = "/login.jsf")
public class EmployeControllerImpl {
    private static final Logger L = LogManager.getLogger(EmployeControllerImpl.class);


    private String login;
    private String password;
    private Employe employe;
    private String prenom;
    private String nom;
    private String email;
    private boolean actif;
    private List<Employe> employes;
    private Integer employeIdToBeUpdated; // Ajouter getter et setter
    private Employe authenticatedUser;

    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean loggedIn;

    @Autowired
    EmployeService employeService;


    public Role[] getRoles() {
        return Role.values();
    }


    public String doLogin() {

        String navigateTo = "null";


        authenticatedUser = employeService.authenticate(login, password);

        if (authenticatedUser != null && authenticatedUser.getRole() ==
                Role.ADMINISTRATEUR) {
            navigateTo = "/admin/welcome.xhtml?faces-redirect=true";

            loggedIn = true;
        } else if (authenticatedUser != null) {
            navigateTo = "/client/welcome.xhtml?faces-redirect=true";

            loggedIn = true;
        } else {

            FacesMessage facesMessage =

                    new FacesMessage("Login Failed: please check your username/password and try again.");

            FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);

        }

        return navigateTo;

    }

    public String doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";

    }

    /*****crud*****/
    public String registerEmploye() {
        Employe emp = new Employe(prenom, nom, email, actif, role, password);
        L.info("zzazaezaeazzaa" + emp);
        L.info("zzazaezaeazzaa" + employeService.ajouterEmploye(emp));


        return "/login.xhtml?faces-redirect=true";

    }

    public String addEmploye() {
        Employe emp = new Employe(prenom, nom, email, actif, role);
        L.info("zzazaezaeazzaa" + emp);
        L.info("zzazaezaeazzaa" + employeService.ajouterEmploye(emp));

        return "/welcome.xhtml?faces-redirect=true";

    }

    public String goToSignup() {
        return "/admin/register.xhtml?faces-redirect=true";
    }

    public String goToSignin() {
        return "/login.xhtml?faces-redirect=true";
    }

    public void test() {
        Employe emp = new Employe(nom, prenom, email, actif, null);

        L.info("zzazaezaeazzaa" + emp);

    }

    public List<Employe> getEmployes() {
        employes = employeService.getAllEmployes();
        return employes;
    }

    public String removeEmploye(int employeId) {
        System.out.println(employeId);
        employeService.deleteEmployeById(employeId);
        return "/welcome.xhtml?faces-redirect=true";
    }

    public String doDisableLogout(int employeId) {
        employeService.deleteEmployeById(employeId);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }


    public void displayEmploye(Employe empl) {
        this.setPrenom(empl.getPrenom());
        this.setNom(empl.getNom());
        this.setActif(empl.getIsActif());
        this.setEmail(empl.getEmail());
        this.setRole(empl.getRole());
        this.setPassword(empl.getPassword());
        this.setEmployeIdToBeUpdated(empl.getId().intValue());
    }

    public void updateEmploye(Employe employe) {
        System.out.println(employe.getId());
        System.out.println(employe.getEmail());

        employeService.ajouterEmploye(employe);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Integer getEmployeIdToBeUpdated() {
        return employeIdToBeUpdated;
    }

    public void setEmployeIdToBeUpdated(Integer employeIdToBeUpdated) {
        this.employeIdToBeUpdated = employeIdToBeUpdated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public Employe getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(Employe authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    public static Logger getL() {
        return L;
    }


}
