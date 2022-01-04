package com.hust.tacojpa.web;

import com.hust.tacojpa.Ingredient;
import com.hust.tacojpa.Order;
import com.hust.tacojpa.Taco;
import com.hust.tacojpa.User;
import com.hust.tacojpa.data.IngredientRepository;
import com.hust.tacojpa.data.TacoRepository;
import com.hust.tacojpa.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.hust.tacojpa.Ingredient.Type;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private IngredientRepository ingredientRepo;

    private TacoRepository tacoRepo;

    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        String username = principal.getName();

        User user = userRepo.findByUsername(username);
        model.addAttribute("user", user);


        return "design";
    }
    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors,@ModelAttribute("order") Order order,Model model) {
        if(errors.hasErrors()){
            return "design";
        }
        // lưu vào thế thôi
        Taco saved = tacoRepo.save(taco);
        order.addDesign(saved);

        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
    }
    /**
     * Lọc phần tử theo loại ingredient
     * @param ingredients
     * @param type
     * @return 1 List Ingredient
     */
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}