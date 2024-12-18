package com.mproduits.MS_Products.Controller;

import com.mproduits.MS_Products.Configurations.ApplicationPropertiesConfiguration;
import com.mproduits.MS_Products.Dao.ProductDao;
import com.mproduits.MS_Products.Model.Product;
import com.mproduits.MS_Products.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
     ProductDao productDao;
    @Autowired
    ApplicationPropertiesConfiguration appProperties;
    @GetMapping(value = "/Produits")
    public List<Product> listDesProduits(){
        System.out.println("****** La liste des produits********* ");
        List<Product> list =productDao.findAll();
        System.out.println("********ProduitController Produit[1]:" +list.get(1) );

       // if(list.isEmpty())
         //   throw new ProductNotFoundException()("Aucun produit n est disponible");
        List<Product> listnumere= list.subList(0,appProperties.getLimitDeProduits());
        return  listnumere;

    }

}
