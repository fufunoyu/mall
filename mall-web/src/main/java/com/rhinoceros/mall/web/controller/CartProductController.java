package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.pojo.CartProduct;
import com.rhinoceros.mall.core.pojo.Product;
import com.rhinoceros.mall.service.service.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartProductController {
    @Autowired
    private CartProductService cartProductService;

    @RequestMapping("/cart/list")
    public String cart( Model model,HttpSession session){
        System.out.println("ttttttttttttt"+session.getAttribute("userId"));
        List<CartProduct> cartProduct = cartProductService.findByUserId((Long)session.getAttribute("userId"));
        for(int i=0;i<cartProduct.size();i++){
            Long tempID=cartProduct.get(i).getProductId();
            List<Product> product = cartProductService.findByProductId(tempID);
            model.addAttribute("product",product);
        }

        model.addAttribute("cartProduct",cartProduct);

        return "cart";
    }
}
