package com.rhinoceros.mall.web.controller;

import com.rhinoceros.mall.core.constant.web.ConstantValue;
import com.rhinoceros.mall.core.po.CartProduct;
import com.rhinoceros.mall.core.po.Product;
import com.rhinoceros.mall.core.po.User;
import com.rhinoceros.mall.core.vo.ProductVo;
import com.rhinoceros.mall.service.service.CartProductService;
import com.rhinoceros.mall.service.service.ProductService;
import com.rhinoceros.mall.web.support.web.annotation.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
public class CartProductController {
    @Autowired
    private CartProductService cartProductService;
    @Autowired
    private ProductService productService;


    /**
     * 浏览购物车商品信息
     */
    @Authentication
    @RequestMapping("/cart/list")
    public String cart(Model model, HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        List<CartProduct> cartProducts = cartProductService.findByUserId(user.getId());
        List<ProductVo> products = new LinkedList<ProductVo>();
        for (int i = 0; i < cartProducts.size(); i++) {
            Long tempID = cartProducts.get(i).getProductId();
            Product product = productService.findById(tempID);
            ProductVo productVo = new ProductVo(product);
            products.add(productVo);
        }
        model.addAttribute("products", products);
        model.addAttribute("cartProducts", cartProducts);
        return "cart";
    }

    /**
     * 修改购物车商品数量
     *
     * @param cartProduct
     * @return
     */
    @Authentication
    @RequestMapping("/cart/update")
    @ResponseBody
    public Integer countByCartProductId(CartProduct cartProduct) {
        return cartProductService.updateSelectionById(cartProduct);
    }

    /**
     * 删除购物车商品信息
     *
     * @param cid
     */
    @Authentication
    @ResponseBody
    @RequestMapping("/cart/delete")
    public String deleteCartProducts(@RequestParam("cid") Long cid) {
        cartProductService.deleteById(cid);
        return "success";
    }


    /**
     * 添加购物车商品
     *
     * @param pid
     * @param num
     * @param session
     * @return
     */
    @Authentication
    @ResponseBody
    @RequestMapping({"/cart/add"})
    public String addToCartProduct(
            @RequestParam("pid") Long pid,
            @RequestParam("num") Integer num,
            HttpSession session
    ) {
        User user = (User) session.getAttribute(ConstantValue.CURRENT_USER);
        cartProductService.add(pid, user.getId(), num);
        return "success";
    }

}
