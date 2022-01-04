package com.hust.tacojpa.web;

import com.hust.tacojpa.DiscountCodeProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/discount")
public class DiscountController {
    private DiscountCodeProps discountCodeProps;

    @Autowired
    public DiscountController(DiscountCodeProps discountCodeProps) {
        this.discountCodeProps = discountCodeProps;
    }

    @GetMapping
    public String displayDiscountCodes(Model model){
        Map<String,Integer> codes = discountCodeProps.getCodes();
        model.addAttribute("codes",codes);
        return "discountList";
    }
}
