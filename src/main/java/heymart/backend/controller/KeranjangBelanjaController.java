package heymart.backend.controller;

import heymart.backend.models.KeranjangBelanja;
import heymart.backend.service.KeranjangBelanjaService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/keranjangbelanja")
public class KeranjangBelanjaController {

    private final KeranjangBelanjaService keranjangBelanjaService;

    public KeranjangBelanjaController(KeranjangBelanjaService keranjangBelanjaService) {
        this.keranjangBelanjaService = keranjangBelanjaService;
    }

    @GetMapping("/create")
    public String createKeranjangBelanjaPage(Model model){
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        model.addAttribute("keranjangBelanja", keranjangBelanja);
        return "CreateKeranjangBelanja";
    }

    @PostMapping("/create")
    public String createKeranjangBelanjaPost(@ModelAttribute KeranjangBelanja keranjangBelanja){
        keranjangBelanjaService.createKeranjangBelanja(keranjangBelanja);
        return "redirect::list";
    }
}
