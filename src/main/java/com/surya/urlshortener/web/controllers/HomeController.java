package com.surya.urlshortener.web.controllers;

import com.surya.urlshortener.ApplicationProperties;
import com.surya.urlshortener.domain.dtos.CreateShortUrlForm;
import com.surya.urlshortener.domain.entities.ShortUrl;
import com.surya.urlshortener.domain.models.CreateShortUrlCmd;
import com.surya.urlshortener.domain.models.ShortUrlDto;
import com.surya.urlshortener.domain.repositories.ShortUrlRepository;
import com.surya.urlshortener.domain.services.ShortUrlService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {


    private final ShortUrlService shortUrlService;
    private final ApplicationProperties properties;

    public HomeController(ShortUrlService shortUrlService, ApplicationProperties properties) {
        this.shortUrlService = shortUrlService;
        this.properties = properties;
    }

    @GetMapping("/")
     public String home(Model model) {
         List<ShortUrlDto> shortUrls = shortUrlService.getAllPublicShortUrls();
         model.addAttribute("shortUrls", shortUrls);
         model.addAttribute("baseUrl", properties.baseUrl());
         model.addAttribute("createShortUrlForm", new CreateShortUrlForm(""));
         return "index";
     }

    @PostMapping("/short-urls")
    String createShortUrl(@ModelAttribute("createShortUrlForm") @Valid CreateShortUrlForm form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        if(bindingResult.hasErrors()) {
            List<ShortUrlDto> shortUrls = shortUrlService.getAllPublicShortUrls();
            model.addAttribute("shortUrls", shortUrls);
            model.addAttribute("baseUrl", properties.baseUrl());
            return "index";
        }

        try {
            CreateShortUrlCmd cmd = new CreateShortUrlCmd(form.originalUrl());
            var shortUrlDto = shortUrlService.createShortUrl(cmd);
            redirectAttributes.addFlashAttribute("successMessage", "Short URL created successfully "+
                    properties.baseUrl()+"/s/"+shortUrlDto.shortKey());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to create short URL");

        }
        return "redirect:/";
    }
}
