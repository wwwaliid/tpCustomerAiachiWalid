/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejb;

import jakarta.ejb.Stateless;
import java.util.List;
import entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class CustomerManager {
    
    public List<Customer> getAllCustomers(){
       Query query = em.createNamedQuery("Customer.findAll");
       return query.getResultList();
    }
        
    public Customer update(Customer customer){
      return em.merge(customer);  
    } 
    @PersistenceContext(unitName="customerPU")
    private EntityManager em;

    public void persist(Customer customer) {
      em.persist(customer);
    }
    public Customer getCustomer(int idCustomer) {  
        return em.find(Customer.class, idCustomer);  
    }
}
