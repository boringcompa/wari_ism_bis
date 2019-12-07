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
import models.Partenaire;
import models.Compte;


        
/**
 *
 * @author thiernosadoudiallo
 */
public class CompteDao implements ISystem<Compte>{
    private DaoMysql dao;
   private final String SQL_INSERT="INSERT INTO `compte` (`id`, `numero`,'solde','partenaire') VALUES (NULL, ?)";
   private final  String SQL_UPDATE="UPDATE `compte` SET `id` = ? WHERE `compte`.`id` =?";         
   private final String SQL_SELECT_ONE="select * from compte where id=?";
   private final String SQL_SELECT_ALL="select * from compte";
   private final String SQL_SELECT_LIBELLE="select * from compte where id=?";
    public CompteDao() {
        dao=  DaoMysql.getInstance();
    }
   
    @Override
    public int create(Compte obj) {
         int result=0;
      
        try {
            dao.initPS(SQL_INSERT);
            dao.getPstm().setInt(1,obj.getId());
            dao.getPstm().setString(3,obj.getNumero());
            dao.getPstm().setDouble(4, obj.getSolde());
            
            
            
            result=dao.executeMaj();
            
            dao.CloseConnection();
             
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return result;
    }

    @Override
    public int update(Compte obj) {
          int result=0;
            try {
        
                /* Execution requete */
                  //PreparedStatement ps=connexion.prepareStatement(sql);
                  dao.initPS(SQL_UPDATE);
                  //ps.setString(1, obj.getLibelle());
                  dao.getPstm().setInt(1,obj.getId());
                  dao.getPstm().setString(3,obj.getNumero());
                  dao.getPstm().setDouble(1,obj.getSolde());
                  result=dao.executeMaj();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result; 
    }

    @Override
    public Compte findById(int id) {
         Compte result=null;
            try {
                result=new Compte();
                
                dao.initPS(SQL_SELECT_ONE);
                /* Execution requete */
                dao.getPstm().setInt(1, id);
                 //ORM table vers Objet
                  ResultSet rs=dao.executeSelect();
                  if(rs.first()){  
                     result.setId(rs.getInt("id"));
                     result.setNumero(rs.getString("numero"));
                     result.setSolde(rs.getDouble("SSSolde"));                     
                  }                  
                  dao.CloseConnection();
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    
    }

    @Override
    public List<Compte> findAll() {
       List<Compte> result=null;
       
            try {
                result=new ArrayList<>();
                
                
                dao.initPS(SQL_SELECT_ALL);
                
                  ResultSet rs=dao.executeSelect();
                  while(rs.next()){
                      Compte c=new Compte();
                      c.setId(rs.getInt("id"));
                      c.setNumero(rs.getString("numero"));
                      c.setSolde(rs.getDouble("solde"));
                     result.add(c);
                  }
            } catch (SQLException ex) { 
             System.out.println("Erreur Connexion à la BD");
        }

         return result;
    }
    

    
    
}
