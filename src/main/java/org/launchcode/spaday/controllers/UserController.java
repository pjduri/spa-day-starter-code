
package org.launchcode.spaday.controllers;

import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method = {RequestMethod.GET})
    String displayAddUserForm() {
        return "user/add";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
        model.addAttribute(user);
        // returns the service selection page
        if (user.getPassword().equals(verify)
                && !user.getPassword().equals(null)
                && !user.getPassword().equals("")) {
            return "user/index";
        // returns add page with username and email fields still filled in
        } else if (!user.getPassword().equals(verify)) {
            model.addAttribute("error", "Passwords Don't Match!");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
        }
        //  changes error return if there are empty fields
        else if ((user.getPassword().equals("")
                || user.getPassword().equals(null))
                || verify.equals("")
                || verify.equals(null)
                || user.getUsername().equals("")
                || user.getUsername().equals(null)
                || user.getEmail().equals("")
                || user.getEmail().equals(null)) {
            model.addAttribute("error", "All Fields Required!");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());
        }
        return "user/add";

    }

}



