/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PartenaireDao;
import dao.ProfilDao;
import dao.UserDao;
import java.util.List;
import java.util.stream.Collectors;
import models.Compte;
import models.Partenaire;
import models.Profil;
import models.User;

/**
 *
 * @author hp
 */
public class PartenaireService {
     PartenaireDao daopartenaire =new PartenaireDao();
    PartenaireDao daouser =new PartenaireDao();
    public boolean creerPartenaire(Partenaire partenaire){
        
        return daopartenaire.create(partenaire)==0?false:true;
    }
    
    public List<Partenaire> listerPartenaire(){
        return daopartenaire.findAll();
    }
    
    public boolean modifierPartenaire(Partenaire p){
        return daopartenaire.update(p)==0?false:true;
    }
    public boolean creerCompte(Compte c){
        Partenaire p=daopartenaire.findById("id");
        c.setPartenaire(p);
          return daouser.create(p)==0?false:true;
    }
    
    public List<User> listerComptes(){

     return  daouser.findAll().stream()
                        .filter(partenaire-> partenaire.getMescomptes().getlibelle().compareToIgnoreCase("compte")==0)
                        .collect(Collectors.toList()); 
    }
   
   
    
}
