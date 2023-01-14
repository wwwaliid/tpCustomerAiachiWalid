package jsf; // A MODIFIER...

import entities.Customer;
import java.io.Serializable;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import ejb.CustomerManager;
import ejb.DiscountCodeManager;
import entities.DiscountCode;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import java.util.List;


@Named
@ViewScoped
public class CustomerDetailsBean implements Serializable {
  private int idCustomer;
  private Customer customer;

  @EJB
  private CustomerManager customerManager;
  @EJB
  private DiscountCodeManager discountCodeManager;

  public int getIdCustomer() {
    return idCustomer;
  }

  public void setIdCustomer(int idCustomer) {
    this.idCustomer = idCustomer;
  }

    public Customer getDetails() {
      return customer;
    }

  public String update() {
    // Modifie la base de données.
    // Il faut affecter à customer (sera expliqué dans le cours).
    customer = customerManager.update(customer);
    return "customerList";
  }

  public void loadCustomer() {
    this.customer = customerManager.getCustomer(idCustomer);
  }
  public List<DiscountCode> getDiscountCodes() {
    return discountCodeManager.getAllDiscountCodes();
  }
  
  public Converter<DiscountCode> getDiscountCodeConverter() {
    return new Converter<DiscountCode>() {
      /**
       * Convertit une String en DiscountCode.
       *
       * @param value valeur à convertir
       */
      @Override
      public DiscountCode getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        return discountCodeManager.findById(value);
      }

      /**
       * Convertit un DiscountCode en String.
       *
       * @param value valeur à convertir
       */
      @Override
      public String getAsString(FacesContext context, UIComponent component, DiscountCode value) {
        if (value == null) return "";
        return value.getDiscountCode();
      }
    };
  }
}