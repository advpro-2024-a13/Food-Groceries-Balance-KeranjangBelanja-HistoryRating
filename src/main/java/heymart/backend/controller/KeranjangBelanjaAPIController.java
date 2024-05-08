package heymart.backend.controller;

import heymart.backend.models.Cart;
import heymart.backend.service.KeranjangBelanjaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/keranjangbelanja")
public class KeranjangBelanjaAPIController {

    @Autowired
    private KeranjangBelanjaService keranjangBelanjaService;

    @GetMapping("/create")
    public String createKeranjangBelanjaPage(Model model) {
        Cart cart = new Cart();
        model.addAttribute("keranjangBelanja", cart);
        return "CreateKeranjangBelanja";
    }

    @PostMapping("/create")
    public String createKeranjangBelanja(@ModelAttribute Cart cart) {
        keranjangBelanjaService.createKeranjangBelanja(cart);
        return "redirect:/keranjangbelanja/list";
    }

    // @GetMapping("/list")
    // public String listKeranjangBelanja(Model model) {
    //     model.addAttribute("keranjangBelanjaList", keranjangBelanjaService.getAllKeranjangBelanja());
    //     return "ListKeranjangBelanja";
    // }

    // @PostMapping("/addProduct")
    // public String addProductToKeranjang(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
    //     keranjangBelanjaService.addProductToKeranjangBelanja(cartId, new Product(productId), quantity);
    //     return "redirect:/keranjangbelanja/view?cartId=" + cartId;
    // }

    // @PostMapping("/removeProduct")
    // public String removeProductFromKeranjang(@RequestParam Long cartId, @RequestParam Long productId) {
    //     keranjangBelanjaService.removeProductFromKeranjangBelanja(cartId, productId);
    //     return "redirect:/keranjangbelanja/view?cartId=" + cartId;
    // }

    // @PostMapping("/updateQuantity")
    // public String updateProductQuantity(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam int newQuantity) {
    //     keranjangBelanjaService.updateProductQuantityInKeranjangBelanja(cartId, productId, newQuantity);
    //     return "redirect:/keranjangbelanja/view?cartId=" + cartId;
    // }

    // @GetMapping("/view")
    // public String viewKeranjangBelanja(@RequestParam Long cartId, Model model) {
    //     KeranjangBelanja keranjangBelanja = keranjangBelanjaService.getKeranjangBelanjaById(cartId);
    //     model.addAttribute("keranjangBelanja", keranjangBelanja);
    //     return "ViewKeranjangBelanja";
    // }
}
