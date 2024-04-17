package heymart.backend.controller;

import heymart.backend.model.KeranjangBelanja;
import heymart.backend.service.KeranjangBelanjaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/keranjangbelanja")
public class KeranjangBelanjaController {

    @Autowired
    private KeranjangBelanjaService keranjangBelanjaService;

    @GetMapping("/create")
    public String createKeranjangBelanjaPage(Model model){
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        model.addAttribute("keranjangBelanja", keranjangBelanja);
        return "CreateKeranjangBelanja";
    }

    @PostMapping("/create")
    public String createKeranjangBelanjaPost(@ModelAttribute KeranjangBelanja keranjangBelanja, Model model){
        keranjangBelanjaService.createKeranjangBelanja(keranjangBelanja);
        return "redirect::list";
    }
}
