/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Compte;
import models.Partenaire;


/**
 *
 * @author hp
 */
public class PartenaireDao implements ISystem<Partenaire>{

        private DaoMysql dao;
   private final String SQL_INSERT="INSERT INTO `partenaire` ('id_user',`email`, `adresse`,'telephone') VALUES (NULL, ?,?,?)";
   private final  String SQL_UPDATE="UPDATE `partenaire` SET 'adresse','telephone' = ?,?,? WHERE `partenaire`.`id_user` =?";         
   private final String SQL_SELECT_ONE="select * from partenaire where id_user=?";
   private final String SQL_SELECT_ALL="select * from partenaire";   
   
  
   public PartenaireDao() {
        dao=  DaoMysql.getInstance();
    }
    @Override
    public int create(Partenaire obj) {
       
        int result=0;
      
        try {
            dao.initPS(SQL_INSERT);
            dao.getPstm().setInt(1,obj.getId() );
            dao.getPstm().setString(2,obj.getEmail() );
            dao.getPstm().setString(3, obj.getAdresse());
            dao.getPstm().setString(4, obj.getTelephone());
            result=dao.executeMaj();
            
            dao.CloseConnection();
             
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return result;
       
   
    }

    @Override
    public int update(Partenaire obj) {
        int result=0;
            try {
        
                /* Execution requete */
                  //PreparedStatement ps=connexion.prepareStatement(sql);
                  dao.initPS(SQL_UPDATE);
                  //ps.setString(1, obj.getLibelle());
                  dao.getPstm().setString(1, obj.getEmail());
                  dao.getPstm().setString(2, obj.getAdresse());
                  dao.getPstm().setString(3, obj.getTelephone());
                  result=dao.executeMaj();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result; 
    }

    @Override
    public Partenaire findById(int id) {
        Partenaire result=null;
            try {
                result=new Partenaire();
                
                dao.initPS(SQL_SELECT_ONE);
                /* Execution requete */
                dao.getPstm().setInt(1, id);
                 //ORM table vers Objet
                  ResultSet rs=dao.executeSelect();
                  if(rs.first()){  
                     result.setEmail(rs.getString("email"));
                     result.setAdresse(rs.getString("adresse"));
                     result.setTelephone(rs.getString("telephone"));                     
                  }                  
                  dao.CloseConnection();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }

    @Override
    public List<Partenaire> findAll() {
       List<Partenaire> result=null;
       
            try {
                result=new ArrayList<>();
                
                
                dao.initPS(SQL_SELECT_ALL);
                
                  ResultSet rs=dao.executeSelect();
                  while(rs.next()){
                      Partenaire p=new Partenaire();
                      p.setEmail(rs.getString("email"));
                      p.setAdresse(rs.getString("Adresse"));
                     result.add(p);
                  }
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }

   
    
}
